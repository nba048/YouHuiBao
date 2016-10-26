/**
 * 
 */
package com.yhb.mvp.view.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.yhb.R;
import com.yhb.base.BaseFragment;
import com.yhb.bean.entity.Order;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.pre.i.IOrderListPre;
import com.yhb.mvp.view.activity.ShowListActivity;
import com.yhb.mvp.view.adapter.OrderAdapter;
import com.yhb.mvp.view.i.IOrderListView;
import com.yhb.widget.XListView;
import com.yhb.widget.XListView.IXListViewListener;

import java.util.List;

/**
 * @author 宋昌明
 *
 */
public class OrderFragment extends BaseFragment implements IOrderListView<Order>, IXListViewListener {
	private List<Order> mDatas;
	private OrderAdapter mAdapter;
	private IOrderListPre mPre;
	private InnerHandler mHandler;
	private XListView mLvDataShow;

	@Override
	protected int bindLayout() {
		return R.layout.fragment_show_list_layout;
	}

	@Override
	protected void setInitView(View mRootView) {
		mLvDataShow = (XListView) mRootView.findViewById(R.id.lv_datashow);
		mLvDataShow.setXListViewListener(this);
	}

	@Override
	protected void setInitData() {
		mHandler = new InnerHandler();
		mPre = ProjectFactroy.newInstanceByOrderListPre(this);
	}

	@Override
	public void onRefresh() {
		mHandler.sendEmptyMessageDelayed(0, 1000);
	}

	@Override
	public void onLoadMore() {
		mPre.lodingMoreDatas(ShowListActivity.mCurrentFunction.getUrl(), ShowListActivity.mTvStart.getText().toString(),
				ShowListActivity.mTvEnd.getText().toString());
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
		mLvDataShow.stopLoadMore();
		try {
			ShowListActivity.mDialog.dismiss();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showMoreData(List<Order> datas) {
		if (mDatas != null) {
			mDatas.addAll(datas);
			mAdapter.notifyDataSetChanged();
			mLvDataShow.stopLoadMore();
			mLvDataShow.stopRefresh();
		}
	}

	private class InnerHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				mPre.lodingDatas(ShowListActivity.mCurrentFunction.getUrl(),
						ShowListActivity.mTvStart.getText().toString(), ShowListActivity.mTvEnd.getText().toString());
				break;

			default:
				break;
			}
		}
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			if (mPre != null && mLvDataShow != null) {
				if (mDatas == null) {
					try {
						ShowListActivity.mDialog.show();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				onRefresh();
			}
		}
	}
}
