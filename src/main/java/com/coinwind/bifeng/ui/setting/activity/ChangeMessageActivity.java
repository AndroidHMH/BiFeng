package com.coinwind.bifeng.ui.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.setting.config.SetMessageHelp;
import com.coinwind.bifeng.ui.setting.contract.SetMessageContract;
import com.coinwind.bifeng.ui.setting.presenter.SetMessagePresenter;
import com.coinwind.bifeng.view.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeMessageActivity extends BaseActivity<SetMessagePresenter> implements SetMessageContract.View {

    @BindView(R.id.change_message_return_btn)
    LinearLayout changeMessageReturnBtn;
    @BindView(R.id.change_message_ok_btn)
    LinearLayout changeMessageOkBtn;
    @BindView(R.id.change_message_title_tv)
    TextView changeMessageTitleTv;
    @BindView(R.id.change_message_et)
    ClearEditText changeMessageEt;
    private Intent intent;
    private String info;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_message;
    }

    @Override
    protected void init() {
        intent = getIntent();
        info = intent.getStringExtra("info");
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.change_message_return_btn, R.id.change_message_ok_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change_message_return_btn:
                finish();
                break;
            case R.id.change_message_ok_btn:
                presenter.changeMessage(SpHelp.getUserInformation(SpHelp.ID), info, changeMessageEt.getText().toString().trim());
                break;
        }
    }

    @Override
    public void showSuccess(String successMsg, String field, String value) {
        ToastHelp.showShort(this, successMsg);
        intent.putExtra("field", field);
        intent.putExtra("value", value);
        setResult(SetMessageHelp.getRequestCode(info), intent);
        finish();
    }

    @Override
    public void showError(String errorMsg) {
        ToastHelp.showShort(this, errorMsg);
    }

    @Override
    public void loginOut() {
        SpHelp.loginOut();
        LoginActivity.openLoginActivity(this);
    }
}
