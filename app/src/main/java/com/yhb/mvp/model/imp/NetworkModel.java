package com.yhb.mvp.model.imp;

import com.google.gson.Gson;
import com.yhb.base.ProjectUtil;
import com.yhb.bean.request.FrequentRequest;
import com.yhb.bean.request.FrequentTradingRequest;
import com.yhb.bean.request.OrderRequest;
import com.yhb.bean.request.OrderSituationRequest;
import com.yhb.bean.request.SelectMarketRequest;
import com.yhb.bean.request.TradeRequest;
import com.yhb.bean.request.TradeSituationRequest;
import com.yhb.bean.response.FrequentResult;
import com.yhb.bean.response.FrequentSituationResult;
import com.yhb.bean.response.MarketResult;
import com.yhb.bean.response.OrderResult;
import com.yhb.bean.response.OrderSituationResult;
import com.yhb.bean.response.TradeResult;
import com.yhb.bean.response.TradeSituationResult;
import com.yhb.mvp.ICallBack;
import com.yhb.mvp.model.i.INetworkModel;

import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class NetworkModel implements INetworkModel {

    @Override
    public void sendOrderList(final OrderRequest request, final ICallBack<OrderResult> callBack) {
        if (request == null) {
            callBack.onResultOK(null);
            return;
        }
        RequestParams entity = new RequestParams(INetworkModel.host + INetworkModel.order_list + request.toString());
        ProjectUtil.L.e("sendOrderList - - > " + entity.toString());
        x.http().get(entity, new CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ProjectUtil.L.e("onSuccess - - > " + result);
                callBack.onResultOK(new Gson().fromJson(result, OrderResult.class));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void sendOrderTotal(final OrderSituationRequest request, final ICallBack<OrderSituationResult> callBack) {
        if (request == null) {
            callBack.onResultOK(null);
            return;
        }
        RequestParams entity = new RequestParams(INetworkModel.host + INetworkModel.order_total + request.toString());
        ProjectUtil.L.e("sendOrderTotal - - > " + entity.toString());
        x.http().get(entity, new CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ProjectUtil.L.e("onSuccess - - > " + result);
                callBack.onResultOK(new Gson().fromJson(result, OrderSituationResult.class));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void sendTradeList(final TradeRequest request, final ICallBack<TradeResult> callBack) {
        if (request == null) {
            callBack.onResultOK(null);
            return;
        }
        RequestParams entity = new RequestParams(INetworkModel.host + INetworkModel.trade_list + request.toString());
        ProjectUtil.L.e("sendTradeList - - > " + entity.toString());
        x.http().get(entity, new CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ProjectUtil.L.e("onSuccess - - > " + result);
                callBack.onResultOK(new Gson().fromJson(result, TradeResult.class));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void sendTradeTotal(final TradeSituationRequest request, final ICallBack<TradeSituationResult> callBack) {
        if (request == null) {
            callBack.onResultOK(null);
            return;
        }
        RequestParams entity = new RequestParams(INetworkModel.host + INetworkModel.trade_total + request.toString());
        ProjectUtil.L.e("sendTradeTotal - - > " + entity.toString());
        x.http().get(entity, new CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ProjectUtil.L.e("onSuccess - - > " + result);
                callBack.onResultOK(new Gson().fromJson(result, TradeSituationResult.class));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void sendFrequentList(final FrequentRequest request, final ICallBack<FrequentResult> callBack) {
        if (request == null) {
            callBack.onResultOK(null);
            return;
        }
        RequestParams entity = new RequestParams(INetworkModel.host + INetworkModel.frequent_list + request.toString());
        ProjectUtil.L.e("sendFrequentList - - > " + entity.toString());
        x.http().get(entity, new CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ProjectUtil.L.e("onSuccess - - > " + result);
                callBack.onResultOK(new Gson().fromJson(result, FrequentResult.class));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void sendFrequentTotal(FrequentTradingRequest request, final ICallBack<FrequentSituationResult> callBack) {
        if (request == null) {
            callBack.onResultOK(null);
            return;
        }
        RequestParams entity = new RequestParams(INetworkModel.host + INetworkModel.frequent_total + request.toString());
        ProjectUtil.L.e("sendFrequentList - - > " + entity.toString());
        x.http().get(entity, new CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ProjectUtil.L.e("onSuccess - - > " + result);
                callBack.onResultOK(new Gson().fromJson(result, FrequentSituationResult.class));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void sendSelectMarket(SelectMarketRequest request, final ICallBack<MarketResult> callBack) {
        RequestParams entity = new RequestParams(INetworkModel.host + INetworkModel.select_market + request.toString());
        x.http().get(entity, new CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ProjectUtil.L.e("onSuccess - - > " + result);
                callBack.onResultOK(new Gson().fromJson(result, MarketResult.class));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

}
