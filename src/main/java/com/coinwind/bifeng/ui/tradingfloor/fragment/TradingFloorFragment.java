package com.coinwind.bifeng.ui.tradingfloor.fragment;


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
 * 交易大厅
 */
public class TradingFloorFragment extends BaseFragment {

    @BindView(R.id.trading_floor_web_view)
    WebView tradingFloorWebView;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trading_floor;
    }

    @Override
    protected void init() {


    }

    @Override
    protected void loadDate() {
        tradingFloorWebView.getSettings().setJavaScriptEnabled(true);
        tradingFloorWebView.getSettings().setBlockNetworkImage(false);
        tradingFloorWebView.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tradingFloorWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        Calendar cale = null;
        cale = Calendar.getInstance();
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DATE);
        tradingFloorWebView.loadUrl("https://m.coinwind.com/share/transaction.html?month=" + month + "&date=" + day);
        tradingFloorWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
