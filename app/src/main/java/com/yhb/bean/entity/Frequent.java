/**
 * 
 */
package com.yhb.bean.entity;

/**
 * @author 宋昌明 stare 频繁交易概览实体
 */
public class Frequent extends AEntity{
	/**
	 * 实体ID
	 */
	private String id;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 发生交易次数
	 */
	private String tradeNum;
	/**
	 * 交易商ID
	 */
	private String firmId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTradeNum() {
		return tradeNum;
	}
	public void setTradeNum(String tradeNum) {
		this.tradeNum = tradeNum;
	}
	public String getFirmId() {
		return firmId;
	}
	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}
	@Override
	public String toString() {
		return "Frequent [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", tradeNum=" + tradeNum
				+ ", firmId=" + firmId + "]";
	}
	
	
}
