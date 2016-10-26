package com.yhb.mvp.view.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhb.R;
import com.yhb.base.BaseActivity;
import com.yhb.base.BaseFragment;
import com.yhb.base.LFragmentPagerAdapter;
import com.yhb.bean.entity.Order;
import com.yhb.mvp.view.fragment.OrderDetailFragment_1;
import com.yhb.mvp.view.fragment.OrderDetailFragment_2;
import com.yhb.mvp.view.fragment.OrderDetailFragment_3;
import com.yhb.mvp.view.fragment.OrderDetailFragment_4;
import com.yhb.mvp.view.fragment.OrderDetailFragment_5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class DetailOrderActivity extends BaseActivity {
    private Order orderItem;
    private ViewPager mVpContentOrderDetail;
    private List<String> titleList;
    private List<BaseFragment> fragments;
    private ImageView titleIvBack;
    private TextView mTvFullView;

    @Override
    protected void onLCreate() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_detail_order;
    }

    @Override
    protected void initView() {
        mVpContentOrderDetail = (ViewPager) findViewById(R.id.vp_comtent_order_detail);
        mTvFullView = (TextView) findViewById(R.id.title_tv_btn_3);
        titleIvBack = (ImageView) findViewById(R.id.title_iv_back);
        titleIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        try {
            orderItem = (Order) getIntent().getSerializableExtra("orderItem");
        } catch (Exception e) {
        }
        titleList = new ArrayList<>();
        fragments = new ArrayList<>();
        titleList.add("委托数量走势");
        titleList.add("委托价格走势");
        titleList.add("委托数量走势");
        titleList.add("委托价格走势");
        titleList.add("委托次数走势");
        fragments.add(new OrderDetailFragment_1(orderItem));
        fragments.add(new OrderDetailFragment_2(orderItem));
        fragments.add(new OrderDetailFragment_3(orderItem));
        fragments.add(new OrderDetailFragment_4(orderItem));
        fragments.add(new OrderDetailFragment_5(orderItem));
        mVpContentOrderDetail.setAdapter(new LFragmentPagerAdapter<BaseFragment>(getSupportFragmentManager(), fragments, titleList));
        mTvFullView.setText("全屏");
        mTvFullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailOrderActivity.this, DetailOrderFullActivity.class));
                finish();
            }
        });
    }
}
