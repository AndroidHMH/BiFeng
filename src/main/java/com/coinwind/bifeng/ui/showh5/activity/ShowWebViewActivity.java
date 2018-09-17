package com.coinwind.bifeng.ui.showh5.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowWebViewActivity extends NoNetworkBaseActivity {

    @BindView(R.id.show_web_view)
    WebView showWebView;
    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    private String url;

    public static void openActivity(Context context, String url, String title) {
        Intent intent = new Intent(context, ShowWebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);

        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_web_view;
    }

    @Override
    protected void init() {
        url = getIntent().getStringExtra("url");
        titleTitleTv.setText(getIntent().getStringExtra("title"));
        showWebView.getSettings().setJavaScriptEnabled(true);//支持js

        showWebView.getSettings().setBlockNetworkImage(false);
        showWebView.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            showWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        showWebView.loadUrl(this.url);
        showWebView.setWebViewClient(new WebViewClient() {
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
