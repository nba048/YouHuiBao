/**
 * 
 */
package com.yhb.bean.response;

import com.yhb.bean.entity.FrequentTrading;

import java.util.List;

/**
 * @author 宋昌明
 *
 */
public class FrequentSituationResult extends AResponse {
	private String code;
	private List<FrequentTrading> datas;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		code = code;
	}

	public List<FrequentTrading> getData() {
		return datas;
	}

	public void setData(List<FrequentTrading> data) {
		this.datas = data;
	}

	@Override
	public String toString() {
		return "FrequentSituationResult [Code=" + code + ", data=" + data + "]";
	}

}
