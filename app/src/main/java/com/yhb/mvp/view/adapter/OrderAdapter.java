/**
 *
 */
package com.yhb.mvp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yhb.R;
import com.yhb.base.LBaseAdapter;
import com.yhb.bean.entity.Order;
import com.yhb.mvp.view.activity.DetailOrderActivity;
import com.yhb.utils.Tools;

import java.util.List;

/**
 * @author 宋昌明
 */
public class OrderAdapter extends LBaseAdapter<Order> {
    private Context mContext;

    /**
     * @param context
     * @param viewid
     * @param objects
     */
    public OrderAdapter(Context context, int viewid, List<Order> objects) {
        super(context, viewid, objects);
        mContext = context;
    }

    @Override
    protected ViewHolder<Order> createHolder(View v) {
        InnerHolder holder = new InnerHolder();
        holder.orderType = (TextView) v.findViewById(R.id.orderType);
        holder.commodityName = (TextView) v.findViewById(R.id.commodityName);
        holder.firmName = (TextView) v.findViewById(R.id.firmName);
        holder.orderMoney = (TextView) v.findViewById(R.id.orderMoney);
        holder.tvMore = (TextView) v.findViewById(R.id.tv_more);
        holder.tvOpen = (TextView) v.findViewById(R.id.tv_open);
        holder.llContent = (LinearLayout) v.findViewById(R.id.ll_content);
        holder.orderNo = (TextView) v.findViewById(R.id.orderNo);
        holder.firmId = (TextView) v.findViewById(R.id.firmId);
        holder.firmIp = (TextView) v.findViewById(R.id.firmIp);
        holder.firmIp_new = (TextView) v.findViewById(R.id.firmIp_new);
        holder.commodityId = (TextView) v.findViewById(R.id.commodityId);
        holder.orderNum = (TextView) v.findViewById(R.id.orderNum);
        holder.orderTime = (TextView) v.findViewById(R.id.orderTime);
        holder.bsFlag = (TextView) v.findViewById(R.id.bsFlags);
        return holder;
    }

    @Override
    protected void bindHolder(ViewHolder<Order> h) {
        final InnerHolder holder = (InnerHolder) h;
        holder.tvOpen.setText("展开");
        holder.llContent.setVisibility(View.GONE);
        if (holder.data.getOrderType() != null && holder.data.getOrderType().equals("转让")) {
            holder.orderType.setText("转让");
            holder.orderType.setTextColor(Color.parseColor("#f16f4f"));
            holder.orderType.setBackgroundResource(R.mipmap.ic_recorder_talk1_rm);
        } else {
            holder.orderType.setText("订立");
            holder.orderType.setTextColor(Color.parseColor("#53cac3"));
            holder.orderType.setBackgroundResource(R.mipmap.ic_recorder_talk1);
        }
        if (holder.data.getBsFlags() != null && holder.data.getBsFlags().equals("买")) {
            holder.bsFlag.setText(holder.data.getBsFlags());
            holder.bsFlag.setTextColor(Color.parseColor("#f16f4f"));
            holder.bsFlag.setBackgroundResource(R.mipmap.ic_recorder_talk1_rm);
        } else {
            holder.bsFlag.setText("卖");
            holder.bsFlag.setTextColor(Color.parseColor("#53cac3"));
            holder.bsFlag.setBackgroundResource(R.mipmap.ic_recorder_talk1);
        }
        holder.commodityName.setText(holder.data.getCommodityName());
        holder.firmName.setText("交易商:" + holder.data.getFirmName());
        holder.orderMoney.setText(Tools.formatNumber(Double.parseDouble(holder.data.getOrderMoney())));
        holder.orderNo.setText(holder.data.getOrderNo());
        holder.firmId.setText(holder.data.getFirmId());
        holder.firmIp.setText(holder.data.getFirmIp());
        Tools.sendLocation(holder.firmIp_new, holder.data.getFirmIp());
        holder.commodityId.setText(holder.data.getCommodityId());
        holder.orderNum.setText(holder.data.getOrderNum());
        holder.orderTime.setText(holder.data.getOrderTime());
        holder.tvMore.setOnClickListener(new OnClickListener<Order>(h) {

            @Override
            public void onClick(View v, ViewHolder<Order> viewHolder) {
                Intent intent = new Intent(mContext, DetailOrderActivity.class);
                intent.putExtra("orderItem", holder.data);
                mContext.startActivity(intent);
            }
        });
        holder.tvOpen.setOnClickListener(new OnClickListener<Order>(h) {

            @Override
            public void onClick(View v, ViewHolder<Order> viewHolder) {
                if (holder.llContent.getVisibility() == View.GONE) {
                    holder.tvOpen.setText("收起");
                    holder.llContent.setVisibility(View.VISIBLE);
                } else {
                    holder.tvOpen.setText("展开");
                    holder.llContent.setVisibility(View.GONE);
                }
            }
        });
    }

    private class InnerHolder extends ViewHolder<Order> {
        private TextView orderType;
        private TextView commodityName;
        private TextView firmName;
        private TextView orderMoney;
        private TextView tvMore;
        private TextView tvOpen;
        private LinearLayout llContent;
        private TextView orderNo;
        private TextView firmId;
        private TextView firmIp;
        private TextView commodityId;
        private TextView orderNum;
        private TextView orderTime;
        private TextView bsFlag;
        private TextView firmIp_new;
    }

}
