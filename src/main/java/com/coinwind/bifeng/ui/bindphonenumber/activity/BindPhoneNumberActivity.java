package com.coinwind.bifeng.ui.bindphonenumber.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.config.ToastHelp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindPhoneNumberActivity extends NoNetworkBaseActivity {

    @BindView(R.id.bing_phone_number_return_btn)
    TextView bingPhoneNumberReturnBtn;
    @BindView(R.id.bing_phone_number_phone_et)
    EditText bingPhoneNumberPhoneEt;
    @BindView(R.id.bing_phone_number_xie_yi_btn)
    TextView bingPhoneNumberXieYiBtn;
    @BindView(R.id.bing_phone_number_next_btn)
    Button bingPhoneNumberNextBtn;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_phone_number;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.bing_phone_number_return_btn, R.id.bing_phone_number_next_btn, R.id.bing_phone_number_xie_yi_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bing_phone_number_return_btn:
                finish();
                break;
            case R.id.bing_phone_number_xie_yi_btn:
                //服务协议
                break;
            case R.id.bing_phone_number_next_btn:
                String phone = bingPhoneNumberPhoneEt.getText().toString().trim();
                if ("".equals(phone) || phone == null) {
                    ToastHelp.showShort(this, "请输入手机号");
                    return;
                }
                Intent intent = new Intent(this, SendBindPhoneNimberActivity.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                finish();
                break;
        }
    }
}
