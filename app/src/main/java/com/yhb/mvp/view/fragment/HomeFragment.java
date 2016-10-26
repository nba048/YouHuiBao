/**
 *
 */
package com.yhb.mvp.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;

import com.yhb.R;
import com.yhb.base.BaseFragment;
import com.yhb.base.LBaseAdapter;
import com.yhb.base.LPagerAdapter;
import com.yhb.base.ProjectUtil;
import com.yhb.bean.entity.NewsMsg;
import com.yhb.bean.entity.NewsPic;
import com.yhb.mvp.view.activity.WebActivity;
import com.yhb.widget.DepthPageTransformer;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author 宋昌明
 */
public class HomeFragment extends BaseFragment {

    private ViewPager mVpContent;
    private ListView mlvContent;
    private List<ImageView> mTitleImages;
    private ArrayList<String> mPicsUrls;
    private ArrayList<NewsMsg> mNewsMsgs;
    private InnerAdapter mAdapter;
    private InnerHandler mHandler;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setInitView(View mRootView) {
        mVpContent = (ViewPager) mRootView.findViewById(R.id.vp_content);
        mlvContent = (ListView) mRootView.findViewById(R.id.lv_content);
    }

    @Override
    protected void setInitData() {
        mHandler = new InnerHandler();
        mNewsMsgs = new ArrayList<NewsMsg>();
        mPicsUrls = new ArrayList<String>();
        BmobQuery<NewsPic> query = new BmobQuery<NewsPic>();
        //按照时间降序
        query.order("-createdAt");
        query.findObjects(new FindListener<NewsPic>() {
            @Override
            public void done(List<NewsPic> list, BmobException e) {
                for (NewsPic item : list) {
                    mPicsUrls.add(item.getmPicUrl());
                }
//                initTitles();
                mVpContent.setVisibility(View.GONE);
            }
        });
        BmobQuery<NewsMsg> query2 = new BmobQuery<NewsMsg>();
        //按照时间降序
        query2.findObjects(new FindListener<NewsMsg>() {
            @Override
            public void done(List<NewsMsg> list, BmobException e) {
                mNewsMsgs.addAll(list);
                initMsgs();
            }
        });

    }

