package com.coinwind.bifeng.ui.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.login.bean.LoginBean;
import com.coinwind.bifeng.ui.login.contract.LoginContract;
import com.coinwind.bifeng.ui.login.presenter.LoginPresenter;
import com.coinwind.bifeng.ui.my.fragment.MyFragment;
import com.coinwind.bifeng.view.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.login_phone_mun_et)
    ClearEditText loginPhoneMunEt;
    @BindView(R.id.login_password_et)
    ClearEditText loginPasswordEt;
    @BindView(R.id.login_get_code_btn)
    TextView loginGetCodeBtn;
    @BindView(R.id.login_goto_psw_login_btn)
    TextView loginGotoPswLoginBtn;
    @BindView(R.id.login_login_btn)
    Button loginLoginBtn;


    public static void openLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.login_get_code_btn, R.id.login_goto_psw_login_btn, R.id.login_login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_get_code_btn:
                String phone = loginPhoneMunEt.getText().toString();
                if (presenter.checkPhone(phone)) {
                    presenter.getCode(phone);
                }
                break;
            case R.id.login_goto_psw_login_btn:
                presenter.changeLogin(loginGotoPswLoginBtn.getText().toString());
                break;
            case R.id.login_login_btn:
                String userName = loginPhoneMunEt.getText().toString();
                String password = loginPasswordEt.getText().toString();
                if (presenter.checkPhone(userName) && presenter.checkPaw(password)) {
                    if (!"使用密码登录".equals(loginGotoPswLoginBtn.getText().toString())) {
                        presenter.pswLogin(userName, password);
                    } else {
                        presenter.phoneLogin(userName, password);
                    }
                }
                break;
        }
    }

    @Override
    public void showCode(String message) {
        ToastHelp.showShort(this, message);
    }

    @Override
    public void usePhoneLogin() {
        if (loginGetCodeBtn.getVisibility() != View.VISIBLE) {
            loginGetCodeBtn.setVisibility(View.VISIBLE);
        }

        loginGotoPswLoginBtn.setText(getResources().getString(R.string.login_use_pasw_login));
        loginPasswordEt.setHint(getResources().getString(R.string.login_code_hint));
    }

    @Override
    public void usePswLogin() {
        if (loginGetCodeBtn.getVisibility() != View.GONE) {
            loginGetCodeBtn.setVisibility(View.GONE);
        }
        loginGotoPswLoginBtn.setText(getResources().getString(R.string.login_use_phone_login));
        loginPasswordEt.setHint(getResources().getString(R.string.login_psw_hint));
    }

    @Override
    public void loginSuccessful(LoginBean.DataBean.UserBean user) {
        SpHelp.putUserInformation( user);
        boolean login = SpHelp.login();
        if (login) {
            ToastHelp.showShort(this, "登录成功");
        }
        finish();
    }

    @Override
    public void loginFailed(String errorMsg) {
        ToastHelp.showShort(this, errorMsg);
    }

}