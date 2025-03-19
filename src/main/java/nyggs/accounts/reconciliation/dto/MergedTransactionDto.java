package nyggs.accounts.reconciliation.dto;

import java.util.List;

import nyggs.accounts.reconciliation.entity.AccountTransactions;

public class MergedTransactionDto {

	private BankStatementDto bankStatementDto;

	private List<AccountTransactions> transactionList;

	public BankStatementDto getBankStatementDto() {
		return bankStatementDto;
	}

	public void setBankStatementDto(BankStatementDto bankStatementDto) {
		this.bankStatementDto = bankStatementDto;
	}

	public List<AccountTransactions> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<AccountTransactions> transactionList) {
		this.transactionList = transactionList;
	}

	public MergedTransactionDto(BankStatementDto bankStatementDto, List<AccountTransactions> transactionList) {
		super();
		this.bankStatementDto = bankStatementDto;
		this.transactionList = transactionList;
	}

	public MergedTransactionDto() {
		super();
	}

}
