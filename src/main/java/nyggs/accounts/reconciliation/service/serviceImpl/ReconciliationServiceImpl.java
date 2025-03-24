package nyggs.accounts.reconciliation.service.serviceImpl;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nyggs.accounts.reconciliation.dto.AccountTransactionFetchResponse;
import nyggs.accounts.reconciliation.dto.BankStatementDto;
import nyggs.accounts.reconciliation.dto.CustomResponse;
import nyggs.accounts.reconciliation.dto.ReconcileRequestDto;
import nyggs.accounts.reconciliation.dto.TransactionRequestDto;
import nyggs.accounts.reconciliation.entity.BankStatement;
import nyggs.accounts.reconciliation.entity.ReconciliationResult;
import nyggs.accounts.reconciliation.enums.ReconciliationStatus;
import nyggs.accounts.reconciliation.enums.TransactionType;
import nyggs.accounts.reconciliation.feignClient.AccountsClient;
import nyggs.accounts.reconciliation.repository.BankStatementRepository;
import nyggs.accounts.reconciliation.repository.ReconciliationResultRepository;
import nyggs.accounts.reconciliation.service.ReconciliationService;

@Service
public class ReconciliationServiceImpl implements ReconciliationService {

	@Autowired
	private AccountsClient accountsClient;

	@Autowired
	private BankStatementRepository bankStatementRepository;

	@Autowired
	private ReconciliationResultRepository reconciliationResultRepository;

	@Override
	public CustomResponse importBankStatement(ReconcileRequestDto requestDto) {
		try {
			MultipartFile file = requestDto.getFile();
			List<BankStatementDto> bankStatementDtoList = parseExcelFile(file, requestDto);
			List<BankStatement> bankStatementList = new ArrayList<>();
			for (BankStatementDto bankStatementDto : bankStatementDtoList) {
				BankStatement bankStatement = new BankStatement();
				bankStatement.setAccountId(requestDto.getAccountId());
				bankStatement.setAccountNumber(bankStatementDto.getAccountNumber());
				bankStatement.setAccountName(bankStatementDto.getAccountName());
				bankStatement.setValueDate(bankStatementDto.getValueDate());
				bankStatement.setTransactionDate(bankStatementDto.getTransactionDate());
				bankStatement.setTransactionPostedDate(bankStatementDto.getTransactionPostedDate());
				if (bankStatementDto.getCreditAmount() != 0d) {
					bankStatement.setAmount(bankStatementDto.getCreditAmount());
					bankStatement.setTransactionType(TransactionType.CREDIT);
				} else {
					bankStatement.setAmount(bankStatementDto.getDebitAmount());
					bankStatement.setTransactionType(TransactionType.DEBIT);
				}
				bankStatement.setBalance(bankStatementDto.getBalance());
				bankStatement.setReferenceNo(bankStatementDto.getReferenceNo());
				bankStatement.setRemarks(bankStatementDto.getTransactionRemarks());
				bankStatement.setIsReconciled(Boolean.FALSE);
				bankStatement.setCreatedAt(new Date());
				bankStatement.setUpdatedAt(new Date());
				bankStatement.setIsActive(Boolean.TRUE);
				bankStatementList.add(bankStatement);
			}
			bankStatementList = bankStatementRepository.saveAll(bankStatementList);
			reconcileTransactions(bankStatementList);
			return new CustomResponse(HttpStatus.OK.value(), bankStatementList, "File imported successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Something went wrong");
		}
	}

