package com.yhb.bean.entity;
/**
 * 委托汇总实体
 * @author 宋昌明
 *
 */
public class OrderSituation extends AEntity{


	/**
	 *"totalNum": 16004109,
	 "commodityName": "橘子",
	 "commodityId": "00004",
	 "bsFlag": null,
	 "totalMoney": 149674.1364300251
	 */
	/**
	 * 商品ID
	 */
	private String commodityId;
	/**
	 * 买卖标识
	 */
	private String bsFlag;
	/**
	 * 商品名称
	 */
	private String commodityName;
	/**
	 * 委托数总量
	 */
	private String totalNum;
	/**
	 * 总金额
	 */
	private String totalMoney;
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public String getBsFlag() {
		return bsFlag;
	}
	public void setBsFlag(String bsFlag) {
		this.bsFlag = bsFlag;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	@Override
	public String toString() {
		return "OrderSituation [commodityId=" + commodityId + ", bsFlag=" + bsFlag + ", commodityName=" + commodityName
				+ ", totalNum=" + totalNum + ", totalMoney=" + totalMoney + "]";
	}
	
	
}
