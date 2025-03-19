package nyggs.accounts.reconciliation.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ReconcileRequestDto {

	private Integer accountId;

	private MultipartFile file;

	private Date fromDate;

	private Date toDate;

	private Date transactionDate;

	private Double amount;

	private String description;

	private String referenceNo;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public ReconcileRequestDto(Integer accountId, MultipartFile file, Date fromDate, Date toDate, Date transactionDate,
			Double amount, String description, String referenceNo) {
		super();
		this.accountId = accountId;
		this.file = file;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.description = description;
		this.referenceNo = referenceNo;
	}

	public ReconcileRequestDto() {
		super();
	}

}
