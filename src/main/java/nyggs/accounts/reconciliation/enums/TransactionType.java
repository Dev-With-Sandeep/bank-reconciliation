package nyggs.accounts.reconciliation.enums;

public enum TransactionType {

	DEBIT(0, "Debit"), CREDIT(1, "Credit");

	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private TransactionType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

}
