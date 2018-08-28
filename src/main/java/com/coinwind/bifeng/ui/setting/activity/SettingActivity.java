package com.coinwind.bifeng.ui.setting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置页面
 */
public class SettingActivity extends NoNetworkBaseActivity {

    @BindView(R.id.setting_login_out_btn)
    Button settingLoginOutBtn;
    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    @BindView(R.id.guan_yu_btn)
    LinearLayout guanYuBtn;
    @BindView(R.id.xie_yi_btn)
    LinearLayout xieYiBtn;
    @BindView(R.id.lian_xi_btn)
    LinearLayout lianXiBtn;
    @BindView(R.id.xiu_gai_btn)
    LinearLayout xiuGaiBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void init() {
        titleTitleTv.setText("设置");
    }

    @OnClick({R.id.title_layout_return_btn, R.id.guan_yu_btn, R.id.xie_yi_btn, R.id.lian_xi_btn, R.id.xiu_gai_btn, R.id.setting_login_out_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.guan_yu_btn:
                GuanYuActivity.openActivity(this, R.mipmap.guanyu_icon);
                break;
            case R.id.xie_yi_btn:
                //协议
                GuanYuActivity.openActivity(this, R.mipmap.yong_hu_xie_yi_icon);
                break;
            case R.id.lian_xi_btn:
                //联系我们
                ContactUsActivity.openActivity(this);
                break;
            case R.id.xiu_gai_btn:
                //修改密码
                startActivity(new Intent(this, ChangePaswActivity.class));
//                Intent intent = new Intent(this, SetMessageActivity.class);
//                intent.putExtra("info", "修改密码");
//                startActivity(intent);
                break;
            case R.id.setting_login_out_btn:
                SpHelp.loginOut();
                finish();
                break;
        }
    }
}