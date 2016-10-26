/**
 * 
 */
package com.yhb.mvp.pre.imp;

import com.yhb.base.ProjectApp;
import com.yhb.bean.entity.Frequent;
import com.yhb.bean.request.FrequentRequest;
import com.yhb.bean.response.FrequentResult;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.ICallBack;
import com.yhb.mvp.model.i.INetworkModel;
import com.yhb.mvp.pre.i.IStareListPre;
import com.yhb.mvp.view.i.IStareListView;

/**
 * @author 宋昌明
 *
 */
public class StareListPre implements IStareListPre {
	private IStareListView<Frequent> mView;
	private INetworkModel mModel;
	private int mCurrentPager = 1;
	private int mDefultSize = 20;

	public StareListPre(IStareListView<Frequent> view) {
		mModel = ProjectFactroy.getInstanceByNetworkModel();
		mView = view;
	}

	@Override
	public void lodingDatas(String url) {
		FrequentRequest request = new FrequentRequest();
		request.setSessionId(ProjectApp.getCurrentUser().getSessionId());
		mModel.sendFrequentList(request, new ICallBack<FrequentResult>() {

			@Override
			public void onResultOK(FrequentResult datas) {
				if (datas != null) {
					mView.setDatas(datas.getData());
					mView.showList();
				}
			}
		});
	}

	@Override
	public void lodingMoreDatas(String mItemUrl) {
		FrequentRequest request = new FrequentRequest();
		request.setSessionId(ProjectApp.getCurrentUser().getSessionId());
		mModel.sendFrequentList(request, new ICallBack<FrequentResult>() {

			@Override
			public void onResultOK(FrequentResult datas) {
				if (datas != null ) {
					mView.showMoreData(datas.getData());
				}
			}
		});
	}

}
