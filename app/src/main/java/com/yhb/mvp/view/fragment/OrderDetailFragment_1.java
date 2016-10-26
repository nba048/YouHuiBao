package com.yhb.mvp.view.fragment;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.yhb.R;
import com.yhb.androidcharts.axis.Axis;
import com.yhb.androidcharts.entity.DateValueEntity;
import com.yhb.androidcharts.entity.IStickEntity;
import com.yhb.androidcharts.entity.ListChartData;
import com.yhb.androidcharts.entity.OHLCEntity;
import com.yhb.androidcharts.view.SlipCandleStickChart;
import com.yhb.base.BaseFragment;
import com.yhb.bean.entity.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class OrderDetailFragment_1 extends BaseFragment {

    private int type;
    protected List<IStickEntity> ohlc;
    protected List<Order> orders;

    SlipCandleStickChart slipcandlestickchart;
    private Order orderItem;
    private TextView mTvContent;

    public OrderDetailFragment_1(Order orderItem) {
        this.orderItem = orderItem;
    }

    public OrderDetailFragment_1(Order orderItem, int type) {
        this.orderItem = orderItem;
        this.type = type;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_order_detail_1;
    }

    @Override
    protected void setInitView(View mRootView) {
        this.slipcandlestickchart = (SlipCandleStickChart) mRootView.findViewById(R.id.slipcandlestickchart);
        mTvContent = (TextView) mRootView.findViewById(R.id.tv_content);
        slipcandlestickchart.setAxisXColor(Color.LTGRAY);
        slipcandlestickchart.setAxisYColor(Color.LTGRAY);
        slipcandlestickchart.setLatitudeColor(Color.GRAY);
        slipcandlestickchart.setLongitudeColor(Color.GRAY);
        slipcandlestickchart.setBorderColor(Color.LTGRAY);
        slipcandlestickchart.setLongitudeFontColor(Color.WHITE);
        slipcandlestickchart.setLatitudeFontColor(Color.WHITE);
    }

    @Override
    protected void setInitData() {


//        ProjectUtil.L.e(""+v.hashCode());
        orders = initOHLC2();
//        ohlc = initohlc();
//        this.ohlc = Tools.changeDataToChart(null);
        initSlipCandleStickChart();
        if (type == 1) {
            mTvContent.setVisibility(View.GONE);
            slipcandlestickchart.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    listener.onChangeText(orders.get(slipcandlestickchart.getSelectedIndex()).toString());
                    return false;
                }
            });
        } else {
            slipcandlestickchart.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mTvContent.setText(
                            orders.get(slipcandlestickchart.getSelectedIndex()).toString());
                    return false;
                }
            });
        }
    }

    private List<IStickEntity> initohlc() {
        // 最大纬线数
        slipcandlestickchart.setLatitudeNum(5);
        // 最大经线数
        slipcandlestickchart.setLongitudeNum(3);
        // 最大价格
        slipcandlestickchart.setMaxValue(500);
        // 最小价格
        slipcandlestickchart.setMinValue(0);

        slipcandlestickchart.setDisplayFrom(0);

        slipcandlestickchart.setDisplayNumber(ohlc.size() - 1);

        slipcandlestickchart.setMinDisplayNumber(5);

//        slipcandlestickchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

        slipcandlestickchart.setDisplayLongitudeTitle(true);
        slipcandlestickchart.setDisplayLatitudeTitle(true);
        slipcandlestickchart.setDisplayLatitude(true);
        slipcandlestickchart.setDisplayLongitude(true);
        slipcandlestickchart.setBackgroundColor(Color.BLACK);

        slipcandlestickchart.setDataQuadrantPaddingTop(5);
        slipcandlestickchart.setDataQuadrantPaddingBottom(5);
        slipcandlestickchart.setDataQuadrantPaddingLeft(5);
        slipcandlestickchart.setDataQuadrantPaddingRight(5);
//      slipcandlestickchart.setAxisYTitleQuadrantWidth(50);
//      slipcandlestickchart.setAxisXTitleQuadrantHeight(20);
        slipcandlestickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        slipcandlestickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_LEFT);
        // 为chart2增加均线
        slipcandlestickchart
                .setStickData(new ListChartData<IStickEntity>(ohlc));
        return null;
    }

    private List<Order> initOHLC2() {
        List<Order> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Order item = new Order();
            item.setId("" + i);
            item.setOrderNum(i + 5 + "");
            item.setFirmName("红宝石");
            item.setFirmIp("192.168.1." + i);
            item.setFirmId("000" + i);
            item.setBsFlags(i % 2 == 0 ? "卖" : "买");
            item.setCommodityName("大西瓜");
            item.setCommodityId("0008");
            item.setFirmId("9527_" + i);
            item.setFirmIp("192.168." + i + ".163");
            item.setFirmName(i % 2 == 0 ? "jarry_" + i : "tom_" + i);
            item.setOrderMoney("" + i * 10);
            item.setOrderNo("010101" + i);
            item.setOrderTime("2016020" + i);
            item.setOrderType("" + (i % 2 == 0 ? "订立" : "转让"));
            list.add(item);
        }
        return list;
    }

    private void initSlipCandleStickChart() {
        // 最大纬线数
        slipcandlestickchart.setLatitudeNum(5);
        // 最大经线数
        slipcandlestickchart.setLongitudeNum(3);
        // 最大价格
        slipcandlestickchart.setMaxValue(50);
        // 最小价格
        slipcandlestickchart.setMinValue(0);

        slipcandlestickchart.setDisplayFrom(0);

        slipcandlestickchart.setDisplayNumber(orders.size() - 1);

        slipcandlestickchart.setMinDisplayNumber(5);

//        slipcandlestickchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

        slipcandlestickchart.setDisplayLongitudeTitle(true);
        slipcandlestickchart.setDisplayLatitudeTitle(true);
        slipcandlestickchart.setDisplayLatitude(true);
        slipcandlestickchart.setDisplayLongitude(true);
        slipcandlestickchart.setBackgroundColor(Color.BLACK);

        slipcandlestickchart.setDataQuadrantPaddingTop(5);
        slipcandlestickchart.setDataQuadrantPaddingBottom(5);
        slipcandlestickchart.setDataQuadrantPaddingLeft(5);
        slipcandlestickchart.setDataQuadrantPaddingRight(5);
//      slipcandlestickchart.setAxisYTitleQuadrantWidth(50);
//      slipcandlestickchart.setAxisXTitleQuadrantHeight(20);
        slipcandlestickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        slipcandlestickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_LEFT);

        ListChartData<IStickEntity> data = new ListChartData<>();
        for (int i = 0; i < orders.size(); i++) {
            int num = Integer.parseInt(orders.get(i).getOrderNum());
            data.add(new OHLCEntity(num + 2, num + 3, num, num + 1, Integer.parseInt(orders.get(i).getOrderTime2())));
        }


