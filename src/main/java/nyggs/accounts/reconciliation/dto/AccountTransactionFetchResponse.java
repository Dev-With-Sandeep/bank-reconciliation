package nyggs.accounts.reconciliation.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import nyggs.accounts.reconciliation.enums.VoucherCategoryType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountTransactionFetchResponse {

	private Long id;

	private Date date;

	private Long txnId;

	private Integer sourceAccountId;

	private String sourceAccountName;

	private String sourceAccountCode;

	private Integer sourceAccountTypeId;

	private String sourceAccountTypeName;

	private Integer targetAccountId;

	private String targetAccountName;

	private String targetAccountCode;

	private Integer targetAccountTypeId;

	private String targetAccountTypeName;

	private Double amount;

	private Double sourceAccountOpeningBalance;

	private String sourceAccountOpeningBalanceTypeName;

	private Double sourceAccountClosingBalance;

	private String sourceAccountClosingBalanceTypeName;

	private Double targetAccountOpeningBalance;

	private String targetAccountOpeningBalanceTypeName;

	private Double targetAccountClosingBalance;

	private String targetAccountClosingBalanceTypeName;

	private String voucherNo;

	private String remarks;

	private Boolean isReverted;

	private Date createdOn;

	private String createdByName;

	private Integer voucherTemplateId;
	private Object jsonData;
	private Boolean isTemporary;
	private Boolean isInterSite;
	private Integer secondarySiteId;

	private Boolean isCashTransaction;
	private Boolean isIgstTransaction;
	private Boolean isCgstTransaction;
	private Boolean isSgstTransaction;
	private Boolean isTdsTransaction;

	private Integer permissionStateId;
	private Boolean isAvailable;
	private String sourceAccountNarration;
	private String targetAccountNarration;

	private Integer overDueDays;

	private Integer overDueDaysCount;

	private Boolean isPaymentDone;

	private VoucherCategoryType voucherCategoryType;

	private String invoiceNo;

	private Boolean isOverdueForDebit;

	private Map<Long, Double> paymentTransactionIds;

	private Double dueAmount;

	private Double remainingSettledAmount;

	private Integer voucherTemplateCategoryId;

	private Long accountCodeId;

	private Long accountTransactionTypeId;

	private String accountCodeName;
	private String accountTransactionTypeName;

	private AccountFetchResponse sourceAccount;
	private AccountFetchResponse targetAccount;

	private Map<String, String> sourceAccountCustomField;
	private Map<String, String> targetAccountCustomField;
	private List<SubNarrationDto> subNarrations;

	private Long cancledTxnId;
	private Boolean isCancledTransaction;

	private String originalSourceAccountName;

	private String originalTargetAccountName;

	public AccountTransactionFetchResponse() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getTxnId() {
		return txnId;
	}

	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}

	public Integer getSourceAccountId() {
		return sourceAccountId;
	}

	public void setSourceAccountId(Integer sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	public String getSourceAccountName() {
		return sourceAccountName;
	}

	public void setSourceAccountName(String sourceAccountName) {
		this.sourceAccountName = sourceAccountName;
	}

	public String getSourceAccountCode() {
		return sourceAccountCode;
	}

	public void setSourceAccountCode(String sourceAccountCode) {
		this.sourceAccountCode = sourceAccountCode;
	}

	public Integer getSourceAccountTypeId() {
		return sourceAccountTypeId;
	}

	public void setSourceAccountTypeId(Integer sourceAccountTypeId) {
		this.sourceAccountTypeId = sourceAccountTypeId;
	}

	public String getSourceAccountTypeName() {
		return sourceAccountTypeName;
	}

	public void setSourceAccountTypeName(String sourceAccountTypeName) {
		this.sourceAccountTypeName = sourceAccountTypeName;
	}

	public Integer getTargetAccountId() {
		return targetAccountId;
	}

	public void setTargetAccountId(Integer targetAccountId) {
		this.targetAccountId = targetAccountId;
	}

	public String getTargetAccountName() {
		return targetAccountName;
	}

	public void setTargetAccountName(String targetAccountName) {
		this.targetAccountName = targetAccountName;
	}

	public String getTargetAccountCode() {
		return targetAccountCode;
	}

	public void setTargetAccountCode(String targetAccountCode) {
		this.targetAccountCode = targetAccountCode;
	}

	public Integer getTargetAccountTypeId() {
		return targetAccountTypeId;
	}

	public void setTargetAccountTypeId(Integer targetAccountTypeId) {
		this.targetAccountTypeId = targetAccountTypeId;
	}

	public String getTargetAccountTypeName() {
		return targetAccountTypeName;
	}

	public void setTargetAccountTypeName(String targetAccountTypeName) {
		this.targetAccountTypeName = targetAccountTypeName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getSourceAccountOpeningBalance() {
		return sourceAccountOpeningBalance;
	}

	public void setSourceAccountOpeningBalance(Double sourceAccountOpeningBalance) {
		this.sourceAccountOpeningBalance = sourceAccountOpeningBalance;
	}

	public String getSourceAccountOpeningBalanceTypeName() {
		return sourceAccountOpeningBalanceTypeName;
	}

	public void setSourceAccountOpeningBalanceTypeName(String sourceAccountOpeningBalanceTypeName) {
		this.sourceAccountOpeningBalanceTypeName = sourceAccountOpeningBalanceTypeName;
	}

	public Double getSourceAccountClosingBalance() {
		return sourceAccountClosingBalance;
	}

	public void setSourceAccountClosingBalance(Double sourceAccountClosingBalance) {
		this.sourceAccountClosingBalance = sourceAccountClosingBalance;
	}

	public String getSourceAccountClosingBalanceTypeName() {
		return sourceAccountClosingBalanceTypeName;
	}

	public void setSourceAccountClosingBalanceTypeName(String sourceAccountClosingBalanceTypeName) {
		this.sourceAccountClosingBalanceTypeName = sourceAccountClosingBalanceTypeName;
	}

	public Double getTargetAccountOpeningBalance() {
		return targetAccountOpeningBalance;
	}

	public void setTargetAccountOpeningBalance(Double targetAccountOpeningBalance) {
		this.targetAccountOpeningBalance = targetAccountOpeningBalance;
	}

	public String getTargetAccountOpeningBalanceTypeName() {
		return targetAccountOpeningBalanceTypeName;
	}

	public void setTargetAccountOpeningBalanceTypeName(String targetAccountOpeningBalanceTypeName) {
		this.targetAccountOpeningBalanceTypeName = targetAccountOpeningBalanceTypeName;
	}

	public Double getTargetAccountClosingBalance() {
		return targetAccountClosingBalance;
	}

	public void setTargetAccountClosingBalance(Double targetAccountClosingBalance) {
		this.targetAccountClosingBalance = targetAccountClosingBalance;
	}

	public String getTargetAccountClosingBalanceTypeName() {
		return targetAccountClosingBalanceTypeName;
	}

	public void setTargetAccountClosingBalanceTypeName(String targetAccountClosingBalanceTypeName) {
		this.targetAccountClosingBalanceTypeName = targetAccountClosingBalanceTypeName;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Boolean getIsReverted() {
		return isReverted;
	}

	public void setIsReverted(Boolean isReverted) {
		this.isReverted = isReverted;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Integer getVoucherTemplateId() {
		return voucherTemplateId;
	}

	public void setVoucherTemplateId(Integer voucherTemplateId) {
		this.voucherTemplateId = voucherTemplateId;
	}

	public Object getJsonData() {
		return jsonData;
	}

	public void setJsonData(Object jsonData) {
		this.jsonData = jsonData;
	}

	public Boolean getIsTemporary() {
		return isTemporary;
	}

	public void setIsTemporary(Boolean isTemporary) {
		this.isTemporary = isTemporary;
	}

	public Boolean getIsInterSite() {
		return isInterSite;
	}

	public void setIsInterSite(Boolean isInterSite) {
		this.isInterSite = isInterSite;
	}

	public Integer getSecondarySiteId() {
		return secondarySiteId;
	}

	public void setSecondarySiteId(Integer secondarySiteId) {
		this.secondarySiteId = secondarySiteId;
	}

	public Boolean getIsCashTransaction() {
		return isCashTransaction;
	}

	public void setIsCashTransaction(Boolean isCashTransaction) {
		this.isCashTransaction = isCashTransaction;
	}

	public Boolean getIsIgstTransaction() {
		return isIgstTransaction;
	}

	public void setIsIgstTransaction(Boolean isIgstTransaction) {
		this.isIgstTransaction = isIgstTransaction;
	}

	public Boolean getIsCgstTransaction() {
		return isCgstTransaction;
	}

	public void setIsCgstTransaction(Boolean isCgstTransaction) {
		this.isCgstTransaction = isCgstTransaction;
	}

	public Boolean getIsSgstTransaction() {
		return isSgstTransaction;
	}

	public void setIsSgstTransaction(Boolean isSgstTransaction) {
		this.isSgstTransaction = isSgstTransaction;
	}

	public Boolean getIsTdsTransaction() {
		return isTdsTransaction;
	}

	public void setIsTdsTransaction(Boolean isTdsTransaction) {
		this.isTdsTransaction = isTdsTransaction;
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

	public String getSourceAccountNarration() {
		return sourceAccountNarration;
	}

	public void setSourceAccountNarration(String sourceAccountNarration) {
		this.sourceAccountNarration = sourceAccountNarration;
	}

	public String getTargetAccountNarration() {
		return targetAccountNarration;
	}

	public void setTargetAccountNarration(String targetAccountNarration) {
		this.targetAccountNarration = targetAccountNarration;
	}

	public Integer getOverDueDays() {
		return overDueDays;
	}

	public void setOverDueDays(Integer overDueDays) {
		this.overDueDays = overDueDays;
	}

	public Integer getOverDueDaysCount() {
		return overDueDaysCount;
	}

	public void setOverDueDaysCount(Integer overDueDaysCount) {
		this.overDueDaysCount = overDueDaysCount;
	}

	public Boolean getIsPaymentDone() {
		return isPaymentDone;
	}

	public void setIsPaymentDone(Boolean isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}

	public VoucherCategoryType getVoucherCategoryType() {
		return voucherCategoryType;
	}

	public void setVoucherCategoryType(VoucherCategoryType voucherCategoryType) {
		this.voucherCategoryType = voucherCategoryType;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Boolean getIsOverdueForDebit() {
		return isOverdueForDebit;
	}

	public void setIsOverdueForDebit(Boolean isOverdueForDebit) {
		this.isOverdueForDebit = isOverdueForDebit;
	}

	public Map<Long, Double> getPaymentTransactionIds() {
		return paymentTransactionIds;
	}

	public void setPaymentTransactionIds(Map<Long, Double> paymentTransactionIds) {
		this.paymentTransactionIds = paymentTransactionIds;
	}

	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public Double getRemainingSettledAmount() {
		return remainingSettledAmount;
	}

	public void setRemainingSettledAmount(Double remainingSettledAmount) {
		this.remainingSettledAmount = remainingSettledAmount;
	}

	public Integer getVoucherTemplateCategoryId() {
		return voucherTemplateCategoryId;
	}

	public void setVoucherTemplateCategoryId(Integer voucherTemplateCategoryId) {
		this.voucherTemplateCategoryId = voucherTemplateCategoryId;
	}

	public Long getAccountCodeId() {
		return accountCodeId;
	}

	public void setAccountCodeId(Long accountCodeId) {
		this.accountCodeId = accountCodeId;
	}

	public Long getAccountTransactionTypeId() {
		return accountTransactionTypeId;
	}

	public void setAccountTransactionTypeId(Long accountTransactionTypeId) {
		this.accountTransactionTypeId = accountTransactionTypeId;
	}

	public String getAccountCodeName() {
		return accountCodeName;
	}

	public void setAccountCodeName(String accountCodeName) {
		this.accountCodeName = accountCodeName;
	}

	public String getAccountTransactionTypeName() {
		return accountTransactionTypeName;
	}

	public void setAccountTransactionTypeName(String accountTransactionTypeName) {
		this.accountTransactionTypeName = accountTransactionTypeName;
	}

	public AccountFetchResponse getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(AccountFetchResponse sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	public AccountFetchResponse getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(AccountFetchResponse targetAccount) {
		this.targetAccount = targetAccount;
	}

	public Map<String, String> getSourceAccountCustomField() {
		return sourceAccountCustomField;
	}

	public void setSourceAccountCustomField(Map<String, String> sourceAccountCustomField) {
		this.sourceAccountCustomField = sourceAccountCustomField;
	}

	public Map<String, String> getTargetAccountCustomField() {
		return targetAccountCustomField;
	}

	public void setTargetAccountCustomField(Map<String, String> targetAccountCustomField) {
		this.targetAccountCustomField = targetAccountCustomField;
	}

	public List<SubNarrationDto> getSubNarrations() {
		return subNarrations;
	}

	public void setSubNarrations(List<SubNarrationDto> subNarrations) {
		this.subNarrations = subNarrations;
	}

	public Long getCancledTxnId() {
		return cancledTxnId;
	}

	public void setCancledTxnId(Long cancledTxnId) {
		this.cancledTxnId = cancledTxnId;
	}

	public Boolean getIsCancledTransaction() {
		return isCancledTransaction;
	}

	public void setIsCancledTransaction(Boolean isCancledTransaction) {
		this.isCancledTransaction = isCancledTransaction;
	}

	public String getOriginalSourceAccountName() {
		return originalSourceAccountName;
	}

	public void setOriginalSourceAccountName(String originalSourceAccountName) {
		this.originalSourceAccountName = originalSourceAccountName;
	}

	public String getOriginalTargetAccountName() {
		return originalTargetAccountName;
	}

	public void setOriginalTargetAccountName(String originalTargetAccountName) {
		this.originalTargetAccountName = originalTargetAccountName;
	}

}
