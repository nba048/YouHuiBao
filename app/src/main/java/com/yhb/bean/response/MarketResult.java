package com.yhb.bean.response;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public class MarketResult extends AResponse{
    public String code;
    public String sessionId;
    public String msg;

    @Override
    public String toString() {
        return "MarketResult{" +
                "code='" + code + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
