/**
 *
 */
package com.yhb.bean.request;

/**
 * @author 宋昌明 请求地址 /stare/details.do 请求参数 {sessionId:”123456”,id:””}
 */
public class FrequentTradingRequest extends ARequest {
    private String sessionId;
    private String id;

    @Override
    public String toString() {
        return "?sessionId=" + sessionId + "&id=" + id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
