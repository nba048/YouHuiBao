/**
 *
 */
package com.yhb.mvp.view.activity;

import android.app.AlertDialog;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nineoldandroids.animation.ValueAnimator;
import com.yhb.R;
import com.yhb.base.BaseActivity;
import com.yhb.base.BaseFragment;
import com.yhb.base.LFragmentPagerAdapter;
import com.yhb.base.ProjectUtil;
import com.yhb.bean.entity.Function;
import com.yhb.mvp.view.fragment.OrderCurrentFragment;
import com.yhb.mvp.view.fragment.OrderHistoryFragment;
import com.yhb.widget.ZoomOutPageTransformer;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

/**
 * @author 宋昌明
 */
public class OrderMainActivity extends BaseActivity implements OnClickListener {
    public static Function mCurrentFunction;
    public static AlertDialog mDialog;
    private ImageView titleIvBack;
    private AutoCompleteTextView tvContent;
    private ImageView titleIvClear;
    private ImageView titleIvBtn;
    private int mCurrentPager;
    private String mMarketName;
    private PagerTabStrip tvModelName;
    private static ImageButton ibFoot;
    private static ImageButton ibTop;
    private static boolean isUp;
    private ViewPager vpContent;
    private ArrayList<String> mTitles;
    private ArrayList<BaseFragment> mFragmens;
    private LinearLayout llTitleCenter;

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
        return R.layout.activity_show_order;
    }

    @Override
    protected void initView() {
        mDialog = new SpotsDialog(this, "加载中...");
        titleIvBack = (ImageView) findViewById(R.id.title_iv_back);
        tvContent = (AutoCompleteTextView) findViewById(R.id.tv_content);
        titleIvClear = (ImageView) findViewById(R.id.title_iv_clear);
        titleIvBtn = (ImageView) findViewById(R.id.title_iv_btn);
        llTitleCenter = (LinearLayout) findViewById(R.id.ll_title_center);
        ibFoot = (ImageButton) findViewById(R.id.ib_foot);
        ibTop = (ImageButton) findViewById(R.id.ib_top);
        tvModelName = (PagerTabStrip) findViewById(R.id.PagerTitleStrip);
        vpContent = (ViewPager) findViewById(R.id.vp_comtent);
        vpContent.setPageTransformer(true, new ZoomOutPageTransformer());
        titleIvClear.setVisibility(View.INVISIBLE);
        llTitleCenter.setVisibility(View.INVISIBLE);
        tvContent.setFocusable(false);
        tvContent.setOnClickListener(this);
        ibFoot.setOnClickListener(this);
        ibTop.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        mDialog.show();
        tvContent.setDropDownAnchor(tvModelName.getId());
        mTitles = new ArrayList<>();
        mFragmens = new ArrayList<BaseFragment>();
        mTitles.add("当前委托");
        mTitles.add("委托历史");
        mFragmens.add(new OrderCurrentFragment());
        mFragmens.add(new OrderHistoryFragment());
        vpContent.setAdapter(new LFragmentPagerAdapter<BaseFragment>(getSupportFragmentManager(), mFragmens, mTitles));
        titleIvClear.setOnClickListener(this);
        titleIvBack.setOnClickListener(this);

        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPager = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == ibFoot) {
            mFragmens.get(mCurrentPager).onDromButtonPre();
        } else if (v == ibTop) {
            mFragmens.get(mCurrentPager).onUpButtonPre();
        } else if (v == titleIvClear) {
            tvContent.setText("");
        } else if (v == titleIvBack) {
            finish();
        } else if (v == tvContent) {
            tvContent.setFocusable(true);
        }
    }


    public static void upAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, ProjectUtil.dip2px(-60));
        animator.setDuration(1000);
        animator.setTarget(ibFoot);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ibFoot.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        ibTop.setVisibility(View.VISIBLE);
        isUp = true;
    }

    public static void downAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(ProjectUtil.dip2px(-60), 0);
        animator.setDuration(1000);
        animator.setTarget(ibFoot);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ibFoot.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        ibTop.setVisibility(View.GONE);
        isUp = false;
    }
}
