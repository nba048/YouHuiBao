package com.yhb.mvp.view.i;

import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 * view层通用父类
 */
public interface IView<T> {
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
     * @param datas
     */
    void showMoreData(List<T> datas);
}
