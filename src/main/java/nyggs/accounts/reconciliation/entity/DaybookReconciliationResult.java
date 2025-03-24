package nyggs.accounts.reconciliation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import nyggs.accounts.reconciliation.enums.ReconciliationStatus;

public class DaybookReconciliationResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "daybook_txn_id")
	private String daybookTxnId;

	@Column(name = "bank_txn_id")
	private String bankTxnId;

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

	public DaybookReconciliationResult(Long id, String daybookTxnId, String bankTxnId, ReconciliationStatus status,
			String description, Double differenceAmount, Date createdAt, Date updatedAt, Boolean isActive) {
		super();
		this.id = id;
		this.daybookTxnId = daybookTxnId;
		this.bankTxnId = bankTxnId;
		this.status = status;
		this.description = description;
		this.differenceAmount = differenceAmount;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isActive = isActive;
	}

	public DaybookReconciliationResult() {
		super();
	}

}
