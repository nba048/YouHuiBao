/**
 * 
 */
package com.yhb.mvp.pre.imp;

import com.yhb.base.ProjectApp;
import com.yhb.bean.entity.TradeSituation;
import com.yhb.bean.request.TradeSituationRequest;
import com.yhb.bean.response.TradeSituationResult;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.ICallBack;
import com.yhb.mvp.model.i.INetworkModel;
import com.yhb.mvp.pre.i.ITradeTotalListPre;
import com.yhb.mvp.view.i.ITradeTotalListView;

/**
 * @author 宋昌明
 *
 */
public class TradeTotalListPre implements ITradeTotalListPre {
	private ITradeTotalListView<TradeSituation> mView;
	private INetworkModel mModel;
	private int mCurrentPager = 1;
	private int mDefultSize = 20;

	public TradeTotalListPre(ITradeTotalListView<TradeSituation> view) {
		mModel = ProjectFactroy.getInstanceByNetworkModel();
		mView = view;
	}

	@Override
	public void lodingDatas(String url, String startTime, String endTime) {
		TradeSituationRequest request = new TradeSituationRequest();
		request.setSessionId(ProjectApp.getCurrentUser().getSessionId());
		request.setStartDate(startTime);
		request.setEndDate(endTime);
		mModel.sendTradeTotal(request, new ICallBack<TradeSituationResult>() {

			@Override
			public void onResultOK(TradeSituationResult datas) {
				if (datas != null) {
					mView.setDatas(datas.getData());
					mView.showList();
				}
			}
		});
	}

	@Override
	public void lodingMoreDatas(String mItemUrl, String startTime, String endTime) {
		TradeSituationRequest request = new TradeSituationRequest();
		request.setSessionId(ProjectApp.getCurrentUser().getSessionId());
		request.setStartDate(startTime);
		request.setEndDate(endTime);
		mModel.sendTradeTotal(request, new ICallBack<TradeSituationResult>() {

			@Override
			public void onResultOK(TradeSituationResult datas) {
				if (datas != null) {
					mView.showMoreData(datas.getData());
				}
			}
		});
	}

}
