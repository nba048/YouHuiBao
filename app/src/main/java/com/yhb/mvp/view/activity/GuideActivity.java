package com.yhb.mvp.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.yhb.R;
import com.yhb.base.ProjectUtil;
import com.yhb.widget.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class GuideActivity extends Activity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        initView();
        initData();
    }

    public int getLayoutID() {
        return R.layout.activity_guide;
    }

    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    @SuppressWarnings("deprecation")
    protected void initData() {
        for (int i = 0; i < imgs.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imgs[i]);
            imageViews.add(imageView);
        }
        MyPagerAdapter adapter = new MyPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private boolean isEnter;

    private int[] imgs = new int[]{R.mipmap.guide_01, R.mipmap.guide_02, R.mipmap.guide_03};
    private List<ImageView> imageViews = new ArrayList<ImageView>();

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            imageView.setScaleType(ScaleType.FIT_XY);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }
    }

    private class MyOnPageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == imageViews.size() - 1 && !isEnter) {
                viewPager.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isEnter = true;
                        final AlertDialog dialog = new SpotsDialog(GuideActivity.this, "正在进入...");
                        dialog.show();
                        viewPager.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                ProjectUtil.SP.putBoolean("isFirst", false);
                                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 3000);
                    }
                }, 500);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