	public void reconcileTransactions(List<BankStatement> bankStatementList) {
		Map<Integer, List<BankStatement>> debitBankStatementTransactions = bankStatementList.stream()
				.filter(txn -> txn.getTransactionType().getId().equals(TransactionType.DEBIT.getId()))
				.collect(Collectors.groupingBy(BankStatement::getAccountId));
		Map<Integer, List<BankStatement>> creditBankStatementTransactions = bankStatementList.stream()
				.filter(txn -> txn.getTransactionType().getId().equals(TransactionType.CREDIT.getId()))
				.collect(Collectors.groupingBy(BankStatement::getAccountId));

		Map<Integer, List<BankStatement>> bankTransactions = bankStatementList.stream()
				.collect(Collectors.groupingBy(BankStatement::getAccountId));

		Optional<Date> minTransactionDate = bankStatementList.stream().map(BankStatement::getTransactionDate)
				.min(Date::compareTo);
		Optional<Date> maxTransactionDate = bankStatementList.stream().map(BankStatement::getTransactionDate)
				.max(Date::compareTo);

		TransactionRequestDto requestDto = new TransactionRequestDto();
		requestDto.setAccountIds(new ArrayList<>(bankTransactions.keySet()));
		requestDto.setFromDate(minTransactionDate.isPresent() ? minTransactionDate.get() : null);
		requestDto.setToDate(maxTransactionDate.isPresent() ? maxTransactionDate.get() : null);

		CustomResponse response = accountsClient.findTransactionsForReconciliation(requestDto, "d3v3lop3r");

		ObjectMapper objectMapper = new ObjectMapper();
		List<AccountTransactionFetchResponse> accountTransactionInfoList = objectMapper.convertValue(response.getData(),
				new TypeReference<List<AccountTransactionFetchResponse>>() {
				});

		Map<Integer, List<AccountTransactionFetchResponse>> daybookDebitTransactionsList = accountTransactionInfoList
				.stream().collect(Collectors.groupingBy(AccountTransactionFetchResponse::getSourceAccountId));

		Map<Integer, List<AccountTransactionFetchResponse>> daybookCreditTransactionsList = accountTransactionInfoList
				.stream().collect(Collectors.groupingBy(AccountTransactionFetchResponse::getTargetAccountId));

		List<ReconciliationResult> reconciliationResults = new ArrayList<>();
		List<ReconciliationResult> matchedTransactions = findMatchedTransactions(debitBankStatementTransactions,
				creditBankStatementTransactions, daybookDebitTransactionsList, daybookCreditTransactionsList);

		List<ReconciliationResult> daybookMissingTransactions = findDayBookMissingTransactions(
				debitBankStatementTransactions, creditBankStatementTransactions, daybookDebitTransactionsList,
				daybookCreditTransactionsList);

		List<ReconciliationResult> bankMissingTransactions = findBankMissingTransactions(debitBankStatementTransactions,
				creditBankStatementTransactions, daybookDebitTransactionsList, daybookCreditTransactionsList);

		reconciliationResults.addAll(matchedTransactions);
		reconciliationResults.addAll(daybookMissingTransactions);
		reconciliationResults.addAll(bankMissingTransactions);
		reconciliationResultRepository.saveAll(reconciliationResults);
	}

