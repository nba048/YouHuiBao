package com.yhb.mvp.pre.i;

/**
 * Created by Administrator on 2016/8/16.
 * Presenter层通用父类
 */
public interface IPresent {
    /**
     * 读取数据
     */
    void lodingDatas(String url);

    /**
     * 读取more数据
     * @param mItemUrl
     */
    void lodingMoreDatas(String mItemUrl);

    /**
     * 完全刷新数据，取最新数据
     * @param url
     */
    void refreshDatas(String url);
}
