package com.yhb.mvp.model.imp;

import android.os.AsyncTask;

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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author 宋昌明
 */
public class TestDataModel implements INetworkModel {


    @Override
    public void sendOrderList(final OrderRequest request, final ICallBack<OrderResult> callBack) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    ProjectUtil.L.e("sendOrderList- - - > " + request.toString());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    InputStream is = ProjectUtil.getContext().getAssets().open("sendOrderList.json");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                    StringBuffer arr = new StringBuffer();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        arr.append(line);
                    }
                    reader.close();
                    return arr.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                callBack.onResultOK(new Gson().fromJson(result, OrderResult.class));
                super.onPostExecute(result);
            }
        }.execute();
    }

    @Override
    public void sendOrderTotal(final OrderSituationRequest request, final ICallBack<OrderSituationResult> callBack) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    ProjectUtil.L.e("sendOrderTotal- - - > " + request.toString());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    InputStream is = ProjectUtil.getContext().getAssets().open("sendOrderTotal.json");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                    StringBuffer arr = new StringBuffer();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        arr.append(line);
                    }
                    reader.close();
                    return arr.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                callBack.onResultOK(new Gson().fromJson(result, OrderSituationResult.class));
                super.onPostExecute(result);
            }
        }.execute();
    }

    @Override
    public void sendTradeList(final TradeRequest request, final ICallBack<TradeResult> callBack) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    ProjectUtil.L.e("sendTradeList- - - > " + request.toString());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    InputStream is = ProjectUtil.getContext().getAssets().open("sendTradeList.json");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                    StringBuffer arr = new StringBuffer();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        arr.append(line);
                    }
                    reader.close();
                    return arr.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                callBack.onResultOK(new Gson().fromJson(result, TradeResult.class));
                super.onPostExecute(result);
            }
        }.execute();
    }

    @Override
    public void sendTradeTotal(final TradeSituationRequest request, final ICallBack<TradeSituationResult> callBack) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    ProjectUtil.L.e("sendTradeTotal- - - > " + request.toString());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    InputStream is = ProjectUtil.getContext().getAssets().open("sendTradeTotal.json");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                    StringBuffer arr = new StringBuffer();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        arr.append(line);
                    }
                    reader.close();
                    return arr.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                callBack.onResultOK(new Gson().fromJson(result, TradeSituationResult.class));
                super.onPostExecute(result);
            }
        }.execute();
    }

    @Override
    public void sendFrequentList(final FrequentRequest request, final ICallBack<FrequentResult> callBack) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    ProjectUtil.L.e("sendFrequentList- - - > " + request.toString());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    InputStream is = ProjectUtil.getContext().getAssets().open("sendFrequentList.json");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                    StringBuffer arr = new StringBuffer();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        arr.append(line);
                    }
                    reader.close();
                    return arr.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                if (request == null)
                    return;
                callBack.onResultOK(new Gson().fromJson(result, FrequentResult.class));
                super.onPostExecute(result);
            }
        }.execute();
    }

    @Override
    public void sendFrequentTotal(final FrequentTradingRequest request,
                                  final ICallBack<FrequentSituationResult> callBack) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                try {
                    ProjectUtil.L.e("sendFrequentTotal- - - > " + request.toString());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    InputStream is = ProjectUtil.getContext().getAssets().open("sendFrequentTotal.json");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                    StringBuffer arr = new StringBuffer();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        arr.append(line);
                    }
                    reader.close();
                    return arr.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                callBack.onResultOK(new Gson().fromJson(result, FrequentSituationResult.class));
                super.onPostExecute(result);
            }
        }.execute();
    }

    @Override
    public void sendSelectMarket(SelectMarketRequest request, ICallBack<MarketResult> callBack) {

    }

}
