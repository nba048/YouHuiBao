package com.yhb.mvp.model.i;


import com.yhb.mvp.ICallBack;

/**
 * Created by Administrator on 2016/8/16. model层通用父类
 */
public interface IModel<T> {
	/**
	 * 根据指定url，获得对应数据集合
	 * 
	 * @param url
	 * @param callback
	 */
	void getDataForResult(String url, ICallBack<T> callback);

	/**
	 * 直接取最新数据
	 * 
	 * @param url
	 * @param callback
	 */
	void getRefreshDatas(String url, ICallBack<T> callback);

}
