package nyggs.accounts.reconciliation.enums;

public enum KycStatus {

	NOT_REQUIRED(0, "NOT REQUIRED"), PENDING(1, "PENDING"), COMPLETED(2, "COMPLETED");

	private Integer id;

	private String name;

	private KycStatus(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
