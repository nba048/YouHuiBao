/**
 * 
 */
package com.yhb.bean.response;

import com.yhb.bean.entity.TradeSituation;

import java.util.List;

/**
 * @author 宋昌明
 *
 *         市场成交情况
 */
public class TradeSituationResult extends AResponse{
	private String code;
	private String startDate;
	private String endDate;
	/**
	 * 交易汇总实体
	 */
	private List<TradeSituation> datas;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public List<TradeSituation> getData() {
		return datas;
	}
	public void setData(List<TradeSituation> data) {
		this.datas = data;
	}
	@Override
	public String toString() {
		return "TradeSituationResult [code=" + code + ", startDate=" + startDate + ", endDate=" + endDate + ", data="
				+ data + "]";
	}
	
	
}
