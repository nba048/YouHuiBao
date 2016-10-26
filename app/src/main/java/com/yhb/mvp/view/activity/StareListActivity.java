/**
 *
 */
package com.yhb.mvp.view.activity;

import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomListViewEx;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.nineoldandroids.animation.ValueAnimator;
import com.yhb.R;
import com.yhb.base.BaseActivity;
import com.yhb.base.ProjectUtil;
import com.yhb.bean.entity.Frequent;
import com.yhb.bean.entity.Function;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.pre.i.IStareListPre;
import com.yhb.mvp.view.adapter.StareAdapter;
import com.yhb.mvp.view.i.IStareListView;
import com.yhb.utils.Tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * @author 宋昌明
 */
public class StareListActivity extends BaseActivity implements OnClickListener, IStareListView<Frequent>, OnDateSetListener, AbsListView.OnScrollListener {

    private ImageView titleIvBack;
    private AutoCompleteTextView tvContent;
    private ImageView titleIvClear;
    private ImageView titleIvBtn;
    private Function mCurrentFunction;
    private String mMarketName;
    private AlertDialog mDialog;
    private TextView tvModelName;
    private ImageButton ibFoot;
    private ImageButton ibTop;
    private boolean isUp;
    private PullToZoomListViewEx mLvContent;
    private IStareListPre mPre;
    private List<Frequent> mDatas;
    private List<Frequent> mDatasSearch;
    private StareAdapter mAdapter;
    private TimePickerDialog mTimeDialog;
    private int mHeight;
    private ArrayAdapter<String> mAutoAdapter;
    private ArrayList<String> mAutoDataList;
    private TextView mFootView;
    private EditText etMinNum;
    private EditText etMaxNum;

