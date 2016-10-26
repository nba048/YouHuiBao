package com.yhb.mvp.view.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.yhb.R;
import com.yhb.base.BaseActivity;
import com.yhb.base.ProjectUtil;

import dmax.dialog.SpotsDialog;

public class LoadActivity extends BaseActivity {

    private AlertDialog mDialog;
    private InnerHandler mHandler;
    private TextView tvMsgShow;

    @Override
    protected void onLCreate() {
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_load;
    }

    @Override
    protected void initView() {
        mDialog = new SpotsDialog(this, "初始化...");
        tvMsgShow = (TextView) findViewById(R.id.tvMsgShow);
    }

    @Override
    protected void initData() {
        mHandler = new InnerHandler();
        mDialog.show();
        mHandler.sendEmptyMessage(0);
    }

    @SuppressLint("HandlerLeak")
    private class InnerHandler extends Handler {
        private ProjectUtil.ToolNetwork mNetwork;

        @SuppressWarnings("static-access")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    tvMsgShow.setText("正在检查网络……");
                    mNetwork = ProjectUtil.ToolNetwork.getInstance();
                    mNetwork.validateNetWork();
                    mHandler.sendEmptyMessageDelayed(1, 500);
                    break;
                case 1:
                    if (mNetwork == null) {
                        finish();
                    }
                    ProjectUtil.T.show("当前网络: " + mNetwork.getNetworkType());
                    mHandler.sendEmptyMessageDelayed(2, 1000);
                    break;
                case 2:
                    tvMsgShow.setText("检查版本……");
                    mHandler.sendEmptyMessageDelayed(3, 500);
                    break;
                case 3:
                    tvMsgShow.setText("检查数据库……");
                    mHandler.sendEmptyMessageDelayed(4, 500);
                    break;
                case 4:
                    mDialog.dismiss();
                    // 版本检测方式2：带更新回调监听
                    PgyUpdateManager.register(LoadActivity.this, new UpdateManagerListener() {
                        @Override
                        public void onUpdateAvailable(final String result) {

                            final AppBean appBean = getAppBeanFromString(result);
                            new android.support.v7.app.AlertDialog.Builder(LoadActivity.this)
                                    .setMessage("新版本：" + appBean.getVersionCode() + " 是否更新？")
                                    .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startDownloadTask(LoadActivity.this, appBean.getDownloadURL());
                                        }
                                    })
                                    .setNegativeButton("暂不", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            mHandler.sendEmptyMessage(5);
                                        }
                                    })
                                    .setCancelable(false)
                                    .create().show();
                        }

                        @Override
                        public void onNoUpdateAvailable() {
                            showToast("已经是最新版本！");
                            mHandler.sendEmptyMessage(5);
                        }
                    });
                    break;
                case 5:
                    mDialog.dismiss();
                    if (ProjectUtil.SP.getBoolean("isFirst", true)) {
                        startActivity(new Intent(LoadActivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(LoadActivity.this, LoginActivity.class));
                    }
                    finish();
                    break;
                default:
                    break;
            }
        }
    }
}
