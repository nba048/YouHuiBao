package com.yhb.mvp.view.activity;

import android.support.v4.view.ViewPager;
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
public class DetailOrderFullActivity extends BaseActivity {
    private Order orderItem;
    private ViewPager mVpContentOrderDetail;
    private List<String> titleList;
    private List<BaseFragment> fragments;
    private TextView mTvContent;

    @Override
    protected void onLCreate() {
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_detail_order_full;
    }

    @Override
    protected void initView() {
        mVpContentOrderDetail = (ViewPager) findViewById(R.id.vp_comtent_order_detail);
        mTvContent = (TextView) findViewById(R.id.tv_content_);

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
        fragments.add(new OrderDetailFragment_1(orderItem, 1));
        fragments.add(new OrderDetailFragment_2(orderItem));
        fragments.add(new OrderDetailFragment_3(orderItem));
        fragments.add(new OrderDetailFragment_4(orderItem));
        fragments.add(new OrderDetailFragment_5(orderItem));
        mVpContentOrderDetail.setAdapter(new LFragmentPagerAdapter<BaseFragment>(getSupportFragmentManager(), fragments, titleList));
        OrderDetailFragment_1.setOnOrderTextChangeListener(new OrderDetailFragment_1.OnOrderTextChangeListener() {
            @Override
            public void onChangeText(String content) {
                mTvContent.setText(content);
            }
        });
        mVpContentOrderDetail.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvContent.setText("只简单做了第一屏...\n双指触摸可放大缩小……。");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
