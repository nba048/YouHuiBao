/**
 *
 */
package com.yhb.mvp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.yhb.R;
import com.yhb.base.LBaseAdapter;
import com.yhb.bean.entity.TradeSituation;
import com.yhb.mvp.view.activity.DetailOrderActivity;
import com.yhb.utils.Tools;

import java.util.List;

/**
 * @author 宋昌明
 */
public class TradeTotalAdapter extends LBaseAdapter<TradeSituation> {

    private Context mContext;

    /**
     * @param context
     * @param viewid
     * @param objects
     */
    public TradeTotalAdapter(Context context, int viewid, List<TradeSituation> objects) {
        super(context, viewid, objects);
        this.mContext = context;
    }

    @Override
    protected ViewHolder<TradeSituation> createHolder(View v) {
        InnerHolder holder = new InnerHolder();
        holder.bsFlag = (TextView) v.findViewById(R.id.bsFlag);
        holder.commodityId = (TextView) v.findViewById(R.id.commodityId);
        holder.CommodityName = (TextView) v.findViewById(R.id.CommodityName);
        holder.totalNum = (TextView) v.findViewById(R.id.totalNum);
        holder.tradeMoney = (TextView) v.findViewById(R.id.tradeMoney);
        holder.tvMore = (TextView) v.findViewById(R.id.tv_more);
        return holder;
    }

    @Override
    protected void bindHolder(ViewHolder<TradeSituation> h) {
        final InnerHolder holder = (InnerHolder) h;
        if (holder.data.getTradeType().equals("买")) {
            holder.bsFlag.setText(holder.data.getTradeType());
            holder.bsFlag.setTextColor(Color.parseColor("#f16f4f"));
            holder.bsFlag.setBackgroundResource(R.mipmap.ic_recorder_talk1_rm);
        } else {
            holder.bsFlag.setText(holder.data.getTradeType());
            holder.bsFlag.setTextColor(Color.parseColor("#53cac3"));
            holder.bsFlag.setBackgroundResource(R.mipmap.ic_recorder_talk1);
        }
        holder.commodityId.setText("ID: " + holder.data.getCommodityId());
        holder.CommodityName.setText("商品: " + holder.data.getCommodityName());
        holder.totalNum.setText("成交总量: " + holder.data.getTotalNum());
        holder.tradeMoney.setText("" + Tools.formatNumber(Double.parseDouble(holder.data.getTotalMoney())) + " 总");
        holder.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailOrderActivity.class);
                intent.putExtra("orderItem", holder.data);
                mContext.startActivity(intent);
            }
        });
    }

    private class InnerHolder extends ViewHolder<TradeSituation> {
        private TextView bsFlag;
        private TextView commodityId;
        private TextView CommodityName;
        private TextView totalNum;
        private TextView tradeMoney;
        private TextView tvMore;
    }

}
