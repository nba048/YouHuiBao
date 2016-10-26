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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomListViewEx;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.nineoldandroids.animation.ValueAnimator;
import com.yhb.R;
import com.yhb.base.BaseActivity;
import com.yhb.base.ProjectUtil;
import com.yhb.bean.entity.Function;
import com.yhb.bean.entity.Trade;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.pre.i.ITradeListPre;
import com.yhb.mvp.view.adapter.TradeAdapter;
import com.yhb.mvp.view.i.ITradeListView;
import com.yhb.utils.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * @author 宋昌明
 */
public class TradeListActivity extends BaseActivity implements OnClickListener, ITradeListView<Trade>, OnDateSetListener, AbsListView.OnScrollListener {

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
    private ITradeListPre mPre;
    private List<Trade> mDatas;
    private List<Trade> mDatasSearch;
    private TradeAdapter mAdapter;
    private TimePickerDialog mTimeDialog;
    private int mHeight;
    private ArrayAdapter<String> mAutoAdapter;
    private ArrayList<String> mAutoDataList;
    private TextView mFootView;

    @Override
    protected void onLCreate() {
        try {
            mCurrentFunction = (Function) getIntent().getSerializableExtra("mFunctions");
            mMarketName = getIntent().getStringExtra("mMarketName");
            mPre = ProjectFactroy.newInstanceByTradeListPre(this);
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
        View headView = LayoutInflater.from(this).inflate(R.layout.order_heard_layout, null, false);
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

    private Spinner spContent1;
    private Spinner spContent2;
    private Spinner spContent3;
    private Spinner spContent4;
    private TextView tvTimeStart;
    private TextView tvTimeEnd;
    private TextView submit;
    private int mSpPosition1;
    private int mSpPosition2;
    private int mSpPosition3;
    private int mSpPosition4;
    private static final int type_start = 0;
    private static final int type_end = 1;
    private int mCurrentType = type_start;

    private void findHeardViews(View heardView) {
        spContent1 = (Spinner) heardView.findViewById(R.id.sp_content_1);
        spContent2 = (Spinner) heardView.findViewById(R.id.sp_content_2);
        spContent3 = (Spinner) heardView.findViewById(R.id.sp_content_3);
        spContent4 = (Spinner) heardView.findViewById(R.id.sp_content_4);
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
        tvContent.setDropDownAnchor(tvModelName.getId());
        tvTimeStart.setText(Tools.getDateToString(System.currentTimeMillis() - (1000 * 60 * 60 * 12)));
        tvTimeEnd.setText(Tools.getDateToString(System.currentTimeMillis()));
        mDatasSearch = new ArrayList<>();
        tvModelName.setText(mMarketName);
        mPre.lodingDatas(mCurrentFunction.getUrl(), tvTimeStart.getText().toString(), tvTimeEnd.getText().toString());
        final ArrayList<String> mSpData1 = new ArrayList<>();
        mSpData1.add("交易类型");
        mSpData1.add("买");
        mSpData1.add("卖");
        mSpData1.add("全部");
        final ArrayList<String> mSpData2 = new ArrayList<>();
        mSpData2.add("成交价格");
        mSpData2.add("由低到高");
        mSpData2.add("由高到低");
        mSpData2.add("全部");
        final ArrayList<String> mSpData3 = new ArrayList<>();
        mSpData3.add("变动资金");
        mSpData3.add("由低到高");
        mSpData3.add("由高到低");
        mSpData3.add("默认");
        final ArrayList<String> mSpData4 = new ArrayList<>();
        mSpData4.add("剩余持仓");
        mSpData4.add("由低到高");
        mSpData4.add("由高到低");
        mSpData4.add("默认");
        spContent1.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner_layout, mSpData1));
        spContent2.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner_layout, mSpData2));
        spContent3.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner_layout, mSpData3));
        spContent4.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner_layout, mSpData4));
        spContent1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 || position == mSpData1.size() - 1) {
                    mSpPosition1 = 0;
                } else {
                    mSpPosition1 = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spContent2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 || position == mSpData2.size() - 1) {
                    mSpPosition2 = 0;
                } else {
                    mSpPosition2 = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spContent3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 || position == mSpData3.size() - 1) {
                    mSpPosition3 = 0;
                } else {
                    mSpPosition3 = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spContent4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 || position == mSpData4.size() - 1) {
                    mSpPosition4 = 0;
                } else {
                    mSpPosition4 = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mDialog.show();

    }

    @Override
    public void onClick(View v) {
        if (v == ibFoot) {
            mLvContent.getPullRootView().smoothScrollToPosition(mAdapter.getCount() - 1);
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
            mPre.lodingDatas(mCurrentFunction.getUrl(), tvTimeStart.getText().toString(), tvTimeEnd.getText().toString());
        } else if (v == mFootView) {
            mDialog.show();
            mPre.lodingMoreDatas(mCurrentFunction.getUrl(), tvTimeStart.getText().toString(), tvTimeEnd.getText().toString());
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
    public void setDatas(List<Trade> datas) {
        if (mDatas == null) {
            mDatas = datas;
        } else {
            mDatas.clear();
            mDatas.addAll(datas);
        }
        initList();
    }

    private void initList() {
        mDatasSearch.clear();
        mDatasSearch.addAll(mDatas);
        if (mAutoAdapter == null) {
            mAutoDataList = new ArrayList<>();
            for (Trade item : mDatas) {
                mAutoDataList.add(item.getCommodityId());
                mAutoDataList.add(item.getCommodityName());
                mAutoDataList.add(item.getFirmIp());
                mAutoDataList.add(item.getTradeNo());
            }
            resetList();
            mAutoAdapter = new ArrayAdapter<String>(this, R.layout.item_spinner_layout, mAutoDataList);
            tvContent.setAdapter(mAutoAdapter);
        } else {
            mAutoDataList.clear();
            for (Trade item : mDatas) {
                mAutoDataList.add(item.getCommodityId());
                mAutoDataList.add(item.getCommodityName());
                mAutoDataList.add(item.getFirmIp());
                mAutoDataList.add(item.getTradeNo());
            }
            resetList();
            mAutoAdapter.notifyDataSetChanged();
        }
        if (mSpPosition1 == 0 && mSpPosition2 == 0 && mSpPosition3 == 0 && mSpPosition4 == 0) {
            return;
        }
        if (mSpPosition1 != 0) {
            for (Trade item : mDatas) {
                if (mSpPosition1 == 2) {
                    if (item.getTradeType().equals("买")) {
                        mDatasSearch.remove(item);
                        continue;
                    }
                } else if (mSpPosition1 == 1) {
                    if (item.getTradeType().equals("卖")) {
                        mDatasSearch.remove(item);
                        continue;
                    }
                }
            }
        }
        if (mSpPosition2 != 0) {
            if (mSpPosition2 == 1) {
                Collections.sort(mDatasSearch, new Comparator<Trade>() {
                    @Override
                    public int compare(Trade lhs, Trade rhs) {
                        double lh = Double.parseDouble(lhs.getTradeMoney());
                        double rh = Double.parseDouble(rhs.getTradeMoney());
                        return (int) (lh - rh);
                    }
                });
            } else if (mSpPosition2 == 2) {
                Collections.sort(mDatasSearch, new Comparator<Trade>() {
                    @Override
                    public int compare(Trade lhs, Trade rhs) {
                        double lh = Double.parseDouble(lhs.getTradeMoney());
                        double rh = Double.parseDouble(rhs.getTradeMoney());
                        return (int) (rh - lh);
                    }
                });
            }
        }
        if (mSpPosition3 != 0) {
            if (mSpPosition3 == 1) {
                Collections.sort(mDatasSearch, new Comparator<Trade>() {
                    @Override
                    public int compare(Trade lhs, Trade rhs) {
                        double lh = Double.parseDouble(lhs.getMoney());
                        double rh = Double.parseDouble(rhs.getMoney());
                        return (int) (lh - rh);
                    }
                });
            } else if (mSpPosition3 == 2) {
                Collections.sort(mDatasSearch, new Comparator<Trade>() {
                    @Override
                    public int compare(Trade lhs, Trade rhs) {
                        double lh = Double.parseDouble(lhs.getMoney());
                        double rh = Double.parseDouble(rhs.getMoney());
                        return (int) (rh - lh);
                    }
                });
            }
        }
        if (mSpPosition4 != 0) {
            if (mSpPosition4 == 1) {
                Collections.sort(mDatasSearch, new Comparator<Trade>() {
                    @Override
                    public int compare(Trade lhs, Trade rhs) {
                        Integer lh = Integer.parseInt(lhs.getCanOutPosition());
                        Integer rh = Integer.parseInt(rhs.getCanOutPosition());
                        return (lh - rh);
                    }
                });
            } else if (mSpPosition4 == 2) {
                Collections.sort(mDatasSearch, new Comparator<Trade>() {
                    @Override
                    public int compare(Trade lhs, Trade rhs) {
                        Integer lh = Integer.parseInt(lhs.getCanOutPosition());
                        Integer rh = Integer.parseInt(rhs.getCanOutPosition());
                        return (rh - lh);
                    }
                });
            }
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
            mAdapter = new TradeAdapter(this, R.layout.item_trade_layout, mDatasSearch);
            mLvContent.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mLvContent.getPullRootView().smoothScrollToPosition(0);
        mDialog.dismiss();
    }

    @Override
    public void showMoreData(List<Trade> datas) {
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
