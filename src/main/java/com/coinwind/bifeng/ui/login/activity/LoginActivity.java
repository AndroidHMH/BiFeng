package com.coinwind.bifeng.ui.login.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.NetWorkHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.home.fragment.NewHomeFragment;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;
import com.coinwind.bifeng.ui.homepage.bean.MessageEvent;
import com.coinwind.bifeng.ui.login.bean.LoginBean;
import com.coinwind.bifeng.ui.login.contract.LoginContract;
import com.coinwind.bifeng.ui.login.presenter.LoginPresenter;
import com.coinwind.bifeng.view.ClearEditText;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.coinwind.bifeng.config.Codes.OVERDUE_RESULT_CODE;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.login_phone_mun_et)
    ClearEditText loginPhoneMunEt;
    @BindView(R.id.login_password_et)
    EditText loginPasswordEt;
    @BindView(R.id.login_get_code_btn)
    TextView loginGetCodeBtn;
    @BindView(R.id.login_goto_psw_login_btn)
    TextView loginGotoPswLoginBtn;
    @BindView(R.id.login_login_btn)
    Button loginLoginBtn;
    @BindView(R.id.login_retutn_btn)
    LinearLayout loginRetutnBtn;
    @BindView(R.id.login_forget_pasw_btn)
    TextView loginForgetPaswBtn;
    @BindView(R.id.login_can_see_psw_btn)
    ImageView loginCanSeePswBtn;
    private boolean isClick = true;


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

    protected void netWorkError() {

    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.login_get_code_btn, R.id.login_goto_psw_login_btn, R.id.login_login_btn, R.id.login_retutn_btn, R.id.login_forget_pasw_btn, R.id.login_can_see_psw_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_retutn_btn:
                finish();
                break;
            case R.id.login_get_code_btn:
                String phone = loginPhoneMunEt.getText().toString();
                if (presenter.checkPhone(phone)) {
                    presenter.getCode(phone);
                }
                break;
            case R.id.login_goto_psw_login_btn:
                if (NetWorkHelp.isNetWorkEnable(this)) {
                    presenter.changeLogin(loginGotoPswLoginBtn.getText().toString());
                } else {
                    ToastHelp.showShort(this, "请检查网络连接");
                }
                break;
            case R.id.login_login_btn:
                if (NetWorkHelp.isNetWorkEnable(this)) {
                    String userName = loginPhoneMunEt.getText().toString();
                    String password = loginPasswordEt.getText().toString();
                    if (presenter.checkPhone(userName) && presenter.checkPaw(password)) {
                        if (!"使用密码登录".equals(loginGotoPswLoginBtn.getText().toString())) {
                            presenter.pswLogin(userName, password);
                        } else {
                            presenter.phoneLogin(userName, password);
                        }
                    }
                } else {
                    ToastHelp.showShort(this, "请检查网络连接");
                }

                break;
            case R.id.login_forget_pasw_btn:
                //忘记密码
                ForgotPasswordActivity.openForgotPasswordActivity(this);
                break;
            case R.id.login_can_see_psw_btn:
                if (isClick) {
                    loginCanSeePswBtn.setImageResource(R.mipmap.can_see);
                    loginPasswordEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    loginCanSeePswBtn.setImageResource(R.mipmap.no_can_see);
                    loginPasswordEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                loginPasswordEt.setSelection(loginPasswordEt.getText().length());
                isClick = !isClick;
                break;
        }
    }

    @Override
    public void showCode(String message) {
        ToastHelp.showShort(this, message);
    }

    @Override
    public void usePhoneLogin() {
        loginPasswordEt.setText("");
        if (loginGetCodeBtn.getVisibility() != View.VISIBLE) {
            loginGetCodeBtn.setVisibility(View.VISIBLE);
        }
        loginCanSeePswBtn.setVisibility(View.GONE);
        loginPasswordEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

        loginGotoPswLoginBtn.setText(getResources().getString(R.string.login_use_pasw_login));
        loginPasswordEt.setHint(getResources().getString(R.string.login_code_hint));
    }

    @Override
    public void usePswLogin() {

        loginPasswordEt.setText("");
        if (loginGetCodeBtn.getVisibility() != View.GONE) {
            loginGetCodeBtn.setVisibility(View.GONE);
        }
        loginCanSeePswBtn.setVisibility(View.VISIBLE);
        loginPasswordEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        isClick = true;
        loginCanSeePswBtn.setImageResource(R.mipmap.no_can_see);

        loginGotoPswLoginBtn.setText(getResources().getString(R.string.login_use_phone_login));
        loginPasswordEt.setHint(getResources().getString(R.string.login_psw_hint));
    }

    @Override
    public void loginSuccessful(LoginBean.DataBean.UserBean user, String sign) {
        SpHelp.putUserInformation(user);
        SpHelp.putSign(sign);
        boolean login = SpHelp.login();
        if (login) {
            ToastHelp.showShort(this, "登录成功");
        }
        String refresh = getIntent().getStringExtra("refresh");
        if (refresh != null && !"".equals(refresh)) {
            setResult(OVERDUE_RESULT_CODE);
        }
        finish();
    }

    @Override
    public void loginFailed(String errorMsg) {
        ToastHelp.showShort(this, errorMsg);
    }
}