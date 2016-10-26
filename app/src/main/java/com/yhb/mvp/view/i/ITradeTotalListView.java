/**
 * 
 */
package com.yhb.mvp.view.i;

import java.util.List;

/**
 * @author 宋昌明
 *
 */
public interface ITradeTotalListView<T> {
	/**
	 * 设置数据源
	 */
	void setDatas(List<T> datas);

	/**
	 * 显示列表,程现界面
	 */
	void showList();

	/**
	 * more datas
	 * 
	 * @param datas
	 */
	void showMoreData(List<T> datas);
}
