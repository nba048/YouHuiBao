/**
 * 
 */
package com.yhb.mvp.pre.i;

/**
 * @author 宋昌明
 *
 */
public interface IStareListPre {
	/**
	 * 读取数据
	 * 
	 */
	void lodingDatas(String url);

	/**
	 * 读取more数据
	 * 
	 */
	void lodingMoreDatas(String mItemUrl);

}
