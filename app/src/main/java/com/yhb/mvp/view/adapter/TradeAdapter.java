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
import com.yhb.bean.entity.Trade;
import com.yhb.mvp.view.activity.DetailOrderActivity;
import com.yhb.utils.Tools;

import java.util.List;

/**
 * @author 宋昌明
 */
public class TradeAdapter extends LBaseAdapter<Trade> {

    private final Context mContext;

    /**
     * @param context
     * @param viewid
     * @param objects
     */
    public TradeAdapter(Context context, int viewid, List<Trade> objects) {
        super(context, viewid, objects);
        mContext = context;
    }

    @Override
    protected ViewHolder<Trade> createHolder(View v) {
        InnerHolder holder = new InnerHolder();
        holder.tradeType = (TextView) v.findViewById(R.id.tradeType);
        holder.commodityName = (TextView) v.findViewById(R.id.commodityName);
        holder.money = (TextView) v.findViewById(R.id.money);
        holder.canOutMoney = (TextView) v.findViewById(R.id.canOutMoney);
        holder.tradeMoney = (TextView) v.findViewById(R.id.tradeMoney);
        holder.tvMore = (TextView) v.findViewById(R.id.tv_more);
        holder.tvOpen = (TextView) v.findViewById(R.id.tv_open);
        holder.llContent = (LinearLayout) v.findViewById(R.id.ll_content);
        holder.commodityId = (TextView) v.findViewById(R.id.commodityId);
        holder.tradeNo = (TextView) v.findViewById(R.id.tradeNo);
        holder.orderNo = (TextView) v.findViewById(R.id.orderNo);
        holder.firmId = (TextView) v.findViewById(R.id.firmId);
        holder.firmIp = (TextView) v.findViewById(R.id.firmIp);
        holder.tradeTime = (TextView) v.findViewById(R.id.tradeTime);
        holder.canOutPosition = (TextView) v.findViewById(R.id.canOutPosition);
        holder.oppCustomerId = (TextView) v.findViewById(R.id.oppCustomerId);
        holder.oppCustomerIp = (TextView) v.findViewById(R.id.oppCustomerIp);
        holder.firmIp_new = (TextView) v.findViewById(R.id.firmIp_new);
        holder.oppCustomerIp_new = (TextView) v.findViewById(R.id.oppCustomerIp_new);
        return holder;
    }

    @Override
    protected void bindHolder(ViewHolder<Trade> h) {
        final InnerHolder holder = (InnerHolder) h;
        holder.tvOpen.setText("收起");
        holder.llContent.setVisibility(View.VISIBLE);
        if (holder.data.getTradeType().equals("买")) {
            holder.tradeType.setText(holder.data.getTradeType());
            holder.tradeType.setTextColor(Color.parseColor("#f16f4f"));
            holder.tradeType.setBackgroundResource(R.mipmap.ic_recorder_talk1_rm);
        } else {
            holder.tradeType.setText(holder.data.getTradeType());
            holder.tradeType.setTextColor(Color.parseColor("#53cac3"));
            holder.tradeType.setBackgroundResource(R.mipmap.ic_recorder_talk1);
        }
        holder.commodityName.setText("" + holder.data.getCommodityId());
        holder.orderNo.setText(holder.data.getOrderNo());
        holder.firmId.setText(holder.data.getFirmId());
        holder.firmIp.setText(holder.data.getFirmIp());
        holder.commodityId.setText("" + holder.data.getCommodityName());
        holder.money.setText("" + Tools.formatNumber(Double.parseDouble(holder.data.getMoney())) + " 变动");
        holder.canOutMoney.setText("" + Tools.formatNumber(Double.parseDouble(holder.data.getCanOutMoney())) + " 剩余");
        holder.tradeMoney.setText("" + Tools.formatNumber(Double.parseDouble(holder.data.getTradeMoney())) + " 成交价");
        holder.tradeNo.setText(holder.data.getTradeNo());
        holder.tradeTime.setText(holder.data.getTradeTime());
        holder.canOutPosition.setText("" + holder.data.getCanOutPosition());
        holder.oppCustomerId.setText(holder.data.getOppCustomerId());
        holder.oppCustomerIp.setText(holder.data.getOppCustomerIp());
        Tools.sendLocation(holder.firmIp_new, holder.data.getFirmIp());
        Tools.sendLocation(holder.oppCustomerIp_new, holder.data.getOppCustomerIp());
        holder.tvMore.setOnClickListener(new OnClickListener<Trade>(h) {

            @Override
            public void onClick(View v, ViewHolder<Trade> viewHolder) {
                Intent intent = new Intent(mContext, DetailOrderActivity.class);
                intent.putExtra("orderItem", holder.data);
                mContext.startActivity(intent);
            }
        });
        holder.tvOpen.setOnClickListener(new OnClickListener<Trade>(h) {

            @Override
            public void onClick(View v, ViewHolder<Trade> viewHolder) {
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

    private class InnerHolder extends ViewHolder<Trade> {
        private TextView tradeType;
        private TextView commodityName;
        private TextView money;
        private TextView canOutMoney;
        private TextView tradeMoney;
        private TextView tvMore;
        private TextView tvOpen;
        private LinearLayout llContent;
        private TextView commodityId;
        private TextView tradeNo;
        private TextView orderNo;
        private TextView firmId;
        private TextView firmIp;
        private TextView tradeTime;
        private TextView canOutPosition;
        private TextView oppCustomerId;
        private TextView oppCustomerIp;
        private TextView oppCustomerIp_new;
        private TextView firmIp_new;
    }

}
