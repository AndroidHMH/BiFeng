package com.coinwind.bifeng.ui.setting.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.setting.contract.ChangePswContract;
import com.coinwind.bifeng.ui.setting.presenter.ChangePswPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码的页面
 */
public class ChangePaswActivity extends BaseActivity<ChangePswPresenter> implements ChangePswContract.View {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    @BindView(R.id.change_psw_user_name_tv)
    TextView changePswUserNameTv;
    @BindView(R.id.change_psw_send_code_btn)
    TextView changePswSendCodeBtn;
    @BindView(R.id.change_psw_psw_et)
    EditText changePswPswEt;
    @BindView(R.id.change_psw_new_psw_et)
    EditText changePswNewPswEt;
    @BindView(R.id.change_psw_can_see_btn)
    ImageView changePswCanSeeBtn;
    @BindView(R.id.change_psw_send_btn)
    Button changePswSendBtn;
    private boolean isClick = true;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_pasw;
    }

    @Override
    protected void init() {
        titleTitleTv.setText("修改密码");
        changePswUserNameTv.setText(SpHelp.getUserInformation(SpHelp.PHONE));
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.title_layout_return_btn, R.id.change_psw_send_code_btn, R.id.change_psw_can_see_btn, R.id.change_psw_send_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.change_psw_send_code_btn:
                presenter.getCode(changePswUserNameTv.getText().toString());
                break;
            case R.id.change_psw_can_see_btn:
                if (isClick) {
                    changePswCanSeeBtn.setImageResource(R.mipmap.can_see);
                    changePswNewPswEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    changePswCanSeeBtn.setImageResource(R.mipmap.no_can_see);
                    changePswNewPswEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                changePswNewPswEt.setSelection(changePswNewPswEt.getText().length());
                isClick = !isClick;
                break;
            case R.id.change_psw_send_btn:
                String phone = changePswUserNameTv.getText().toString();
                String shortMsgCode = changePswPswEt.getText().toString().trim();
                String newpsw = changePswNewPswEt.getText().toString().trim();
                if (presenter.isNewPsw(newpsw) && presenter.isShortMsgCode(shortMsgCode)) {
                    presenter.changePsw(phone, newpsw, shortMsgCode);
                }
                break;
        }
    }

    @Override
    public void changeSuccess() {
        ToastHelp.showShort(this, "修改密码成功");
        finish();
    }

    @Override
    public void changeError(String errorMsg) {
        ToastHelp.showShort(this, errorMsg);

    }
}
