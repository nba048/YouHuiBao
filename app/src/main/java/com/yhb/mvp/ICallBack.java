package com.yhb.mvp;

/**
 * Created by Administrator on 2016/8/16.
 */
public interface ICallBack<T> {
	/**
	 * 当操作，有结果时调用
	 * 
	 * @param datas
	 */
	void onResultOK(T datas);
}
