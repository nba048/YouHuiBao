package com.yhb.mvp.view.activity;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhb.R;
import com.yhb.base.BaseActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import dmax.dialog.SpotsDialog;

/**
 * Created by Administrator on 2016/10/11 0011.
 */
public class WebActivity extends BaseActivity implements View.OnClickListener {
    private WebView mWebview;
    private String mUrl;
    private SpotsDialog mDialog;
    private ImageView mExitView;
    private TextView mTitleView;

    @Override
    protected void onLCreate() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        mExitView = (ImageView) findViewById(R.id.title_iv_back);
        mTitleView = (TextView) findViewById(R.id.title_tv_text);
        mWebview = (WebView) findViewById(R.id.mWebview);
        mTitleView.setText("详情");
        mExitView.setOnClickListener(this);
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mDialog.dismiss();
            }
        });
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mDialog = new SpotsDialog(this, "loding...");
        mDialog.show();
    }


    @Override
    protected void initData() {
        mUrl = getIntent().getStringExtra("news_url");
        if (mUrl != null) {
            final RequestParams request = new RequestParams(mUrl);
            x.http().get(request, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if (result.contains("http://www.cacec.com.cn/")) {
                        Document doc = Jsoup.parse(result);
                        Elements current = doc.select(".wjs_ej_R");
                        mWebview.loadDataWithBaseURL("http://www.cacec.com.cn/", current.html(), "text/html", "utf-8", mUrl);
                        mDialog.dismiss();
                    } else {
                        mWebview.loadUrl(mUrl);
                    }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v == mExitView)
            finish();
    }
}
