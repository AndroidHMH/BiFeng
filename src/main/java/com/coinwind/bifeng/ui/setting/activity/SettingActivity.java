package com.coinwind.bifeng.ui.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.bindphonenumber.activity.BindPhoneNumberActivity;
import com.coinwind.bifeng.ui.home.fragment.NewHomeFragment;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;
import com.coinwind.bifeng.ui.my.activity.PerfectInformationActivity;
import com.coinwind.bifeng.ui.share.activity.InvitationActivity;
import com.coinwind.bifeng.ui.task.activity.DoNewTaskActivity;
import com.coinwind.bifeng.ui.task.activity.NewTaskActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.coinwind.bifeng.ui.task.activity.DoNewTaskActivity.PHONE_SUCCESS_CODE;
import static com.coinwind.bifeng.ui.task.activity.NewTaskActivity.PHONE_REQUEST_CODE;

/**
 * 设置页面
 */
public class SettingActivity extends NoNetworkBaseActivity {


    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.setting_user_info_btn)
    LinearLayout settingUserInfoBtn;
    @BindView(R.id.setting_language_btn)
    LinearLayout settingLanguageBtn;
    @BindView(R.id.setting_version_number_btn)
    LinearLayout settingVersionNumberBtn;
    @BindView(R.id.setting_login_out_btn)
    Button settingLoginOutBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {
        titleTitleTv.setText("设置");
    }

    @OnClick({R.id.title_layout_return_btn, R.id.setting_user_info_btn, R.id.setting_language_btn, R.id.setting_version_number_btn, R.id.setting_login_out_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.setting_user_info_btn:
                //个人资料
                if (SpHelp.getIsVisit() == 0) {
                    startActivity(new Intent(this, PerfectInformationActivity.class));
                } else {
                    Intent bindIntent = new Intent(this, BindPhoneNumberActivity.class);
                    startActivityForResult(bindIntent, PHONE_REQUEST_CODE);
                    ToastHelp.showShort(this, "您当前身份为游客，无法邀请好友");
                }
                break;
            case R.id.setting_language_btn:
                break;
            case R.id.setting_version_number_btn:
                break;
            case R.id.setting_login_out_btn:
                //退出登录
                if (SpHelp.getIsVisit() == 0) {
                    SpHelp.loginOut();
                    ToastHelp.showShort(this, "退出登录成功");
                    finish();
                } else {
                    Intent bindIntent = new Intent(this, BindPhoneNumberActivity.class);
                    startActivityForResult(bindIntent, PHONE_REQUEST_CODE);
                    ToastHelp.showShort(this, "您当前身份为游客，无法邀请好友");
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHONE_REQUEST_CODE) {
            if (resultCode == PHONE_SUCCESS_CODE) {//绑定手机号成功
                ToastHelp.showShort(this, "绑定手机号成功");
                reLoadFragView(R.id.main_layout, NewHomeFragment.class);
            }
        }
    }
}