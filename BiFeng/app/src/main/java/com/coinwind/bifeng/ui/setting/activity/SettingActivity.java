package com.coinwind.bifeng.ui.setting.activity;

import android.os.Bundle;
import android.widget.Button;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.setting_login_out_btn)
    Button settingLoginOutBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadDate() {

    }


    @OnClick(R.id.setting_login_out_btn)
    public void onViewClicked() {
        SpHelp.loginOut(this);
        finish();
    }
}
