/**
 *
 */
package com.yhb.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomListViewEx;
import com.yhb.R;
import com.yhb.base.BaseActivity;
import com.yhb.base.LBaseAdapter;
import com.yhb.base.ProjectApp;
import com.yhb.bean.entity.Function;
import com.yhb.mvp.model.i.INetworkModel;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * @author 宋昌明
 */
public class MarketActivity extends BaseActivity {
    private PullToZoomListViewEx mLvContent;
    private String mMarketName;
    private List<Function> mFunctions;
    private TextView mTvMarketName;
    private ImageView mIvBack;
    private TextView mTvTitle;
    private InnerAdapter mAdapter;

    @Override
    protected void onLCreate() {
        mFunctions = new ArrayList<>();
        try {
            if (ProjectApp.getCurrentUser().getType().equals("1")) {
                Function item1 = new Function();
                item1.setName("频繁交易");
                item1.setUrl(INetworkModel.host + INetworkModel.frequent_list);
                mFunctions.add(item1);
            }
            BmobQuery<Function> query = new BmobQuery<Function>();
            //按照时间降序
            query.findObjects(new FindListener<Function>() {
                @Override
                public void done(List<Function> list, BmobException e) {
                    mFunctions.addAll(list);
                    if (mAdapter != null) {
                        mAdapter.notifyDataSetChanged();
                    }
                }
            });
            mMarketName = getIntent().getStringExtra("marketName");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_market_list;
    }

    @Override
    protected void initView() {
        mTvTitle = (TextView) findViewById(R.id.title_tv_text);
        mTvTitle.setText("功能列表");
        mLvContent = (PullToZoomListViewEx) findViewById(R.id.zoom_scrollview);
        View headView = LayoutInflater.from(this).inflate(R.layout.market_heard_layout, null, false);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.market_zoom_layout, null, false);
        mLvContent.setHeaderView(headView);
        mLvContent.setZoomView(zoomView);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenWidth = localDisplayMetrics.widthPixels;
        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth,
                (int) (6.0F * (mScreenWidth / 16.0F)));
        mLvContent.setHeaderLayoutParams(localObject);
        mLvContent.getPullRootView().setSelector(R.drawable.list_selector);
        mTvMarketName = (TextView) headView.findViewById(R.id.tv_market_name);
        mIvBack = (ImageView) findViewById(R.id.title_iv_back);
        mIvBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        mTvMarketName.setText(mMarketName);
        if (mFunctions != null) {
            mAdapter = new InnerAdapter(this, R.layout.item_market_layout, mFunctions);
            mLvContent.setAdapter(mAdapter);
        }
        mLvContent.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Intent intent = null;
                    Function item = mFunctions.get(--position);
                    if (item.getUrl().contains(INetworkModel.order_list)) {
                        intent = new Intent(MarketActivity.this, OrderMainActivity.class);
                    } else if (item.getUrl().contains(INetworkModel.trade_list)) {
                        intent = new Intent(MarketActivity.this, TradeListActivity.class);
                    } else if (item.getUrl().contains(INetworkModel.frequent_list)) {
                        intent = new Intent(MarketActivity.this, StareListActivity.class);
                    } else if (item.getUrl().contains(INetworkModel.order_total)) {
                        intent = new Intent(MarketActivity.this, OrderTotalListActivity.class);
                    } else if (item.getUrl().contains(INetworkModel.trade_total)) {
                        intent = new Intent(MarketActivity.this, TradeTotalListActivity.class);
                    } else if (true) {
                        intent = new Intent(MarketActivity.this, ShowListActivity.class);
                    }
                    intent.putExtra("mMarketName", "" + item.getName());
                    intent.putExtra("mFunctions", item);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class InnerAdapter extends LBaseAdapter<Function> {

        /**
         * @param context
         * @param viewid
         * @param objects
         */
        public InnerAdapter(Context context, int viewid, List<Function> objects) {
            super(context, viewid, objects);
        }

        @Override
        protected ViewHolder<Function> createHolder(View v) {
            InnerHolder holder = new InnerHolder();
            holder.iv_image = (TextView) v.findViewById(R.id.iv_image);
            holder.tv_recharge = (TextView) v.findViewById(R.id.tv_recharge);
            return holder;
        }

        @Override
        protected void bindHolder(ViewHolder<Function> h) {
            InnerHolder holder = (InnerHolder) h;
            holder.tv_recharge.setText(holder.data.getName());
            holder.iv_image.setText(holder.data.getName());
            if (ProjectApp.getCurrentUser().getType().equals("1") && holder.data.getName().contains("频繁")) {
                holder.iv_image.setText("Vip");
                holder.iv_image.setTextColor(Color.parseColor("#f16f4f"));
                holder.iv_image.setBackgroundResource(R.mipmap.ic_recorder_talk1_rm);
            } else {
                holder.iv_image.setTextColor(Color.parseColor("#53cac3"));
                holder.iv_image.setBackgroundResource(R.mipmap.ic_recorder_talk1);
                if (holder.data.getName().contains("委托")) {
                    holder.iv_image.setText("委托");
                } else if (holder.data.getName().contains("成交")) {
                    holder.iv_image.setText("成交");
                }
            }
        }

        private class InnerHolder extends ViewHolder<Function> {
            private TextView iv_image;
            private TextView tv_recharge;
        }
    }
}
