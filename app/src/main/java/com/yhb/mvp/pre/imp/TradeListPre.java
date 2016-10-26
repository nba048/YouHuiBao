/**
 *
 */
package com.yhb.mvp.pre.imp;

import com.yhb.base.ProjectApp;
import com.yhb.bean.entity.Trade;
import com.yhb.bean.request.TradeRequest;
import com.yhb.bean.response.TradeResult;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.ICallBack;
import com.yhb.mvp.model.i.INetworkModel;
import com.yhb.mvp.pre.i.ITradeListPre;
import com.yhb.mvp.view.i.ITradeListView;

/**
 * @author 宋昌明
 */
public class TradeListPre implements ITradeListPre {
    private ITradeListView<Trade> mView;
    private INetworkModel mModel;
    private int mCurrentPager = 1;
    private int mDefultSize = 20;

    public TradeListPre(ITradeListView<Trade> view) {
        mModel = ProjectFactroy.getInstanceByNetworkModel();
        mView = view;
    }

    @Override
    public void lodingDatas(String url, String startTime, String endTime) {
        TradeRequest request = new TradeRequest();
        request.setEndDate(endTime);
        request.setPageNo("" + mCurrentPager);
        request.setPageSize("" + mDefultSize);
        request.setSessionId(ProjectApp.getCurrentUser().getSessionId());
        request.setStartDate(startTime);
        mModel.sendTradeList(request, new ICallBack<TradeResult>() {
            @Override
            public void onResultOK(TradeResult datas) {
                if (datas != null) {
                    mView.setDatas(datas.getData());
                    mView.showList();
                }
            }
        });
    }

    @Override
    public void lodingMoreDatas(String mItemUrl, String startTime, String endTime) {
        TradeRequest request = new TradeRequest();
        request.setEndDate(endTime);
        request.setPageNo("" + (++mCurrentPager));
        request.setPageSize("" + mDefultSize);
        request.setSessionId(ProjectApp.getCurrentUser().getSessionId());
        request.setStartDate(startTime);
        mModel.sendTradeList(request, new ICallBack<TradeResult>() {

            @Override
            public void onResultOK(TradeResult datas) {
                if (datas != null) {
                    mView.showMoreData(datas.getData());
                }
            }
        });
    }

}
