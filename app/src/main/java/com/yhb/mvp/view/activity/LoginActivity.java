package com.yhb.mvp.view.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.yhb.R;
import com.yhb.base.BaseActivity;
import com.yhb.base.BaseFragment;
import com.yhb.mvp.view.fragment.LoginInputFragment;
import com.yhb.mvp.view.fragment.LoginPhoneFragment;
import com.yhb.widget.ZoomOutPageTransformer;

import java.util.ArrayList;

public class LoginActivity extends BaseActivity implements OnClickListener {

    private ArrayList<BaseFragment> mFragmentList;
    private ArrayList<String> mTitleNames;
    private InnerPagerAdapter mFragmentAdapter;
    private TextView mTvQQ;
    private TextView mTvWX;

    @Override
    protected void onLCreate() {
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_login_man;
    }

    @Override
    protected void initView() {
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        vpContentLogin = (ViewPager) findViewById(R.id.vp_content_login);
        mTvQQ = (TextView) findViewById(R.id.tvQQ);
        mTvWX = (TextView) findViewById(R.id.tvWX);
        mTvQQ.setOnClickListener(this);
        mTvWX.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mTitleNames = new ArrayList<String>();
        mTitleNames.add("手机号登陆");
        mTitleNames.add("用户名登陆");
        mFragmentList = new ArrayList<BaseFragment>();
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_first, mTitleNames.get(0)))
                .addItem(new BottomNavigationItem(R.drawable.ic_second, mTitleNames.get(1))).initialise();

        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                vpContentLogin.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        mFragmentList.add(new LoginPhoneFragment());
        mFragmentList.add(new LoginInputFragment());

        vpContentLogin.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mFragmentAdapter = new InnerPagerAdapter(getSupportFragmentManager());
        vpContentLogin.setAdapter(mFragmentAdapter);
        vpContentLogin.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private BottomNavigationBar mBottomNavigationBar;
    private ViewPager vpContentLogin;

    private class InnerPagerAdapter extends FragmentPagerAdapter {
        public InnerPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public BaseFragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleNames.get(position);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mTvQQ) {
            showToast("QQ登陆开发中...");
        } else if (v == mTvWX) {
            showToast("微信登陆开发中...");
        }
    }
}
