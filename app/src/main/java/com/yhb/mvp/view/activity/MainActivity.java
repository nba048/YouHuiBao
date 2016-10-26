package com.yhb.mvp.view.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yhb.R;
import com.yhb.base.BaseActivity;
import com.yhb.base.BaseFragment;
import com.yhb.base.ProjectUtil;
import com.yhb.bean.entity.Frequent;
import com.yhb.bean.entity.FrequentTrading;
import com.yhb.bean.entity.Order;
import com.yhb.bean.entity.OrderSituation;
import com.yhb.bean.entity.Trade;
import com.yhb.bean.entity.TradeSituation;
import com.yhb.bean.request.FrequentRequest;
import com.yhb.bean.request.FrequentTradingRequest;
import com.yhb.bean.request.OrderRequest;
import com.yhb.bean.request.OrderSituationRequest;
import com.yhb.bean.request.TradeRequest;
import com.yhb.bean.request.TradeSituationRequest;
import com.yhb.bean.response.FrequentResult;
import com.yhb.bean.response.FrequentSituationResult;
import com.yhb.bean.response.OrderResult;
import com.yhb.bean.response.OrderSituationResult;
import com.yhb.bean.response.TradeResult;
import com.yhb.bean.response.TradeSituationResult;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.ICallBack;
import com.yhb.mvp.model.i.INetworkModel;
import com.yhb.mvp.view.fragment.MeFragment;
import com.yhb.mvp.view.fragment.ModelFragment;

import java.util.ArrayList;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private INetworkModel mModel;

    @Override
    protected void onLCreate() {
        mModel = ProjectFactroy.getInstanceByNetworkModel();
//        testData();
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        flMainContent = (FrameLayout) findViewById(R.id.fl_main_content);
//        llTab = (LinearLayout) findViewById(R.id.ll_tab);
//        llTab1 = (LinearLayout) findViewById(R.id.ll_tab_1);
        ivTab1 = (ImageView) findViewById(R.id.iv_tab_1);
        tvTab1 = (TextView) findViewById(R.id.tv_tab_1);
        llTab2 = (LinearLayout) findViewById(R.id.ll_tab_2);
        ivTab2 = (ImageView) findViewById(R.id.iv_tab_2);
        llTab3 = (LinearLayout) findViewById(R.id.ll_tab_3);
        ivTab3 = (ImageView) findViewById(R.id.iv_tab_3);
        vContactsPoint = (View) findViewById(R.id.v_contacts_point);
        tvContactsCount = (TextView) findViewById(R.id.tv_contacts_count);
        llTab4 = (LinearLayout) findViewById(R.id.ll_tab_4);
        ivTab4 = (ImageView) findViewById(R.id.iv_tab_4);
        vMyPoint = (View) findViewById(R.id.v_my_point);
        vAnnounce = (View) findViewById(R.id.v_announce);
        tvInterviewTip = (TextView) findViewById(R.id.tv_interview_tip);
        initWidget();
    }


    /**
     */
    private void initWidget() {
        llTab2.setSelected(true);
        llTab2.setOnClickListener(this);
        llTab3.setOnClickListener(this);
        llTab4.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setInitFragment();
    }

    private FrameLayout flMainContent;
    //    private LinearLayout llTab;
