/**
 * 
 */
package com.yhb.bean.request;

/**
 * @author 宋昌明 频繁交易概览 /stare/list.do 请求参数 {sessionId:”123456”}
 * 
 */
public class FrequentRequest extends ARequest{
	private String sessionId;

	@Override
	public String toString() {
		return "?sessionId=" + sessionId ;
	}
//	@Override
//	public String toString() {
//		return "{sessionId:" + sessionId + "}";
//	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
