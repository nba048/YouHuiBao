package com.yhb.mvp.view.fragment;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
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
import com.yhb.base.ProjectApp;
import com.yhb.base.ProjectUtil;
import com.yhb.bean.response.User;
import com.yhb.factroy.ProjectFactroy;
import com.yhb.mvp.model.i.INetworkModel;
import com.yhb.mvp.view.activity.MainActivity;
import com.yhb.mvp.view.activity.RegisterActivity;
import com.yhb.utils.SecurityCode;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import dmax.dialog.SpotsDialog;

/**
 * Created by Administrator on 2016/9/30 0030.
 */
public class LoginPhoneFragment extends BaseFragment implements View.OnClickListener, TextWatcher {
    private SecurityCode mCode;
    private INetworkModel mModel;
    private AlertDialog mDialog;
    private android.support.v7.app.AlertDialog mDialog2;
    private boolean isAuto = false;

    @Override
    protected int bindLayout() {
        mModel = ProjectFactroy.getInstanceByNetworkModel();
        return R.layout.fragment_login2;
    }

    @Override
    protected void setInitView(View mRootView) {
        mDialog = new SpotsDialog(getlActivity());
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
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void setInitData() {
        initDialog();
        mCode = SecurityCode.getInstance();
        etPhone.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
        ivClear.setOnClickListener(this);
        ivClear2.setOnClickListener(this);
        ivYanZhenMa.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        ivYanZhenMa.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivYanZhenMa.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ivYanZhenMa.setImageBitmap(mCode.createBitmap(ivYanZhenMa.getWidth(), ivYanZhenMa.getHeight()));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        lodingState();
    }

    private void lodingState() {
        final String pass = ProjectUtil.SP.getString("password", "");
        final String name = ProjectUtil.SP.getString("username", "");
        if (ProjectUtil.isEmpty(pass) && ProjectUtil.isEmpty(name)) {
            return;
        } else {
            isAuto = true;
            mDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    etPhone.setText(name);
                    etPassword.setText(pass);
                    etYanZhenMa.setText(mCode.getCode());
                    loging();
                }
            }, 1500);
        }
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
                startActivity(new Intent(getlActivity(), RegisterActivity.class));
                break;
            default:
                break;
        }
    }

    private void loging() {
        if (ProjectUtil.StringUtil.isPhone(etPhone.getText().toString())) {
            if (etPassword.getText().toString().length() > 6) {
                if (etYanZhenMa.getText().toString().equalsIgnoreCase(mCode.getCode())) {
                    mDialog.show();
                    User user = new User();
                    user.setUsername(etPhone.getText().toString());
                    user.setPassword(etPassword.getText().toString());
                    user.login(new MySaveListener());
                } else {
                    onClick(ivYanZhenMa);
                }
            } else {
                toastShow("密码要大于 6 位哟 ^_^");
            }
        } else {
            toastShow("手机号不对哟 ^_^");
        }
    }

    private class MySaveListener extends SaveListener<User> {

        @Override
        public void done(final User user, final BmobException e) {
            if (e == null) {
                if (BmobUser.getCurrentUser(User.class) != null) {
                    ProjectUtil.SP.putString("phoneNumber", etPhone.getText().toString());
                    ProjectApp.setCurrentUser(BmobUser.getCurrentUser(User.class));
                    etYanZhenMa.setEnabled(false);
                    etPhone.setEnabled(false);
                    etPassword.setEnabled(false);
                    if (isAuto) {
                        startActivity(new Intent(getlActivity(), MainActivity.class));
                        getlActivity().finish();
                        return;
                    } else {
                        mDialog2.show();
                    }
                }
            } else {
                toastShow("帐号或密码有误!");
                etPhone.setText("");
                etPassword.setText("");
                etYanZhenMa.setText("");
            }
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

    private void initDialog() {
        mDialog2 = new android.support.v7.app.AlertDialog.Builder(getlActivity())
                .setMessage("是否记录登陆状态？")
                .setPositiveButton("记录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ProjectUtil.SP.putString("password", etPassword.getText().toString());
                        ProjectUtil.SP.putString("username", etPhone.getText().toString());
                        startActivity(new Intent(getlActivity(), MainActivity.class));
                        getlActivity().finish();
                    }
                })
                .setNegativeButton("暂不", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getlActivity(), MainActivity.class));
                        getlActivity().finish();
                    }
                })
                .setCancelable(false)
                .create();


    }
}
