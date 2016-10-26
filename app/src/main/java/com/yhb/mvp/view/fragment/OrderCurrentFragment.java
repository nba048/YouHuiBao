package com.yhb.mvp.view.fragment;

import android.view.View;
import android.widget.AbsListView;

import com.yhb.R;
import com.yhb.base.BaseFragment;
import com.yhb.bean.entity.Order;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.pre.i.IOrderListPre;
import com.yhb.mvp.view.activity.OrderMainActivity;
import com.yhb.mvp.view.adapter.OrderAdapter;
import com.yhb.mvp.view.i.IOrderListView;
import com.yhb.utils.Tools;
import com.yhb.widget.XListView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class OrderCurrentFragment extends BaseFragment implements IOrderListView<Order>, XListView.IXListViewListener {

    private XListView mLvDataShow;
    private IOrderListPre mPre;
    private List<Order> mDatas;
    private OrderAdapter mAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_order_current;
    }

    @Override
    protected void setInitView(View mRootView) {
        mLvDataShow = (XListView) mRootView;
    }

    @Override
    protected void setInitData() {
        mPre = ProjectFactroy.newInstanceByOrderListPre(this);
        mLvDataShow.setXListViewListener(this);
        mLvDataShow.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://空闲状态
                        OrderMainActivity.upAnim();
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        OrderMainActivity.downAnim();
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        onRefresh();
    }

    @Override
    public void setDatas(List<Order> datas) {
        if (mDatas == null) {
            mDatas = datas;
        } else {
            mDatas.clear();
            mDatas.addAll(datas);
        }
    }

    @Override
    public void showList() {
        if (mAdapter == null && mDatas != null) {
            mAdapter = new OrderAdapter(getlActivity(), R.layout.item_order_layout, mDatas);
            mLvDataShow.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mLvDataShow.smoothScrollToPosition(0);
        mLvDataShow.stopRefresh();
        toastShow("拉取数据成功");
        OrderMainActivity.mDialog.dismiss();
    }

    @Override
    public void showMoreData(List<Order> datas) {
        if (mDatas != null) {
            mDatas.addAll(datas);
            mAdapter.notifyDataSetChanged();
            mLvDataShow.stopLoadMore();
        }
    }

    @Override
    public void onRefresh() {
        long millis = System.currentTimeMillis();
        mPre.lodingDatas(OrderMainActivity.mCurrentFunction.getUrl(), Tools.getDateToString(millis), Tools.getDateToString(millis));
    }

    @Override
    public void onLoadMore() {
        long millis = System.currentTimeMillis();
        mPre.lodingMoreDatas(OrderMainActivity.mCurrentFunction.getUrl(), Tools.getDateToString(millis), Tools.getDateToString(millis));
    }

    @Override
    public void onUpButtonPre() {
        super.onUpButtonPre();
        mLvDataShow.smoothScrollToPosition(0);
    }

    @Override
    public void onDromButtonPre() {
        super.onDromButtonPre();
        mLvDataShow.smoothScrollToPosition(mAdapter.getCount() - 1);
    }
}
