package nyggs.accounts.reconciliation.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import nyggs.accounts.reconciliation.enums.KycStatus;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "acc_accounts")
//uniqueConstraints = @UniqueConstraint(columnNames = { "siteId", "hoAccountId" }))
public class Accounts {

	private Integer id;

	private Integer parentId;

	private String code;

	private String name;

	private String displayName;

	private Boolean isMappedWithTallyName;

	private String description;

	private String key;

	private Integer accountTypeId;

	private AccountConfig config;

	private KycStatus kycStatus;

	private String panCard;

	private String gstNo;

	private Integer masterId;

	private Long version;

	private Integer companyId;

	private Boolean isActive;

	private Date createdOn;

	private Integer createdBy;

	private Date updatedOn;

	private Integer updatedBy;

	private AccountType accountType;

	private FinancialYear financialYear;

//	private Integer financialYearId;

	private Integer hoAccountId;

	private Integer parentAccountId;

	private Boolean isContainsChildAccount;

	private Integer plateformId;
	private Integer otherPlateformAccountTypeId;
	private Integer otherPlateformAccountId;

	private Integer siteId;

	private Integer parentSiteId;

	private Boolean isEnableForCurrentSite;

	private Boolean isOpeningBalanceUpdatable;

	private Integer permissionStateId;

	private Boolean isAvailable;

	private Double raisedRequestAmount;

	private Double tempBalance;

	private Double openingBalance;

	private Double balance;

	private Double openingDebitBalance;

	private Double totalDebitBalance;

	private Double openingCreditBalance;

	private Double totalCreditBalance;

	private Double cashOpeningBalance;

	private Double cashBalance;

	private Double cashOpeningDebitBalance;

	private Double cashClosingDebitBalance;

	private Double cashOpeningCreditBalance;

	private Double cashClosingCreditBalance;

	private Integer defaultOverDueDays;

	private Double igstOpeningBalance;

	private Double igstBalance;

	private Double igstOpeningDebitBalance;

	private Double igstClosingDebitBalance;

	private Double igstOpeningCreditBalance;

	private Double igstClosingCreditBalance;

	private Double cgstOpeningBalance;

	private Double cgstBalance;

	private Double cgstOpeningDebitBalance;

	private Double cgstClosingDebitBalance;

	private Double cgstOpeningCreditBalance;

	private Double cgstClosingCreditBalance;

	private Double sgstOpeningBalance;

	private Double sgstBalance;

	private Double sgstOpeningDebitBalance;

	private Double sgstClosingDebitBalance;

	private Double sgstOpeningCreditBalance;

	private Double sgstClosingCreditBalance;

	private Double tdsOpeningBalance;

	private Double tdsBalance;

	private Double tdsOpeningDebitBalance;

	private Double tdsClosingDebitBalance;

	private Double tdsOpeningCreditBalance;

	private Double tdsClosingCreditBalance;

	private Double approvedOpeningBalance;

	private Double approvedBalance;

	private Double approvedOpeningDebitBalance;

	private Double approvedClosingDebitBalance;

	private Double approvedOpeningCreditBalance;

	private Double approvedClosingCreditBalance;

	private Double approvedCashOpeningBalance;

	private Double approvedCashBalance;

	private Double approvedCashOpeningDebitBalance;

	private Double approvedCashClosingDebitBalance;

	private Double approvedCashOpeningCreditBalance;

	private Double approvedCashClosingCreditBalance;

	private Double approvedIgstOpeningBalance;
	private Double approvedIgstBalance;
	private Double approvedIgstOpeningDebitBalance;
	private Double approvedIgstClosingDebitBalance;
	private Double approvedIgstOpeningCreditBalance;
	private Double approvedIgstClosingCreditBalance;

	private Double approvedCgstOpeningBalance;
	private Double approvedCgstBalance;
	private Double approvedCgstOpeningDebitBalance;
	private Double approvedCgstClosingDebitBalance;
	private Double approvedCgstOpeningCreditBalance;
	private Double approvedCgstClosingCreditBalance;

	private Double approvedSgstOpeningBalance;
	private Double approvedSgstBalance;
	private Double approvedSgstOpeningDebitBalance;
	private Double approvedSgstClosingDebitBalance;
	private Double approvedSgstOpeningCreditBalance;
	private Double approvedSgstClosingCreditBalance;

	private Double approvedTdsOpeningBalance;
	private Double approvedTdsBalance;
	private Double approvedTdsOpeningDebitBalance;
	private Double approvedTdsClosingDebitBalance;
	private Double approvedTdsOpeningCreditBalance;
	private Double approvedTdsClosingCreditBalance;

	private Integer childAccountTypeId;

	private Boolean isTxnInChildAccount;

	private Boolean isAccountSiteType;

	private String generateAccountNumber;

	private String thirdPartyAccountNumber;
	
	private Boolean isNotForRegularUse;
	
	private String address;
	
	private String state;
	
	private String pinCode;
	
	private String location;
	
	private String stateCode;

//
	public Accounts() {
		super();
	}

	public Accounts(Integer id) {
		this.id = id;
	}

