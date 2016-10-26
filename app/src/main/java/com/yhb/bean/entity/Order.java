package com.yhb.bean.entity;

import com.yhb.utils.Tools;

/**
 * 委托实体
 *
 * @author 宋昌明
 */
public class Order extends AEntity {
    /**
     "id": "5807707707373c0f30f35873",
     "orderNo": "0",
     "firmId": "100011",
     "firmIp": "127.0.0.1",
     "firmName": "高万刚",
     "bsFlag": null,
     "delayOrderType": "订立",
     "commodityId": "00005",
     "commodityName": "银",
     "quantily": 4747,
     "price": 47.62491,
     "orderTime": 1476710561875
     */

    /**
     * 数据库主键
     */
    private String id;
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
     * 委托类别
     */
    private String firmName;
    /**
     * 交易商名称
     */
    private String bsFlag;
    /**
     * 买卖标识
     */
    private String delayOrderType;
    /**
     * 商品ID
     */
    private String commodityId;
    /**
     * 商品名称
     */
    private String commodityName;
    /**
     * 委托数量
     */
    private String quantily;
    /**
     * 委托价格
     */
    private String price;
    /**
     * 委托时间
     */
    private String orderTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getBsFlags() {
        if (bsFlag == null)
            bsFlag = "";
        return bsFlag;
    }

    public void setBsFlags(String bsFlags) {
        this.bsFlag = bsFlags;
    }

    public String getOrderType() {
        return delayOrderType;
    }

    public void setOrderType(String orderType) {
        this.delayOrderType = orderType;
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

    public String getOrderNum() {
        try {
            int v = Integer.parseInt(quantily);
        } catch (Exception e) {
            quantily = "0";
        }
        return quantily;
    }

    public void setOrderNum(String orderNum) {
        this.quantily = orderNum;
    }

    public String getOrderMoney() {
        try {
            double v = Double.parseDouble(price);
        } catch (Exception e) {
            price = "0.00";
        }
        return price;
    }

    public void setOrderMoney(String orderMoney) {
        this.price = orderMoney;
    }

    public String getOrderTime() {
        String timer = null;
        try {
            timer = Tools.getDateToString(Long.parseLong(orderTime));
        } catch (Exception e) {
            timer = orderTime;
        }
        return "" + timer;
    }

    public String getOrderTime2() {
        return "" + orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", firmId='" + firmId + '\'' +
                ", firmIp='" + firmIp + '\'' +
                ", firmName='" + firmName + '\'' +
                ", bsFlag='" + bsFlag + '\'' +
                ", delayOrderType='" + delayOrderType + '\'' +
                ", commodityId='" + commodityId + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", quantily='" + quantily + '\'' +
                ", price='" + price + '\'' +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }
}
