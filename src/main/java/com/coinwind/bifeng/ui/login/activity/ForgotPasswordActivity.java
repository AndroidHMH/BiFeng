package com.coinwind.bifeng.ui.login.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.setting.activity.ChangePaswActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPasswordActivity extends NoNetworkBaseActivity {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.forgot_pasw_et)
    EditText forgotPaswEt;
    @BindView(R.id.forgot_pasw_btn)
    Button forgotPaswBtn;

    public static void openForgotPasswordActivity(Context context) {
        Intent intent = new Intent(context, ForgotPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected void init() {
        titleTitleTv.setVisibility(View.GONE);
    }

    @OnClick({R.id.title_layout_return_btn, R.id.forgot_pasw_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.forgot_pasw_btn:
                String phone = forgotPaswEt.getText().toString().trim();
                if (checkPhone(phone)) {
                    Intent intent = new Intent(this, ChangePaswActivity.class);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                }
                break;
        }
    }

    public boolean checkPhone(String phone) {
        if (phone == null) {
            ToastHelp.showShort(this, "手机号为空");
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            ToastHelp.showShort(this, "手机号为空");

            return false;
        }
        String pattern = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$";
        if (!phone.matches(pattern)) {
            ToastHelp.showShort(this, "手机号格式错误");
            return false;
        }
        return true;
    }
}
