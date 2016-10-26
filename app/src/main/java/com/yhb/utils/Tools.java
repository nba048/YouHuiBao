package com.yhb.utils;

import android.support.v4.util.LruCache;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.yhb.androidcharts.entity.IStickEntity;
import com.yhb.androidcharts.entity.OHLCEntity;
import com.yhb.base.ProjectUtil;
import com.yhb.bean.entity.AEntity;
import com.yhb.bean.entity.Order;
import com.yhb.bean.response.LocationResult;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

/**
 * Created by CWQ on 2016/9/18.
 */
public class Tools {
    private static LruCache<String, String> mMap = new LruCache<>(1000);

    private static String getLocationUrl(String ip) {
        return "http://apis.juhe.cn/ip/ip2addr?ip=" + ip + "&key=9156c0f57a0d2e59bd4e597b58bfe589";
    }

    public static void shakeAimation(View view) {
        ViewPropertyAnimator.animate(view).translationXBy(ProjectUtil.dip2px(5)).setInterpolator(new CycleInterpolator(3)).setDuration(500).start();
    }

    static SimpleDateFormat sf = new SimpleDateFormat("MM月 dd日 hh:mm");
    static SimpleDateFormat sf_ = new SimpleDateFormat("yyyy-MM-dd");

    public static String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }

    public static String getDateToString_(long time) {
        Date d = new Date(time);
        return sf_.format(d);
    }

    public static Long getDateToLong(String time) {
        try {
            Date parse = sf_.parse(time);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(System.currentTimeMillis()).getTime();
    }
    public static Long getDateToLong_(String time) {
        try {
            Date parse = sf.parse(time);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(System.currentTimeMillis()).getTime();
    }

    /**
     * 使用java.util.Formatter,保留小数点后两位
     */
    public static String formatNumber(double value) {
    /*
     * %.2f % 表示 小数点前任意位数 2 表示两位小数 格式后的结果为 f 表示浮点型
     */
        return new Formatter().format("%.2f", value).toString();
    }

    public static synchronized void sendLocation(final TextView textView, final String ip) {
        if (!ProjectUtil.isEmpty(ip)) {
            String value = mMap.get(ip);
            if (value != null) {
                textView.setText(value);
                return;
            }
            RequestParams request = new RequestParams(getLocationUrl(ip));
            x.http().get(request, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    LocationResult json = new Gson().fromJson(result, LocationResult.class);
                    textView.setText(json.getResult().getArea());
                    mMap.put(ip, json.getResult().getArea());
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
    }

    public static List<IStickEntity> changeDataToChart(List<AEntity> datas) {
        if (datas != null && datas.get(0) instanceof Order) {
            List<IStickEntity> list = new ArrayList<>();
            for (int i = 0; i < datas.size(); i++) {
                Order bean = (Order) datas.get(i);
                int open = Integer.parseInt(bean.getOrderNum());
                list.add(new OHLCEntity(open, (open * 1.1), (open * 0.9), open, i));
            }
            return list;
        } else if (true) {
            return null;
        }
        return null;
    }
}
