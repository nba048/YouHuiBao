/**
 *
 */
package com.yhb.mvp.view.fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.yhb.R;
import com.yhb.base.BaseFragment;

/**
 * @author 宋昌明
 */
public class MeFragment extends BaseFragment {
    private PullToZoomScrollViewEx mScrollView;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void setInitView(View mRootView) {
        mScrollView = (PullToZoomScrollViewEx) mRootView.findViewById(R.id.pull_scrollview);
        View headView = LayoutInflater.from(getlActivity()).inflate(R.layout.profile_headview_me, null, false);
        View zoomView = LayoutInflater.from(getlActivity()).inflate(R.layout.profile_zoomview_me, null, false);
        View contentView = LayoutInflater.from(getlActivity()).inflate(R.layout.profile_content, null, false);
        RelativeLayout viewById = (RelativeLayout) contentView.findViewById(R.id.rl_pre_interview);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new NullPointerException("点击了item1_test_bug____");
            }
        });
        mScrollView.setHeaderView(headView);
        mScrollView.setZoomView(zoomView);
        mScrollView.setScrollContentView(contentView);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getlActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth,
                (int) (7.0F * (mScreenWidth / 16.0F)));
        mScrollView.setHeaderLayoutParams(localObject);
    }

    @Override
    protected void setInitData() {
    }


}
