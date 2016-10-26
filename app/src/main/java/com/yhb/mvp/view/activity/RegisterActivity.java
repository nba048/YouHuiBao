package com.yhb.mvp.view.activity;

import android.app.AlertDialog;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.CycleInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewPropertyAnimator;
import com.yhb.R;
import com.yhb.base.BaseActivity;
import com.yhb.base.ProjectUtil;
import com.yhb.bean.response.User;
import com.yhb.utils.Tools;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import dmax.dialog.SpotsDialog;

public class RegisterActivity extends BaseActivity implements TextWatcher, OnClickListener {

    private InnerHandler mHandler;
    private TextView tvCodeMsg;
    private AlertDialog mDialog;
    private ImageView ivClear2;
    private ImageView ivClear3;
    private EditText etPassWord3;
    private EditText etPassWord2;

    @Override
    protected void onLCreate() {
        mHandler = new InnerHandler();
        mDialog = new SpotsDialog(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_get_started;
    }

    @Override
    protected void initView() {
        etPhone = (EditText) findViewById(R.id.et_phone);
        ivClear1 = (ImageView) findViewById(R.id.iv_clear);
        etConfirmationCode = (EditText) findViewById(R.id.et_confirmation_code);
        ivResultCode = (ImageView) findViewById(R.id.iv_result_code);
        llSendCode = (LinearLayout) findViewById(R.id.ll_send_code);
        tvSendCode = (TextView) findViewById(R.id.tv_send_code);
        llVoiceCall = (LinearLayout) findViewById(R.id.ll_voice_call);
        tvConfirmationCode = (TextView) findViewById(R.id.tv_confirmation_code);
        tvConfirm = (Button) findViewById(R.id.tv_confirm);
        tvUserProtocol = (TextView) findViewById(R.id.tv_user_protocol);
        tvPasswordLogin = (TextView) findViewById(R.id.tv_password_login);
        tvCodeMsg = (TextView) findViewById(R.id.tvCodeMsg);

        ivClear2 = (ImageView) findViewById(R.id.iv_clear2);
        ivClear3 = (ImageView) findViewById(R.id.iv_clear3);
        etPassWord2 = (EditText) findViewById(R.id.et_password2);
        etPassWord3 = (EditText) findViewById(R.id.et_password3);


        etPhone.addTextChangedListener(this);
        etPassWord2.addTextChangedListener(this);
        etPassWord3.addTextChangedListener(this);
        etConfirmationCode.addTextChangedListener(this);
        ivClear1.setOnClickListener(this);
        ivClear2.setOnClickListener(this);
        ivClear3.setOnClickListener(this);
        tvSendCode.setOnClickListener(this);
        tvConfirmationCode.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        tvPasswordLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tvConfirmationCode.setEnabled(false);
    }

    private EditText etPhone;
    private ImageView ivClear1;
    private EditText etConfirmationCode;
    private ImageView ivResultCode;
    private LinearLayout llSendCode;
    private TextView tvSendCode;
    private LinearLayout llVoiceCall;
    private TextView tvConfirmationCode;
    private Button tvConfirm;
    private TextView tvUserProtocol;
    private TextView tvPasswordLogin;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (etPhone.getText().length() > 2) {
            ivClear1.setVisibility(View.VISIBLE);
        } else {
            ivClear1.setVisibility(View.INVISIBLE);
        }
        if (etPassWord2.getText().length() > 2) {
            ivClear2.setVisibility(View.VISIBLE);
        } else {
            ivClear2.setVisibility(View.INVISIBLE);
        }
        if (etPassWord3.getText().length() > 2) {
            ivClear3.setVisibility(View.VISIBLE);
        } else {
            ivClear3.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        if (v == ivClear1) {
            etPhone.setText("");
        } else if (v == tvSendCode) {
            mHandler.sendEmptyMessage(0);
        } else if (v == tvConfirmationCode) {
            mHandler.sendEmptyMessage(0);
            etConfirmationCode.setText("");
        } else if (v == tvConfirm) {
            String phone = etPhone.getText().toString();
            String pass2 = etPassWord2.getText().toString();
            String pass3 = etPassWord3.getText().toString();
            if (ProjectUtil.isEmpty(phone)) {
                etPhone.requestFocus();
                etPhone.setError("请填写！");
                Tools.shakeAimation(etPhone);
                mDialog.dismiss();
                return;
            }
            if (!ProjectUtil.StringUtil.isPhone(phone)) {
                etPhone.requestFocus();
                etPhone.setError("格式不对！");
                Tools.shakeAimation(etPhone);
                mDialog.dismiss();
                return;
            }
            if (!pass2.equals(pass3)) {
                etPassWord2.requestFocus();
                etPassWord2.setError("两次不一样！");
                Tools.shakeAimation(etPassWord2);
                Tools.shakeAimation(etPassWord3);
                mDialog.dismiss();
                return;
            }
            if (pass2.length() < 4) {
                etPassWord2.requestFocus();
                etPassWord2.setError("密码太短！");
                Tools.shakeAimation(etPassWord2);
                mDialog.dismiss();
                return;
            }
            if (pass3.length() < 4) {
                etPassWord3.requestFocus();
                etPassWord3.setError("密码太短！");
                Tools.shakeAimation(etPassWord2);
                mDialog.dismiss();
                return;
            }
            startRegist();
        } else if (v == tvPasswordLogin) {
            finish();
        } else if (v == ivClear2) {
            etPassWord2.setText("");
        } else if (v == ivClear3) {
            etPassWord3.setText("");
        }
    }

    private void startRegist() {
        mDialog.show();
        User user = new User();
        user.setUsername(etPhone.getText().toString());
        user.setMobilePhoneNumber(etPhone.getText().toString());
        user.setPassword(etPassWord2.getText().toString());
        user.setType("0");
        user.signOrLogin(etConfirmationCode.getText().toString(), new MySaveListener());
    }

    private class TimeThread extends Thread {
        private int mCurrentTime = 90;

        @Override
        public void run() {
            while (mCurrentTime > 0 && isRun) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (mCurrentTime != 1)
                            tvSendCode.setText("" + (mCurrentTime--));
                        if (mCurrentTime == 88) {
                            BmobSMS.requestSMSCode(etPhone.getText().toString(), "yhb", new MyQueryListener());
                        }
                    }
                });
                try {
                    Thread.sleep(999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mCurrentTime == 1) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            etPhone.setEnabled(true);
                            tvSendCode.setEnabled(true);
                            tvSendCode.setText("重发");
                            isRun = false;
                        }
                    });
                }
            }
        }
    }

    private class InnerHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (ProjectUtil.StringUtil.isPhone(etPhone.getText().toString())) {
                        startRequestCode();
                    } else {
                        showToast("请输入正确手机号 ^_^");
                        shakeAimation(etPhone);
                    }
                    break;
                case 1:
                    mDialog.dismiss();
                    break;
                default:
                    break;
            }
        }
    }

    private static boolean isRun = false;
    private boolean isCodeOk = false;


    public void shakeAimation(View view) {
        ViewPropertyAnimator.animate(view).translationXBy(ProjectUtil.dip2px(5))
                .setInterpolator(new CycleInterpolator(3)).setDuration(500).start();
    }

    public void startRequestCode() {
        if (isRun) {
            return;
        }
        isRun = true;
        new TimeThread().start();
        etPhone.setEnabled(false);
        ivClear1.setVisibility(View.INVISIBLE);
    }

    private class MyQueryListener extends QueryListener<Integer> {

        @Override
        public void done(Integer integer, BmobException e) {
            if (e == null) {
                tvConfirm.setEnabled(true);
            } else {
                e.printStackTrace();
            }
        }
    }

    private class MySaveListener extends SaveListener<User> {

        @Override
        public void done(User user, final BmobException e) {
            if (e == null) {
                finish();
                showToast("注册成功！");
            } else {
                switch (e.getErrorCode()) {
                    case 207:
                        etConfirmationCode.requestFocus();
                        etConfirmationCode.setError("验证码有误！");
                        Tools.shakeAimation(etConfirmationCode);
                        break;
                    default:
                        showToast("" + e.getMessage());
                        break;
                }
                mDialog.dismiss();
            }
        }
    }
}
