package nyggs.accounts.reconciliation.dto;

import java.util.Date;

public class DashboardRequestDto {

	private Long accountId;

	private Integer statusId;

	private Date fromDate;

	private Date toDate;

	private Long pageNo;

	private Integer pageSize;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
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

	public Long getPageNo() {
		return pageNo;
	}

	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public DashboardRequestDto(Long accountId, Integer statusId, Date fromDate, Date toDate) {
		super();
		this.accountId = accountId;
		this.statusId = statusId;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public DashboardRequestDto() {
		super();
	}

}