    @Override
    protected void onLCreate() {
        try {
            mCurrentFunction = (Function) getIntent().getSerializableExtra("mFunctions");
            mMarketName = getIntent().getStringExtra("mMarketName");
            mPre = ProjectFactroy.newInstanceByStareListPre(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_show_trade_list;
    }

    @Override
    protected void initView() {
        mDialog = new SpotsDialog(this, "加载中...");
        titleIvBack = (ImageView) findViewById(R.id.title_iv_back);
        tvContent = (AutoCompleteTextView) findViewById(R.id.tv_content);
        titleIvClear = (ImageView) findViewById(R.id.title_iv_clear);
        titleIvBtn = (ImageView) findViewById(R.id.title_iv_btn);
        ibFoot = (ImageButton) findViewById(R.id.ib_foot);
        ibTop = (ImageButton) findViewById(R.id.ib_top);
        tvModelName = (TextView) findViewById(R.id.tv_model_name);
        mLvContent = (PullToZoomListViewEx) findViewById(R.id.zoom_scrollview);
        View headView = LayoutInflater.from(this).inflate(R.layout.stare_heard_layout, null, false);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.order_zoom_layout, null, false);
        mFootView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_spinner_layout, null, false);
        mFootView.setText("下一页");
        mFootView.setTextSize(15);
        mFootView.setPadding(8, 11, 8, 11);
        findHeardViews(headView);
        mLvContent.setHeaderView(headView);
        mLvContent.setZoomView(zoomView);
        mLvContent.getPullRootView().addFooterView(mFootView);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenWidth = localDisplayMetrics.widthPixels;
//        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth, (int) (4.0F * (mScreenWidth / 16.0F)));
        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth, tvModelName.getHeight());
        mLvContent.setHeaderLayoutParams(localObject);
        mLvContent.getPullRootView().setSelector(R.drawable.list_selector);
        titleIvClear.setVisibility(View.INVISIBLE);
        titleIvClear.setOnClickListener(this);
        ibFoot.setOnClickListener(this);
        ibTop.setOnClickListener(this);
        titleIvBack.setOnClickListener(this);
        tvModelName.setOnClickListener(this);
        mFootView.setOnClickListener(this);
        initTimer();
        tvContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (tvContent.getText().toString().length() > 0) {
                    titleIvClear.setVisibility(View.VISIBLE);
                } else {
                    titleIvClear.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initTimer() {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        mTimeDialog = new TimePickerDialog.Builder().setCallBack(this).setCancelStringId("取消").setSureStringId("确定")
                .setTitleStringId("选择时间").setYearText("年").setMonthText("月").setDayText("日").setHourText("时")
                .setMinuteText("分").setCyclic(true).setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setMaxMillseconds(System.currentTimeMillis())
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg)).setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12).build();
    }

    private TextView tvTimeStart;
    private TextView tvTimeEnd;
    private TextView submit;
    private static final int type_start = 0;
    private static final int type_end = 1;
    private int mCurrentType = type_start;

    private void findHeardViews(View heardView) {
        etMinNum = (EditText) heardView.findViewById(R.id.minNum);
        etMaxNum = (EditText) heardView.findViewById(R.id.maxNum);
        tvTimeStart = (TextView) heardView.findViewById(R.id.tv_time_start);
        tvTimeEnd = (TextView) heardView.findViewById(R.id.tv_time_end);
        submit = (TextView) heardView.findViewById(R.id.submit);
        tvTimeEnd.setOnClickListener(this);
        tvTimeStart.setOnClickListener(this);
        submit.setOnClickListener(this);
        mLvContent.getPullRootView().setOnScrollListener(this);
    }

    @Override
    protected void initData() {
        tvContent.setHint("交易商ID");
        tvContent.setDropDownAnchor(tvModelName.getId());
        tvTimeStart.setText(Tools.getDateToString(System.currentTimeMillis() - (1000 * 60 * 60 * 12)));
        tvTimeEnd.setText(Tools.getDateToString(System.currentTimeMillis()));
        mDatasSearch = new ArrayList<>();
        tvModelName.setText(mMarketName);
        mPre.lodingDatas(mCurrentFunction.getUrl());
        mDialog.show();

    }

    @Override
    public void onClick(View v) {
        if (v == ibFoot) {
            try {
                mLvContent.getPullRootView().smoothScrollToPosition(mAdapter.getCount() - 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (v == ibTop || v == tvModelName) {
            mLvContent.getPullRootView().smoothScrollToPosition(0);
        } else if (v == titleIvClear) {
            tvContent.setText("");
        } else if (v == titleIvBack) {
            finish();
        } else if (v == tvTimeEnd) {
            mTimeDialog.show(getSupportFragmentManager(), "结束日期");
            mCurrentType = type_end;
        } else if (v == tvTimeStart) {
            mTimeDialog.show(getSupportFragmentManager(), "开始日期");
            mCurrentType = type_start;
        } else if (v == submit) {
            mDialog.show();
            mPre.lodingDatas(mCurrentFunction.getUrl());
        } else if (v == mFootView) {
            mDialog.show();
            mPre.lodingMoreDatas(mCurrentFunction.getUrl());
        }
    }


    public void upAnim() {
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

    public void downAnim() {
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

    @Override
    protected void onResume() {
        super.onResume();
        mHeight = mLvContent.getHeight();
    }

    @Override
    public void setDatas(List<Frequent> datas) {
        if (mDatas == null) {
            mDatas = datas;
        } else {
            mDatas.clear();
            mDatas.addAll(datas);
        }
        initEtNum();
        initList();
    }

    private void initEtNum() {
        if (etMinNum.getText().toString().length() > 0 && etMaxNum.getText().toString().length() > 0) {
            if (etMinNum.getText().toString().equals("0") || etMaxNum.getText().toString().equals("0"))
                return;
            int min = Integer.parseInt(etMinNum.getText().toString());
            int max = Integer.parseInt(etMaxNum.getText().toString());
            ArrayList<Frequent> list = new ArrayList<>();
            list.addAll(mDatas);
            for (Frequent item : list) {
                try {
                    int current = Integer.parseInt(item.getTradeNum());
                    if (current < min) {
                        mDatas.remove(item);
                    } else if (current > max) {
                        mDatas.remove(item);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initList() {
        mDatasSearch.clear();
        mDatasSearch.addAll(mDatas);
        if (mAutoAdapter == null) {
            mAutoDataList = new ArrayList<>();
            for (Frequent item : mDatas) {
                mAutoDataList.add(item.getFirmId());
            }
            resetList();
            mAutoAdapter = new ArrayAdapter<String>(this, R.layout.item_spinner_layout, mAutoDataList);
            tvContent.setAdapter(mAutoAdapter);
        } else {
            mAutoDataList.clear();
            for (Frequent item : mDatas) {
                mAutoDataList.add(item.getFirmId());
            }
            resetList();
            mAutoAdapter.notifyDataSetChanged();
        }
    }

    private void resetList() {
        HashSet<String> set = new HashSet<>();
        set.addAll(mAutoDataList);
        mAutoDataList.clear();
        mAutoDataList.addAll(set);
    }

    @Override
    public void showList() {
        if (mAdapter == null && mDatas != null) {
            mAdapter = new StareAdapter(this, R.layout.item_stare_layout, mDatasSearch);
            mLvContent.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mLvContent.getPullRootView().smoothScrollToPosition(0);
        mDialog.dismiss();
    }

    @Override
    public void showMoreData(List<Frequent> datas) {
        if (mDatas != null) {
            mDatas.addAll(datas);
            initList();
            mDialog.dismiss();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        switch (mCurrentType) {
            case type_start:
                tvTimeStart.setText(Tools.getDateToString(millseconds));
                break;
            case type_end:
                tvTimeEnd.setText(Tools.getDateToString(millseconds));
                break;

            default:
                break;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://空闲状态
                upAnim();
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                downAnim();
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

}