	public Accounts(Integer id, Integer parentId, String code, String name, String description, Double openingBalance,
			Double balance, String key, Integer accountTypeId, AccountConfig config, KycStatus kycStatus,
			String panCard, String gstNo, Integer masterId, Integer companyId, Boolean isActive, Date createdOn,
			Integer createdBy, Date updatedOn, Integer updatedBy, Double totalDebitBalance, Double totalCreditBalance) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.code = code;
		this.name = name;
		this.description = description;
		this.openingBalance = openingBalance;
		this.balance = balance;
		this.key = key;
		this.accountTypeId = accountTypeId;
		this.config = config;
		this.kycStatus = kycStatus;
		this.panCard = panCard;
		this.gstNo = gstNo;
		this.masterId = masterId;
		this.companyId = companyId;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.totalDebitBalance = totalDebitBalance;
		this.totalCreditBalance = totalCreditBalance;
	}

	public Accounts(Integer id, Integer parentId, String code, String name, String description, Double openingBalance,
			Double balance, String key, Integer accountTypeId, AccountConfig config, KycStatus kycStatus,
			String panCard, String gstNo, Integer masterId, Integer companyId, Boolean isActive, Date createdOn,
			Integer createdBy, Date updatedOn, Integer updatedBy, Double openingDebitBalance, Double totalDebitBalance,
			Double openingCreditBalance, Double totalCreditBalance,

			Double cashOpeningBalance,

			Double cashBalance,

			Double cashOpeningDebitBalance,

			Double cashClosingDebitBalance,

			Double cashOpeningCreditBalance,

			Double cashClosingCreditBalance,

			Double igstOpeningBalance,

			Double igstBalance,

			Double igstOpeningDebitBalance,

			Double igstClosingDebitBalance,

			Double igstOpeningCreditBalance,

			Double igstClosingCreditBalance,

			Double cgstOpeningBalance,

			Double cgstBalance,

			Double cgstOpeningDebitBalance,

			Double cgstClosingDebitBalance,

			Double cgstOpeningCreditBalance,

			Double cgstClosingCreditBalance,

			Double sgstOpeningBalance,

			Double sgstBalance,

			Double sgstOpeningDebitBalance,

			Double sgstClosingDebitBalance,

			Double sgstOpeningCreditBalance,

			Double sgstClosingCreditBalance,

			Double tdsOpeningBalance,

			Double tdsBalance,

			Double tdsOpeningDebitBalance,

			Double tdsClosingDebitBalance,

			Double tdsOpeningCreditBalance,

			Double tdsClosingCreditBalance,

			Double approvedOpeningBalance, Double approvedBalance, Double approvedOpeningDebitBalance,
			Double approvedClosingDebitBalance, Double approvedOpeningCreditBalance,
			Double approvedClosingCreditBalance, Double approvedCashOpeningBalance, Double approvedCashBalance,
			Double approvedCashOpeningDebitBalance, Double approvedCashClosingDebitBalance,
			Double approvedCashOpeningCreditBalance, Double approvedCashClosingCreditBalance,

			Double approvedIgstOpeningBalance, Double approvedIgstBalance, Double approvedIgstOpeningDebitBalance,
			Double approvedIgstClosingDebitBalance, Double approvedIgstOpeningCreditBalance,
			Double approvedIgstClosingCreditBalance,

			Double approvedCgstOpeningBalance, Double approvedCgstBalance, Double approvedCgstOpeningDebitBalance,
			Double approvedCgstClosingDebitBalance, Double approvedCgstOpeningCreditBalance,
			Double approvedCgstClosingCreditBalance,

			Double approvedSgstOpeningBalance, Double approvedSgstBalance, Double approvedSgstOpeningDebitBalance,
			Double approvedSgstClosingDebitBalance, Double approvedSgstOpeningCreditBalance,
			Double approvedSgstClosingCreditBalance,

			Double approvedTdsOpeningBalance, Double approvedTdsBalance, Double approvedTdsOpeningDebitBalance,
			Double approvedTdsClosingDebitBalance, Double approvedTdsOpeningCreditBalance,
			Double approvedTdsClosingCreditBalance) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.code = code;
		this.name = name;
		this.description = description;
		this.openingBalance = openingBalance;
		this.balance = balance;
		this.key = key;
		this.accountTypeId = accountTypeId;
		this.config = config;
		this.kycStatus = kycStatus;
		this.panCard = panCard;
		this.gstNo = gstNo;
		this.masterId = masterId;
		this.companyId = companyId;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.openingDebitBalance = openingDebitBalance;
		this.totalDebitBalance = totalDebitBalance;
		this.openingCreditBalance = openingCreditBalance;
		this.totalCreditBalance = totalCreditBalance;

		this.cashOpeningBalance = cashOpeningBalance;

		this.cashBalance = cashBalance;

		this.cashOpeningDebitBalance = cashOpeningDebitBalance;

		this.cashClosingDebitBalance = cashClosingDebitBalance;

		this.cashOpeningCreditBalance = cashOpeningCreditBalance;

		this.cashClosingCreditBalance = cashClosingCreditBalance;

		this.igstOpeningBalance = igstOpeningBalance;
		this.igstBalance = igstBalance;
		this.igstOpeningDebitBalance = igstOpeningDebitBalance;
		this.igstClosingDebitBalance = igstClosingDebitBalance;
		this.igstOpeningCreditBalance = igstOpeningCreditBalance;
		this.igstClosingCreditBalance = igstClosingCreditBalance;

		this.cgstOpeningBalance = cgstOpeningBalance;
		this.cgstBalance = cgstBalance;
		this.cgstOpeningDebitBalance = cgstOpeningDebitBalance;
		this.cgstClosingDebitBalance = cgstClosingDebitBalance;
		this.cgstOpeningCreditBalance = cgstOpeningCreditBalance;
		this.cgstClosingCreditBalance = cgstClosingCreditBalance;

		this.sgstOpeningBalance = sgstOpeningBalance;
		this.sgstBalance = sgstBalance;
		this.sgstOpeningDebitBalance = sgstOpeningDebitBalance;
		this.sgstClosingDebitBalance = sgstClosingDebitBalance;
		this.sgstOpeningCreditBalance = sgstOpeningCreditBalance;
		this.sgstClosingCreditBalance = sgstClosingCreditBalance;

		this.tdsOpeningBalance = tdsOpeningBalance;
		this.tdsBalance = tdsBalance;
		this.tdsOpeningDebitBalance = tdsOpeningDebitBalance;
		this.tdsClosingDebitBalance = tdsClosingDebitBalance;
		this.tdsOpeningCreditBalance = tdsOpeningCreditBalance;
		this.tdsClosingCreditBalance = tdsClosingCreditBalance;

		this.approvedOpeningBalance = approvedOpeningBalance;
		this.approvedBalance = approvedBalance;
		this.approvedOpeningDebitBalance = approvedOpeningDebitBalance;
		this.approvedClosingDebitBalance = approvedClosingDebitBalance;
		this.approvedOpeningCreditBalance = approvedOpeningCreditBalance;
		this.approvedClosingCreditBalance = approvedClosingCreditBalance;

		this.approvedCashOpeningBalance = approvedCashOpeningBalance;
		this.approvedCashBalance = approvedCashBalance;
		this.approvedCashOpeningDebitBalance = approvedCashOpeningDebitBalance;
		this.approvedCashClosingDebitBalance = approvedCashClosingDebitBalance;
		this.approvedCashOpeningCreditBalance = approvedCashOpeningCreditBalance;
		this.approvedCashClosingCreditBalance = approvedCashClosingCreditBalance;

		this.approvedIgstOpeningBalance = approvedIgstOpeningBalance;
		this.approvedIgstBalance = approvedIgstBalance;
		this.approvedIgstOpeningDebitBalance = approvedIgstOpeningDebitBalance;
		this.approvedIgstClosingDebitBalance = approvedIgstClosingDebitBalance;
		this.approvedIgstOpeningCreditBalance = approvedIgstOpeningCreditBalance;
		this.approvedIgstClosingCreditBalance = approvedIgstClosingCreditBalance;

		this.approvedCgstOpeningBalance = approvedCgstOpeningBalance;
		this.approvedCgstBalance = approvedCgstBalance;
		this.approvedCgstOpeningDebitBalance = approvedCgstOpeningDebitBalance;
		this.approvedCgstClosingDebitBalance = approvedCgstClosingDebitBalance;
		this.approvedCgstOpeningCreditBalance = approvedCgstOpeningCreditBalance;
		this.approvedCgstClosingCreditBalance = approvedCgstClosingCreditBalance;

		this.approvedSgstOpeningBalance = approvedSgstOpeningBalance;
		this.approvedSgstBalance = approvedSgstBalance;
		this.approvedSgstOpeningDebitBalance = approvedSgstOpeningDebitBalance;
		this.approvedSgstClosingDebitBalance = approvedSgstClosingDebitBalance;
		this.approvedSgstOpeningCreditBalance = approvedSgstOpeningCreditBalance;
		this.approvedSgstClosingCreditBalance = approvedSgstClosingCreditBalance;

		this.approvedTdsOpeningBalance = approvedTdsOpeningBalance;
		this.approvedTdsBalance = approvedTdsBalance;
		this.approvedTdsOpeningDebitBalance = approvedTdsOpeningDebitBalance;
		this.approvedTdsClosingDebitBalance = approvedTdsClosingDebitBalance;
		this.approvedTdsOpeningCreditBalance = approvedTdsOpeningCreditBalance;
		this.approvedTdsClosingCreditBalance = approvedTdsClosingCreditBalance;

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

	@Column(name = "parent_id")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "display_name")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "is_mapped_with_tally_name")
	public Boolean getIsMappedWithTallyName() {
		return isMappedWithTallyName;
	}

	public void setIsMappedWithTallyName(Boolean isMappedWithTallyName) {
		this.isMappedWithTallyName = isMappedWithTallyName;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "opening_balance")
	public Double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}

	@Column(name = "temp_balance")
	public Double getTempBalance() {
		return tempBalance;
	}

	public void setTempBalance(Double tempBalance) {
		this.tempBalance = tempBalance;
	}

	@Column(name = "balance")
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "`key`")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "type_id")
	public Integer getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(Integer accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "config_id")
	public AccountConfig getConfig() {
		return config;
	}

	public void setConfig(AccountConfig config) {
		this.config = config;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "kyc_status")
	public KycStatus getKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(KycStatus kycStatus) {
		this.kycStatus = kycStatus;
	}

	@Column(name = "master_id")
	public Integer getMasterId() {
		return masterId;
	}

	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}

	@Version
	@Column(name = "version")
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Column(name = "company_id")
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Column(name = "is_active")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "created_on")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "updated_on")
	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Column(name = "updated_by")
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@OneToOne
	@JoinColumn(name = "type_id", updatable = false, insertable = false)
	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Column(name = "pan_card")
	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	@Column(name = "gst_no")
	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "financial_year_id", insertable = false, updatable = false)
	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}

