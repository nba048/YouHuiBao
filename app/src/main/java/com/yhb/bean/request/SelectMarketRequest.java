package com.yhb.bean.request;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public class SelectMarketRequest extends ARequest {
    public String marketId;
    public String firmId;
    public String password;

    @Override
    public String toString() {
        return "?marketId=" + marketId + "&firmId=" + firmId + "&password=" + password;
    }
}
