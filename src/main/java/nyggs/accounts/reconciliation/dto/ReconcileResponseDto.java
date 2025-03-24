package nyggs.accounts.reconciliation.dto;

import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import nyggs.accounts.reconciliation.enums.TransactionType;

@JsonInclude(Include.NON_NULL)
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReconcileResponseDto {

	private Long id;

	private String accountName;

	private Integer accountId;

	private String accountNumber;

	private Double withDrawalAmount;

	private Double depositAmount;

	private Double balance;

	private String transactionType;

	private String dayBookTxnId;

	private String bankTxnId;

	private Long bankStatementId;

	private String status;

	private String description;

	private Date txnDate;

	private Boolean isActive;

	private BankStatementDto bankStatementDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDayBookTxnId() {
		return dayBookTxnId;
	}

	public void setDayBookTxnId(String dayBookTxnId) {
		this.dayBookTxnId = dayBookTxnId;
	}

	public String getBankTxnId() {
		return bankTxnId;
	}

	public void setBankTxnId(String bankTxnId) {
		this.bankTxnId = bankTxnId;
	}

	public Long getBankStatementId() {
		return bankStatementId;
	}

	public void setBankStatementId(Long bankStatementId) {
		this.bankStatementId = bankStatementId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getWithDrawalAmount() {
		return withDrawalAmount;
	}

	public void setWithDrawalAmount(Double withDrawalAmount) {
		this.withDrawalAmount = withDrawalAmount;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public BankStatementDto getBankStatementDto() {
		return bankStatementDto;
	}

	public void setBankStatementDto(BankStatementDto bankStatementDto) {
		this.bankStatementDto = bankStatementDto;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public ReconcileResponseDto() {
		super();
	}

}
