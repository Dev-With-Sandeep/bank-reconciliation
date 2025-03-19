package nyggs.accounts.reconciliation.enums;

public enum AccountTypeEnum {

	ASSETS("Assets"), LIABILITIES("Liabilities"), EQUITY("Equity"), EXPENSES("Expenses"), REVENUE("Revenue");

	private String name;

	private AccountTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
