package com.coinwind.bifeng.ui.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

/**
 * 修改用户信息的页面
 */
public class SetMessageActivity extends BaseActivity<SetMessagePresenter> implements SetMessageContract.View {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.set_message_et)
    ClearEditText setMessageEt;
    @BindView(R.id.set_message_all_count_tv)
    TextView setMessageAllCountTv;
    @BindView(R.id.set_message_send_btn)
    Button setMessageSendBtn;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    @BindView(R.id.set_message_count_layout)
    LinearLayout setMessageCountLayout;
    @BindView(R.id.set_message_qi_yi_et)
    EditText setMessageQiYiEt;
    private String info;
    private Intent intent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_message;
    }

    @Override
    protected void init() {
        intent = getIntent();
        info = intent.getStringExtra("info");
        SetMessageHelp.setTitle(info, titleTitleTv);
        SetMessageHelp.setHintText(info, setMessageEt, setMessageCountLayout, setMessageQiYiEt);
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.title_layout_return_btn, R.id.set_message_send_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.set_message_send_btn:
                if (setMessageEt.getVisibility() == View.VISIBLE) {
                    presenter.changeMessage(SpHelp.getUserInformation(SpHelp.ID), info, setMessageEt.getText().toString().trim());
                } else {
                    presenter.changeMessage(SpHelp.getUserInformation(SpHelp.ID), info, setMessageQiYiEt.getText().toString().trim());
                }
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
