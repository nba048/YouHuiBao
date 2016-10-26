package com.yhb.mvp.view.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.yhb.R;
import com.yhb.bean.response.MarketResult;
import com.yhb.mvp.model.i.INetworkModel;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class OutherActivity extends AppCompatActivity implements View.OnClickListener, OnDateSetListener {

    private INetworkModel mModel;
    private TextView mTvContent;
    private Spinner mSpSelect;
    private EditText mEtAddress;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private List<String> mContents;
    private TimePickerDialog mTimeDialog;
    private Button mBtn0;
    private long mExitTime;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mSpSelect = (Spinner) findViewById(R.id.sp_select);
        mEtAddress = (EditText) findViewById(R.id.et_address);
        setSupportActionBar(toolbar);
        mBtn0 = (Button) findViewById(R.id.bt_0);
        mBtn1 = (Button) findViewById(R.id.bt_1);
        mBtn2 = (Button) findViewById(R.id.bt_2);
        mBtn3 = (Button) findViewById(R.id.bt_3);
        mBtn4 = (Button) findViewById(R.id.bt_4);
        mBtn5 = (Button) findViewById(R.id.bt_5);
        mBtn4.setText("&");
        mBtn5.setText("=");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestMsg();
                Snackbar.make(view, "请稍候……。", Snackbar.LENGTH_LONG)
                        .setAction("action", null).show();
            }

        });
        initData();
        listener();
        initTime();
    }

    private void requestMsg() {
        final String uri = mEtAddress.getText().toString();
        RequestParams bean = new RequestParams(uri);
        mTvContent.setText(uri + "\n\n" + mTvContent.getText());
        x.http().get(bean, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mTvContent.setText(result + "\n\n" + mTvContent.getText());
                mEtAddress.setText(INetworkModel.host);
                if (uri.contains("selectMarket") && result != null) {
                    MarketResult json = new Gson().fromJson(result, MarketResult.class);
                    if (json.sessionId != null && json.sessionId.length() > 5)
                        mContents.add("sessionId=" + json.sessionId);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void listener() {
        mBtn0.setOnClickListener(this);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
        mBtn5.setOnClickListener(this);
    }

    private void initData() {
        mContents = new ArrayList<String>();
        mContents.add("下拉列表");
        mContents.add(INetworkModel.host + INetworkModel.select_market);
        mContents.add("http://");
        mContents.add("192.168.0.");
        mContents.add("/LogWeb");
        mContents.add("/user/loginOut.do");
        mContents.add("/user/selectMarket.do");
        mContents.add("/order/list.do");
        mContents.add("/order/orderTotal.do");
        mContents.add("/trade/list.do");
        mContents.add("/trade/tradeTotal.do");
        mContents.add("/stare/list.do");
        mContents.add("/stare/details.do");
        mContents.add("marketId=1");
        mContents.add("firmId=100011");
        mContents.add("password=11111111");
        mContents.add("startDate=");
        mContents.add("endDate=");
        mContents.add("pageNo=1");
        mContents.add("pageSize=10");
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mContents);
        mSpSelect.setAdapter(mAdapter);
        mSpSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    return;
                mEtAddress.append(mContents.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initTime() {
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
    public void onClick(View v) {
        if (v == mBtn1) {
            mEtAddress.append(mBtn1.getText());
        } else if (v == mBtn2) {
            mEtAddress.append(mBtn2.getText());
        } else if (v == mBtn3) {
            mEtAddress.append(mBtn3.getText());
        } else if (v == mBtn4) {
            mEtAddress.append(mBtn4.getText());
        } else if (v == mBtn5) {
            mEtAddress.append(mBtn5.getText());
        } else if (v == mBtn0) {
            mTimeDialog.show(getSupportFragmentManager(), "");
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        mEtAddress.append("" + millseconds);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (SystemClock.uptimeMillis() - mExitTime > 5000) {
                Toast.makeText(this, "再按一次退出调试", Toast.LENGTH_SHORT).show();
                mExitTime = SystemClock.uptimeMillis();
            } else {
                OutherActivity.this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}