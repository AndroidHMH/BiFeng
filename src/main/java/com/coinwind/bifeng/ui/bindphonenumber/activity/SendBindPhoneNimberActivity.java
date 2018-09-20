package com.coinwind.bifeng.ui.bindphonenumber.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.bindphonenumber.contract.SendBindPhoneNumberContract;
import com.coinwind.bifeng.ui.bindphonenumber.entity.SendBindPhoneNumberEntity;
import com.coinwind.bifeng.ui.bindphonenumber.presenter.SendBindPhoneNumberPresenter;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.task.activity.DoNewTaskActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendBindPhoneNimberActivity extends BaseActivity<SendBindPhoneNumberPresenter> implements SendBindPhoneNumberContract.View {

    @BindView(R.id.send_bing_phone_number_return_btn)
    TextView sendBingPhoneNumberReturnBtn;
    @BindView(R.id.send_bing_phone_number_phone_tv)
    TextView sendBingPhoneNumberPhoneTv;
    @BindView(R.id.send_bing_phone_number_code_et)
    EditText sendBingPhoneNumberCodeEt;
    @BindView(R.id.send_bing_phone_number_code_btn)
    TextView sendBingPhoneNumberCodeBtn;
    @BindView(R.id.send_bing_phone_number_pasw_et)
    EditText sendBingPhoneNumberPaswEt;
    @BindView(R.id.send_bing_phone_number_can_see_btn)
    ImageView sendBingPhoneNumberCanSeeBtn;
    @BindView(R.id.send_bing_phone_number_yaop_qing_code_et)
    EditText sendBingPhoneNumberYaopQingCodeEt;
    @BindView(R.id.send_bing_phone_number_send_btn)
    Button sendBingPhoneNumberSendBtn;
    private boolean isClick;
    private String phone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_bind_phone_nimber;
    }

    @Override
    protected void init() {
        phone = getIntent().getStringExtra("phone");
        sendBingPhoneNumberPhoneTv.setText(phone);
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.send_bing_phone_number_return_btn, R.id.send_bing_phone_number_code_btn, R.id.send_bing_phone_number_can_see_btn, R.id.send_bing_phone_number_send_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_bing_phone_number_return_btn:
                finish();
                break;
            case R.id.send_bing_phone_number_code_btn:
                presenter.sendCode(phone);
                break;
            case R.id.send_bing_phone_number_can_see_btn:
                if (isClick) {
                    sendBingPhoneNumberCanSeeBtn.setImageResource(R.mipmap.can_see);
                    sendBingPhoneNumberPaswEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    sendBingPhoneNumberCanSeeBtn.setImageResource(R.mipmap.no_can_see);
                    sendBingPhoneNumberPaswEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                sendBingPhoneNumberPaswEt.setSelection(sendBingPhoneNumberPaswEt.getText().length());
                isClick = !isClick;
                break;
            case R.id.send_bing_phone_number_send_btn:
                String pasw = sendBingPhoneNumberPaswEt.getText().toString().trim();
                String yaoQingCode = sendBingPhoneNumberYaopQingCodeEt.getText().toString().trim();
                String code = sendBingPhoneNumberCodeEt.getText().toString().trim();
                if (presenter.checkCode(code) && presenter.checkPaw(pasw)) {
                    presenter.sendBindPhoneNumber(phone, pasw, code, yaoQingCode);
                }
                break;
        }
    }

    @Override
    public void showBindSuccess(SendBindPhoneNumberEntity.DataBean data) {
        SpHelp.putIsVisit(0);
        SpHelp.putUserInformation(SpHelp.ID, data.getUserId());
        SpHelp.putSign(data.getSign());
        //绑定手机号成功的回调
        setResult(DoNewTaskActivity.PHONE_SUCCESS_CODE);
        finish();
    }

    @Override
    public void showBindMsg(String errorMsg) {
        //提示信息
        ToastHelp.showShort(this, errorMsg);
    }

    @Override
    public void goToLogin(String msg) {
        ToastHelp.showShort(this, msg);
        LoginActivity.openLoginActivity(this);
        finish();
    }
}
