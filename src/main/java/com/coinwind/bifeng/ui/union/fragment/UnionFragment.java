package com.coinwind.bifeng.ui.union.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseFragment;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 公会页面
 */
public class UnionFragment extends BaseFragment {
    @BindView(R.id.union_web_view)
    WebView unionWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_union;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadDate() {
        unionWebView.getSettings().setJavaScriptEnabled(true);
        unionWebView.getSettings().setBlockNetworkImage(false);
        unionWebView.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            unionWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        unionWebView.loadUrl("https://m.coinwind.com/share/guild.html");
        unionWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
