/**
 * 
 */
package com.yhb.mvp.pre.imp;

import com.yhb.base.ProjectApp;
import com.yhb.bean.entity.Order;
import com.yhb.bean.request.OrderRequest;
import com.yhb.bean.response.OrderResult;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.ICallBack;
import com.yhb.mvp.model.i.INetworkModel;
import com.yhb.mvp.pre.i.IOrderListPre;
import com.yhb.mvp.view.i.IOrderListView;
import com.yhb.utils.Tools;

/**
 * @author 宋昌明
 *
 */
public class OrderListPre implements IOrderListPre {
	private IOrderListView<Order> mView;
	private INetworkModel mModel;
	private int mCurrentPager = 1;
	private int mDefultSize = 20;

	public OrderListPre(IOrderListView<Order> view) {
		mModel = ProjectFactroy.getInstanceByNetworkModel();
		mView = view;
	}

	@Override
	public void lodingDatas(String url, String startTime, String endTime) {
		OrderRequest request = new OrderRequest();
		request.setEndDate(""+Tools.getDateToLong_(endTime));
		request.setPageNo("" + mCurrentPager);
		request.setPageSize("" + mDefultSize);
		request.setSessionId(ProjectApp.getCurrentUser().getSessionId());
		request.setStartDate(""+Tools.getDateToLong_(startTime));
		mModel.sendOrderList(request, new ICallBack<OrderResult>() {

			@Override
			public void onResultOK(OrderResult datas) {
				if (datas != null) {
					mView.setDatas(datas.getData());
					mView.showList();
				}
			}
		});
	}

	@Override
	public void lodingMoreDatas(String mItemUrl, String startTime, String endTime) {
		OrderRequest request = new OrderRequest();
		request.setEndDate(""+Tools.getDateToLong_(endTime));
		request.setPageNo("" + (++mCurrentPager));
		request.setPageSize("" + mDefultSize);
		request.setSessionId(ProjectApp.getCurrentUser().getSessionId());
		request.setStartDate(""+Tools.getDateToLong_(startTime));
		mModel.sendOrderList(request, new ICallBack<OrderResult>() {

			@Override
			public void onResultOK(OrderResult datas) {
				if (datas != null) {
					mView.showMoreData(datas.getData());
				}
			}
		});
	}

}
