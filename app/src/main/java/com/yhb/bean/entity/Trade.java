package com.yhb.bean.entity;

import com.yhb.utils.Tools;

/**
 * 成交实体
 *
 * @author 宋昌明
 */
public class Trade extends AEntity {
    /**
     * 数据库主键
     */
    private String marketId;
    /**
     * 交易单号
     */
    private String tradeNo;
    /**
     * 委托单号
     */
    private String orderNo;
    /**
     * 交易商ID
     */
    private String firmId;
    /**
     * 交易商IP
     */
    private String firmIp;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 商品ID
     */
    private String commodityId;
    /**
     * 商品名称
     */
    private String commodityName;
    /**
     * 交易数量
     */
    private String tradeNum;
    /**
     * 成交价格
     */
    private String tradeMoney;
    /**
     * 变动资金
     */
    private String money;
    /**
     * 剩余资金
     */
    private String canOutMoney;
    /**
     * 剩余持仓
     */
    private String canOutPosition;
    /**
     * 成交时间
     */
    private String tradeTime;
    /**
     * 交易对手ID
     */
    private String oppCustomerId;
    /**
     * 交易对手IP
     */
    private String oppCustomerIp;

    public String getId() {
        return marketId;
    }

    public void setId(String id) {
        this.marketId = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getFirmIp() {
        return firmIp;
    }

    public void setFirmIp(String firmIp) {
        this.firmIp = firmIp;
    }

    public String getTradeType() {
        if (tradeType == null)
            tradeType = "";
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getTradeNum() {
        try {
            int v = Integer.parseInt(tradeNum);
        } catch (Exception e) {
            tradeNum = "0";
        }
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
    }

    public String getTradeMoney() {
        try {
            double v = Double.parseDouble(tradeMoney);
        } catch (Exception e) {
            tradeMoney = "0.00";
        }
        return tradeMoney;
    }

    public void setTradeMoney(String tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getMoney() {
        try {
            double v = Double.parseDouble(money);
        } catch (Exception e) {
            money = "0.00";
        }
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCanOutMoney() {
        try {
            double v = Double.parseDouble(canOutMoney);
        } catch (Exception e) {
            canOutMoney = "0.00";
        }
        return canOutMoney;
    }

    public void setCanOutMoney(String canOutMoney) {
        this.canOutMoney = canOutMoney;
    }

    public String getCanOutPosition() {
        try {
            int v = Integer.parseInt(canOutPosition);
        } catch (Exception e) {
            canOutPosition = "0";
        }
        return canOutPosition;
    }

    public void setCanOutPosition(String canOutPosition) {
        this.canOutPosition = canOutPosition;
    }

    public String getTradeTime() {
        return Tools.getDateToString(Long.parseLong(tradeTime));
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getOppCustomerId() {
        return oppCustomerId;
    }

    public void setOppCustomerId(String oppCustomerId) {
        this.oppCustomerId = oppCustomerId;
    }

    public String getOppCustomerIp() {
        return oppCustomerIp;
    }

    public void setOppCustomerIp(String oppCustomerIp) {
        this.oppCustomerIp = oppCustomerIp;
    }

    @Override
    public String toString() {
        return "Trade [marketId=" + marketId + ", tradeNo=" + tradeNo + ", orderNo=" + orderNo + ", firmId=" + firmId + ", firmIp="
                + firmIp + ", tradeType=" + tradeType + ", commodityId=" + commodityId + ", commodityName="
                + commodityName + ", tradeNum=" + tradeNum + ", tradeMoney=" + tradeMoney + ", money=" + money
                + ", canOutMoney=" + canOutMoney + ", canOutPosition=" + canOutPosition + ", tradeTime=" + tradeTime
                + ", oppCustomerId=" + oppCustomerId + ", oppCustomerIp=" + oppCustomerIp + "]";
    }


}
