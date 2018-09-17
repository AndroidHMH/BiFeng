package com.coinwind.bifeng.ui.setting.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.ui.share.activity.InvitationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关于我们页面
 */
public class GuanYuActivity extends NoNetworkBaseActivity {


    @BindView(R.id.guan_yu_web_view)
    WebView guanYuWebView;
    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;

    public static void openActivity(Context context, String url) {
        Intent intent = new Intent(context, GuanYuActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_guan_yu;
    }

    @Override
    protected void init() {
        guanYuWebView.getSettings().setJavaScriptEnabled(true);
        guanYuWebView.getSettings().setBlockNetworkImage(false);
        guanYuWebView.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            guanYuWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        String url = getIntent().getStringExtra("url");
        guanYuWebView.loadUrl(url);
        guanYuWebView.setWebViewClient(new WebViewClient() {

            //返回值：true 不会显示网页资源，需要等待你的处理，false 就认为系统没有做处理，会显示网页资源
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


    @OnClick(R.id.title_layout_return_btn)
    public void onViewClicked() {
        finish();
    }
}