//    private LinearLayout llTab1;
    private ImageView ivTab1;
    private TextView tvTab1;
    private LinearLayout llTab2;
    private ImageView ivTab2;
    private LinearLayout llTab3;
    private ImageView ivTab3;
    private View vContactsPoint;
    private TextView tvContactsCount;
    private LinearLayout llTab4;
    private ImageView ivTab4;
    private View vMyPoint;
    private View vAnnounce;
    private TextView tvInterviewTip;
    private ArrayList<BaseFragment> mFragmens;

    @Override
    public void onClick(View v) {
        if (v == llTab2) {
            changeTabBackgrand();
            changeFragment(0);
            ivTab2.setSelected(true);
        } else if (v == llTab3) {
            changeTabBackgrand();
            changeFragment(1);
            ivTab3.setSelected(true);
        } else if (v == llTab4) {
            changeTabBackgrand();
            changeFragment(2);
            ivTab4.setSelected(true);
        }
    }

    private void changeTabBackgrand() {
        ivTab2.setSelected(false);
        ivTab3.setSelected(false);
        ivTab4.setSelected(false);
    }

    private void setInitFragment() {
        mFragmens = new ArrayList<BaseFragment>();
        mFragmens.add(new ModelFragment());
        mFragmens.add(new MeFragment());
        mFragmens.add(new MeFragment());
        getSupportFragmentManager().beginTransaction().add(flMainContent.getId(), mFragmens.get(0))
                .add(flMainContent.getId(), mFragmens.get(1)).add(flMainContent.getId(), mFragmens.get(2))
                .commit();
        changeFragment(0);
        onClick(llTab2);
    }

    private void changeFragment(int index) {
        getSupportFragmentManager().beginTransaction().hide(mFragmens.get(0)).hide(mFragmens.get(1))
                .hide(mFragmens.get(2)).show(mFragmens.get(index)).commit();
    }

    /**
     * test....................
     */

    private void testData() {

        mModel.sendFrequentList(new FrequentRequest(), new ICallBack<FrequentResult>() {

            @Override
            public void onResultOK(FrequentResult datas) {
                for (Frequent item : datas.getData()) {
                    item.save(new SaveListener<String>() {
                        @Override
                        public void done(String arg0, BmobException arg1) {
                            ProjectUtil.L.e(arg0 + " - - > " + arg1.getMessage());
                        }
                    });
                }
            }
        });
        mModel.sendFrequentTotal(new FrequentTradingRequest(), new ICallBack<FrequentSituationResult>() {

            @Override
            public void onResultOK(FrequentSituationResult datas) {
                for (FrequentTrading item : datas.getData()) {
                    item.save(new SaveListener<String>() {
                        @Override
                        public void done(String arg0, BmobException arg1) {
                            ProjectUtil.L.e(arg0 + " - - > " + arg1.getMessage());
                        }
                    });
                }
            }
        });
        mModel.sendOrderList(new OrderRequest(), new ICallBack<OrderResult>() {

            @Override
            public void onResultOK(OrderResult datas) {
                for (Order item : datas.getData()) {
                    item.save(new SaveListener<String>() {
                        @Override
                        public void done(String arg0, BmobException arg1) {
                            ProjectUtil.L.e(arg0 + " - - > " + arg1.getMessage());
                        }
                    });
                }
            }
        });
        mModel.sendOrderTotal(new OrderSituationRequest(), new ICallBack<OrderSituationResult>() {

            @Override
            public void onResultOK(OrderSituationResult datas) {
                for (OrderSituation item : datas.getData()) {
                    item.save(new SaveListener<String>() {
                        @Override
                        public void done(String arg0, BmobException arg1) {
                            ProjectUtil.L.e(arg0 + " - - > " + arg1.getMessage());
                        }
                    });
                }
            }
        });
        mModel.sendTradeList(new TradeRequest(), new ICallBack<TradeResult>() {

            @Override
            public void onResultOK(TradeResult datas) {
                for (Trade item : datas.getData()) {
                    item.save(new SaveListener<String>() {
                        @Override
                        public void done(String arg0, BmobException arg1) {
                            ProjectUtil.L.e(arg0 + " - - > " + arg1.getMessage());
                        }
                    });
                }
            }
        });
        mModel.sendTradeTotal(new TradeSituationRequest(), new ICallBack<TradeSituationResult>() {

            @Override
            public void onResultOK(TradeSituationResult datas) {
                for (TradeSituation item : datas.getData()) {
                    item.save(new SaveListener<String>() {
                        @Override
                        public void done(String arg0, BmobException arg1) {
                            ProjectUtil.L.e(arg0 + " - - > " + arg1.getMessage());
                        }
                    });
                }
            }
        });
    }
}
