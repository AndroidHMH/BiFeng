package com.coinwind.bifeng.ui.share.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.ui.submittask.activity.SubmitCommentsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareActivity extends BaseActivity {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.share_web_view)
    WebView shareWebView;
    @BindView(R.id.share_fen_xiang_btn)
    LinearLayout shareFenXiangBtn;
    @BindView(R.id.share_next_btn)
    LinearLayout shareNextBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    protected void init() {
        shareWebView.loadUrl("https://www.baidu.com");
        shareWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.share_fen_xiang_btn, R.id.share_next_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.share_fen_xiang_btn:
                break;
            case R.id.share_next_btn:
                Intent intent = new Intent(this, SubmitCommentsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
