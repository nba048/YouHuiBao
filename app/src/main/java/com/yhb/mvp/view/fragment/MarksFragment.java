/**
 *
 */
package com.yhb.mvp.view.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yhb.R;
import com.yhb.base.BaseFragment;
import com.yhb.base.LBaseAdapter;
import com.yhb.base.ProjectApp;
import com.yhb.bean.entity.Market;
import com.yhb.bean.request.SelectMarketRequest;
import com.yhb.bean.response.MarketResult;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.ICallBack;
import com.yhb.mvp.model.i.INetworkModel;
import com.yhb.mvp.view.activity.MarketActivity;
import com.yhb.utils.Tools;

import org.xutils.x;

import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * @author 宋昌明
 */
public class MarksFragment extends BaseFragment implements View.OnClickListener, TextWatcher {

    private SpotsDialog mDialog;
    private GridView mGvContent;
    private List<Market> mMarkets;
    private INetworkModel mModel;
    private Dialog dialog;
    private TextView mDialogTvTitle;
    private EditText mDialogEt1;
    private EditText mDialogEt2;
    private ImageView mDialogIvc1;
    private ImageView mDialogIvc2;
    private RelativeLayout mDialogBtn1;
    private RelativeLayout mDialogBtn2;
    private String mCurrentMarketId;
    private String mCurrentMarketName;

    /**
     * @param markets
     */
    public MarksFragment(List<Market> markets) {
        this.mMarkets = markets;
        mModel = ProjectFactroy.getInstanceByNetworkModel();
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_markes;
    }

    @Override
    protected void setInitView(View mRootView) {
        mGvContent = (GridView) mRootView.findViewById(R.id.lv_content);
        mDialog = new SpotsDialog(getlActivity(), "请稍等...");
    }

    @Override
    protected void setInitData() {
        if (mMarkets != null && mMarkets.size() > 0) {
            mGvContent.setAdapter(new InnerAdapter(getlActivity(), R.layout.item_horder_marks, mMarkets));
        }
        initDialog();
        mGvContent.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.show();
                mCurrentMarketId = "" + mMarkets.get(position).getMarketId();
                mCurrentMarketName = "" + mMarkets.get(position).getMarketName();
                mDialogTvTitle.setText(mCurrentMarketName);
            }
        });
    }

    private void initDialog() {
        View rootView = getlActivity().getLayoutInflater().inflate(R.layout.dialog_input, null);
        mDialogTvTitle = (TextView) rootView.findViewById(R.id.tv_market);
        mDialogEt1 = (EditText) rootView.findViewById(R.id.et_firmId);
        mDialogEt2 = (EditText) rootView.findViewById(R.id.et_password);
        mDialogIvc1 = (ImageView) rootView.findViewById(R.id.iv_clear);
        mDialogIvc2 = (ImageView) rootView.findViewById(R.id.iv_clear2);
        mDialogBtn1 = (RelativeLayout) rootView.findViewById(R.id.rl_dialog_btn_left);
        mDialogBtn2 = (RelativeLayout) rootView.findViewById(R.id.rl_dialog_btn_right);
        mDialogEt1.addTextChangedListener(this);
        mDialogEt2.addTextChangedListener(this);
        mDialogIvc1.setOnClickListener(this);
        mDialogIvc2.setOnClickListener(this);
        mDialogBtn1.setOnClickListener(this);
        mDialogBtn2.setOnClickListener(this);
        Dialog mDialog = new Dialog(getlActivity(), R.style.alert_dialog);
        dialog = mDialog;
        dialog.setContentView(rootView);
    }

    @Override
    public void onClick(View v) {
        if (v == mDialogIvc1) {
            mDialogEt1.setText("");
        } else if (v == mDialogIvc2) {
            mDialogEt2.setText("");
        } else if (v == mDialogBtn1) {
            dialog.dismiss();
        } else if (v == mDialogBtn2) {
            if (mDialogEt1.getText().toString().length() < 2) {
                toastShow("请正确输入！");
                Tools.shakeAimation(mDialogEt1);
                return;
            }
            if (mDialogEt2.getText().toString().length() < 2) {
                toastShow("请正确输入！");
                Tools.shakeAimation(mDialogEt2);
                return;
            }
            requestGongNeng();
        }
    }

    private void requestGongNeng() {
        SelectMarketRequest request = new SelectMarketRequest();
        request.firmId = (mDialogEt1.getText().toString());
        request.password = (mDialogEt2.getText().toString());
        request.marketId = ("1");
        dialog.dismiss();
        mDialog.show();
        mModel.sendSelectMarket(request, new ICallBack<MarketResult>() {
            @Override
            public void onResultOK(MarketResult datas) {
                if (datas != null && datas.code.equals("200")) {
                    Intent intent = new Intent(getlActivity(), MarketActivity.class);
                    intent.putExtra("marketName", mCurrentMarketName);
                    ProjectApp.getCurrentUser().setSessionId(datas.sessionId);
                    getlActivity().startActivity(intent);
                } else {
                    toastShow("" + datas.msg);
                    mDialogEt1.setText("");
                    mDialogEt2.setText("");
                }
                mDialog.dismiss();
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mDialogEt1.getText().length() > 2) {
            mDialogIvc1.setVisibility(View.VISIBLE);
        } else {
            mDialogIvc1.setVisibility(View.INVISIBLE);
        }
        if (mDialogEt2.getText().length() > 2) {
            mDialogIvc2.setVisibility(View.VISIBLE);
        } else {
            mDialogIvc2.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private class InnerAdapter extends LBaseAdapter<Market> {

        /**
         * @param context
         * @param viewid
         * @param objects
         */
        public InnerAdapter(Context context, int viewid, List<Market> objects) {
            super(context, viewid, objects);
        }

        @Override
        protected ViewHolder<Market> createHolder(View v) {
            InnerHolder holder = new InnerHolder();
            holder.iv_tool_item = (ImageView) v.findViewById(R.id.iv_tool_item);
            holder.tv_tool_item = (TextView) v.findViewById(R.id.tv_tool_item);
            return holder;
        }

        @Override
        protected void bindHolder(ViewHolder<Market> h) {
            InnerHolder holder = (InnerHolder) h;
            holder.tv_tool_item.setText(holder.data.getMarketName());
            if (holder.data.getIconUrl() != null && holder.data.getIconUrl().length() > 6)
                x.image().bind(holder.iv_tool_item, holder.data.getIconUrl());
        }

        private class InnerHolder extends ViewHolder<Market> {
            private ImageView iv_tool_item;
            private TextView tv_tool_item;
        }
    }
}
