package com.yhb.mvp.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.yhb.R;
import com.yhb.base.BaseActivity;
import com.yhb.base.ProjectUtil;
import com.yhb.bean.response.User;
import com.yhb.utils.Tools;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class RegistActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mExitView;
    private TextView mTitleView;
    private EditText username;
    private EditText password1;
    private EditText password2;
    private EditText phonenumber;
    private EditText phonecode;
    private Button btnReqCode;
    private ActionProcessButton btnSubmit;

    @Override
    protected void onLCreate() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_regiest;
    }

    @Override
    protected void initView() {
        mExitView = (ImageView) findViewById(R.id.title_iv_back);
        mTitleView = (TextView) findViewById(R.id.title_tv_text);
        username = (EditText) findViewById(R.id.username);
        password1 = (EditText) findViewById(R.id.password1);
        password2 = (EditText) findViewById(R.id.password2);
        phonenumber = (EditText) findViewById(R.id.phonenumber);
        phonecode = (EditText) findViewById(R.id.phonecode);
        btnReqCode = (Button) findViewById(R.id.btn_req_code);
        btnSubmit = (ActionProcessButton) findViewById(R.id.btn_submit);
        mTitleView.setText("注册");
        mExitView.setOnClickListener(this);
        btnReqCode.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        phonecode.setEnabled(false);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v == mExitView) {
            finish();
        } else if (v == btnSubmit) {
            requserRegister();
        } else if (v == btnReqCode) {
            requsetMsg();
        }
    }

    private void requserRegister() {
        String userName = username.getText().toString();
        if (!(userName.length() > 3)) {
            username.requestFocus();
            username.setError("至少4位！");
            Tools.shakeAimation(username);
            return;
        }
        String passWord1 = password1.getText().toString();
        if (!(passWord1.length() > 5)) {
            password1.requestFocus();
            password1.setError("至少6位！");
            Tools.shakeAimation(password1);
            return;
        }
        String passWord2 = password2.getText().toString();
        if (!(passWord2.length() > 5)) {
            password2.requestFocus();
            password2.setError("至少6位！");
            Tools.shakeAimation(password2);
            return;
        }
        if (!passWord1.equals(passWord2)) {
            password2.requestFocus();
            password2.setError("两次密码不一至！");
            Tools.shakeAimation(password2);
            return;
        }
        String code = phonecode.getText().toString();
        if (code.length() == 0) {
            Tools.shakeAimation(btnReqCode);
            return;
        }
        User user = new User();
        user.setUsername(userName);
        user.setMobilePhoneNumber(phonenumber.getText().toString());
        user.setPassword(passWord1);
        user.setType("0");
        user.signOrLogin(code, new MySaveListener());

        btnSubmit.setProgress(50);
    }

    private void requsetMsg() {
        String phone = phonenumber.getText().toString();
        if (ProjectUtil.isEmpty(phone)) {
            phonenumber.requestFocus();
            phonenumber.setError("请填写！");
            Tools.shakeAimation(phonenumber);
            return;
        }
        if (!ProjectUtil.StringUtil.isPhone(phone)) {
            phonenumber.requestFocus();
            phonenumber.setError("格式不对！");
            Tools.shakeAimation(phonenumber);
            return;
        }
        phonenumber.setEnabled(false);
        btnReqCode.setEnabled(false);
        new TimeThread().start();
    }

    private class MyQueryListener extends QueryListener<Integer> {

        @Override
        public void done(Integer integer, BmobException e) {
            if (e == null) {
                phonecode.setEnabled(true);
            } else {
                e.printStackTrace();
            }
        }
    }

    private class TimeThread extends Thread {
        private int mCurrentTime = 90;

        @Override
        public void run() {
            while (mCurrentTime > 0) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (mCurrentTime != 1)
                            btnReqCode.setText("" + (mCurrentTime--));
                        if (mCurrentTime == 88) {
                            BmobSMS.requestSMSCode(phonenumber.getText().toString(), "yhb", new MyQueryListener());
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
                            btnReqCode.setEnabled(true);
                            btnReqCode.setText("重发");
                        }
                    });
                }
            }
        }
    }

    private class MySaveListener extends SaveListener<User> {

        @Override
        public void done(User user, final BmobException e) {
            btnSubmit.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (e == null) {
                        btnSubmit.setProgress(100);
                        btnSubmit.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                                showToast("注册成功！");
                            }
                        }, 1000);
                    } else {
                        showToast("" + e.getErrorCode());
                        btnSubmit.setProgress(-1);
                        switch (e.getErrorCode()) {
                            case 207:
                                phonecode.requestFocus();
                                phonecode.setError("验证码有误！");
                                Tools.shakeAimation(phonecode);
                                break;
                        }

                    }
                }
            }, 1300);
        }
    }
}
