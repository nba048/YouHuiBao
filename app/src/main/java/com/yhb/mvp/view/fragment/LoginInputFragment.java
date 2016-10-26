package com.yhb.mvp.view.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhb.R;
import com.yhb.base.BaseFragment;
import com.yhb.mvp.view.activity.RegisterActivity;
import com.yhb.utils.SecurityCode;

/**
 * Created by Administrator on 2016/9/30 0030.
 */
public class LoginInputFragment extends BaseFragment implements View.OnClickListener, TextWatcher {

    private SecurityCode mCode;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_login1;
    }

    @Override
    protected void setInitView(View mRootView) {
        etPhone = (EditText) mRootView.findViewById(R.id.et_phone);
        ivClear = (ImageView) mRootView.findViewById(R.id.iv_clear);
        etYanZhenMa = (EditText) mRootView.findViewById(R.id.et_yan_zhen_ma);
        ivClear2 = (ImageView) mRootView.findViewById(R.id.iv_clear2);
        etPassword = (EditText) mRootView.findViewById(R.id.et_password);
        ivYanZhenMa = (ImageView) mRootView.findViewById(R.id.iv_yan_zhen_ma);
        tvForgetPassword = (TextView) mRootView.findViewById(R.id.tv_forget_password);
        tvZhaoPassword = (TextView) mRootView.findViewById(R.id.tv_zhao_password);
        tvLogin = (Button) mRootView.findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(this);

        etPhone.setEnabled(false);
        etPassword.setEnabled(false);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void setInitData() {
        mCode = SecurityCode.getInstance();
        etPhone.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
        ivClear.setOnClickListener(this);
        ivClear2.setOnClickListener(this);
        ivYanZhenMa.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        tvZhaoPassword.setOnClickListener(this);
        ivYanZhenMa.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivYanZhenMa.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ivYanZhenMa.setImageBitmap(mCode.createBitmap(ivYanZhenMa.getWidth(), ivYanZhenMa.getHeight()));
            }
        });
    }

    private EditText etPhone;
    private ImageView ivClear;
    private EditText etYanZhenMa;
    private ImageView ivClear2;
    private EditText etPassword;
    private ImageView ivYanZhenMa;
    private TextView tvForgetPassword;
    private TextView tvZhaoPassword;
    private Button tvLogin;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_clear:
                etPhone.setText("");
                break;
            case R.id.iv_clear2:
                etPassword.setText("");
                break;
            case R.id.tv_login:
                loging();
                break;
            case R.id.iv_yan_zhen_ma:
                ivYanZhenMa.setImageBitmap(mCode.createBitmap(ivYanZhenMa.getWidth(), ivYanZhenMa.getHeight()));
                etYanZhenMa.setText("");
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(getActivity(), RegisterActivity.class));
                break;
            case R.id.tv_zhao_password:
                break;
            default:
                break;
        }
    }

    private void loging() {
        if (etYanZhenMa.getText().toString().equalsIgnoreCase(mCode.getCode())) {

        } else {
            onClick(ivYanZhenMa);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (etPhone.getText().length() > 2) {
            ivClear.setVisibility(View.VISIBLE);
        } else {
            ivClear.setVisibility(View.INVISIBLE);
        }
        if (etPassword.getText().length() > 2) {
            ivClear2.setVisibility(View.VISIBLE);
        } else {
            ivClear2.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && ivYanZhenMa != null) {
            onClick(ivYanZhenMa);
        }
    }
}
