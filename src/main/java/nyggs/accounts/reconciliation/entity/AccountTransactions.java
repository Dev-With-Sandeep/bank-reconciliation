package nyggs.accounts.reconciliation.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonType;

import nyggs.accounts.reconciliation.enums.BalanceProcessStatus;
import nyggs.accounts.reconciliation.enums.PaymentStatus;
import nyggs.accounts.reconciliation.enums.VoucherCategoryType;
import nyggs.accounts.reconciliation.util.PaymentTransactionIdsConverter;

@TypeDef(name = "json", typeClass = JsonType.class)
@Entity
@Table(name = "acc_account_transaction")
public class AccountTransactions {

	private Long id;

	private Date date;

	private Long txnId;

	private Integer sourceAccountId;

	private Integer targetAccountId;

	private Double amount;

	private Double sourceAccountOpeningBalance;

	private Double sourceAccountClosingBalance;

	private Double targetAccountOpeningBalance;

	private Double targetAccountClosingBalance;

	private String referenceNo;

	private String voucherNo;

	private String remarks;

	private Integer siteId;

	private Integer companyId;

	private Boolean isReverted;

	private Boolean isActive;

	private Date createdOn;

	private Long createdOnInMs;

	private Integer createdBy;

	private String createdByName;

	private Accounts sourceAccount;

	private Accounts targetAccount;

