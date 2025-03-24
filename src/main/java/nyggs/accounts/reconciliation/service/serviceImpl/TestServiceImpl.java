package nyggs.accounts.reconciliation.service.serviceImpl;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import nyggs.accounts.reconciliation.entity.AccountTransactions;
import nyggs.accounts.reconciliation.entity.Accounts;
import nyggs.accounts.reconciliation.entity.BankStatement;
import nyggs.accounts.reconciliation.entity.ReconciliationResult;
import nyggs.accounts.reconciliation.enums.ReconciliationStatus;
import nyggs.accounts.reconciliation.enums.TransactionType;
import nyggs.accounts.reconciliation.feignClient.AccountsClient;
import nyggs.accounts.reconciliation.repository.AccountTransactionsRepository;
import nyggs.accounts.reconciliation.repository.AccountsRepository;
import nyggs.accounts.reconciliation.repository.BankStatementRepository;
import nyggs.accounts.reconciliation.repository.ReconciliationResultRepository;
import nyggs.accounts.reconciliation.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private AccountTransactionsRepository accountTransactionsRepository;

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private BankStatementRepository bankStatementRepository;

	@Autowired
	private ReconciliationResultRepository reconciliationResultRepository;

	@Autowired
	private AccountsClient accountsClient;

//	@Override
//	public CustomResponse reconcileStatement(ReconcileRequestDto requestDto) {
//		try {
//			MultipartFile file = requestDto.getFile();
//			List<BankStatementDto> bankStatementDtoList = parseExcelFile(file);
//			Optional<Accounts> accountsOptional = accountsRepository.findById(requestDto.getAccountId());
//			List<AccountTransactions> accountTransactionList = new ArrayList<>();
//			if (accountsOptional.isPresent()) {
//				accountTransactionList = accountTransactionsRepository
//						.findAllByAccountId(accountsOptional.get().getId());
//			} else {
//				return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Account not found");
//			}
//			return new CustomResponse(HttpStatus.OK.value(), accountTransactionList, "File imported successfully");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Something went wrong");
//		}
//	}

