package com.coinwind.bifeng.ui.setting.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.config.ToastHelp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 联系我们界面
 */
public class ContactUsActivity extends NoNetworkBaseActivity {


    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    @BindView(R.id.website_tv)
    TextView websiteTv;
    @BindView(R.id.website_btn)
    LinearLayout websiteBtn;
    @BindView(R.id.customer_service_tv)
    TextView customerServiceTv;
    @BindView(R.id.website_weixin_tv)
    TextView websiteWeixinTv;
    @BindView(R.id.customer_service_email_tv)
    TextView customerServiceEmailTv;

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, ContactUsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_us;
    }

    @Override
    protected void init() {
        titleTitleTv.setText("联系我们");
    }


    @OnClick({R.id.title_layout_return_btn, R.id.website_btn, R.id.customer_service_btn, R.id.website_weixin_btn, R.id.customer_service_email_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.website_btn:
                copyContent(websiteTv);
                break;
            case R.id.customer_service_btn:
                copyContent(customerServiceTv);
                break;
            case R.id.website_weixin_btn:
                copyContent(websiteWeixinTv);
                break;
            case R.id.customer_service_email_btn:
                copyContent(customerServiceEmailTv);
                break;
        }
    }

    private void copyContent(TextView textView) {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(textView.getText());
        ToastHelp.showShort(this, textView.getText() + "已经复制到剪切板");
    }

}
