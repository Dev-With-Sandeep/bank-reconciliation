package nyggs.accounts.reconciliation.enums;

public enum ReconciliationStatus {

	PENDING(0, "Pending"), MATCHED(1, "Matched"), PARTIALLY_MATCHED(2, "Partially Matched"),
	MISSING_IN_BOOK(3, "Missing In Book"), MISSING_IN_BANK(4, "Missing In Bank");

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

	private ReconciliationStatus(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

}
