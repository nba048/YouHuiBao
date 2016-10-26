/**
 *
 */
package com.yhb.mvp.view.activity;

import android.app.AlertDialog;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.yhb.R;
import com.yhb.base.BaseActivity;
import com.yhb.base.BaseFragment;
import com.yhb.base.LFragmentAdapter;
import com.yhb.base.ProjectApp;
import com.yhb.bean.entity.Function;
import com.yhb.mvp.view.fragment.OrderFragment;
import com.yhb.widget.PagerTab;
import com.yhb.widget.ZoomOutPageTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dmax.dialog.SpotsDialog;

/**
 * @author 宋昌明
 */
public class ShowListActivity extends BaseActivity implements OnClickListener, OnDateSetListener {
    private static final int type_start = 0;
    private static final int type_end = 1;
    public static Function mCurrentFunction;
    public static TextView mTvStart;
    public static TextView mTvEnd;
    public static AlertDialog mDialog;
    private int mCurrentType = type_start;
    private ImageView mIvBack;
    private TextView mTvText;
    private String mMarketName;
    private TextView mTvModelName;
    private TimePickerDialog mTimeDialog;
    private TextView mTvSubmit;
    private PagerTab mPagerTab;
    private ViewPager mViewPager;
    private LFragmentAdapter mTabsAdapter;
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<String> mFragmentNames;

    @Override
    protected void onLCreate() {
        try {
            mCurrentFunction = (Function) getIntent().getSerializableExtra("mFunctions");
            mMarketName = getIntent().getStringExtra("mMarketName");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_show_list;
    }

    @Override
    protected void initView() {
        mDialog = new SpotsDialog(this, "加载中...");
        mIvBack = (ImageView) findViewById(R.id.title_iv_back);
        mTvText = (TextView) findViewById(R.id.title_tv_text);
        mTvStart = (TextView) findViewById(R.id.tv_time_start);
        mTvEnd = (TextView) findViewById(R.id.tv_time_end);
        mTvModelName = (TextView) findViewById(R.id.tv_model_name);
        mTvSubmit = (TextView) findViewById(R.id.submit);
        // 可以滑动的ViewPager
        mPagerTab = (PagerTab) findViewById(R.id.pt_tab_2);
        mViewPager = (ViewPager) findViewById(R.id.vp_content_base_2);
        mIvBack.setOnClickListener(this);
        mTvStart.setOnClickListener(this);
        mTvEnd.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        mTimeDialog = new TimePickerDialog.Builder().setCallBack(this).setCancelStringId("取消").setSureStringId("确定")
                .setTitleStringId("开始时间").setYearText("年").setMonthText("月").setDayText("日").setHourText("时")
                .setMinuteText("分").setCyclic(true).setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setMaxMillseconds(System.currentTimeMillis())
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg)).setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12).build();
    }

    @Override
    protected void initData() {
        mTvStart.setText(getDateToString(System.currentTimeMillis() - (1000 * 60 * 60 * 25)));
        mTvEnd.setText(getDateToString(System.currentTimeMillis()));
        mTvText.setText(mMarketName);
        mTvModelName.setText(mCurrentFunction.getName());
        mTabsAdapter = new LFragmentAdapter(getSupportFragmentManager(), mPagerTab, mViewPager);
        mFragments = new ArrayList<BaseFragment>();
        mFragmentNames = new ArrayList<String>();
        mFragments.add(new OrderFragment());
        mFragments.add(new OrderFragment());
        mFragmentNames.add("委托列表");
        mFragmentNames.add("成交列表");
        if (ProjectApp.getCurrentUser().getType().equals("1")) {
            mFragmentNames.add("频繁交易");
            mFragments.add(new OrderFragment());
        }
        for (int i = 0; i < mFragmentNames.size(); i++) {
            mTabsAdapter.addTab(mFragmentNames.get(i), mFragments.get(i));
        }
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mTabsAdapter);
        mPagerTab.setViewPager(mViewPager);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPagerTab.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                if (arg0 == 0) {
                    mFragments.get(arg0).setUserVisibleHint(true);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == mIvBack) {
            finish();
        } else if (v == mTvStart) {
            mTimeDialog.show(getSupportFragmentManager(), "开始日期");
            mCurrentType = type_start;
        } else if (v == mTvEnd) {
            mTimeDialog.show(getSupportFragmentManager(), "结束日期");
            mCurrentType = type_end;
        } else if (v == mTvSubmit) {
            mDialog.show();
            mFragments.get(mViewPager.getCurrentItem()).setUserVisibleHint(true);
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        switch (mCurrentType) {
            case type_start:
                mTvStart.setText(getDateToString(millseconds));
                break;
            case type_end:
                mTvEnd.setText(getDateToString(millseconds));
                break;

            default:
                break;
        }
    }

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

}
