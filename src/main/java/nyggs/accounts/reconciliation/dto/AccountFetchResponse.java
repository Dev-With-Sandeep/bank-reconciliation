package nyggs.accounts.reconciliation.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountFetchResponse {

	private Integer id;

	private String code;

	private String name;

	private String displayName;

	private String description;

	private Double openingBalance;

	private String openingBalanceTypeName;

	private Double balance;

	private String balanceTypeName;

	private String key;

	private Integer accountTypeId;

	private String accountTypeName;

	private Integer kycStatusId;

	private String kycStatusName;

	private Integer masterId;

	private Date createdOn;

	private Double totalCreditBalance;

	private Double totalDebitBalance;

	private Integer siteId;

	private AccountFetchResponse parentAccountDto;

	private String panCard;

	private String gstNo;

	private Integer permissionStateId;
	private Boolean isAvailable;

	private Boolean isEnableForCurrentSite;

	private Integer defaultOverDueDays;

	private Double currentDueAmount;

	private Double overdueAmount;

	private Double overdue15Amount;

	private Double overdue30Amount;

	private Double overdue45Amount;

	private Double overdue60Amount;

	private Double overdue90Amount;

	private Double overdueAbove90Amount;

	private String generateAccountNumber;

	private String address;

	private String state;

	private String pinCode;

	private String location;

	private String stateCode;

	public AccountFetchResponse() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public String getOpeningBalanceTypeName() {
		return openingBalanceTypeName;
	}

	public void setOpeningBalanceTypeName(String openingBalanceTypeName) {
		this.openingBalanceTypeName = openingBalanceTypeName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getBalanceTypeName() {
		return balanceTypeName;
	}

	public void setBalanceTypeName(String balanceTypeName) {
		this.balanceTypeName = balanceTypeName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(Integer accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

	public Integer getKycStatusId() {
		return kycStatusId;
	}

	public void setKycStatusId(Integer kycStatusId) {
		this.kycStatusId = kycStatusId;
	}

	public String getKycStatusName() {
		return kycStatusName;
	}

	public void setKycStatusName(String kycStatusName) {
		this.kycStatusName = kycStatusName;
	}

	public Integer getMasterId() {
		return masterId;
	}

	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Double getTotalCreditBalance() {
		return totalCreditBalance;
	}

	public void setTotalCreditBalance(Double totalCreditBalance) {
		this.totalCreditBalance = totalCreditBalance;
	}

	public Double getTotalDebitBalance() {
		return totalDebitBalance;
	}

	public void setTotalDebitBalance(Double totalDebitBalance) {
		this.totalDebitBalance = totalDebitBalance;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public AccountFetchResponse getParentAccountDto() {
		return parentAccountDto;
	}

	public void setParentAccountDto(AccountFetchResponse parentAccountDto) {
		this.parentAccountDto = parentAccountDto;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public Integer getPermissionStateId() {
		return permissionStateId;
	}

	public void setPermissionStateId(Integer permissionStateId) {
		this.permissionStateId = permissionStateId;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Boolean getIsEnableForCurrentSite() {
		return isEnableForCurrentSite;
	}

	public void setIsEnableForCurrentSite(Boolean isEnableForCurrentSite) {
		this.isEnableForCurrentSite = isEnableForCurrentSite;
	}

	public Integer getDefaultOverDueDays() {
		return defaultOverDueDays;
	}

	public void setDefaultOverDueDays(Integer defaultOverDueDays) {
		this.defaultOverDueDays = defaultOverDueDays;
	}

	public Double getCurrentDueAmount() {
		return currentDueAmount;
	}

	public void setCurrentDueAmount(Double currentDueAmount) {
		this.currentDueAmount = currentDueAmount;
	}

	public Double getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(Double overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public Double getOverdue15Amount() {
		return overdue15Amount;
	}

	public void setOverdue15Amount(Double overdue15Amount) {
		this.overdue15Amount = overdue15Amount;
	}

	public Double getOverdue30Amount() {
		return overdue30Amount;
	}

	public void setOverdue30Amount(Double overdue30Amount) {
		this.overdue30Amount = overdue30Amount;
	}

	public Double getOverdue45Amount() {
		return overdue45Amount;
	}

	public void setOverdue45Amount(Double overdue45Amount) {
		this.overdue45Amount = overdue45Amount;
	}

	public Double getOverdue60Amount() {
		return overdue60Amount;
	}

	public void setOverdue60Amount(Double overdue60Amount) {
		this.overdue60Amount = overdue60Amount;
	}

	public Double getOverdue90Amount() {
		return overdue90Amount;
	}

	public void setOverdue90Amount(Double overdue90Amount) {
		this.overdue90Amount = overdue90Amount;
	}

	public Double getOverdueAbove90Amount() {
		return overdueAbove90Amount;
	}

	public void setOverdueAbove90Amount(Double overdueAbove90Amount) {
		this.overdueAbove90Amount = overdueAbove90Amount;
	}

	public String getGenerateAccountNumber() {
		return generateAccountNumber;
	}

	public void setGenerateAccountNumber(String generateAccountNumber) {
		this.generateAccountNumber = generateAccountNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

}