//    @Column(name = "financial_year_id", insertable = false, updatable = false)
//	public Integer getFinancialYearId() {
//		return financialYearId;
//	}
//
//	public void setFinancialYearId(Integer financialYearId) {
//		this.financialYearId = financialYearId;
//	}

	@Column(name = "total_credit_balance")
	public Double getTotalCreditBalance() {
		return totalCreditBalance;
	}

	public void setTotalCreditBalance(Double totalCreditBalance) {
		this.totalCreditBalance = totalCreditBalance;
	}

	@Column(name = "total_debit_balance")
	public Double getTotalDebitBalance() {
		return totalDebitBalance;
	}

	public void setTotalDebitBalance(Double totalDebitBalance) {
		this.totalDebitBalance = totalDebitBalance;
	}

	@Column(name = "opening_debit_balance")
	public Double getOpeningDebitBalance() {
		return openingDebitBalance;
	}

	public void setOpeningDebitBalance(Double openingDebitBalance) {
		this.openingDebitBalance = openingDebitBalance;
	}

	@Column(name = "opening_credit_balance")
	public Double getOpeningCreditBalance() {
		return openingCreditBalance;
	}

	public void setOpeningCreditBalance(Double openingCreditBalance) {
		this.openingCreditBalance = openingCreditBalance;
	}

	@Column(name = "ho_account_id")
	public Integer getHoAccountId() {
		return hoAccountId;
	}

	public void setHoAccountId(Integer hoAccountId) {
		this.hoAccountId = hoAccountId;
	}

	@Column(name = "plateform_id")
	public Integer getPlateformId() {
		return plateformId;
	}

	public void setPlateformId(Integer plateformId) {
		this.plateformId = plateformId;
	}

	@Column(name = "other_plateform_account_type_id")
	public Integer getOtherPlateformAccountTypeId() {
		return otherPlateformAccountTypeId;
	}

	public void setOtherPlateformAccountTypeId(Integer otherPlateformAccountTypeId) {
		this.otherPlateformAccountTypeId = otherPlateformAccountTypeId;
	}

	@Column(name = "other_plateform_account_id")
	public Integer getOtherPlateformAccountId() {
		return otherPlateformAccountId;
	}

	public void setOtherPlateformAccountId(Integer otherPlateformAccountId) {
		this.otherPlateformAccountId = otherPlateformAccountId;
	}

	@Column(name = "site_id")
	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	@Column(name = "is_opening_balance_updatable")
	public Boolean getIsOpeningBalanceUpdatable() {
		return isOpeningBalanceUpdatable;
	}

	public void setIsOpeningBalanceUpdatable(Boolean isOpeningBalanceUpdatable) {
		this.isOpeningBalanceUpdatable = isOpeningBalanceUpdatable;
	}

	@Column(name = "raised_request_amount")
	public Double getRaisedRequestAmount() {
		return raisedRequestAmount;
	}

	public void setRaisedRequestAmount(Double raisedRequestAmount) {
		this.raisedRequestAmount = raisedRequestAmount;
	}

	@Column(name = "cash_opening_balance")
	public Double getCashOpeningBalance() {
		return cashOpeningBalance;
	}

	public void setCashOpeningBalance(Double cashOpeningBalance) {
		this.cashOpeningBalance = cashOpeningBalance;
	}

	@Column(name = "cash_balance")
	public Double getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(Double cashBalance) {
		this.cashBalance = cashBalance;
	}

	@Column(name = "cash_opening_debit_balance")
	public Double getCashOpeningDebitBalance() {
		return cashOpeningDebitBalance;
	}

	public void setCashOpeningDebitBalance(Double cashOpeningDebitBalance) {
		this.cashOpeningDebitBalance = cashOpeningDebitBalance;
	}

	@Column(name = "cash_Closing_debit_balance")
	public Double getCashClosingDebitBalance() {
		return cashClosingDebitBalance;
	}

	public void setCashClosingDebitBalance(Double cashClosingDebitBalance) {
		this.cashClosingDebitBalance = cashClosingDebitBalance;
	}

	@Column(name = "cash_opening_credit_balance")
	public Double getCashOpeningCreditBalance() {
		return cashOpeningCreditBalance;
	}

	public void setCashOpeningCreditBalance(Double cashOpeningCreditBalance) {
		this.cashOpeningCreditBalance = cashOpeningCreditBalance;
	}

	@Column(name = "cash_Closing_credit_balance")
	public Double getCashClosingCreditBalance() {
		return cashClosingCreditBalance;
	}

	public void setCashClosingCreditBalance(Double cashClosingCreditBalance) {
		this.cashClosingCreditBalance = cashClosingCreditBalance;
	}

	@Column(name = "default_over_due_days")
	public Integer getDefaultOverDueDays() {
		return defaultOverDueDays;
	}

	public void setDefaultOverDueDays(Integer defaultOverDueDays) {
		this.defaultOverDueDays = defaultOverDueDays;
	}

	// Igst Opening Balance
	@Column(name = "igst_opening_balance")
	public Double getIgstOpeningBalance() {
		return igstOpeningBalance;
	}

	// Igst Balance
	@Column(name = "igst_balance")
	public Double getIgstBalance() {
		return igstBalance;
	}

	// Igst Opening Debit Balance
	@Column(name = "igst_opening_debit_balance")
	public Double getIgstOpeningDebitBalance() {
		return igstOpeningDebitBalance;
	}

	// Igst Closing Debit Balance
	@Column(name = "igst_Closing_debit_balance")
	public Double getIgstClosingDebitBalance() {
		return igstClosingDebitBalance;
	}

	// Igst Opening Credit Balance
	@Column(name = "igst_opening_credit_balance")
	public Double getIgstOpeningCreditBalance() {
		return igstOpeningCreditBalance;
	}

	// Igst Closing Credit Balance
	@Column(name = "igst_Closing_credit_balance")
	public Double getIgstClosingCreditBalance() {
		return igstClosingCreditBalance;
	}

	// Cgst Opening Balance
	@Column(name = "cgst_opening_balance")
	public Double getCgstOpeningBalance() {
		return cgstOpeningBalance;
	}

	// Cgst Balance
	@Column(name = "cgst_balance")
	public Double getCgstBalance() {
		return cgstBalance;
	}

	// Cgst Opening Debit Balance
	@Column(name = "cgst_opening_debit_balance")
	public Double getCgstOpeningDebitBalance() {
		return cgstOpeningDebitBalance;
	}

	// Cgst Closing Debit Balance
	@Column(name = "cgst_Closing_debit_balance")
	public Double getCgstClosingDebitBalance() {
		return cgstClosingDebitBalance;
	}

	// Cgst Opening Credit Balance
	@Column(name = "cgst_opening_credit_balance")
	public Double getCgstOpeningCreditBalance() {
		return cgstOpeningCreditBalance;
	}

	// Cgst Closing Credit Balance
	@Column(name = "cgst_Closing_credit_balance")
	public Double getCgstClosingCreditBalance() {
		return cgstClosingCreditBalance;
	}

	// Sgst Opening Balance
	@Column(name = "sgst_opening_balance")
	public Double getSgstOpeningBalance() {
		return sgstOpeningBalance;
	}

	// Sgst Balance
	@Column(name = "sgst_balance")
	public Double getSgstBalance() {
		return sgstBalance;
	}

	// Sgst Opening Debit Balance
	@Column(name = "sgst_opening_debit_balance")
	public Double getSgstOpeningDebitBalance() {
		return sgstOpeningDebitBalance;
	}

	// Sgst Closing Debit Balance
	@Column(name = "sgst_Closing_debit_balance")
	public Double getSgstClosingDebitBalance() {
		return sgstClosingDebitBalance;
	}

	// Sgst Opening Credit Balance
	@Column(name = "sgst_opening_credit_balance")
	public Double getSgstOpeningCreditBalance() {
		return sgstOpeningCreditBalance;
	}

	// Sgst Closing Credit Balance
	@Column(name = "sgst_Closing_credit_balance")
	public Double getSgstClosingCreditBalance() {
		return sgstClosingCreditBalance;
	}

	// Sgst Current Time Period Debit Balance

	// Tds Opening Balance
	@Column(name = "tds_opening_balance")
	public Double getTdsOpeningBalance() {
		return tdsOpeningBalance;
	}

	// Tds Balance
	@Column(name = "tds_balance")
	public Double getTdsBalance() {
		return tdsBalance;
	}

	// Tds Opening Debit Balance
	@Column(name = "tds_opening_debit_balance")
	public Double getTdsOpeningDebitBalance() {
		return tdsOpeningDebitBalance;
	}

	// Tds Closing Debit Balance
	@Column(name = "tds_Closing_debit_balance")
	public Double getTdsClosingDebitBalance() {
		return tdsClosingDebitBalance;
	}

	// Tds Opening Credit Balance
	@Column(name = "tds_opening_credit_balance")
	public Double getTdsOpeningCreditBalance() {
		return tdsOpeningCreditBalance;
	}

	// Tds Closing Credit Balance
	@Column(name = "tds_Closing_credit_balance")
	public Double getTdsClosingCreditBalance() {
		return tdsClosingCreditBalance;
	}

	@Column(name = "approved_opening_balance")
	public Double getApprovedOpeningBalance() {
		return approvedOpeningBalance;
	}

	@Column(name = "approved_balance")
	public Double getApprovedBalance() {
		return approvedBalance;
	}

	@Column(name = "approved_opening_debit_balance")
	public Double getApprovedOpeningDebitBalance() {
		return approvedOpeningDebitBalance;
	}

	@Column(name = "approved_Closing_debit_balance")
	public Double getApprovedClosingDebitBalance() {
		return approvedClosingDebitBalance;
	}

	@Column(name = "approved_opening_credit_balance")
	public Double getApprovedOpeningCreditBalance() {
		return approvedOpeningCreditBalance;
	}

	@Column(name = "approved_Closing_credit_balance")
	public Double getApprovedClosingCreditBalance() {
		return approvedClosingCreditBalance;
	}

	@Column(name = "approved_cash_opening_balance")
	public Double getApprovedCashOpeningBalance() {
		return approvedCashOpeningBalance;
	}

	@Column(name = "approved_cash_balance")
	public Double getApprovedCashBalance() {
		return approvedCashBalance;
	}

	@Column(name = "approved_cash_opening_debit_balance")
	public Double getApprovedCashOpeningDebitBalance() {
		return approvedCashOpeningDebitBalance;
	}

	@Column(name = "approved_cash_Closing_debit_balance")
	public Double getApprovedCashClosingDebitBalance() {
		return approvedCashClosingDebitBalance;
	}

	@Column(name = "approved_cash_opening_credit_balance")
	public Double getApprovedCashOpeningCreditBalance() {
		return approvedCashOpeningCreditBalance;
	}

	@Column(name = "approved_cash_Closing_credit_balance")
	public Double getApprovedCashClosingCreditBalance() {
		return approvedCashClosingCreditBalance;
	}

	// Igst Opening Balance
	@Column(name = "approved_igst_opening_balance")
	public Double getApprovedIgstOpeningBalance() {
		return approvedIgstOpeningBalance;
	}

	// Igst Balance
	@Column(name = "approved_igst_balance")
	public Double getApprovedIgstBalance() {
		return approvedIgstBalance;
	}

	// Igst Opening Debit Balance
	@Column(name = "approved_igst_opening_debit_balance")
	public Double getApprovedIgstOpeningDebitBalance() {
		return approvedIgstOpeningDebitBalance;
	}

	// Igst Closing Debit Balance
	@Column(name = "approved_igst_Closing_debit_balance")
	public Double getApprovedIgstClosingDebitBalance() {
		return approvedIgstClosingDebitBalance;
	}

	// Igst Opening Credit Balance
	@Column(name = "approved_igst_opening_credit_balance")
	public Double getApprovedIgstOpeningCreditBalance() {
		return approvedIgstOpeningCreditBalance;
	}

	// Igst Closing Credit Balance
	@Column(name = "approved_igst_Closing_credit_balance")
	public Double getApprovedIgstClosingCreditBalance() {
		return approvedIgstClosingCreditBalance;
	}

	// Cgst Opening Balance
	@Column(name = "approved_cgst_opening_balance")
	public Double getApprovedCgstOpeningBalance() {
		return approvedCgstOpeningBalance;
	}

	// Cgst Balance
	@Column(name = "approved_cgst_balance")
	public Double getApprovedCgstBalance() {
		return approvedCgstBalance;
	}

	// Cgst Opening Debit Balance
	@Column(name = "approved_cgst_opening_debit_balance")
	public Double getApprovedCgstOpeningDebitBalance() {
		return approvedCgstOpeningDebitBalance;
	}

	// Cgst Closing Debit Balance
	@Column(name = "approved_cgst_Closing_debit_balance")
	public Double getApprovedCgstClosingDebitBalance() {
		return approvedCgstClosingDebitBalance;
	}

	// Cgst Opening Credit Balance
	@Column(name = "approved_cgst_opening_credit_balance")
	public Double getApprovedCgstOpeningCreditBalance() {
		return approvedCgstOpeningCreditBalance;
	}

	// Cgst Closing Credit Balance
	@Column(name = "approved_cgst_Closing_credit_balance")
	public Double getApprovedCgstClosingCreditBalance() {
		return approvedCgstClosingCreditBalance;
	}

	// Sgst Opening Balance
	@Column(name = "approved_sgst_opening_balance")
	public Double getApprovedSgstOpeningBalance() {
		return approvedSgstOpeningBalance;
	}

	// Sgst Balance
	@Column(name = "approved_sgst_balance")
	public Double getApprovedSgstBalance() {
		return approvedSgstBalance;
	}

	// Sgst Opening Debit Balance
	@Column(name = "approved_sgst_opening_debit_balance")
	public Double getApprovedSgstOpeningDebitBalance() {
		return approvedSgstOpeningDebitBalance;
	}

	// Sgst Closing Debit Balance
	@Column(name = "approved_sgst_Closing_debit_balance")
	public Double getApprovedSgstClosingDebitBalance() {
		return approvedSgstClosingDebitBalance;
	}

	// Sgst Opening Credit Balance
	@Column(name = "approved_sgst_opening_credit_balance")
	public Double getApprovedSgstOpeningCreditBalance() {
		return approvedSgstOpeningCreditBalance;
	}

	// Sgst Closing Credit Balance
	@Column(name = "approved_sgst_Closing_credit_balance")
	public Double getApprovedSgstClosingCreditBalance() {
		return approvedSgstClosingCreditBalance;
	}

	// Tds Opening Balance
	@Column(name = "approved_tds_opening_balance")
	public Double getApprovedTdsOpeningBalance() {
		return approvedTdsOpeningBalance;
	}

	// Tds Balance
	@Column(name = "approved_tds_balance")
	public Double getApprovedTdsBalance() {
		return approvedTdsBalance;
	}

	// Tds Opening Debit Balance
	@Column(name = "approved_tds_opening_debit_balance")
	public Double getApprovedTdsOpeningDebitBalance() {
		return approvedTdsOpeningDebitBalance;
	}

	// Tds Closing Debit Balance
	@Column(name = "approved_tds_Closing_debit_balance")
	public Double getApprovedTdsClosingDebitBalance() {
		return approvedTdsClosingDebitBalance;
	}

	// Tds Opening Credit Balance
	@Column(name = "approved_tds_opening_credit_balance")
	public Double getApprovedTdsOpeningCreditBalance() {
		return approvedTdsOpeningCreditBalance;
	}

	// Tds Closing Credit Balance
	@Column(name = "approved_tds_Closing_credit_balance")
	public Double getApprovedTdsClosingCreditBalance() {
		return approvedTdsClosingCreditBalance;
	}

	// Tds Current Time Period Debit Balance

	@Column(name = "permission_state_id")
	public Integer getPermissionStateId() {
		return permissionStateId;
	}

	public void setPermissionStateId(Integer permissionStateId) {
		this.permissionStateId = permissionStateId;
	}

	@Column(name = "is_available")
	public Boolean getIsAvailable() {
		return isAvailable;
	}

	@Column(name = "parent_site_id")
	public Integer getParentSiteId() {
		return parentSiteId;
	}

	public void setParentSiteId(Integer parentSiteId) {
		this.parentSiteId = parentSiteId;
	}

	@Column(name = "is_enable_for_current_site")
	public Boolean getIsEnableForCurrentSite() {
		return isEnableForCurrentSite;
	}

	public void setIsEnableForCurrentSite(Boolean isEnableForCurrentSite) {
		this.isEnableForCurrentSite = isEnableForCurrentSite;
	}

	@Column(name = "parent_account_id")
	public Integer getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(Integer parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	@Column(name = "is_contains_child_account")
	public Boolean getIsContainsChildAccount() {
		return isContainsChildAccount;
	}

	public void setIsContainsChildAccount(Boolean isContainsChildAccount) {
		this.isContainsChildAccount = isContainsChildAccount;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public void setIgstOpeningBalance(Double igstOpeningBalance) {
		this.igstOpeningBalance = igstOpeningBalance;
	}

	public void setIgstBalance(Double igstBalance) {
		this.igstBalance = igstBalance;
	}

	public void setIgstOpeningDebitBalance(Double igstOpeningDebitBalance) {
		this.igstOpeningDebitBalance = igstOpeningDebitBalance;
	}

	public void setIgstClosingDebitBalance(Double igstClosingDebitBalance) {
		this.igstClosingDebitBalance = igstClosingDebitBalance;
	}

	public void setIgstOpeningCreditBalance(Double igstOpeningCreditBalance) {
		this.igstOpeningCreditBalance = igstOpeningCreditBalance;
	}

	public void setIgstClosingCreditBalance(Double igstClosingCreditBalance) {
		this.igstClosingCreditBalance = igstClosingCreditBalance;
	}

	public void setCgstOpeningBalance(Double cgstOpeningBalance) {
		this.cgstOpeningBalance = cgstOpeningBalance;
	}

	public void setCgstBalance(Double cgstBalance) {
		this.cgstBalance = cgstBalance;
	}

	public void setCgstOpeningDebitBalance(Double cgstOpeningDebitBalance) {
		this.cgstOpeningDebitBalance = cgstOpeningDebitBalance;
	}

	public void setCgstClosingDebitBalance(Double cgstClosingDebitBalance) {
		this.cgstClosingDebitBalance = cgstClosingDebitBalance;
	}

	public void setCgstOpeningCreditBalance(Double cgstOpeningCreditBalance) {
		this.cgstOpeningCreditBalance = cgstOpeningCreditBalance;
	}

	public void setCgstClosingCreditBalance(Double cgstClosingCreditBalance) {
		this.cgstClosingCreditBalance = cgstClosingCreditBalance;
	}

	public void setSgstOpeningBalance(Double sgstOpeningBalance) {
		this.sgstOpeningBalance = sgstOpeningBalance;
	}

	public void setSgstBalance(Double sgstBalance) {
		this.sgstBalance = sgstBalance;
	}

	public void setSgstOpeningDebitBalance(Double sgstOpeningDebitBalance) {
		this.sgstOpeningDebitBalance = sgstOpeningDebitBalance;
	}

	public void setSgstClosingDebitBalance(Double sgstClosingDebitBalance) {
		this.sgstClosingDebitBalance = sgstClosingDebitBalance;
	}

	public void setSgstOpeningCreditBalance(Double sgstOpeningCreditBalance) {
		this.sgstOpeningCreditBalance = sgstOpeningCreditBalance;
	}

	public void setSgstClosingCreditBalance(Double sgstClosingCreditBalance) {
		this.sgstClosingCreditBalance = sgstClosingCreditBalance;
	}

	public void setTdsOpeningBalance(Double tdsOpeningBalance) {
		this.tdsOpeningBalance = tdsOpeningBalance;
	}

	public void setTdsBalance(Double tdsBalance) {
		this.tdsBalance = tdsBalance;
	}

	public void setTdsOpeningDebitBalance(Double tdsOpeningDebitBalance) {
		this.tdsOpeningDebitBalance = tdsOpeningDebitBalance;
	}

	public void setTdsClosingDebitBalance(Double tdsClosingDebitBalance) {
		this.tdsClosingDebitBalance = tdsClosingDebitBalance;
	}

	public void setTdsOpeningCreditBalance(Double tdsOpeningCreditBalance) {
		this.tdsOpeningCreditBalance = tdsOpeningCreditBalance;
	}

	public void setTdsClosingCreditBalance(Double tdsClosingCreditBalance) {
		this.tdsClosingCreditBalance = tdsClosingCreditBalance;
	}

	public void setApprovedOpeningBalance(Double approvedOpeningBalance) {
		this.approvedOpeningBalance = approvedOpeningBalance;
	}

	public void setApprovedBalance(Double approvedBalance) {
		this.approvedBalance = approvedBalance;
	}

	public void setApprovedOpeningDebitBalance(Double approvedOpeningDebitBalance) {
		this.approvedOpeningDebitBalance = approvedOpeningDebitBalance;
	}

	public void setApprovedClosingDebitBalance(Double approvedClosingDebitBalance) {
		this.approvedClosingDebitBalance = approvedClosingDebitBalance;
	}

	public void setApprovedOpeningCreditBalance(Double approvedOpeningCreditBalance) {
		this.approvedOpeningCreditBalance = approvedOpeningCreditBalance;
	}

	public void setApprovedClosingCreditBalance(Double approvedClosingCreditBalance) {
		this.approvedClosingCreditBalance = approvedClosingCreditBalance;
	}

	public void setApprovedCashOpeningBalance(Double approvedCashOpeningBalance) {
		this.approvedCashOpeningBalance = approvedCashOpeningBalance;
	}

	public void setApprovedCashBalance(Double approvedCashBalance) {
		this.approvedCashBalance = approvedCashBalance;
	}

	public void setApprovedCashOpeningDebitBalance(Double approvedCashOpeningDebitBalance) {
		this.approvedCashOpeningDebitBalance = approvedCashOpeningDebitBalance;
	}

	public void setApprovedCashClosingDebitBalance(Double approvedCashClosingDebitBalance) {
		this.approvedCashClosingDebitBalance = approvedCashClosingDebitBalance;
	}

	public void setApprovedCashOpeningCreditBalance(Double approvedCashOpeningCreditBalance) {
		this.approvedCashOpeningCreditBalance = approvedCashOpeningCreditBalance;
	}

	public void setApprovedCashClosingCreditBalance(Double approvedCashClosingCreditBalance) {
		this.approvedCashClosingCreditBalance = approvedCashClosingCreditBalance;
	}

	public void setApprovedIgstOpeningBalance(Double approvedIgstOpeningBalance) {
		this.approvedIgstOpeningBalance = approvedIgstOpeningBalance;
	}

	public void setApprovedIgstBalance(Double approvedIgstBalance) {
		this.approvedIgstBalance = approvedIgstBalance;
	}

	public void setApprovedIgstOpeningDebitBalance(Double approvedIgstOpeningDebitBalance) {
		this.approvedIgstOpeningDebitBalance = approvedIgstOpeningDebitBalance;
	}

	public void setApprovedIgstClosingDebitBalance(Double approvedIgstClosingDebitBalance) {
		this.approvedIgstClosingDebitBalance = approvedIgstClosingDebitBalance;
	}

	public void setApprovedIgstOpeningCreditBalance(Double approvedIgstOpeningCreditBalance) {
		this.approvedIgstOpeningCreditBalance = approvedIgstOpeningCreditBalance;
	}

	public void setApprovedIgstClosingCreditBalance(Double approvedIgstClosingCreditBalance) {
		this.approvedIgstClosingCreditBalance = approvedIgstClosingCreditBalance;
	}

	public void setApprovedCgstOpeningBalance(Double approvedCgstOpeningBalance) {
		this.approvedCgstOpeningBalance = approvedCgstOpeningBalance;
	}

	public void setApprovedCgstBalance(Double approvedCgstBalance) {
		this.approvedCgstBalance = approvedCgstBalance;
	}

	public void setApprovedCgstOpeningDebitBalance(Double approvedCgstOpeningDebitBalance) {
		this.approvedCgstOpeningDebitBalance = approvedCgstOpeningDebitBalance;
	}

	public void setApprovedCgstClosingDebitBalance(Double approvedCgstClosingDebitBalance) {
		this.approvedCgstClosingDebitBalance = approvedCgstClosingDebitBalance;
	}

	public void setApprovedCgstOpeningCreditBalance(Double approvedCgstOpeningCreditBalance) {
		this.approvedCgstOpeningCreditBalance = approvedCgstOpeningCreditBalance;
	}

	public void setApprovedCgstClosingCreditBalance(Double approvedCgstClosingCreditBalance) {
		this.approvedCgstClosingCreditBalance = approvedCgstClosingCreditBalance;
	}

	public void setApprovedSgstOpeningBalance(Double approvedSgstOpeningBalance) {
		this.approvedSgstOpeningBalance = approvedSgstOpeningBalance;
	}

	public void setApprovedSgstBalance(Double approvedSgstBalance) {
		this.approvedSgstBalance = approvedSgstBalance;
	}

	public void setApprovedSgstOpeningDebitBalance(Double approvedSgstOpeningDebitBalance) {
		this.approvedSgstOpeningDebitBalance = approvedSgstOpeningDebitBalance;
	}

	public void setApprovedSgstClosingDebitBalance(Double approvedSgstClosingDebitBalance) {
		this.approvedSgstClosingDebitBalance = approvedSgstClosingDebitBalance;
	}

	public void setApprovedSgstOpeningCreditBalance(Double approvedSgstOpeningCreditBalance) {
		this.approvedSgstOpeningCreditBalance = approvedSgstOpeningCreditBalance;
	}

	public void setApprovedSgstClosingCreditBalance(Double approvedSgstClosingCreditBalance) {
		this.approvedSgstClosingCreditBalance = approvedSgstClosingCreditBalance;
	}

	public void setApprovedTdsOpeningBalance(Double approvedTdsOpeningBalance) {
		this.approvedTdsOpeningBalance = approvedTdsOpeningBalance;
	}

	public void setApprovedTdsBalance(Double approvedTdsBalance) {
		this.approvedTdsBalance = approvedTdsBalance;
	}

	public void setApprovedTdsOpeningDebitBalance(Double approvedTdsOpeningDebitBalance) {
		this.approvedTdsOpeningDebitBalance = approvedTdsOpeningDebitBalance;
	}

	public void setApprovedTdsClosingDebitBalance(Double approvedTdsClosingDebitBalance) {
		this.approvedTdsClosingDebitBalance = approvedTdsClosingDebitBalance;
	}

	public void setApprovedTdsOpeningCreditBalance(Double approvedTdsOpeningCreditBalance) {
		this.approvedTdsOpeningCreditBalance = approvedTdsOpeningCreditBalance;
	}

	public void setApprovedTdsClosingCreditBalance(Double approvedTdsClosingCreditBalance) {
		this.approvedTdsClosingCreditBalance = approvedTdsClosingCreditBalance;
	}

	@Column(name = "child_account_type_id")
	public Integer getChildAccountTypeId() {
		return childAccountTypeId;
	}

	public void setChildAccountTypeId(Integer childAccountTypeId) {
		this.childAccountTypeId = childAccountTypeId;
	}

	@Column(name = "is_txn_in_child_account")
	public Boolean getIsTxnInChildAccount() {
		return isTxnInChildAccount;
	}

	public void setIsTxnInChildAccount(Boolean isTxnInChildAccount) {
		this.isTxnInChildAccount = isTxnInChildAccount;
	}

	@Column(name = "is_account_site_type")
	public Boolean getIsAccountSiteType() {
		return isAccountSiteType;
	}

	public void setIsAccountSiteType(Boolean isAccountSiteType) {
		this.isAccountSiteType = isAccountSiteType;
	}

	@Column(name = "generate_account_number")
	public String getGenerateAccountNumber() {
		return generateAccountNumber;
	}

	public void setGenerateAccountNumber(String generateAccountNumber) {
		this.generateAccountNumber = generateAccountNumber;
	}

	@Column(name = "third_party_account_number")
	public String getThirdPartyAccountNumber() {
		return thirdPartyAccountNumber;
	}

	public void setThirdPartyAccountNumber(String thirdPartyAccountNumber) {
		this.thirdPartyAccountNumber = thirdPartyAccountNumber;
	}

	@Column(name = "not_for_regular_use")
	public Boolean getIsNotForRegularUse() {
		return isNotForRegularUse;
	}

	public void setIsNotForRegularUse(Boolean isNotForRegularUse) {
		this.isNotForRegularUse = isNotForRegularUse;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "pin_code")
	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@Column(name = "location")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "state_code")
	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}


}