	public List<ReconciliationResult> findMatchedTransactions(
			Map<Integer, List<BankStatement>> debitBankStatementTransactions,
			Map<Integer, List<BankStatement>> creditBankStatementTransactions,
			Map<Integer, List<AccountTransactionFetchResponse>> daybookDebitTransactionsList,
			Map<Integer, List<AccountTransactionFetchResponse>> daybookCreditTransactionsList) {
		List<ReconciliationResult> reconciliationResultList = new ArrayList<>();

		for (Map.Entry<Integer, List<BankStatement>> txnEntry : debitBankStatementTransactions.entrySet()) {
			List<AccountTransactionFetchResponse> dayBookDebitTxnList = daybookDebitTransactionsList
					.get(txnEntry.getKey());
			for (BankStatement bankStatement : txnEntry.getValue()) {
				Optional<AccountTransactionFetchResponse> txnFetchResponseOptional = dayBookDebitTxnList.stream()
						.filter(txn -> txn.getAmount().equals(bankStatement.getAmount())).findFirst();
				if (txnFetchResponseOptional.isPresent()) {
					ReconciliationResult reconciliationResult = new ReconciliationResult();
					reconciliationResult.setAccountId(bankStatement.getAccountId());
					reconciliationResult.setAccountNumber(bankStatement.getAccountNumber());
					reconciliationResult.setAccountName(bankStatement.getAccountName());
					reconciliationResult.setBankStatementId(bankStatement.getId());
					reconciliationResult.setBankTxnId(bankStatement.getId().toString());
					reconciliationResult.setDaybookTxnId(txnFetchResponseOptional.get().getId().toString());
					reconciliationResult.setDescription("Matched");
					reconciliationResult.setStatus(ReconciliationStatus.MATCHED);
					reconciliationResult.setDifferenceAmount(0.0);
					reconciliationResult.setCreatedAt(new Date());
					reconciliationResult.setUpdatedAt(new Date());
					reconciliationResult.setIsActive(Boolean.TRUE);
					reconciliationResultList.add(reconciliationResult);
				}
			}
		}

		for (Map.Entry<Integer, List<BankStatement>> txnEntry : creditBankStatementTransactions.entrySet()) {
			List<AccountTransactionFetchResponse> dayBookCreditTxnList = daybookCreditTransactionsList
					.get(txnEntry.getKey());
			for (BankStatement bankStatement : txnEntry.getValue()) {
				Optional<AccountTransactionFetchResponse> txnFetchResponseOptional = dayBookCreditTxnList.stream()
						.filter(txn -> txn.getAmount().equals(bankStatement.getAmount())).findFirst();
				if (txnFetchResponseOptional.isPresent()) {
					ReconciliationResult reconciliationResult = new ReconciliationResult();
					reconciliationResult.setAccountId(bankStatement.getAccountId());
					reconciliationResult.setAccountNumber(bankStatement.getAccountNumber());
					reconciliationResult.setAccountName(bankStatement.getAccountName());
					reconciliationResult.setBankStatementId(bankStatement.getId());
					reconciliationResult.setBankTxnId(bankStatement.getId().toString());
					reconciliationResult.setDaybookTxnId(txnFetchResponseOptional.get().getId().toString());
					reconciliationResult.setDescription("Matched");
					reconciliationResult.setStatus(ReconciliationStatus.MATCHED);
					reconciliationResult.setDifferenceAmount(0.0);
					reconciliationResult.setCreatedAt(new Date());
					reconciliationResult.setUpdatedAt(new Date());
					reconciliationResult.setIsActive(Boolean.TRUE);
					reconciliationResultList.add(reconciliationResult);
				}
			}
		}
		return reconciliationResultList;
	}

	public List<ReconciliationResult> findDayBookMissingTransactions(
			Map<Integer, List<BankStatement>> debitBankStatementTransactions,
			Map<Integer, List<BankStatement>> creditBankStatementTransactions,
			Map<Integer, List<AccountTransactionFetchResponse>> debitDayBookTransactions,
			Map<Integer, List<AccountTransactionFetchResponse>> creditDayBookTransactions) {

		List<ReconciliationResult> reconciliationResultList = new ArrayList<>();

		for (Map.Entry<Integer, List<BankStatement>> bankStatementEntry : debitBankStatementTransactions.entrySet()) {
			List<AccountTransactionFetchResponse> dayBookTxnList = debitDayBookTransactions
					.get(bankStatementEntry.getKey());

			for (BankStatement bankStatement : bankStatementEntry.getValue()) {

				Optional<AccountTransactionFetchResponse> txnResponseOptional = dayBookTxnList.stream()
						.filter(txn -> txn.getAmount().equals(bankStatement.getAmount())).findFirst();
				if (!txnResponseOptional.isPresent()) {
					ReconciliationResult reconciliationResult = new ReconciliationResult();
					reconciliationResult.setAccountId(bankStatement.getAccountId());
					reconciliationResult.setAccountNumber(bankStatement.getAccountNumber());
					reconciliationResult.setAccountName(bankStatement.getAccountName());
					reconciliationResult.setBankStatementId(bankStatement.getId());
					reconciliationResult.setBankTxnId(bankStatement.getId().toString());
					reconciliationResult.setDescription("Missing in Daybook");
					reconciliationResult.setStatus(ReconciliationStatus.MISSING_IN_BOOK);
					reconciliationResult.setDifferenceAmount(0.0);
					reconciliationResult.setCreatedAt(new Date());
					reconciliationResult.setUpdatedAt(new Date());
					reconciliationResult.setIsActive(Boolean.TRUE);
					reconciliationResultList.add(reconciliationResult);
				}
			}

		}

		for (Map.Entry<Integer, List<BankStatement>> bankStatementEntry : creditBankStatementTransactions.entrySet()) {
			List<AccountTransactionFetchResponse> dayBookTxnList = creditDayBookTransactions
					.get(bankStatementEntry.getKey());

			for (BankStatement bankStatement : bankStatementEntry.getValue()) {

				Optional<AccountTransactionFetchResponse> txnResponseOptional = dayBookTxnList.stream()
						.filter(txn -> txn.getAmount().equals(bankStatement.getAmount())).findFirst();
				if (!txnResponseOptional.isPresent()) {
					ReconciliationResult reconciliationResult = new ReconciliationResult();
					reconciliationResult.setAccountId(bankStatement.getAccountId());
					reconciliationResult.setAccountNumber(bankStatement.getAccountNumber());
					reconciliationResult.setAccountName(bankStatement.getAccountName());
					reconciliationResult.setBankStatementId(bankStatement.getId());
					reconciliationResult.setBankTxnId(bankStatement.getId().toString());
					reconciliationResult.setDescription("Missing in Daybook");
					reconciliationResult.setStatus(ReconciliationStatus.MISSING_IN_BOOK);
					reconciliationResult.setDifferenceAmount(0.0);
					reconciliationResult.setCreatedAt(new Date());
					reconciliationResult.setUpdatedAt(new Date());
					reconciliationResult.setIsActive(Boolean.TRUE);
					reconciliationResultList.add(reconciliationResult);
				}
			}

		}

		return reconciliationResultList;
	}

