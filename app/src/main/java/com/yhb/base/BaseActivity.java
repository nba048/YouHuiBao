package com.yhb.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;

/**
 * Created by Android on 2016/9/6.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static BaseActivity mActivity;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        PgyCrashManager.register(this);
        PgyFeedbackShakeManager.register(this,true);
        onLCreate();
        this.mActivity = this;
        setContentView(getLayoutID());
//        ProjectApp.getmActivitys().add(this);
        initView();
        initData();
    }



    protected abstract void onLCreate();

    public abstract int getLayoutID();

    protected abstract void initView();

    protected abstract void initData();

    public void showToast(String content) {
        ProjectApp.getToast(content).show();
    }

    public static BaseActivity getActivity() {
        return mActivity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ProjectApp.getmActivitys().remove(this);
        PgyCrashManager.unregister();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }
}
