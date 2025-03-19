package nyggs.accounts.reconciliation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import nyggs.accounts.reconciliation.dto.SubNarrationDto;

@Entity
@Table(name = "sub_narration")
public class SubNarration {
	private Long id;

	private String itemName;
	private Double quantity;
	private Double amount;
	private String narration;
	private AccountTransactions accountTransaction;
	private Boolean isPercentage;

	public SubNarration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubNarration(Long id, String itemName, Double quantity, Double amount, String narration,
			AccountTransactions accountTransaction, Boolean isPercentage) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.quantity = quantity;
		this.amount = amount;
		this.narration = narration;
		this.accountTransaction = accountTransaction;
		this.isPercentage = isPercentage;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "item_name")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "quantity")
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@ManyToOne()
	@JoinColumn(name = "account_transaction_id")
	public AccountTransactions getAccountTransaction() {
		return accountTransaction;
	}

	public void setAccountTransaction(AccountTransactions accountTransaction) {
		this.accountTransaction = accountTransaction;
	}

	@Column(name = "narration")
	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	@Column(name = "is_percentage")
	public Boolean getIsPercentage() {
		return isPercentage;
	}

	public void setIsPercentage(Boolean isPercentage) {
		this.isPercentage = isPercentage;
	}

	public SubNarrationDto convertEntityToDto(SubNarration subNarration) {
		SubNarrationDto subNarrationDto = new SubNarrationDto(subNarration.getId(), subNarration.getItemName(),
				subNarration.getQuantity(), subNarration.getAmount(), subNarration.getNarration(), null,
				subNarration.getIsPercentage());

		return subNarrationDto;
	}
}
