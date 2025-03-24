package nyggs.accounts.reconciliation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import nyggs.accounts.reconciliation.enums.ReconciliationStatus;

@Entity
@Table(name = "reconciliation_result")
public class ReconciliationResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "account_id")
	private Integer accountId;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "daybook_txn_id")
	private String daybookTxnId;

	@Column(name = "bank_txn_id")
	private String bankTxnId;

	@Column(name = "bank_statement_id")
	private Long bankStatementId;

	@Column(name = "status")
	private ReconciliationStatus status;

	@Column(name = "description")
	private String description;

	@Column(name = "difference_amount")
	private Double differenceAmount;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "is_active")
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDaybookTxnId() {
		return daybookTxnId;
	}

	public void setDaybookTxnId(String daybookTxnId) {
		this.daybookTxnId = daybookTxnId;
	}

	public String getBankTxnId() {
		return bankTxnId;
	}

	public void setBankTxnId(String bankTxnId) {
		this.bankTxnId = bankTxnId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getBankStatementId() {
		return bankStatementId;
	}

	public void setBankStatementId(Long bankStatementId) {
		this.bankStatementId = bankStatementId;
	}

	public ReconciliationStatus getStatus() {
		return status;
	}

	public void setStatus(ReconciliationStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getDifferenceAmount() {
		return differenceAmount;
	}

	public void setDifferenceAmount(Double differenceAmount) {
		this.differenceAmount = differenceAmount;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public ReconciliationResult(Integer accountId, String accountNumber, String daybookTxnId, String bankTxnId,
			Long bankStatementId, ReconciliationStatus status, String description, Double differenceAmount,
			Date createdAt, Date updatedAt, Boolean isActive) {
		super();
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.daybookTxnId = daybookTxnId;
		this.bankTxnId = bankTxnId;
		this.bankStatementId = bankStatementId;
		this.status = status;
		this.description = description;
		this.differenceAmount = differenceAmount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isActive = isActive;
	}

	public ReconciliationResult() {
		super();
	}

}
