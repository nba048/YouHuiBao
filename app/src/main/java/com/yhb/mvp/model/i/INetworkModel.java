package com.yhb.mvp.model.i;

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

public interface INetworkModel {
    String host = "http://182.150.46.244:12345/LogWeb";
    String log_in = "/user/login.do";
    String log_out = "/user/logout.do";
    String select_model = "/user/selectModel.do";
    String select_market = "/user/selectMarket.do";
    String register = "/user/register.do";
    String validate = "/user/validate.do";
    String set_password = "/user/setPassword.do";
    String order_list = "/order/list.do";
    String order_total = "/order/orderTotal.do";
    String trade_list = "/trade/list.do";
    String trade_total = "/trade/tradeTotal.do";
    String frequent_list = "/stare/list.do";
    String frequent_total = "/stare/details.do";

    void sendOrderList(OrderRequest request, ICallBack<OrderResult> callBack);

    void sendOrderTotal(OrderSituationRequest request, ICallBack<OrderSituationResult> callBack);

    void sendTradeList(TradeRequest request, ICallBack<TradeResult> callBack);

    void sendTradeTotal(TradeSituationRequest request, ICallBack<TradeSituationResult> callBack);

    void sendFrequentList(FrequentRequest request, ICallBack<FrequentResult> callBack);

    void sendFrequentTotal(FrequentTradingRequest request, ICallBack<FrequentSituationResult> callBack);

    void sendSelectMarket(SelectMarketRequest request, ICallBack<MarketResult> callBack);
}