	private Boolean isTemporary;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "financial_year_id")
	private FinancialYear financialYear;

	private Boolean isHavingVoucher;

	private Integer voucherTemplateTypeId;

	private Integer plateformId;

	private Long otherPlateformTransactionId;

	private String formData;

	private Double sourceAccountOpeningDebitBalance;

	private Double sourceAccountTotalDebitBalance;

	private Double sourceAccountOpeningCreditBalance;

	private Double sourceAccountTotalCreditBalance;

	private Double targetAccountOpeningDebitBalance;

	private Double targetAccountTotalDebitBalance;

	private Double targetAccountOpeningCreditBalance;

	private Double targetAccountTotalCreditBalance;

	private Double raisedRequestAmount;

	private Integer secondarySiteId;

	private Boolean isInterSite;

	private Integer transactionSerialNo;

	private Integer transactionInfoSerialNo;

	private Boolean isMasterTransaction;

	private Boolean isCashTransaction;

	private Boolean isIgstTransaction;

	private Boolean isCgstTransaction;

	private Boolean isSgstTransaction;

	private Boolean isTdsTransaction;

	private Integer permissionStateId;

	private Boolean isAvailable;

	@Transient
	private Boolean isApprovedBalance;

	private Long narrationMasterId;

	private String sourceAccountNarration;

	private String targetAccountNarration;

	private Integer version;

	private BalanceProcessStatus balanceProcessStatus;

	private String processedFailedRemark;

	private Integer dueDaysCount;

	private Integer overDueDaysCount;

	private Date dueDate;

	private Date overDueDate;

	private Boolean isDue;

	private Boolean isOverDue;

	private Boolean isPaymentDone;

	private VoucherCategoryType voucherCategoryType;

	private String invoiceNo;

	private Boolean isOverdueForDebit;

	// private String paymentTransactionIds;

	private Map<Long, Double> paymentTransactionIds;

	private Double dueAmount;

	private Double remainingSettledAmount;

	private PaymentStatus paymentStatus;

	private Integer voucherTemplateCategoryId;

	private Long accountCodeId;

	private Long accountTransactionTypeId;

	private Integer typeId;

	@JsonIgnore
	private List<SubNarration> subNarrations;

	private Boolean isCancledTransaction;

	private Long cancledMasterTxnId;

	private Integer originalSourceAccountId;

	private Integer originalTargetAccountId;

	private Boolean isInvoiceGenerated;

	private String eInvoiceDetail;

	private String eInvoiceErrorDetail;

	private String irn;

	private String invoiceStatus;

	private Boolean isEwayBillGenerated;

	private Boolean isNewAdded;

	private Boolean isTxnDeleted;

	private Double tempBalance;

	private Boolean isUpdatedBalance;

	private List<Long> tagIds;

	public AccountTransactions() {
		super();
	}

	public AccountTransactions(Long id, Date date, Long txnId, Integer sourceAccountId, Integer targetAccountId,
			Double amount, Double sourceAccountOpeningBalance, Double sourceAccountClosingBalance,
			Double targetAccountOpeningBalance, Double targetAccountClosingBalance, String referenceNo,
			String voucherNo, String remarks, Integer siteId, Integer companyId, Boolean isReverted, Boolean isActive,
			Date createdOn, Long createdOnInMs, Integer createdBy, String createdByName) {
		super();
		this.id = id;
		this.date = date;
		this.txnId = txnId;
		this.sourceAccountId = sourceAccountId;
		this.targetAccountId = targetAccountId;
		this.amount = amount;
		this.sourceAccountOpeningBalance = sourceAccountOpeningBalance;
		this.sourceAccountClosingBalance = sourceAccountClosingBalance;
		this.targetAccountOpeningBalance = targetAccountOpeningBalance;
		this.targetAccountClosingBalance = targetAccountClosingBalance;
		this.referenceNo = referenceNo;
		this.voucherNo = voucherNo;
		this.remarks = remarks;
		this.siteId = siteId;
		this.companyId = companyId;
		this.isReverted = isReverted;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.createdOnInMs = createdOnInMs;
		this.createdBy = createdBy;
		this.createdByName = createdByName;
	}

	public AccountTransactions(Long id, Date date, Long txnId, Integer sourceAccountId, Integer targetAccountId,
			Double amount, Double sourceAccountOpeningBalance, Double sourceAccountClosingBalance,
			Double targetAccountOpeningBalance, Double targetAccountClosingBalance, String referenceNo,
			String voucherNo, String remarks, Integer siteId, Integer companyId, Boolean isReverted, Boolean isActive,
			Date createdOn, Long createdOnInMs, Integer createdBy, String createdByName, FinancialYear financialYear) {
		super();
		this.id = id;
		this.date = date;
		this.txnId = txnId;
		this.sourceAccountId = sourceAccountId;
		this.targetAccountId = targetAccountId;
		this.amount = amount;
		this.sourceAccountOpeningBalance = sourceAccountOpeningBalance;
		this.sourceAccountClosingBalance = sourceAccountClosingBalance;
		this.targetAccountOpeningBalance = targetAccountOpeningBalance;
		this.targetAccountClosingBalance = targetAccountClosingBalance;
		this.referenceNo = referenceNo;
		this.voucherNo = voucherNo;
		this.remarks = remarks;
		this.siteId = siteId;
		this.companyId = companyId;
		this.isReverted = isReverted;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.createdOnInMs = createdOnInMs;
		this.createdBy = createdBy;
		this.createdByName = createdByName;
		this.financialYear = financialYear;
	}

	public AccountTransactions(Long id, Date date, Long txnId, Integer sourceAccountId, Integer targetAccountId,
			Double amount, Double sourceAccountOpeningBalance, Double sourceAccountClosingBalance,
			Double targetAccountOpeningBalance, Double targetAccountClosingBalance, String referenceNo,
			String voucherNo, String remarks, Integer siteId, Integer companyId, Boolean isReverted, Boolean isActive,
			Date createdOn, Long createdOnInMs, Integer createdBy, String createdByName, FinancialYear financialYear,
			Integer voucherTemplateTypeId, String formData, Boolean isTemporary) {
		super();
		this.id = id;
		this.date = date;
		this.txnId = txnId;
		this.sourceAccountId = sourceAccountId;
		this.targetAccountId = targetAccountId;
		this.amount = amount;
		this.sourceAccountOpeningBalance = sourceAccountOpeningBalance;
		this.sourceAccountClosingBalance = sourceAccountClosingBalance;
		this.targetAccountOpeningBalance = targetAccountOpeningBalance;
		this.targetAccountClosingBalance = targetAccountClosingBalance;
		this.referenceNo = referenceNo;
		this.voucherNo = voucherNo;
		this.remarks = remarks;
		this.siteId = siteId;
		this.companyId = companyId;
		this.isReverted = isReverted;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.createdOnInMs = createdOnInMs;
		this.createdBy = createdBy;
		this.createdByName = createdByName;
		this.financialYear = financialYear;
		this.voucherTemplateTypeId = voucherTemplateTypeId;
		this.formData = formData;
		this.isTemporary = isTemporary;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "txn_id")
	public Long getTxnId() {
		return txnId;
	}

	public void setTxnId(Long txnId) {
		this.txnId = txnId;
	}

	@Column(name = "debit_account_id")
	public Integer getSourceAccountId() {
		return sourceAccountId;
	}

	public void setSourceAccountId(Integer sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	@Column(name = "credit_account_id")
	public Integer getTargetAccountId() {
		return targetAccountId;
	}

	public void setTargetAccountId(Integer targetAccountId) {
		this.targetAccountId = targetAccountId;
	}

	@Column(name = "amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "debit_account_opening_balance")
	public Double getSourceAccountOpeningBalance() {
		return sourceAccountOpeningBalance;
	}

	public void setSourceAccountOpeningBalance(Double sourceAccountOpeningBalance) {
		this.sourceAccountOpeningBalance = sourceAccountOpeningBalance;
	}

	@Column(name = "debit_account_closing_balance")
	public Double getSourceAccountClosingBalance() {
		return sourceAccountClosingBalance;
	}

	public void setSourceAccountClosingBalance(Double sourceAccountClosingBalance) {
		this.sourceAccountClosingBalance = sourceAccountClosingBalance;
	}

	@Column(name = "credit_account_opening_balance")
	public Double getTargetAccountOpeningBalance() {
		return targetAccountOpeningBalance;
	}

	public void setTargetAccountOpeningBalance(Double targetAccountOpeningBalance) {
		this.targetAccountOpeningBalance = targetAccountOpeningBalance;
	}

	@Column(name = "credit_account_closing_balance")
	public Double getTargetAccountClosingBalance() {
		return targetAccountClosingBalance;
	}

	public void setTargetAccountClosingBalance(Double targetAccountClosingBalance) {
		this.targetAccountClosingBalance = targetAccountClosingBalance;
	}

	@Column(name = "reference_no")
	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	@Column(name = "voucher_no")
	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "site_id")
	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	@Column(name = "company_id")
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Column(name = "is_reverted")
	public Boolean getIsReverted() {
		return isReverted;
	}

	public void setIsReverted(Boolean isReverted) {
		this.isReverted = isReverted;
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

	@Column(name = "created_on_in_ms")
	public Long getCreatedOnInMs() {
		return createdOnInMs;
	}

	public void setCreatedOnInMs(Long createdOnInMs) {
		this.createdOnInMs = createdOnInMs;
	}

	@Column(name = "created_by")
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_by_name")
	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	@OneToOne
	@JoinColumn(name = "debit_account_id", insertable = false, updatable = false)
	public Accounts getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(Accounts sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	@OneToOne
	@JoinColumn(name = "credit_account_id", insertable = false, updatable = false)
	public Accounts getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(Accounts targetAccount) {
		this.targetAccount = targetAccount;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "financial_year_id")
	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}

	@Column(name = "is_having_voucher")
	public Boolean getIsHavingVoucher() {
		return isHavingVoucher;
	}

	public void setIsHavingVoucher(Boolean isHavingVoucher) {
		this.isHavingVoucher = isHavingVoucher;
	}

	@Column(name = "voucher_template_type_id")
	public Integer getVoucherTemplateTypeId() {
		return voucherTemplateTypeId;
	}

	public void setVoucherTemplateTypeId(Integer voucherTemplateTypeId) {
		this.voucherTemplateTypeId = voucherTemplateTypeId;
	}

	@Column(name = "form_data")
	public String getFormData() {
		return formData;
	}

	public void setFormData(String formData) {
		this.formData = formData;
	}

	@Column(name = "source_account_opening_debit_balance")
	public Double getSourceAccountOpeningDebitBalance() {
		return sourceAccountOpeningDebitBalance;
	}

	public void setSourceAccountOpeningDebitBalance(Double sourceAccountOpeningDebitBalance) {
		this.sourceAccountOpeningDebitBalance = sourceAccountOpeningDebitBalance;
	}

	@Column(name = "source_account_total_debit_balance")
	public Double getSourceAccountTotalDebitBalance() {
		return sourceAccountTotalDebitBalance;
	}

	public void setSourceAccountTotalDebitBalance(Double sourceAccountTotalDebitBalance) {
		this.sourceAccountTotalDebitBalance = sourceAccountTotalDebitBalance;
	}

	@Column(name = "source_account_opening_credit_balance")
	public Double getSourceAccountOpeningCreditBalance() {
		return sourceAccountOpeningCreditBalance;
	}

	public void setSourceAccountOpeningCreditBalance(Double sourceAccountOpeningCreditBalance) {
		this.sourceAccountOpeningCreditBalance = sourceAccountOpeningCreditBalance;
	}

	@Column(name = "source_account_total_credit_balance")
	public Double getSourceAccountTotalCreditBalance() {
		return sourceAccountTotalCreditBalance;
	}

	public void setSourceAccountTotalCreditBalance(Double sourceAccountTotalCreditBalance) {
		this.sourceAccountTotalCreditBalance = sourceAccountTotalCreditBalance;
	}

	@Column(name = "target_account_opening_debit_balance")
	public Double getTargetAccountOpeningDebitBalance() {
		return targetAccountOpeningDebitBalance;
	}

	public void setTargetAccountOpeningDebitBalance(Double targetAccountOpeningDebitBalance) {
		this.targetAccountOpeningDebitBalance = targetAccountOpeningDebitBalance;
	}

	@Column(name = "target_account_total_debit_balance")
	public Double getTargetAccountTotalDebitBalance() {
		return targetAccountTotalDebitBalance;
	}

	public void setTargetAccountTotalDebitBalance(Double targetAccountTotalDebitBalance) {
		this.targetAccountTotalDebitBalance = targetAccountTotalDebitBalance;
	}

	@Column(name = "target_account_opening_credit_balance")
	public Double getTargetAccountOpeningCreditBalance() {
		return targetAccountOpeningCreditBalance;
	}

	public void setTargetAccountOpeningCreditBalance(Double targetAccountOpeningCreditBalance) {
		this.targetAccountOpeningCreditBalance = targetAccountOpeningCreditBalance;
	}

	@Column(name = "target_account_total_credit_balance")

	public Double getTargetAccountTotalCreditBalance() {
		return targetAccountTotalCreditBalance;
	}

	public void setTargetAccountTotalCreditBalance(Double targetAccountTotalCreditBalance) {
		this.targetAccountTotalCreditBalance = targetAccountTotalCreditBalance;
	}

	@Column(name = "is_temporary")
	public Boolean getIsTemporary() {
		return isTemporary;
	}

	public void setIsTemporary(Boolean temporary) {
		isTemporary = temporary;
	}

	@Column(name = "raised_request_amount")
	public Double getRaisedRequestAmount() {
		return raisedRequestAmount;
	}

	public void setRaisedRequestAmount(Double raisedRequestAmount) {
		this.raisedRequestAmount = raisedRequestAmount;
	}

	@Column(name = "secondary_site_id")
	public Integer getSecondarySiteId() {
		return secondarySiteId;
	}

	public void setSecondarySiteId(Integer secondarySiteId) {
		this.secondarySiteId = secondarySiteId;
	}

	@Column(name = "is_inter_site")
	public Boolean getIsInterSite() {
		return isInterSite;
	}

	public void setIsInterSite(Boolean interSite) {
		this.isInterSite = interSite;
	}

	@Column(name = "transaction_serial_no")
	public Integer getTransactionSerialNo() {
		return transactionSerialNo;
	}

	public void setTransactionSerialNo(Integer transactionSerialNo) {
		this.transactionSerialNo = transactionSerialNo;
	}

	@Column(name = "is_master_transaction")
	public Boolean getIsMasterTransaction() {
		return isMasterTransaction;
	}

	public void setIsMasterTransaction(Boolean isMasterTransaction) {
		this.isMasterTransaction = isMasterTransaction;
	}

	@Column(name = "is_cash_transaction")
	public Boolean getIsCashTransaction() {
		return isCashTransaction;
	}

	@Column(name = "is_igst_transaction")
	public Boolean getIsIgstTransaction() {
		return isIgstTransaction;
	}

	@Column(name = "is_cgst_transaction")
	public Boolean getIsCgstTransaction() {
		return isCgstTransaction;
	}

	@Column(name = "is_sgst_transaction")
	public Boolean getIsSgstTransaction() {
		return isSgstTransaction;
	}

	@Column(name = "is_tds_transaction")
	public Boolean getIsTdsTransaction() {
		return isTdsTransaction;
	}

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

	@Column(name = "is_invoice_generated")
	public Boolean getIsInvoiceGenerated() {
		return isInvoiceGenerated;
	}

	public void setIsInvoiceGenerated(Boolean invoiceGenerated) {
		isInvoiceGenerated = invoiceGenerated;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public void setIsCashTransaction(Boolean isCashTransaction) {
		this.isCashTransaction = isCashTransaction;
	}

	public void setIsIgstTransaction(Boolean isIgstTransaction) {
		this.isIgstTransaction = isIgstTransaction;
	}

	public void setIsCgstTransaction(Boolean isCgstTransaction) {
		this.isCgstTransaction = isCgstTransaction;
	}

	public void setIsSgstTransaction(Boolean isSgstTransaction) {
		this.isSgstTransaction = isSgstTransaction;
	}

	public void setIsTdsTransaction(Boolean isTdsTransaction) {
		this.isTdsTransaction = isTdsTransaction;
	}

	@Transient
	public Boolean getIsApprovedBalance() {
		return isApprovedBalance;
	}

	public void setIsApprovedBalance(Boolean isApprovedBalance) {
		this.isApprovedBalance = isApprovedBalance;
	}

	@Column(name = "transaction_info_serial_no")
	public Integer getTransactionInfoSerialNo() {
		return transactionInfoSerialNo;
	}

	public void setTransactionInfoSerialNo(Integer transactionInfoSerialNo) {
		this.transactionInfoSerialNo = transactionInfoSerialNo;
	}

	@Column(name = "narration_master_id")
	public Long getNarrationMasterId() {
		return narrationMasterId;
	}

	public void setNarrationMasterId(Long narrationMasterId) {
		this.narrationMasterId = narrationMasterId;
	}

	@Column(name = "other_plateform_id")
	public Integer getPlateformId() {
		return plateformId;
	}

	public void setPlateformId(Integer plateformId) {
		this.plateformId = plateformId;
	}

	@Column(name = "other_plateform_transaction_id")
	public Long getOtherPlateformTransactionId() {
		return otherPlateformTransactionId;
	}

	public void setOtherPlateformTransactionId(Long otherPlateformTransactionId) {
		this.otherPlateformTransactionId = otherPlateformTransactionId;
	}

	@Column(name = "source_account_narration")
	public String getSourceAccountNarration() {
		return sourceAccountNarration;
	}

	public void setSourceAccountNarration(String sourceAccountNarration) {
		this.sourceAccountNarration = sourceAccountNarration;
	}

	@Column(name = "target_account_narration")
	public String getTargetAccountNarration() {
		return targetAccountNarration;
	}

	public void setTargetAccountNarration(String targetAccountNarration) {
		this.targetAccountNarration = targetAccountNarration;
	}

	@Column(name = "version")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "processed_failed_remarks")
	public String getProcessedFailedRemark() {
		return processedFailedRemark;
	}

	@Column(name = "balance_process_status")
	public BalanceProcessStatus getBalanceProcessStatus() {
		return balanceProcessStatus;
	}

	public void setBalanceProcessStatus(BalanceProcessStatus balanceProcessStatus) {
		this.balanceProcessStatus = balanceProcessStatus;
	}

	public void setProcessedFailedRemark(String processedFailedRemark) {
		this.processedFailedRemark = processedFailedRemark;
	}

	@Column(name = "due_days_count")
	public Integer getDueDaysCount() {
		return dueDaysCount;
	}

	public void setDueDaysCount(Integer dueDaysCount) {
		this.dueDaysCount = dueDaysCount;
	}

	@Column(name = "over_due_days_count")
	public Integer getOverDueDaysCount() {
		return overDueDaysCount;
	}

	@Column(name = "due_date")

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name = "over_due_date")
	public Date getOverDueDate() {
		return overDueDate;
	}

	public void setOverDueDate(Date overDueDate) {
		this.overDueDate = overDueDate;
	}

	public void setOverDueDaysCount(Integer overDueDaysCount) {
		this.overDueDaysCount = overDueDaysCount;
	}

	@Column(name = "is_due")
	public Boolean getIsDue() {
		return isDue;
	}

	public void setIsDue(Boolean isDue) {
		this.isDue = isDue;
	}

	@Column(name = "is_over_due")
	public Boolean getIsOverDue() {
		return isOverDue;
	}

	public void setIsOverDue(Boolean isOverDue) {
		this.isOverDue = isOverDue;
	}

	@Column(name = "is_payment_done")
	public Boolean getIsPaymentDone() {
		return isPaymentDone;
	}

	public void setIsPaymentDone(Boolean isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}

	@Column(name = "voucher_category_type")
	public VoucherCategoryType getVoucherCategoryType() {
		return voucherCategoryType;
	}

	public void setVoucherCategoryType(VoucherCategoryType voucherCategoryType) {
		this.voucherCategoryType = voucherCategoryType;
	}

	@Column(name = "invoice_number")
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name = "is_overdue_for_debit")
	public Boolean getIsOverdueForDebit() {
		return isOverdueForDebit;
	}

	public void setIsOverdueForDebit(Boolean isOverdueForDebit) {
		this.isOverdueForDebit = isOverdueForDebit;
	}

	@Convert(converter = PaymentTransactionIdsConverter.class)
	@Column(name = "payment_transaction_ids")
	public Map<Long, Double> getPaymentTransactionIds() {
		return paymentTransactionIds;
	}

	public void setPaymentTransactionIds(Map<Long, Double> paymentTransactionIds) {
		this.paymentTransactionIds = paymentTransactionIds;
	}

	@Column(name = "due_amount")
	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	@Column(name = "remaining_settled_amount")
	public Double getRemainingSettledAmount() {
		return remainingSettledAmount;
	}

	public void setRemainingSettledAmount(Double remainingSettledAmount) {
		this.remainingSettledAmount = remainingSettledAmount;
	}

	@Column(name = "payment_status")
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Column(name = "voucher_template_category_id")
	public Integer getVoucherTemplateCategoryId() {
		return voucherTemplateCategoryId;
	}

	public void setVoucherTemplateCategoryId(Integer voucherTemplateCategoryId) {
		this.voucherTemplateCategoryId = voucherTemplateCategoryId;
	}

	@Column(name = "account_code_id")
	public Long getAccountCodeId() {
		return accountCodeId;
	}

	@Column(name = "account_transaction_type_id")
	public Long getAccountTransactionTypeId() {
		return accountTransactionTypeId;
	}

	public void setAccountTransactionTypeId(Long accountTransactionTypeId) {
		this.accountTransactionTypeId = accountTransactionTypeId;
	}

	public void setAccountCodeId(Long accountCodeId) {
		this.accountCodeId = accountCodeId;
	}

	@Column(name = "type_id")
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@OneToMany(mappedBy = "accountTransaction", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	public List<SubNarration> getSubNarrations() {
		return subNarrations;
	}

	public void setSubNarrations(List<SubNarration> subNarrations) {
		this.subNarrations = subNarrations;
	}

	@Column(name = "is_cancled_transaction")
	public Boolean getIsCancledTransaction() {
		return isCancledTransaction;
	}

	public void setIsCancledTransaction(Boolean isCancledTransaction) {
		this.isCancledTransaction = isCancledTransaction;
	}

	@Column(name = "cancled_master_txn_id")
	public Long getCancledMasterTxnId() {
		return cancledMasterTxnId;
	}

	public void setCancledMasterTxnId(Long cancledMasterTxnId) {
		this.cancledMasterTxnId = cancledMasterTxnId;
	}

	@Column(name = "original_source_account_id")
	public Integer getOriginalSourceAccountId() {
		return originalSourceAccountId;
	}

	public void setOriginalSourceAccountId(Integer originalSourceAccountId) {
		this.originalSourceAccountId = originalSourceAccountId;
	}

	@Column(name = "original_target_account_id")
	public Integer getOriginalTargetAccountId() {
		return originalTargetAccountId;
	}

	public void setOriginalTargetAccountId(Integer originalTargetAccountId) {
		this.originalTargetAccountId = originalTargetAccountId;
	}

	@Column(name = "e_invoice_detail")
	public String geteInvoiceDetail() {
		return eInvoiceDetail;
	}

	public void seteInvoiceDetail(String eInvoiceDetail) {
		this.eInvoiceDetail = eInvoiceDetail;
	}

	@Column(name = "e_invoice_error_detail")
	public String geteInvoiceErrorDetail() {
		return eInvoiceErrorDetail;
	}

	public void seteInvoiceErrorDetail(String eInvoiceErrorDetail) {
		this.eInvoiceErrorDetail = eInvoiceErrorDetail;
	}

	@Column(name = "irn")
	public String getIrn() {
		return irn;
	}

	public void setIrn(String irn) {
		this.irn = irn;
	}

	@Column(name = "invoice_status")
	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	@Column(name = "is_eway_bill_generated")
	public Boolean getIsEwayBillGenerated() {
		return isEwayBillGenerated;
	}

	public void setIsEwayBillGenerated(Boolean isEwayBillGenerated) {
		this.isEwayBillGenerated = isEwayBillGenerated;
	}

	public AccountTransactions(String invoiceStatus) {
		super();
		this.invoiceStatus = invoiceStatus;
	}

	@Column(name = "is_new_added")
	public Boolean getIsNewAdded() {
		return isNewAdded;
	}

	public void setIsNewAdded(Boolean isNewAdded) {
		this.isNewAdded = isNewAdded;
	}

	@Column(name = "is_txn_deleted")
	public Boolean getIsTxnDeleted() {
		return isTxnDeleted;
	}

	public void setIsTxnDeleted(Boolean isTxnDeleted) {
		this.isTxnDeleted = isTxnDeleted;
	}

	@Column(name = "temp_balance")
	public Double getTempBalance() {
		return tempBalance;
	}

	public void setTempBalance(Double tempBalance) {
		this.tempBalance = tempBalance;
	}

	@Column(name = "is_updated_balance")
	public Boolean getIsUpdatedBalance() {
		return isUpdatedBalance;
	}

	public void setIsUpdatedBalance(Boolean isUpdatedBalance) {
		this.isUpdatedBalance = isUpdatedBalance;
	}

	@Type(type = "json") // ✅ Correct type
	@Column(name = "tag_ids", columnDefinition = "JSON")
	public List<Long> getTagIds() {
		return tagIds;
	}

	public void setTagIds(List<Long> tagIds) {
		this.tagIds = tagIds;
	}

}
