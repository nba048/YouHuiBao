package com.yhb.mvp.view.fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomListViewEx;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.yhb.R;
import com.yhb.base.BaseFragment;
import com.yhb.bean.entity.Order;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.pre.i.IOrderListPre;
import com.yhb.mvp.view.activity.OrderMainActivity;
import com.yhb.mvp.view.adapter.OrderAdapter;
import com.yhb.mvp.view.i.IOrderListView;
import com.yhb.utils.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class OrderHistoryFragment extends BaseFragment implements View.OnClickListener, IOrderListView<Order> ,OnDateSetListener {
    private PullToZoomListViewEx mLvContent;
    private TextView mFootView;
    private TimePickerDialog mTimeDialog;
    private IOrderListPre mPre;
    private ArrayList<Order> mDatasSearch;
    private List<Order> mDatas;
    private OrderAdapter mAdapter;


    @Override
    protected int bindLayout() {
        return R.layout.fragment_order_history;
    }

    @Override
    protected void setInitView(View mRootView) {
        mLvContent = (PullToZoomListViewEx) mRootView.findViewById(R.id.zoom_scrollview);
        View headView = LayoutInflater.from(getlActivity()).inflate(R.layout.order_heard_layout, null, false);
        View zoomView = LayoutInflater.from(getlActivity()).inflate(R.layout.order_zoom_layout, null, false);
        mFootView = (TextView) LayoutInflater.from(getlActivity()).inflate(R.layout.item_spinner_layout, null, false);
        mFootView.setText("下一页");
        mFootView.setTextSize(15);
        mFootView.setPadding(8, 9, 8, 9);
        findHeardViews(headView);
        mLvContent.setHeaderView(headView);
        mLvContent.setZoomView(zoomView);
        mLvContent.getPullRootView().addFooterView(mFootView);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getlActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenWidth = localDisplayMetrics.widthPixels;
//        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth, (int) (4.0F * (mScreenWidth / 16.0F)));
        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth, headView.getHeight());
        mLvContent.setHeaderLayoutParams(localObject);
        mLvContent.getPullRootView().setSelector(R.drawable.list_selector);
        initTimer();
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
        mFootView.setOnClickListener(this);
        mLvContent.getPullRootView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://空闲状态
                        OrderMainActivity.upAnim();
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        OrderMainActivity.downAnim();
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void initTimer() {
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        mTimeDialog = new TimePickerDialog.Builder().setCallBack(this).setCancelStringId("取消").setSureStringId("确定")
                .setTitleStringId("查询时间").setYearText("年").setMonthText("月").setDayText("日").setHourText("时")
                .setMinuteText("分").setCyclic(true).setMinMillseconds(System.currentTimeMillis() - tenYears)
                .setMaxMillseconds(System.currentTimeMillis())
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg)).setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12).build();
    }

    @Override
    protected void setInitData() {
        mPre = ProjectFactroy.newInstanceByOrderListPre(this);
        tvTimeStart.setText(Tools.getDateToString(System.currentTimeMillis() - (1000 * 60 * 60 * 12)));
        tvTimeEnd.setText(Tools.getDateToString(System.currentTimeMillis()));
        mDatasSearch = new ArrayList<Order>();
        mPre.lodingDatas(OrderMainActivity.mCurrentFunction.getUrl(), tvTimeStart.getText().toString(), tvTimeEnd.getText().toString());
        final ArrayList<String> mSpData1 = new ArrayList<>();
        mSpData1.add("买卖标识");
        mSpData1.add("买");
        mSpData1.add("卖");
        mSpData1.add("全部");
        final ArrayList<String> mSpData2 = new ArrayList<>();
        mSpData2.add("委托类别");
        mSpData2.add("订立");
        mSpData2.add("转让");
        mSpData2.add("全部");
        final ArrayList<String> mSpData3 = new ArrayList<>();
        mSpData3.add("委托单价");
        mSpData3.add("由低到高");
        mSpData3.add("由高到低");
        mSpData3.add("默认");
        final ArrayList<String> mSpData4 = new ArrayList<>();
        mSpData4.add("委托数量");
        mSpData4.add("由低到高");
        mSpData4.add("由高到低");
        mSpData4.add("默认");
        spContent1.setAdapter(new ArrayAdapter<String>(getlActivity(), R.layout.item_spinner_layout, mSpData1));
        spContent2.setAdapter(new ArrayAdapter<String>(getlActivity(), R.layout.item_spinner_layout, mSpData2));
        spContent3.setAdapter(new ArrayAdapter<String>(getlActivity(), R.layout.item_spinner_layout, mSpData3));
        spContent4.setAdapter(new ArrayAdapter<String>(getlActivity(), R.layout.item_spinner_layout, mSpData4));
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
    }

    @Override
    public void onClick(View v) {
        if (v == tvTimeEnd) {
            mTimeDialog.show(getChildFragmentManager(), "结束日期");
            mCurrentType = type_end;
        } else if (v == tvTimeStart) {
            mTimeDialog.show(getChildFragmentManager(), "开始日期");
            mCurrentType = type_start;
        } else if (v == submit) {
            OrderMainActivity.mDialog.show();
            mPre.lodingDatas(OrderMainActivity.mCurrentFunction.getUrl(), tvTimeStart.getText().toString(), tvTimeEnd.getText().toString());
        } else if (v == mFootView) {
            OrderMainActivity.mDialog.show();
            mPre.lodingMoreDatas(OrderMainActivity.mCurrentFunction.getUrl(), tvTimeStart.getText().toString(), tvTimeEnd.getText().toString());
        }
    }
    private void initList() {
        mDatasSearch.clear();
        mDatasSearch.addAll(mDatas);
        if (mSpPosition1 == 0 && mSpPosition2 == 0 && mSpPosition3 == 0 && mSpPosition4 == 0) {
            return;
        }
        if (mSpPosition1 != 0) {
            for (Order item : mDatas) {
                if (mSpPosition1 == 2) {
                    if (item.getBsFlags().equals("买")) {
                        mDatasSearch.remove(item);
                        continue;
                    }
                } else if (mSpPosition1 == 1) {
                    if (item.getBsFlags().equals("卖")) {
                        mDatasSearch.remove(item);
                        continue;
                    }
                }
            }
        }
        if (mSpPosition2 != 0) {
            for (Order item : mDatas) {
                if (mSpPosition2 == 2) {
                    if (item.getOrderType().equals("订立")) {
                        mDatasSearch.remove(item);
                        continue;
                    }
                } else if (mSpPosition2 == 1) {
                    if (item.getOrderType().equals("转让")) {
                        mDatasSearch.remove(item);
                        continue;
                    }
                }
            }
        }
        if (mSpPosition3 != 0) {
            if (mSpPosition3 == 1) {
                Collections.sort(mDatasSearch, new Comparator<Order>() {
                    @Override
                    public int compare(Order lhs, Order rhs) {
                        double lh = Double.parseDouble(lhs.getOrderMoney());
                        double rh = Double.parseDouble(rhs.getOrderMoney());
                        return (int) (lh - rh);
                    }
                });
            } else if (mSpPosition3 == 2) {
                Collections.sort(mDatasSearch, new Comparator<Order>() {
                    @Override
                    public int compare(Order lhs, Order rhs) {
                        double lh = Double.parseDouble(lhs.getOrderMoney());
                        double rh = Double.parseDouble(rhs.getOrderMoney());
                        return (int) (rh - lh);
                    }
                });
            }
        }
        if (mSpPosition4 != 0) {
            if (mSpPosition4 == 1) {
                Collections.sort(mDatasSearch, new Comparator<Order>() {
                    @Override
                    public int compare(Order lhs, Order rhs) {
                        Integer lh = Integer.parseInt(lhs.getOrderNum());
                        Integer rh = Integer.parseInt(rhs.getOrderNum());
                        return (lh - rh);
                    }
                });
            } else if (mSpPosition4 == 2) {
                Collections.sort(mDatasSearch, new Comparator<Order>() {
                    @Override
                    public int compare(Order lhs, Order rhs) {
                        Integer lh = Integer.parseInt(lhs.getOrderNum());
                        Integer rh = Integer.parseInt(rhs.getOrderNum());
                        return (rh - lh);
                    }
                });
            }
        }
    }

    @Override
    public void setDatas(List<Order> datas) {
        if (mDatas == null) {
            mDatas = datas;
        } else {
            mDatas.clear();
            mDatas.addAll(datas);
        }
        initList();
    }

    @Override
    public void showList() {
        if (mAdapter == null && mDatas != null) {
            mAdapter = new OrderAdapter(getlActivity(), R.layout.item_order_layout, mDatasSearch);
            mLvContent.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mLvContent.getPullRootView().smoothScrollToPosition(0);
        OrderMainActivity.mDialog.dismiss();
    }

    @Override
    public void showMoreData(List<Order> datas) {
        if (mDatas != null) {
            mDatas.addAll(datas);
            initList();
            OrderMainActivity.mDialog.dismiss();
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
    public void onUpButtonPre() {
        super.onUpButtonPre();
        mLvContent.getPullRootView().smoothScrollToPosition(0);
    }

    @Override
    public void onDromButtonPre() {
        super.onDromButtonPre();
        mLvContent.getPullRootView().smoothScrollToPosition(mAdapter.getCount() - 1);
    }
}
