package com.yhb.bean.entity;

/**
 * 交易汇总实体
 * 
 * @author 宋昌明
 *
 */
public class TradeSituation extends AEntity {
	/**
	 *"totalNum": 34006599,
	 "tradeType": "卖",
	 "money": 312448.92204880714,
	 "commodityName": null,
	 "commodityId": "白菜"
	 */
	/**
	 * 商品ID
	 */
	private String commodityId;
	/**
	 * 交易类型
	 */
	private String tradeType;
	/**
	 * 商品名称
	 */
	private String commodityName;
	/**
	 * 成交总量总量
	 */
	private String totalNum;
	/**
	 * 总金额
	 */
	private String money;

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
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
		return money;
	}

	public void setTotalMoney(String totalMoney) {
		this.money = totalMoney;
	}

	@Override
	public String toString() {
		return "TradeSituation [commodityId=" + commodityId + ", tradeType=" + tradeType + ", CommodityName="
				+ commodityName + ", totalNum=" + totalNum + ", totalMoney=" + money + "]";
	}

}
