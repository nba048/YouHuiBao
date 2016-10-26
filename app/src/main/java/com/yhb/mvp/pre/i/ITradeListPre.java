/**
 * 
 */
package com.yhb.mvp.pre.i;

/**
 * @author 宋昌明
 *
 */
public interface ITradeListPre {
	/**
	 * 读取数据
	 * 
	 * @param endTime
	 * @param endTime
	 */
	void lodingDatas(String url, String startTime, String endTime);

	/**
	 * 读取more数据
	 * 
	 * @param mItemUrl
	 * @param string2
	 * @param string
	 */
	void lodingMoreDatas(String mItemUrl, String startTime, String endTime);

}
