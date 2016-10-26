package com.yhb.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.yhb.R;
import com.yhb.base.BaseActivity;
import com.yhb.base.ProjectApp;
import com.yhb.base.ProjectUtil;
import com.yhb.bean.response.User;
import com.yhb.utils.Tools;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity_ extends BaseActivity implements View.OnClickListener {

    private EditText mUsernameView;
    private EditText mPasswordView;
    private ImageView mExitView;
    private TextView mTitleView;
    private ActionProcessButton mBtnLogin;
    private TextView mOutherView;
    private TextView mRegeistView;

    @Override
    protected void onLCreate() {

    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mUsernameView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);
        mExitView = (ImageView) findViewById(R.id.title_iv_back);
        mTitleView = (TextView) findViewById(R.id.title_tv_text);
        mOutherView = (TextView) findViewById(R.id.title_tv_btn_3);
        mRegeistView = (TextView) findViewById(R.id.login_register_txt);
        mBtnLogin = (ActionProcessButton) findViewById(R.id.apb_login);
    }

    @Override
    protected void initData() {
        mTitleView.setText("登陆");
        mOutherView.setText("其它");
        mOutherView.setOnClickListener(this);
        mExitView.setOnClickListener(this);
        mRegeistView.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mUsernameView.setText(ProjectUtil.SP.getString("phoneNumber", ""));
        if (mUsernameView.getText().toString().length() == 11)
            mPasswordView.requestFocus();
        mOutherView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(LoginActivity_.this, DetailOrderActivity.class));
                return false;
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == mExitView) {
            ProjectApp.finish();
        } else if (v == mOutherView) {
            startActivity(new Intent(this, OutherActivity.class));
        } else if (v == mRegeistView) {
            startActivity(new Intent(this, RegistActivity.class));
        } else if (v == mBtnLogin) {
            if (mUsernameView.getText().toString().length() != 11) {
                mUsernameView.setError("请输入正确帐号！");
                Tools.shakeAimation(mUsernameView);
                return;
            }
            if (mPasswordView.getText().toString().length() < 5) {
                mPasswordView.setError("密码过短！");
                Tools.shakeAimation(mPasswordView);
                return;
            }
            mPasswordView.setEnabled(false);
            mUsernameView.setEnabled(false);
            User user = new User();
            user.setUsername(mUsernameView.getText().toString());
            user.setPassword(mPasswordView.getText().toString());
            user.login(new MySaveListener());
            mBtnLogin.setProgress(50);
        }
    }

    private class MySaveListener extends SaveListener<User> {

        @Override
        public void done(final User user, final BmobException e) {
            mBtnLogin.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (e == null) {
                        mBtnLogin.setProgress(100);
                        mBtnLogin.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (BmobUser.getCurrentUser(User.class) != null) {
                                    ProjectUtil.SP.putString("phoneNumber", mUsernameView.getText().toString());
                                    ProjectApp.setCurrentUser(BmobUser.getCurrentUser(User.class));
                                    startActivity(new Intent(LoginActivity_.this, MainActivity.class));
                                    finish();
                                }
                            }
                        }, 1000);
                    } else {
                        showToast("帐号或密码有误!");
                        mBtnLogin.setProgress(-1);
                        mPasswordView.setEnabled(true);
                        mUsernameView.setEnabled(true);
                        mPasswordView.setText("");
                        mUsernameView.setText("");
                    }
                }
            }, 1500);
        }
    }
}

