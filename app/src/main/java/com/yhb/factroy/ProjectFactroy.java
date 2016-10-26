package com.yhb.factroy;

import com.yhb.bean.V;
import com.yhb.bean.entity.AEntity;
import com.yhb.bean.entity.Frequent;
import com.yhb.bean.entity.FrequentTrading;
import com.yhb.bean.entity.Function;
import com.yhb.bean.entity.Market;
import com.yhb.bean.entity.Model;
import com.yhb.bean.entity.Order;
import com.yhb.bean.entity.OrderSituation;
import com.yhb.bean.entity.Trade;
import com.yhb.bean.entity.TradeSituation;
import com.yhb.bean.request.ARequest;
import com.yhb.bean.request.FrequentRequest;
import com.yhb.bean.request.FrequentTradingRequest;
import com.yhb.bean.request.OrderRequest;
import com.yhb.bean.request.OrderSituationRequest;
import com.yhb.bean.request.TradeRequest;
import com.yhb.bean.request.TradeSituationRequest;
import com.yhb.bean.response.AResponse;
import com.yhb.bean.response.FrequentResult;
import com.yhb.bean.response.FrequentSituationResult;
import com.yhb.bean.response.OrderResult;
import com.yhb.bean.response.OrderSituationResult;
import com.yhb.bean.response.TradeResult;
import com.yhb.bean.response.TradeSituationResult;
import com.yhb.mvp.model.i.INetworkModel;
import com.yhb.mvp.model.imp.NetworkModel;
import com.yhb.mvp.pre.i.IOrderListPre;
import com.yhb.mvp.pre.i.IOrderTotalListPre;
import com.yhb.mvp.pre.i.IRegisterPre;
import com.yhb.mvp.pre.i.IStareDetailListPre;
import com.yhb.mvp.pre.i.IStareListPre;
import com.yhb.mvp.pre.i.ITradeListPre;
import com.yhb.mvp.pre.i.ITradeTotalListPre;
import com.yhb.mvp.pre.imp.OrderListPre;
import com.yhb.mvp.pre.imp.OrderTotalListPre;
import com.yhb.mvp.pre.imp.RegisterPre;
import com.yhb.mvp.pre.imp.StareDetailListPre;
import com.yhb.mvp.pre.imp.StareListPre;
import com.yhb.mvp.pre.imp.TradeListPre;
import com.yhb.mvp.pre.imp.TradeTotalListPre;
import com.yhb.mvp.view.i.IOrderListView;
import com.yhb.mvp.view.i.IOrderTotalListView;
import com.yhb.mvp.view.i.IRegisterView;
import com.yhb.mvp.view.i.IStareDetailListView;
import com.yhb.mvp.view.i.IStareListView;
import com.yhb.mvp.view.i.ITradeListView;
import com.yhb.mvp.view.i.ITradeTotalListView;

public class ProjectFactroy {

    private static INetworkModel mModel;

    public static IRegisterPre newInstanceByRegestPre(IRegisterView view) {
        return new RegisterPre(view);
    }

    public static IOrderListPre newInstanceByOrderListPre(IOrderListView view) {
        return new OrderListPre(view);
    }

    public static ITradeListPre newInstanceByTradeListPre(ITradeListView view) {
        return new TradeListPre(view);
    }

    public static IStareListPre newInstanceByStareListPre(IStareListView view) {
        return new StareListPre(view);
    }

    public static IStareDetailListPre newInstanceByStareDetailListPre(IStareDetailListView view) {
        return new StareDetailListPre(view);
    }
    public static IOrderTotalListPre newInstanceByOrderTotalListPre(IOrderTotalListView view) {
        return new OrderTotalListPre(view);
    }
    public static ITradeTotalListPre newInstanceByTradeTotalListPre(ITradeTotalListView view) {
        return new TradeTotalListPre(view);
    }

    public static INetworkModel getInstanceByNetworkModel() {
        if (mModel == null) {
             mModel = new NetworkModel();
//            mModel = new TestDataModel();
        }
        return mModel;
    }

    public static AEntity getBeanByEntity(int type) {
        switch (type) {
            case V.bean_entity_frequent:
                return new Frequent();
            case V.bean_entity_frequent_trading:
                return new FrequentTrading();
            case V.bean_entity_function:
                return new Function();
            case V.bean_entity_market:
                return new Market();
            case V.bean_entity_model:
                return new Model();
            case V.bean_entity_order:
                return new Order();
            case V.bean_entity_order_situation:
                return new OrderSituation();
            case V.bean_entity_trade:
                return new Trade();
            case V.bean_entity_trade_situation:
                return new TradeSituation();
            default:
                return null;
        }
    }

    public static ARequest getBeanByRequest(int type) {
        switch (type) {
            case V.bean_request_frequent:
                return new FrequentRequest();
            case V.bean_request_frequent_trading:
                return new FrequentTradingRequest();
            case V.bean_request_order:
                return new OrderRequest();
            case V.bean_request_order_situation:
                return new OrderSituationRequest();
            case V.bean_request_trade:
                return new TradeRequest();
            case V.bean_request_trade_situation:
                return new TradeSituationRequest();
            default:
                return null;
        }
    }

    public static AResponse getBeanByResponse(int type) {
        switch (type) {
            case V.bean_response_order:
                return new OrderResult();
            case V.bean_response_order_situation:
                return new OrderSituationResult();
            case V.bean_response_trade:
                return new TradeResult();
            case V.bean_response_trade_situation:
                return new TradeSituationResult();
            case V.bean_response_frequent:
                return new FrequentResult();
            case V.bean_response_frequent_situation:
                return new FrequentSituationResult();
            default:
                return null;
        }
    }

}