    private void initMsgs() {
        if (mNewsMsgs.size() > 0) {
            mAdapter = new InnerAdapter(getlActivity(), R.layout.item_zhidou_recharge, mNewsMsgs);
            mlvContent.setAdapter(mAdapter);
            mlvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getlActivity(), WebActivity.class);
                    intent.putExtra("news_url", mNewsMsgs.get(position).getUrl());
                    startActivity(intent);
                }
            });
        }
    }

    private void initTitles() {
        mTitleImages = new ArrayList<ImageView>();
        for (int i = 0; i < mPicsUrls.size(); i++) {
            final ImageView item = new ImageView(getlActivity());
            item.setImageResource(R.mipmap.bg_banner_advertisement);
            x.image().bind(item, mPicsUrls.get(i));
            item.setScaleType(ScaleType.FIT_XY);
            mTitleImages.add(item);
        }
//        savedatas();
        mVpContent.setAdapter(new LPagerAdapter<ImageView>(mTitleImages));
        mVpContent.setPageTransformer(true, new DepthPageTransformer());
        mHandler.sendEmptyMessageDelayed(1, 4000);
    }

    private void savedatas() {
        NewsMsg msg1 = new NewsMsg();
        msg1.setType("经济·金融衍生品");
        msg1.setTitle("成培元：坚持合规运营，直面行业整合");
        msg1.setTime("2016-10-11");
        msg1.setUrl("http://www.cacec.com.cn/jeecms/mtbd/1991.htm");
        mNewsMsgs.add(msg1);
        NewsMsg msg2 = new NewsMsg();
        msg2.setType("人民网");
        msg2.setTitle("中国工艺艺术品交易所深圳体验馆正式亮相");
        msg2.setTime("2016-10-10");
        msg2.setUrl("http://www.cacec.com.cn/jeecms/mtbd/1689.htm");
        mNewsMsgs.add(msg2);
        NewsMsg msg3 = new NewsMsg();
        msg3.setType("搜狐网");
        msg3.setTitle("中国艺交所深圳体验馆亮相");
        msg3.setTime("2016-10-09");
        msg3.setUrl("http://www.cacec.com.cn/jeecms/mtbd/1688.htm");
        mNewsMsgs.add(msg3);
        NewsMsg msg4 = new NewsMsg();
        msg4.setType("深圳晚报");
        msg4.setTitle("中国工艺艺术品交易所深圳体验馆正式亮相");
        msg4.setTime("2016-10-08");
        msg4.setUrl("http://www.cacec.com.cn/jeecms/mtbd/1690.htm");
        mNewsMsgs.add(msg4);
        NewsMsg msg5 = new NewsMsg();
        msg5.setType("北青网");
        msg5.setTitle("中国艺交所携手香港新华汇富集团、顺峰集团开启艺术品产业新格局");
        msg5.setTime("2016-10-07");
        msg5.setUrl("http://www.cacec.com.cn/jeecms/mtbd/1696.htm");
        mNewsMsgs.add(msg5);
        NewsMsg msg6 = new NewsMsg();
        msg6.setType("中国文化报");
        msg6.setTitle("中国艺交所顺景体验馆在京揭牌");
        msg6.setTime("2016-10-06");
        msg6.setUrl("http://www.cacec.com.cn/jeecms/mtbd/1698.htm");
        mNewsMsgs.add(msg6);
        NewsMsg msg7 = new NewsMsg();
        msg7.setType("网易新闻");
        msg7.setTitle("中国艺交所与尼泊尔国家文化中心签约(组图)");
        msg7.setTime("2016-10-05");
        msg7.setUrl("http://www.cacec.com.cn/jeecms/mtbd/1695.htm");
        mNewsMsgs.add(msg7);
        NewsMsg msg8 = new NewsMsg();
        msg8.setType("人民网");
        msg8.setTitle("中国艺交所与尼泊尔国家文化中心签约");
        msg8.setTime("2016-10-04");
        msg8.setUrl("http://www.cacec.com.cn/jeecms/mtbd/1694.htm");
        mNewsMsgs.add(msg8);
        NewsMsg msg9 = new NewsMsg();
        msg9.setType("中国经济网");
        msg9.setTitle("中国艺交所与尼泊尔国家文化中心签约");
        msg9.setTime("2016-10-03");
        msg9.setUrl("http://www.cacec.com.cn/jeecms/mtbd/1693.htm");
        mNewsMsgs.add(msg9);

        mPicsUrls.add("http://www.cacec.com.cn/jeecms/u/cms/www/201609/211656264r6e.jpg");
        mPicsUrls.add("http://www.cacec.com.cn/jeecms/u/cms/www/201609/211656500asi.jpg");
        mPicsUrls.add("http://www.cacec.com.cn/jeecms/u/cms/www/201605/12165349f49z.jpg");
        for (NewsMsg item : mNewsMsgs) {
            item.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    ProjectUtil.L.e("" + (e == null));
                }
            });
        }
        for (String item : mPicsUrls) {
            NewsPic newsPic = new NewsPic();
            newsPic.setmPicUrl(item);
            newsPic.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    ProjectUtil.L.e("" + (e == null));
                }
            });
        }
    }

    private class InnerAdapter extends LBaseAdapter<NewsMsg> {

        /**
         * @param context
         * @param viewid
         * @param objects
         */
        public InnerAdapter(Context context, int viewid, List<NewsMsg> objects) {
            super(context, viewid, objects);
        }

        @Override
        protected ViewHolder<NewsMsg> createHolder(View v) {
            InnerHolder holder = new InnerHolder();
            holder.iv_image = (ImageView) v.findViewById(R.id.iv_image);
            holder.tv_price = (TextView) v.findViewById(R.id.tv_price);
            holder.tv_bonus = (TextView) v.findViewById(R.id.tv_bonus);
            holder.tv_recharge = (TextView) v.findViewById(R.id.tv_recharge);
            holder.iv_image.setVisibility(View.GONE);
            return holder;
        }

        @Override
        protected void bindHolder(ViewHolder<NewsMsg> h) {
            InnerHolder holder = (InnerHolder) h;
            holder.tv_recharge.setText(holder.data.getTitle());
            holder.tv_bonus.setText("媒体: " + holder.data.getType());
            holder.tv_price.setText(holder.data.getTime());
        }

        private class InnerHolder extends ViewHolder<NewsMsg> {
            private ImageView iv_image;
            private TextView tv_price;
            private TextView tv_recharge;
            private TextView tv_bonus;
        }
    }

    private int mCurrentPosition;

    private class InnerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (mVpContent.getCurrentItem() == mTitleImages.size() - 1) {
                        mCurrentPosition = 0;
                    }
                    mVpContent.setCurrentItem(++mCurrentPosition, true);
                    mHandler.sendEmptyMessageDelayed(1, 4000);
                    break;
            }
        }
    }
}
