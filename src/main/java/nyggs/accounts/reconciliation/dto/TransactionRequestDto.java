package nyggs.accounts.reconciliation.dto;

import java.util.Date;
import java.util.List;

public class TransactionRequestDto {

	private List<Integer> accountIds;

	private Date fromDate;

	private Date toDate;

	public List<Integer> getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(List<Integer> accountIds) {
		this.accountIds = accountIds;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public TransactionRequestDto(List<Integer> accountIds, Date fromDate, Date toDate) {
		super();
		this.accountIds = accountIds;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public TransactionRequestDto() {
		super();
	}
}
