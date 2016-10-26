/**
 *
 */
package com.yhb.mvp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.yhb.R;
import com.yhb.base.LBaseAdapter;
import com.yhb.bean.entity.Frequent;
import com.yhb.mvp.view.activity.StareDetailListActivity;
import com.yhb.utils.Tools;

import java.util.List;

/**
 * @author 宋昌明
 */
public class StareAdapter extends LBaseAdapter<Frequent> {

    private Context mContext;

    /**
     * @param context
     * @param viewid
     * @param objects
     */
    public StareAdapter(Context context, int viewid, List<Frequent> objects) {
        super(context, viewid, objects);
        this.mContext = context;
    }

    @Override
    protected ViewHolder<Frequent> createHolder(View v) {
        InnerHolder holder = new InnerHolder();
        holder.tradeNum = (TextView) v.findViewById(R.id.tradeNum);
        holder.firmId = (TextView) v.findViewById(R.id.firmId);
        holder.startTime = (TextView) v.findViewById(R.id.startTime);
        holder.endTime = (TextView) v.findViewById(R.id.endTime);
        holder.tvMore = (TextView) v.findViewById(R.id.tv_more);
        return holder;
    }

    @Override
    protected void bindHolder(ViewHolder<Frequent> h) {
        final InnerHolder holder = (InnerHolder) h;
        holder.tradeNum.setText(holder.data.getTradeNum() + "次");
        holder.firmId.setText("交易商： " + holder.data.getFirmId());
        holder.startTime.setText("开始： " + Tools.getDateToString(Long.parseLong(holder.data.getStartTime())));
        holder.endTime.setText("结束： " + Tools.getDateToString(Long.parseLong(holder.data.getEndTime())));
        holder.tvMore.setOnClickListener(new OnClickListener<Frequent>(h) {
            @Override
            public void onClick(View v, ViewHolder<Frequent> viewHolder) {
                Intent intent = new Intent(mContext, StareDetailListActivity.class);
                intent.putExtra("mFrequents", holder.data);
                mContext.startActivity(intent);
            }
        });
    }

    private class InnerHolder extends ViewHolder<Frequent> {
        private TextView tradeNum;
        private TextView firmId;
        private TextView startTime;
        private TextView endTime;
        private TextView tvMore;
    }

}
