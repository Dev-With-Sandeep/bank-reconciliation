package nyggs.accounts.reconciliation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "acc_account_config")
public class AccountConfig {

	private Integer id;

	private Double minTransactionAmountLimit;

	private Double maxTransactionAmountLimit;

	private Boolean isActive;

	private Date updatedOn;

	public AccountConfig() {
		super();
	}

	public AccountConfig(Integer id, Double minTransactionAmountLimit, Double maxTransactionAmountLimit,
			Boolean isActive, Date updatedOn) {
		super();
		this.id = id;
		this.minTransactionAmountLimit = minTransactionAmountLimit;
		this.maxTransactionAmountLimit = maxTransactionAmountLimit;
		this.isActive = isActive;
		this.updatedOn = updatedOn;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "min_transaction_amount_limit")
	public Double getMinTransactionAmountLimit() {
		return minTransactionAmountLimit;
	}

	public void setMinTransactionAmountLimit(Double minTransactionAmountLimit) {
		this.minTransactionAmountLimit = minTransactionAmountLimit;
	}

	@Column(name = "max_transaction_amount_limit")
	public Double getMaxTransactionAmountLimit() {
		return maxTransactionAmountLimit;
	}

	public void setMaxTransactionAmountLimit(Double maxTransactionAmountLimit) {
		this.maxTransactionAmountLimit = maxTransactionAmountLimit;
	}

	@Column(name = "is_active")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "updated_on")
	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