//	@Override
//	public CustomResponse reconcileStatement(ReconcileRequestDto requestDto) {
//		try {
//			MultipartFile file = requestDto.getFile();
//			List<BankStatementDto> bankStatementDtoList = parseExcelFile(file);
//			List<BankStatementDto> reconciledData = new ArrayList<>();
//			Optional<Accounts> accountsOptional = accountsRepository.findById(requestDto.getAccountId());
//			List<AccountTransactions> accountTransactionList = new ArrayList<>();
//			if (accountsOptional.isPresent()) {
//				accountTransactionList = accountTransactionsRepository
//						.findAllByAccountId(accountsOptional.get().getId());
//
////				// Transactions Not Found.
//				for (BankStatementDto bankStatementDto : bankStatementDtoList) {
//					Optional<AccountTransactions> accountTransaction = accountTransactionList.stream()
//							.filter(txn -> txn.getAmount().equals(bankStatementDto.getCreditAmount())
//									|| txn.getAmount().equals(bankStatementDto.getDebitAmount()))
//							.findFirst();
//					if (!accountTransaction.isPresent()) {
//						reconciledData.add(bankStatementDto);
//					}
//				}
//
//			} else {
//				return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Account not found");
//			}
//			return new CustomResponse(HttpStatus.OK.value(), reconciledData, "File imported successfully");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Something went wrong");
//		}
//	}

	@Override
	public CustomResponse reconcileStatement(ReconcileRequestDto requestDto) {
		try {
			MultipartFile file = requestDto.getFile();
			List<BankStatementDto> bankStatementDtoList = parseExcelFile(file, requestDto);
			List<BankStatementDto> reconciledData = new ArrayList<>();
			List<BankStatement> bankStatementList = new ArrayList<>();
			for (BankStatementDto bankStatementDto : bankStatementDtoList) {
				BankStatement bankStatement = new BankStatement();
				bankStatement.setAccountId(bankStatementDto.getAccountId());
				bankStatement.setTransactionDate(bankStatementDto.getTransactionDate());
				if (bankStatementDto.getCreditAmount() != 0d) {
					bankStatement.setAmount(bankStatementDto.getCreditAmount());
					bankStatement.setTransactionType(TransactionType.CREDIT);
				} else {
					bankStatement.setAmount(bankStatementDto.getDebitAmount());
					bankStatement.setTransactionType(TransactionType.DEBIT);
				}
				bankStatement.setReferenceNo(bankStatementDto.getReferenceNo());
				bankStatement.setRemarks(bankStatementDto.getTransactionRemarks());
				bankStatement.setAccountNumber(bankStatementDto.getAccountNumber());
				bankStatement.setIsReconciled(Boolean.FALSE);
				bankStatement.setCreatedAt(new Date());
				bankStatement.setUpdatedAt(new Date());
				bankStatement.setIsActive(Boolean.TRUE);
				bankStatementList.add(bankStatement);
			}
			bankStatementRepository.saveAll(bankStatementList);
			return new CustomResponse(HttpStatus.OK.value(), reconciledData, "File imported successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Something went wrong");
		}
	}

	private List<BankStatementDto> parseExcelFile(MultipartFile file, ReconcileRequestDto requestDto) throws Exception {
		List<BankStatementDto> statementList = new ArrayList<>();
		InputStream inputStream = file.getInputStream();
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		int rowIndex = 0;

		String accountNumber = null;
		Row accountRow = sheet.getRow(4);
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

//	@Override
//	public CustomResponse reconcileNow() {
//		try {
//			List<BankStatement> bankStatementList = bankStatementRepository.findAllPendingData();
//			Map<Integer, List<BankStatement>> bankTransactions = bankStatementList.stream()
//					.collect(Collectors.groupingBy(BankStatement::getAccountId));
//			Optional<Date> minTransactionDate = bankStatementList.stream().map(BankStatement::getTransactionDate)
//					.min(Date::compareTo);
//
//			Optional<Date> maxTransactionDate = bankStatementList.stream().map(BankStatement::getTransactionDate)
//					.max(Date::compareTo);
//
//			TransactionRequestDto requestDto = new TransactionRequestDto();
//			requestDto.setAccountIds(new ArrayList<>(bankTransactions.keySet()));
//			requestDto.setFromDate(minTransactionDate.isPresent() ? minTransactionDate.get() : null);
//			requestDto.setToDate(maxTransactionDate.isPresent() ? maxTransactionDate.get() : null);
//
//			CustomResponse response = accountsClient.findTransactionsForReconciliation(requestDto, "d3v3lop3r");
//
//			ObjectMapper objectMapper = new ObjectMapper();
//			List<AccountTransactionFetchResponse> accountTransactionInfoList = objectMapper
//					.convertValue(response.getData(), new TypeReference<List<AccountTransactionFetchResponse>>() {
//					});
//
//			return new CustomResponse(HttpStatus.OK.value(), accountTransactionInfoList, "Success");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Something went wrong");
//		}
//	}

//	@Override
//	public CustomResponse reconcileNow() {
//		try {
//			List<BankStatement> notFoundTxn = new ArrayList<>();
//			List<ReconciliationResult> reconciliationResultList = new ArrayList<>();
//			List<BankStatement> bankStatementList = bankStatementRepository.findAllPendingData();
//			Map<Integer, List<BankStatement>> debitTransactions = bankStatementList.stream()
//					.filter(txn -> txn.getTransactionType().getId().equals(TransactionType.DEBIT.getId()))
//					.collect(Collectors.groupingBy(BankStatement::getAccountId));
//			Map<Integer, List<BankStatement>> creditTransactions = bankStatementList.stream()
//					.filter(txn -> txn.getTransactionType().getId().equals(TransactionType.CREDIT.getId()))
//					.collect(Collectors.groupingBy(BankStatement::getAccountId));
//
//			Map<Integer, List<BankStatement>> bankTransactions = bankStatementList.stream()
//					.collect(Collectors.groupingBy(BankStatement::getAccountId));
//
//			Optional<Date> minTransactionDate = bankStatementList.stream().map(BankStatement::getTransactionDate)
//					.min(Date::compareTo);
//
//			Optional<Date> maxTransactionDate = bankStatementList.stream().map(BankStatement::getTransactionDate)
//					.max(Date::compareTo);
//
//			TransactionRequestDto requestDto = new TransactionRequestDto();
//			requestDto.setAccountIds(new ArrayList<>(bankTransactions.keySet()));
//			requestDto.setFromDate(minTransactionDate.isPresent() ? minTransactionDate.get() : null);
//			requestDto.setToDate(maxTransactionDate.isPresent() ? maxTransactionDate.get() : null);
//
//			CustomResponse response = accountsClient.findTransactionsForReconciliation(requestDto, "d3v3lop3r");
//
//			ObjectMapper objectMapper = new ObjectMapper();
//			List<AccountTransactionFetchResponse> accountTransactionInfoList = objectMapper
//					.convertValue(response.getData(), new TypeReference<List<AccountTransactionFetchResponse>>() {
//					});
//			Map<Integer, List<AccountTransactionFetchResponse>> daybookDebitTransactionsList = accountTransactionInfoList
//					.stream().collect(Collectors.groupingBy(AccountTransactionFetchResponse::getSourceAccountId));
//
//			Map<Integer, List<AccountTransactionFetchResponse>> daybookCreditTransactionsList = accountTransactionInfoList
//					.stream().collect(Collectors.groupingBy(AccountTransactionFetchResponse::getTargetAccountId));
//
//			for (Map.Entry<Integer, List<BankStatement>> entry : debitTransactions.entrySet()) {
//				List<BankStatement> debitTxns = entry.getValue();
//				for (BankStatement bankStatement : debitTxns) {
//					Optional<AccountTransactionFetchResponse> daybookTransaction = accountTransactionInfoList.stream()
//							.filter(txn -> txn.getSourceAccountId().equals(bankStatement.getAccountId())
//									&& txn.getAmount().equals(bankStatement.getAmount()))
//							.findFirst();
//					if (!daybookTransaction.isPresent()) {
//						notFoundTxn.add(bankStatement);
//					}
//				}
//			}
//
//			for (Map.Entry<Integer, List<BankStatement>> entry : creditTransactions.entrySet()) {
//				List<BankStatement> creditTxns = entry.getValue();
//				for (BankStatement bankStatement : creditTxns) {
//					Optional<AccountTransactionFetchResponse> daybookTransaction = accountTransactionInfoList.stream()
//							.filter(txn -> txn.getTargetAccountId().equals(bankStatement.getAccountId())
//									&& txn.getAmount().equals(bankStatement.getAmount()))
//							.findFirst();
//					if (!daybookTransaction.isPresent()) {
//						notFoundTxn.add(bankStatement);
//					}
//				}
//			}
//
//			return new CustomResponse(HttpStatus.OK.value(), notFoundTxn, "Success");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Something went wrong");
//		}
//	}

	@Override
	public CustomResponse reconcileNow() {
		try {
			List<BankStatement> notFoundTxn = new ArrayList<>();
			List<ReconciliationResult> reconciliationResultList = new ArrayList<>();
			List<BankStatement> bankStatementList = bankStatementRepository.findAllPendingData();
			Map<Integer, List<BankStatement>> debitTransactions = bankStatementList.stream()
					.filter(txn -> txn.getTransactionType().getId().equals(TransactionType.DEBIT.getId()))
					.collect(Collectors.groupingBy(BankStatement::getAccountId));
			Map<Integer, List<BankStatement>> creditTransactions = bankStatementList.stream()
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
			List<AccountTransactionFetchResponse> accountTransactionInfoList = objectMapper
					.convertValue(response.getData(), new TypeReference<List<AccountTransactionFetchResponse>>() {
					});
			Map<Integer, List<AccountTransactionFetchResponse>> daybookDebitTransactionsList = accountTransactionInfoList
					.stream().collect(Collectors.groupingBy(AccountTransactionFetchResponse::getSourceAccountId));

			Map<Integer, List<AccountTransactionFetchResponse>> daybookCreditTransactionsList = accountTransactionInfoList
					.stream().collect(Collectors.groupingBy(AccountTransactionFetchResponse::getTargetAccountId));

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

			reconciliationResultRepository.saveAll(reconciliationResultList);

			return new CustomResponse(HttpStatus.OK.value(), notFoundTxn, "Success");
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Something went wrong");
		}
	}
}
