package com.yhb.bean.entity;

/**
 * 频繁交易实体
 * 
 * @author 宋昌明
 *
 */
public class FrequentTrading extends AEntity{
	/**
	 * 数据库主键
	 */
	private String id;
	private String belongFre;
	private String firmName;
	private String oppCustomerName;
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
	 * 交易商Ip
	 */
	private String firmIp;
	/**
	 * 交易类型
	 */
	private String tradeType;
	/**
	 * 商品Id
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
	 * 交易时间
	 */
	private String tradeTime;
	/**
	 * 交易对手id
	 */
	private String oppCustomerId;
	/**
	 * 交易对手Ip
	 */
	private String oppCustomerIp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
		return tradeNum;
	}
	public void setTradeNum(String tradeNum) {
		this.tradeNum = tradeNum;
	}
	public String getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(String tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCanOutMoney() {
		return canOutMoney;
	}
	public void setCanOutMoney(String canOutMoney) {
		this.canOutMoney = canOutMoney;
	}
	public String getCanOutPosition() {
		return canOutPosition;
	}
	public void setCanOutPosition(String canOutPosition) {
		this.canOutPosition = canOutPosition;
	}
	public String getTradeTime() {
		return tradeTime;
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

	public String getBelongFre() {
		return belongFre;
	}

	public void setBelongFre(String belongFre) {
		this.belongFre = belongFre;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getOppCustomerName() {
		return oppCustomerName;
	}

	public void setOppCustomerName(String oppCustomerName) {
		this.oppCustomerName = oppCustomerName;
	}

	@Override
	public String toString() {
		return "FrequentTrading [id=" + id + ", tradeNo=" + tradeNo + ", orderNo=" + orderNo + ", firmId=" + firmId
				+ ", firmIp=" + firmIp + ", tradeType=" + tradeType + ", commodityId=" + commodityId
				+ ", commodityName=" + commodityName + ", tradeNum=" + tradeNum + ", tradeMoney=" + tradeMoney
				+ ", money=" + money + ", canOutMoney=" + canOutMoney + ", canOutPosition=" + canOutPosition
				+ ", tradeTime=" + tradeTime + ", oppCustomerId=" + oppCustomerId + ", oppCustomerIp=" + oppCustomerIp
				+ "]";
	}
	
	
	
}
