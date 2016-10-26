/**
 *
 */
package com.yhb.mvp.pre.imp;

import com.yhb.base.ProjectApp;
import com.yhb.bean.entity.FrequentTrading;
import com.yhb.bean.request.FrequentTradingRequest;
import com.yhb.bean.response.FrequentSituationResult;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.ICallBack;
import com.yhb.mvp.model.i.INetworkModel;
import com.yhb.mvp.pre.i.IStareDetailListPre;
import com.yhb.mvp.view.i.IStareDetailListView;

/**
 * @author 宋昌明
 */
public class StareDetailListPre implements IStareDetailListPre {
    private IStareDetailListView<FrequentTrading> mView;
    private INetworkModel mModel;
    private int mCurrentPager = 1;
    private int mDefultSize = 20;

    public StareDetailListPre(IStareDetailListView<FrequentTrading> view) {
        mModel = ProjectFactroy.getInstanceByNetworkModel();
        mView = view;
    }

    @Override
    public void lodingDatas(String id) {
        FrequentTradingRequest request = new FrequentTradingRequest();
        request.setSessionId(ProjectApp.getCurrentUser().getSessionId());
        request.setId(id);
        mModel.sendFrequentTotal(request, new ICallBack<FrequentSituationResult>() {
            @Override
            public void onResultOK(FrequentSituationResult datas) {
                if (datas != null ) {
                    mView.setDatas(datas.getData());
                    mView.showList();
                }
            }
        });
    }

    @Override
    public void lodingMoreDatas(String id) {
        FrequentTradingRequest request = new FrequentTradingRequest();
        request.setId(id);
        request.setSessionId(ProjectApp.getCurrentUser().getSessionId());
        mModel.sendFrequentTotal(request, new ICallBack<FrequentSituationResult>() {

            @Override
            public void onResultOK(FrequentSituationResult datas) {
                if (datas != null) {
                    mView.showMoreData(datas.getData());
                }
            }
        });
    }

}
