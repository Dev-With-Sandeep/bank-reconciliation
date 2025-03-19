package nyggs.accounts.reconciliation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import nyggs.accounts.reconciliation.enums.AccountTypeEnum;

@Entity
@Table(name = "acc_account_type")
public class AccountType {

	private Integer id;

	private Integer parentId;

	private String name;

	private Boolean legalDocumentRequired;

	private AccountTypeEnum type;

	private Boolean isKycRequired;

	private Boolean transactionEnabledWithoutKyc;

	private Integer natureId;

	private Integer companyId;

	private Boolean isActive;

	private Date updatedOn;

	private Integer updatedBy;

	private Boolean isChildAccountCreationNeeded;
	
	private Boolean isAccountNumberingDone;
	
	private Integer rangeLimitUsed;
	
	private Boolean isEInvoiceDetailMandatory;


	public AccountType() {
		super();
	}

	public AccountType(Integer id, Integer parentId, String name, Boolean legalDocumentRequired, AccountTypeEnum type,
			Boolean isKycRequired, Boolean transactionEnabledWithoutKyc, Integer natureId, Integer companyId,
			Boolean isActive, Date updatedOn, Integer updatedBy) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.legalDocumentRequired = legalDocumentRequired;
		this.type = type;
		this.isKycRequired = isKycRequired;
		this.transactionEnabledWithoutKyc = transactionEnabledWithoutKyc;
		this.natureId = natureId;
		this.companyId = companyId;
		this.isActive = isActive;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}
	
	

	public AccountType(Integer id, Integer parentId, String name, Boolean legalDocumentRequired, AccountTypeEnum type,
			Boolean isKycRequired, Boolean transactionEnabledWithoutKyc, Integer natureId, Integer companyId,
			Boolean isActive, Date updatedOn, Integer updatedBy, Boolean isEInvoiceDetailMandatory) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.legalDocumentRequired = legalDocumentRequired;
		this.type = type;
		this.isKycRequired = isKycRequired;
		this.transactionEnabledWithoutKyc = transactionEnabledWithoutKyc;
		this.natureId = natureId;
		this.companyId = companyId;
		this.isActive = isActive;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.isEInvoiceDetailMandatory = isEInvoiceDetailMandatory;
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

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "legal_document_required")
	public Boolean getLegalDocumentRequired() {
		return legalDocumentRequired;
	}

	public void setLegalDocumentRequired(Boolean legalDocumentRequired) {
		this.legalDocumentRequired = legalDocumentRequired;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "type")
	public AccountTypeEnum getType() {
		return type;
	}

	public void setType(AccountTypeEnum type) {
		this.type = type;
	}

	@Column(name = "is_kyc_required")
	public Boolean getIsKycRequired() {
		return isKycRequired;
	}

	public void setIsKycRequired(Boolean isKycRequired) {
		this.isKycRequired = isKycRequired;
	}

	@Column(name = "transaction_enabled_without_kyc")
	public Boolean getTransactionEnabledWithoutKyc() {
		return transactionEnabledWithoutKyc;
	}

	public void setTransactionEnabledWithoutKyc(Boolean transactionEnabledWithoutKyc) {
		this.transactionEnabledWithoutKyc = transactionEnabledWithoutKyc;
	}

	@Column(name = "nature_id")
	public Integer getNatureId() {
		return natureId;
	}

	public void setNatureId(Integer natureId) {
		this.natureId = natureId;
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

	@Column(name = "is_child_account_creation_needed")
	public Boolean getIsChildAccountCreationNeeded() {
		return isChildAccountCreationNeeded;
	}

	public void setIsChildAccountCreationNeeded(Boolean isChildAccountCreationNeeded) {
		this.isChildAccountCreationNeeded = isChildAccountCreationNeeded;
	}

	@Column(name = "is_account_numbering_done")
	public Boolean getIsAccountNumberingDone() {
		return isAccountNumberingDone;
	}

	public void setIsAccountNumberingDone(Boolean isAccountNumberingDone) {
		this.isAccountNumberingDone = isAccountNumberingDone;
	}

	@Column(name = "range_limit_used")
	public Integer getRangeLimitUsed() {
		return rangeLimitUsed;
	}

	public void setRangeLimitUsed(Integer rangeLimitUsed) {
		this.rangeLimitUsed = rangeLimitUsed;
	}

	@Column(name = "is_e_invoice_detail_mandatory")
	public Boolean getIsEInvoiceDetailMandatory() {
		return isEInvoiceDetailMandatory;
	}

	public void setIsEInvoiceDetailMandatory(Boolean isEInvoiceDetailMandatory) {
		this.isEInvoiceDetailMandatory = isEInvoiceDetailMandatory;
	}
	
}
