/**
 *
 */
package com.yhb.bean.request;

/**
 * @author 宋昌明 请求地址 /order/orderTotal.do 请求参数
 *         {sessionId:”123456”,startDate:”2016-9-21”,endDate:”2016-9-22”}
 */
public class OrderSituationRequest extends ARequest {
    private String sessionId;
    private String startDate;
    private String endDate;

    //	@Override
//	public String toString() {
//		return "{sessionId:" + sessionId + ", startDate:" + startDate + ", endDate:" + endDate + "}";
//	}
    @Override
    public String toString() {
        return "?sessionId=" + sessionId;
    }

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

}
