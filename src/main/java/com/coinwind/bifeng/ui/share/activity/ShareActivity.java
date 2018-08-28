package com.coinwind.bifeng.ui.share.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.ShareHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.submittask.activity.SubmitCommentsActivity;
import com.coinwind.bifeng.ui.task.activity.AnswerTaskActivity;
import com.coinwind.bifeng.ui.task.biz.ShareCallback;
import com.coinwind.bifeng.ui.task.config.SetViewHelp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分享任务的界面
 */
public class ShareActivity extends BaseActivity {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.share_web_view)
    WebView shareWebView;
    @BindView(R.id.share_fen_xiang_btn)
    LinearLayout shareFenXiangBtn;
    @BindView(R.id.share_all_layout)
    LinearLayout shareAllLayout;
    @BindView(R.id.share_next_btn)
    LinearLayout shareNextBtn;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    private TaskBean bean;
    public static final int REQUEST_PERMISSION_CODE = 100;
    private String shareLinkUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void init() {
        bean = (TaskBean) getIntent().getSerializableExtra("bean");
        shareWebView.getSettings().setJavaScriptEnabled(true);
        shareWebView.getSettings().setBlockNetworkImage(false);
        shareWebView.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            shareWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        shareLinkUrl = Urls.SHARE_LINK_URL + "?taskId=" + bean.getId();
        shareWebView.loadUrl(shareLinkUrl);

        // 设置setWebChromeClient对象
        shareWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.e("ANDROID_LAB", "TITLE=" + title);
                titleTitleTv.setText(title);
            }

        });
    }

    @Override
    protected void loadDate() {

    }

    @Override
    protected void hideErrorView() {
        shareAllLayout.setVisibility(View.GONE);
    }

    @Override
    protected void showSuccessView() {
        shareAllLayout.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.title_layout_return_btn, R.id.share_fen_xiang_btn, R.id.share_next_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.share_fen_xiang_btn:
                if (SpHelp.getLoginStatus()) {
                    SetViewHelp.applySharePermission(this, REQUEST_PERMISSION_CODE, new ShareCallback() {
                        @Override
                        public void share() {
                            ShareHelp.shareLink(ShareActivity.this, shareLinkUrl, bean.getTitle(), bean.getHead_img(), bean.getContent());
                        }
                    });
                } else {
                    LoginActivity.openLoginActivity(this);
                }
                break;
            case R.id.share_next_btn:
                Intent intent = new Intent(this, SubmitCommentsActivity.class);
                intent.putExtra("bean", bean);
                intent.putExtra("type", "noPhone");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        SetViewHelp.sharePermissionResult(this, grantResults, new ShareCallback() {
            @Override
            public void share() {
                ShareHelp.shareLink(ShareActivity.this, shareLinkUrl, bean.getTitle(), bean.getHead_img(), bean.getContent());
            }
        });
    }
}
