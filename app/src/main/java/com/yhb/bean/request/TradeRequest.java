/**
 * 
 */
package com.yhb.bean.request;

/**
 * @author 宋昌明 请求地址 /trade/list.do 请求参数
 *         {sessionId:”123456”,startDate:”2016-9-21”,
 *         endDate:”2016-9-22”,pageNo:1,pageSize:10}
 * 
 */
public class TradeRequest extends ARequest{
	private String sessionId;
	private String startDate;
	private String endDate;
	private String pageNo;
	private String pageSize;

	@Override
	public String toString() {
		return "?sessionId=" + sessionId +"&pageNo=" + pageNo + "&pageSize=" + pageSize;
	}
//	@Override
//	public String toString() {
//		return "{sessionId:" + sessionId + ", startDate:" + startDate + ", endDate:" + endDate + ", pageNo:" + pageNo
//				+ ", pageSize:" + pageSize + "}";
//	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

}