	public List<ReconciliationResult> findBankMissingTransactions(
			Map<Integer, List<BankStatement>> debitBankStatementTransactions,
			Map<Integer, List<BankStatement>> creditBankStatementTransactions,
			Map<Integer, List<AccountTransactionFetchResponse>> debitDayBookTransactions,
			Map<Integer, List<AccountTransactionFetchResponse>> creditDayBookTransactions) {

		List<ReconciliationResult> reconciliationResultList = new ArrayList<>();

		for (Map.Entry<Integer, List<AccountTransactionFetchResponse>> dayBookTxnEntry : debitDayBookTransactions
				.entrySet()) {
			List<BankStatement> debitBankTxnList = debitBankStatementTransactions.get(dayBookTxnEntry.getKey());
			if (debitBankTxnList != null && !debitBankTxnList.isEmpty()) {
				for (AccountTransactionFetchResponse txnResponse : dayBookTxnEntry.getValue()) {
					Optional<BankStatement> bankStatementOptional = debitBankTxnList.stream()
							.filter(txn -> txn.getAmount().equals(txnResponse.getAmount())).findFirst();
					if (!bankStatementOptional.isPresent()) {
						ReconciliationResult reconciliationResult = new ReconciliationResult();
						reconciliationResult.setAccountId(txnResponse.getSourceAccountId());
						reconciliationResult.setAccountNumber(null);
						reconciliationResult.setAccountName(txnResponse.getSourceAccountName());
						reconciliationResult.setBankStatementId(null);
						reconciliationResult.setBankTxnId(null);
						reconciliationResult.setDaybookTxnId(txnResponse.getId().toString());
						reconciliationResult.setDescription("Missing in Bank");
						reconciliationResult.setStatus(ReconciliationStatus.MISSING_IN_BANK);
						reconciliationResult.setDifferenceAmount(0.0);
						reconciliationResult.setCreatedAt(new Date());
						reconciliationResult.setUpdatedAt(new Date());
						reconciliationResult.setIsActive(Boolean.TRUE);
						reconciliationResultList.add(reconciliationResult);
					}
				}
			}
		}

		for (Map.Entry<Integer, List<AccountTransactionFetchResponse>> dayBookTxnEntry : creditDayBookTransactions
				.entrySet()) {
			List<BankStatement> creditBankTxnList = creditBankStatementTransactions.get(dayBookTxnEntry.getKey());
			if (creditBankTxnList != null && !creditBankTxnList.isEmpty()) {
				for (AccountTransactionFetchResponse txnResponse : dayBookTxnEntry.getValue()) {
					Optional<BankStatement> bankStatementOptional = creditBankTxnList.stream()
							.filter(txn -> txn.getAmount().equals(txnResponse.getAmount())).findFirst();
					if (!bankStatementOptional.isPresent()) {
						ReconciliationResult reconciliationResult = new ReconciliationResult();
						reconciliationResult.setAccountId(txnResponse.getTargetAccountId());
						reconciliationResult.setAccountNumber(null);
						reconciliationResult.setAccountName(txnResponse.getTargetAccountName());
						reconciliationResult.setBankStatementId(null);
						reconciliationResult.setBankTxnId(null);
						reconciliationResult.setDaybookTxnId(txnResponse.getId().toString());
						reconciliationResult.setDescription("Missing in Bank");
						reconciliationResult.setStatus(ReconciliationStatus.MISSING_IN_BANK);
						reconciliationResult.setDifferenceAmount(0.0);
						reconciliationResult.setCreatedAt(new Date());
						reconciliationResult.setUpdatedAt(new Date());
						reconciliationResult.setIsActive(Boolean.TRUE);
						reconciliationResultList.add(reconciliationResult);
					}
				}
			}
		}
		return reconciliationResultList;
	}

