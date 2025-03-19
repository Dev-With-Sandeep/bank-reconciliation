package nyggs.accounts.reconciliation.dto;

import java.util.Date;

public class BankStatementDto {

	private Long sNo;

	private String transactionId;

	private Date valueDate;

	private Date transactionDate;

	private Date transactionPostedDate;

	private String referenceNo;

	private String transactionRemarks;

	private Double debitAmount;

	private Double creditAmount;

	private Double balance;

	public Long getsNo() {
		return sNo;
	}

	public void setsNo(Long sNo) {
		this.sNo = sNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Date getTransactionPostedDate() {
		return transactionPostedDate;
	}

	public void setTransactionPostedDate(Date transactionPostedDate) {
		this.transactionPostedDate = transactionPostedDate;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getTransactionRemarks() {
		return transactionRemarks;
	}

	public void setTransactionRemarks(String transactionRemarks) {
		this.transactionRemarks = transactionRemarks;
	}

	public Double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(Double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public Double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public BankStatementDto(Long sNo, String transactionId, Date valueDate, Date transactionDate,
			Date transactionPostedDate, String referenceNo, String transactionRemarks, Double debitAmount,
			Double creditAmount, Double balance) {
		super();
		this.sNo = sNo;
		this.transactionId = transactionId;
		this.valueDate = valueDate;
		this.transactionDate = transactionDate;
		this.transactionPostedDate = transactionPostedDate;
		this.referenceNo = referenceNo;
		this.transactionRemarks = transactionRemarks;
		this.debitAmount = debitAmount;
		this.creditAmount = creditAmount;
		this.balance = balance;
	}

	public BankStatementDto() {
		super();
	}

}