//        // 为chart2增加均线
        slipcandlestickchart
                .setStickData(data);

    }

    protected void initOHLC() {
        List<IStickEntity> ohlc = new ArrayList<IStickEntity>();

        this.ohlc = new ArrayList<IStickEntity>();
        for (int i = 1; i < 9; i++) {
            ohlc.add(new OHLCEntity((i + 1), (i + 3), (i + 0), (i + 2), 20130426));
        }
        for (int i = ohlc.size() - 1; i > 1; i--) {
            ohlc.add(new OHLCEntity((i + 2), (i + 3), (i + 0), (i + 1), 20130426));
        }
        // for (int i = ohlc.size(); i > 0; i--) {
        // this.ohlc.add(ohlc.get(i - 1));
        // }

        this.ohlc.addAll(ohlc);
    }

    protected List<DateValueEntity> initMA(int days) {

        if (days < 2) {
            return null;
        }

        List<DateValueEntity> MA5Values = new ArrayList<DateValueEntity>();

        float sum = 0;
        float avg = 0;
        for (int i = 0; i < this.ohlc.size(); i++) {
            float close = (float) ((OHLCEntity) ohlc.get(i)).getClose();
            if (i < days) {
                sum = sum + close;
                avg = sum / (i + 1f);
            } else {
                sum = sum + close
                        - (float) ((OHLCEntity) ohlc.get(i - days)).getClose();
                avg = sum / days;
            }
            MA5Values.add(new DateValueEntity(avg, ohlc.get(i).getDate()));
        }

        return MA5Values;
    }

    public interface OnOrderTextChangeListener {
        void onChangeText(String content);
    }


    private static OnOrderTextChangeListener listener;

    public static void setOnOrderTextChangeListener(OnOrderTextChangeListener listene) {
        listener = listene;
    }
}
