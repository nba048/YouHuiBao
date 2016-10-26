package com.yhb.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yhb.widget.PagerTab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/13/013.
 */
public class LFragmentAdapter extends FragmentPagerAdapter {

    protected PagerTab mPagerTab;
    protected List<String> mTitles;
    protected List<Fragment> mFragments;
    protected ViewPager mVpContent;

    public LFragmentAdapter(FragmentManager fm, PagerTab tab, ViewPager pager) {
        super(fm);
        mTitles = new ArrayList<String>();
        mFragments = new ArrayList<Fragment>();
        mVpContent = pager;
        mPagerTab = tab;
    }

    public void addTab(String title, Fragment fragment) {
        mTitles.add(title);
        mFragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mFragments.size() == 1) {
            mPagerTab.setVisibility(View.GONE);
        } else {
            mPagerTab.setVisibility(View.VISIBLE);
        }
        return mTitles.get(position);
    }
}
