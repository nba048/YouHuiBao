/**
 *
 */
package com.yhb.mvp.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.yhb.R;
import com.yhb.base.BaseFragment;
import com.yhb.base.LBaseAdapter;
import com.yhb.base.ProjectApp;
import com.yhb.bean.entity.Market;
import com.yhb.bean.entity.Model;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import dmax.dialog.SpotsDialog;

/**
 * @author 宋昌明
 */
public class ModelFragment extends BaseFragment {

    private GridView mGvContent;
    private FrameLayout mFlContent;
    private AlertDialog mDialog;
    private TextView mTvModelName;
    private List<Model> mModels;
    private InnerAdapter mAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_model;
    }

    @Override
    protected void setInitView(View mRootView) {
        mGvContent = (GridView) mRootView.findViewById(R.id.gv_datashow);
        mFlContent = (FrameLayout) mRootView.findViewById(R.id.fl_comtent);
        mTvModelName = (TextView) mRootView.findViewById(R.id.tv_model_name);
        mDialog = new SpotsDialog(getlActivity(), "请稍候...");
        mDialog.show();
    }

    @Override
    protected void setInitData() {
        mModels = new ArrayList<>();
        Model home = new Model();
        home.setModelId("0");
        home.setModelName("首页资讯");
        mModels.add(home);
        final BmobQuery<Model> query = new BmobQuery<Model>();
        //按照时间降序
        query.order("-createdAt");
        //执行查询，第一个参数为上下文，第二个参数为查找的回调
        query.findObjects(new FindListener<Model>() {
            @Override
            public void done(List<Model> list, BmobException e) {
                mModels.addAll(list);
                mDialog.dismiss();
                ProjectApp.getCurrentUser().setmModels(mModels);
                mAdapter.notifyDataSetChanged();
            }
        });
        mAdapter = new InnerAdapter(getlActivity(), R.layout.item_horder_model, mModels);
        mGvContent.setAdapter(mAdapter);
        changeFragment(new HomeFragment());
        mGvContent.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    changeFragment(new HomeFragment());
                    return;
                }
                mDialog.show();
                BmobQuery<Market> query = new BmobQuery<Market>();
                query.addWhereEqualTo("modelId", "" + position);
                query.findObjects(new FindListener<Market>() {
                    @Override
                    public void done(List<Market> list, BmobException e) {
                        if (e == null && list != null) {
                            mDialog.dismiss();
                            changeFragment(new MarksFragment(list));
                        }
                    }
                });
            }
        });
    }

    private void changeFragment(BaseFragment fragment) {
        mFlContent.removeAllViews();
        getChildFragmentManager().beginTransaction().replace(mFlContent.getId(), fragment).commit();
    }

    private class InnerAdapter extends LBaseAdapter<Model> {
        /**
         * @param context
         * @param viewid
         * @param objects
         */
        public InnerAdapter(Context context, int viewid, List<Model> objects) {
            super(context, viewid, objects);
        }

        @Override
        protected ViewHolder<Model> createHolder(View v) {
            InnerHolder holder = new InnerHolder();
            holder.mTvItemName = (TextView) v.findViewById(R.id.tv_tool_item);
            return holder;
        }

        @Override
        protected void bindHolder(ViewHolder<Model> h) {
            InnerHolder holder = (InnerHolder) h;
            holder.mTvItemName.setText(holder.data.getModelName());
        }

        private class InnerHolder extends ViewHolder<Model> {
            private TextView mTvItemName;
        }
    }
}
