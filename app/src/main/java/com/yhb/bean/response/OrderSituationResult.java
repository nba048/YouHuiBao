/**
 * 
 */
package com.yhb.bean.response;

import com.yhb.bean.entity.OrderSituation;

import java.util.List;

/**
 * @author 宋昌明 市场委托情况
 */
public class OrderSituationResult extends AResponse{
	/**
	 * 市场id
	 */
	private String marketId;
	private String startDate;
	private String endDate;
	private String code;
	/**
	 * 委托汇总实体
	 */
	private List<OrderSituation> datas;

	public String getCode() {
		return code;
	}

	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<OrderSituation> getData() {
		return datas;
	}
	public void setData(List<OrderSituation> data) {
		this.datas = data;
	}
	@Override
	public String toString() {
		return "OrderSituationResult [marketId=" + marketId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", data=" + data + "]";
	}
	
	
}