	public List<ReconciliationResult> findMissingBankTransactions(Map<Integer, List<BankStatement>> debitTransactions,
			Map<Integer, List<BankStatement>> creditTransactions,
			List<AccountTransactionFetchResponse> accountTransactionInfoList) {
		List<ReconciliationResult> reconciliationResultList = new ArrayList<>();
		for (Map.Entry<Integer, List<BankStatement>> entry : debitTransactions.entrySet()) {
			List<BankStatement> debitTxns = entry.getValue();
			for (BankStatement bankStatement : debitTxns) {
				Optional<AccountTransactionFetchResponse> daybookTransaction = accountTransactionInfoList.stream()
						.filter(txn -> txn.getSourceAccountId().equals(bankStatement.getAccountId())
								&& txn.getAmount().equals(bankStatement.getAmount()))
						.findFirst();
				if (!daybookTransaction.isPresent()) {
					ReconciliationResult result = new ReconciliationResult();
					result.setBankTxnId(bankStatement.getId().toString());
					result.setDaybookTxnId(null);
					result.setDifferenceAmount(0.0);
					result.setDescription("Transaction Not Found");
					result.setStatus(ReconciliationStatus.MISSING_IN_BOOK);
					result.setCreatedAt(new Date());
					result.setUpdatedAt(new Date());
					result.setIsActive(Boolean.TRUE);
					reconciliationResultList.add(result);
				} else {
					ReconciliationResult result = new ReconciliationResult();
					result.setBankTxnId(bankStatement.getId().toString());
					result.setDaybookTxnId(daybookTransaction.get().getTxnId().toString());
					result.setDifferenceAmount(0.0);
					result.setDescription("Transaction Found");
					result.setStatus(ReconciliationStatus.MATCHED);
					result.setCreatedAt(new Date());
					result.setUpdatedAt(new Date());
					result.setIsActive(Boolean.TRUE);
					reconciliationResultList.add(result);
				}
			}
		}

		for (Map.Entry<Integer, List<BankStatement>> entry : creditTransactions.entrySet()) {
			List<BankStatement> creditTxns = entry.getValue();
			for (BankStatement bankStatement : creditTxns) {
				Optional<AccountTransactionFetchResponse> daybookTransaction = accountTransactionInfoList.stream()
						.filter(txn -> txn.getTargetAccountId().equals(bankStatement.getAccountId())
								&& txn.getAmount().equals(bankStatement.getAmount()))
						.findFirst();
				if (!daybookTransaction.isPresent()) {
					ReconciliationResult result = new ReconciliationResult();
					result.setBankTxnId(bankStatement.getId().toString());
					result.setDaybookTxnId(null);
					result.setDifferenceAmount(0.0);
					result.setDescription("Transaction Not Found");
					result.setStatus(ReconciliationStatus.MISSING_IN_BOOK);
					result.setCreatedAt(new Date());
					result.setUpdatedAt(new Date());
					result.setIsActive(Boolean.TRUE);
					reconciliationResultList.add(result);
				} else {
					ReconciliationResult result = new ReconciliationResult();
					result.setBankTxnId(bankStatement.getId().toString());
					result.setDaybookTxnId(daybookTransaction.get().getTxnId().toString());
					result.setDifferenceAmount(0.0);
					result.setDescription("Transaction Found");
					result.setStatus(ReconciliationStatus.MATCHED);
					result.setCreatedAt(new Date());
					result.setUpdatedAt(new Date());
					result.setIsActive(Boolean.TRUE);
					reconciliationResultList.add(result);
				}
			}
		}
		return reconciliationResultList;
	}

