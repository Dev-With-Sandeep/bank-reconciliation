package nyggs.accounts.reconciliation.service.serviceImpl;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import nyggs.accounts.reconciliation.dto.BankStatementDto;
import nyggs.accounts.reconciliation.dto.CustomResponse;
import nyggs.accounts.reconciliation.dto.ReconcileRequestDto;
import nyggs.accounts.reconciliation.entity.AccountTransactions;
import nyggs.accounts.reconciliation.entity.Accounts;
import nyggs.accounts.reconciliation.repository.AccountTransactionsRepository;
import nyggs.accounts.reconciliation.repository.AccountsRepository;
import nyggs.accounts.reconciliation.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private AccountTransactionsRepository accountTransactionsRepository;

	@Autowired
	private AccountsRepository accountsRepository;

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

	@Override
	public CustomResponse reconcileStatement(ReconcileRequestDto requestDto) {
		try {
			MultipartFile file = requestDto.getFile();
			List<BankStatementDto> bankStatementDtoList = parseExcelFile(file);
			List<BankStatementDto> reconciledData = new ArrayList<>();
			Optional<Accounts> accountsOptional = accountsRepository.findById(requestDto.getAccountId());
			List<AccountTransactions> accountTransactionList = new ArrayList<>();
			if (accountsOptional.isPresent()) {
				accountTransactionList = accountTransactionsRepository
						.findAllByAccountId(accountsOptional.get().getId());

				// Transactions Not Found.
				for (BankStatementDto bankStatementDto : bankStatementDtoList) {
					Optional<AccountTransactions> accountTransaction = accountTransactionList.stream()
							.filter(txn -> txn.getAmount().equals(bankStatementDto.getCreditAmount())
									|| txn.getAmount().equals(bankStatementDto.getDebitAmount()))
							.findFirst();
					if (!accountTransaction.isPresent()) {
						reconciledData.add(bankStatementDto);
					}
				}

			} else {
				return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Account not found");
			}
			return new CustomResponse(HttpStatus.OK.value(), reconciledData, "File imported successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomResponse(HttpStatus.BAD_REQUEST.value(), null, "Something went wrong");
		}
	}

	private List<BankStatementDto> parseExcelFile(MultipartFile file) throws Exception {
		List<BankStatementDto> statementList = new ArrayList<>();
		InputStream inputStream = file.getInputStream();
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		int rowIndex = 0;

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
}
