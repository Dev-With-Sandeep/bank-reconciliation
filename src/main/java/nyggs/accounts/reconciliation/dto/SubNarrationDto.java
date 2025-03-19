package nyggs.accounts.reconciliation.dto;

import nyggs.accounts.reconciliation.entity.SubNarration;

public class SubNarrationDto {
	private Long id;
	private String itemName;
	private Double quantity;
	private Double amount;
	private String narration;
	private Long transactionId;
	private Boolean isPercentage;

	public SubNarrationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubNarrationDto(Long id, String itemName, Double quantity, Double amount, String narration,
			Long transactionId, Boolean isPercentage) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.quantity = quantity;
		this.amount = amount;
		this.narration = narration;
		this.transactionId = transactionId;
		this.isPercentage = isPercentage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Boolean getIsPercentage() {
		return isPercentage;
	}

	public void setIsPercentage(Boolean isPercentage) {
		this.isPercentage = isPercentage;
	}

	public SubNarration convertDtoToEntity(SubNarrationDto subNarrationDto) {
		SubNarration subNarration = new SubNarration(subNarrationDto.getId(), subNarrationDto.getItemName(),
				subNarrationDto.getQuantity(), subNarrationDto.getAmount(), subNarrationDto.getNarration(), null,
				subNarrationDto.getIsPercentage());

		return subNarration;
	}
}