	private List<BankStatementDto> parseExcelFile(MultipartFile file, ReconcileRequestDto requestDto) throws Exception {
		List<BankStatementDto> statementList = new ArrayList<>();
		InputStream inputStream = file.getInputStream();
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		int rowIndex = 0;

		String accountNumber = null;
		String accountName = null;
		Row accountRow = sheet.getRow(4);
		Row accountNameRow = sheet.getRow(2);
		if (accountNameRow != null) {
			accountName = getStringValue(accountNameRow, 1);
		}
		if (accountRow != null) {
			accountNumber = getStringValue(accountRow, 1);
			System.out.println("Extracted Account Number: " + accountNumber);
		}

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (rowIndex < 16) {
				rowIndex++;
				continue;
			}

			String firstColumnValue = getStringValue(row, 0);
			if (!isInteger(firstColumnValue)) {
				System.out.println(
						"Non-integer value found in first column at row index " + rowIndex + ". Stopping execution.");
				break;
			}

			BankStatementDto statement = new BankStatementDto();
			statement.setsNo(parseLong(getStringValue(row, 0)));
			statement.setAccountId(requestDto.getAccountId());
			statement.setAccountNumber(accountNumber);
			statement.setAccountName(accountName);
			statement.setTransactionId(getStringValue(row, 1));
			statement.setValueDate(parseValueOrTransactionDate(row, 2)); // Parses "02/Apr/2024"
			statement.setTransactionDate(parseValueOrTransactionDate(row, 3)); // Parses "02/Apr/2024"
			statement.setTransactionPostedDate(parseTransactionPostedDate(row, 4)); // Parses "02/04/2024 10:49:52 AM"
			statement.setReferenceNo(getStringValue(row, 5));
			statement.setTransactionRemarks(getStringValue(row, 6));
			statement.setDebitAmount(parseDouble(getStringValue(row, 7)));
			statement.setCreditAmount(parseDouble(getStringValue(row, 8)));
			statement.setBalance(parseDouble(getStringValue(row, 9)));

			statementList.add(statement);
			rowIndex++;
		}
		workbook.close();

		return statementList;
	}

	// ✅ Helper method for Long parsing (Handles empty/null values)
	private Long parseLong(String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		try {
			return Long.parseLong(value.trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ✅ Helper method for Double parsing (Handles empty/null values)
	private Double parseDouble(String value) {
		if (value == null || value.trim().isEmpty()) {
			return 0.0;
		}
		try {
			// Remove commas if present
			value = value.replace(",", "");
			return Double.parseDouble(value.trim());
		} catch (NumberFormatException e) {
			System.err.println("Invalid Double value: " + value);
			return 0.0;
		}
	}

	// ✅ Parses Value Date or Transaction Date (Format: "02/Apr/2024")
	private Date parseValueOrTransactionDate(Row row, int cellIndex) {
		String dateStr = getStringValue(row, cellIndex);
		if (dateStr == null || dateStr.isEmpty())
			return null;

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
			return dateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ✅ Parses Transaction Posted Date (Format: "02/04/2024 10:49:52 AM")
	private Date parseTransactionPostedDate(Row row, int cellIndex) {
		String dateStr = getStringValue(row, cellIndex);
		if (dateStr == null || dateStr.isEmpty())
			return null;

		try {
			SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.ENGLISH);
			return dateTimeFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ✅ Improved getStringValue() Method
	private String getStringValue(Row row, int cellIndex) {
		Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
		if (cell == null)
			return null;

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue().trim();
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue()).trim();
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue()).trim();
		default:
			return null;
		}
	}

	private boolean isInteger(String value) {
		if (value == null || value.trim().isEmpty()) {
			return false;
		}
		try {
			Integer.parseInt(value.trim());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public CustomResponse reconcile() {
		// TODO Auto-generated method stub
		return null;
	}

}
