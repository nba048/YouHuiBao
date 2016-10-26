package com.yhb.mvp.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pgyersdk.crash.PgyCrashManager;
import com.yhb.R;
import com.yhb.base.BaseActivity;

import dmax.dialog.SpotsDialog;

/**
 * Created by Administrator on 2016/10/25 0025.
 */
public class ErrorActivity extends BaseActivity {
    private EditText mEtInput;
    private TextView mTVcommit;
    private AlertDialog mDialog;

    @Override
    protected void onLCreate() {
        PgyCrashManager.register(this);
        mDialog = new SpotsDialog(this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_error;
    }

    @Override
    protected void initView() {
        mEtInput = (EditText) findViewById(R.id.et_input);
        mTVcommit = (TextView) findViewById(R.id.tv_commit);
    }

    @Override
    protected void initData() {
        mTVcommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();
                try {
                    throw new UserException(mEtInput.getText().toString());
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(ErrorActivity.this, e);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDialog.dismiss();
                        showToast("感谢您的支持 ^_^");
                        startActivity(new Intent(ErrorActivity.this, LoginActivity.class));
                    }
                }, 1500);
            }
        });
        mEtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEtInput.getText().length() > 2) {
                    mTVcommit.setEnabled(true);
                } else {
                    mTVcommit.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private class UserException extends Exception {

        public UserException(String detailMessage) {
            super(detailMessage);
        }
    }
}
