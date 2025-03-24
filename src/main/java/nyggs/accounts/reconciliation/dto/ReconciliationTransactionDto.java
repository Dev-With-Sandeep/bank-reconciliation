package nyggs.accounts.reconciliation.dto;

public class ReconciliationTransactionDto {

	private BankStatementDto bankStatementDto;

	private AccountTransactionFetchResponse accountTransactionFetchResponse;

	public BankStatementDto getBankStatementDto() {
		return bankStatementDto;
	}

	public void setBankStatementDto(BankStatementDto bankStatementDto) {
		this.bankStatementDto = bankStatementDto;
	}

	public AccountTransactionFetchResponse getAccountTransactionFetchResponse() {
		return accountTransactionFetchResponse;
	}

	public void setAccountTransactionFetchResponse(AccountTransactionFetchResponse accountTransactionFetchResponse) {
		this.accountTransactionFetchResponse = accountTransactionFetchResponse;
	}

	public ReconciliationTransactionDto(BankStatementDto bankStatementDto,
			AccountTransactionFetchResponse accountTransactionFetchResponse) {
		super();
		this.bankStatementDto = bankStatementDto;
		this.accountTransactionFetchResponse = accountTransactionFetchResponse;
	}

	public ReconciliationTransactionDto() {
		super();
	}

}
