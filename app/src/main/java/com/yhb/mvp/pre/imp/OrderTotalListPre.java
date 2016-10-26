/**
 * 
 */
package com.yhb.mvp.pre.imp;

import com.yhb.base.ProjectApp;
import com.yhb.bean.entity.OrderSituation;
import com.yhb.bean.request.OrderSituationRequest;
import com.yhb.bean.response.OrderSituationResult;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.ICallBack;
import com.yhb.mvp.model.i.INetworkModel;
import com.yhb.mvp.pre.i.IOrderTotalListPre;
import com.yhb.mvp.view.i.IOrderTotalListView;

/**
 * @author 宋昌明
 *
 */
public class OrderTotalListPre implements IOrderTotalListPre {
	private IOrderTotalListView<OrderSituation> mView;
	private INetworkModel mModel;
	private int mCurrentPager = 1;
	private int mDefultSize = 20;

	public OrderTotalListPre(IOrderTotalListView<OrderSituation> view) {
		mModel = ProjectFactroy.getInstanceByNetworkModel();
		mView = view;
	}

	@Override
	public void lodingDatas(String url, String startTime, String endTime) {
		OrderSituationRequest request = new OrderSituationRequest();
		request.setSessionId(ProjectApp.getCurrentUser().getSessionId());
		request.setStartDate(startTime);
		request.setEndDate(endTime);
		mModel.sendOrderTotal(request, new ICallBack<OrderSituationResult>() {

			@Override
			public void onResultOK(OrderSituationResult datas) {
				if (datas != null) {
					mView.setDatas(datas.getData());
					mView.showList();
				}
			}
		});
	}

	@Override
	public void lodingMoreDatas(String mItemUrl, String startTime, String endTime) {
		OrderSituationRequest request = new OrderSituationRequest();
		request.setSessionId(ProjectApp.getCurrentUser().getSessionId());
		request.setStartDate(startTime);
		request.setEndDate(endTime);
		mModel.sendOrderTotal(request, new ICallBack<OrderSituationResult>() {

			@Override
			public void onResultOK(OrderSituationResult datas) {
				if (datas != null) {
					mView.showMoreData(datas.getData());
				}
			}
		});
	}

}
