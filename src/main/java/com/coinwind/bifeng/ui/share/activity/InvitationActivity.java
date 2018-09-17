package com.coinwind.bifeng.ui.share.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.ShareHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.share.contract.InvitationContract;
import com.coinwind.bifeng.ui.share.presenter.InvitationPresenter;
import com.coinwind.bifeng.ui.task.activity.DoNewTaskActivity;
import com.coinwind.bifeng.ui.task.biz.ShareCallback;
import com.coinwind.bifeng.ui.task.config.SetViewHelp;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvitationActivity extends BaseActivity<InvitationPresenter> implements View.OnClickListener, InvitationContract.View {


    public ImageView share_img;
    public LinearLayout save_phone_btn;
    public LinearLayout share_wei_xin_btn;
    public LinearLayout share_peng_you_quan_btn;
    public TextView share_return_btn;
    @BindView(R.id.invitation_web_view)
    WebView invitationWebView;
    @BindView(R.id.invitation_title_tv)
    TextView invitationTitleTv;
    @BindView(R.id.invitation_return_btn)
    LinearLayout invitationReturnBtn;
    @BindView(R.id.invitation_open_share_btn)
    LinearLayout invitationOpenShareBtn;
    @BindView(R.id.invitation_pro)
    LinearLayout invitationPro;
    @BindView(R.id.invitation_title_layout)
    RelativeLayout invitationTitleLayout;
    private RelativeLayout share_popup_layout_btn;
    private PopupWindow popupWindow;

    public static final int REQUEST_PERMISSION_CODE = 100;
    public static final int SHARE_WEIXIN_CIRCLE_PERMISSION_CODE = 300;
    public static final int SAVE_REQUEST_PERMISSION_CODE = 200;
    private String imgUrl;
    private String newUrl;
    private String shareUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invitation;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void init() {
        invitationTitleTv.setText("邀请好友");
        initPopup();

        invitationWebView.getSettings().setJavaScriptEnabled(true);//支持js

        invitationWebView.getSettings().setBlockNetworkImage(false);
        invitationWebView.getSettings().setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            invitationWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        String url = Urls.YAO_QING_URL;
        invitationWebView.loadUrl(url);
        invitationWebView.setWebViewClient(new WebViewClient() {


            //返回值：true 不会显示网页资源，需要等待你的处理，false 就认为系统没有做处理，会显示网页资源
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //当url里面包含webview字段的时候，则跳转到ShowActivity原生页面，否则还是继续显示网页
                //比如：在百度输入框里面输入webview在点击搜索，再点击任何有webview字段的链接，
                //则不继续显示网页，而是跳转到自己定义的原生页面
                if (!TextUtils.isEmpty(url) && url.contains("_lujing_")) {
                    String[] split = url.split("_");
                    String ma = split[split.length - 1];
                    try {
                        String nickName = new String(SpHelp.getUserInformation(SpHelp.NICK_NAME).getBytes(), "UTF-8");
                        shareUrl = "https://m.coinwind.com/share/kehuyaoq.html?ma=" + ma.substring(0, ma.length() - 1) + "&name=" + nickName;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return true;
                } else if (!TextUtils.isEmpty(url) && url.contains("_iscopy_")) {
                    String[] split = url.split("_");
                    copyContent(split[split.length - 1]);
                    return true;
                } else if (!TextUtils.isEmpty(url) && url.contains("_cancel_")) {//所有的取消信号
                    InvitationActivity.this.finish();
                    return true;
                } else if (!TextUtils.isEmpty(url) && url.contains("_rntent_")) {//需要跳转
                    newUrl = "https://m.coinwind.com/share/invitation.html?userId=" + SpHelp.getUserInformation(SpHelp.ID) + "&sign=" + SpHelp.getSign();
                    invitationTitleLayout.setVisibility(View.VISIBLE);
                    view.loadUrl(newUrl);
                }
                return false;
            }
        });
    }

    private void copyContent(String content) {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setText(content);
        ToastHelp.showShort(this, content + "已经复制到剪切板");
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.invitation_return_btn, R.id.invitation_open_share_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.invitation_return_btn:
                finish();
                break;
            case R.id.invitation_open_share_btn:
                if (shareUrl != null && !"".equals(shareUrl))
                    showPopup();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_phone_btn:
                //保存到本地
                SetViewHelp.saveLocalPermission(this, SAVE_REQUEST_PERMISSION_CODE, new ShareCallback() {
                    @Override
                    public void share() {
                        presenter.loadImg(imgUrl);
                    }
                });
                break;
            case R.id.share_wei_xin_btn:
                SetViewHelp.applySharePermission(this, REQUEST_PERMISSION_CODE, new ShareCallback() {
                    @Override
                    public void share() {
                        ShareHelp.shareLink(InvitationActivity.this, shareUrl, SpHelp.getUserInformation(SpHelp.HEAD_IMG), SpHelp.getUserInformation(SpHelp.NICK_NAME) + "邀请您免费领取海量积分，可兑换交易所的Token！我已领取¥10");
                    }
                });
                dismissPopup();
                break;
            case R.id.share_peng_you_quan_btn:
                //分享朋友圈
                SetViewHelp.applySharePermission(this, SHARE_WEIXIN_CIRCLE_PERMISSION_CODE, new ShareCallback() {
                    @Override
                    public void share() {
//                        ShareHelp.shareCircleLink(InvitationActivity.this, shareUrl, "2头部标题", "http://coinwind.oss-cn-qingdao.aliyuncs.com/20180916030542_636.jpg", "分享链接简介");
                    }
                });
                dismissPopup();
                break;
            case R.id.share_return_btn:
                dismissPopup();
                break;
            case R.id.share_popup_layout_btn:
                dismissPopup();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                SetViewHelp.sharePermissionResult(this, grantResults, new ShareCallback() {
                    @Override
                    public void share() {
                        ShareHelp.shareLink(InvitationActivity.this, shareUrl, SpHelp.getUserInformation(SpHelp.HEAD_IMG), SpHelp.getUserInformation(SpHelp.NICK_NAME) + "邀请您免费领取海量积分，可兑换交易所的Token！我已领取¥10");
                    }
                });
                break;
            case SHARE_WEIXIN_CIRCLE_PERMISSION_CODE:
                SetViewHelp.sharePermissionResult(this, grantResults, new ShareCallback() {
                    @Override
                    public void share() {
//                        ShareHelp.shareCircleLink(InvitationActivity.this, shareUrl, "4头部标题", "http://coinwind.oss-cn-qingdao.aliyuncs.com/20180916030542_636.jpg", "分享链接简介");
                    }
                });
                break;
            case SAVE_REQUEST_PERMISSION_CODE:
                SetViewHelp.saveLocalPermissionResult(this, grantResults, new ShareCallback() {
                    @Override
                    public void share() {
                        presenter.loadImg(imgUrl);
                    }
                });
                break;
        }

    }

    @Override
    public void showImgLoad() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dismissPopup();
                ToastHelp.showShort(InvitationActivity.this, "下载成功");
            }
        });
    }

    @Override
    public void showImgError() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dismissPopup();
                ToastHelp.showShort(InvitationActivity.this, "下载失败");
            }
        });
    }


    class ChromeClient extends WebChromeClient {

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
            Log.e("tag", "on Show Custom View >>>>>webView");

        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
//            mProgressBar.setProgress(newProgress);
//            if (newProgress == mProgressBar.getMax()) {
//                mProgressBar.setVisibility(View.GONE);
//            } else {
//                mProgressBar.setVisibility(View.VISIBLE);
//            }
        }

    }

    private void initPopup() {
        View inflate = getLayoutInflater().inflate(R.layout.share_popup_layout, null);
        initViews(inflate);
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        popupWindow.setOutsideTouchable(true);
    }

    private void initViews(View rootView) {
        this.share_img = (ImageView) rootView.findViewById(R.id.share_img);
        this.save_phone_btn = (LinearLayout) rootView.findViewById(R.id.save_phone_btn);
        this.share_wei_xin_btn = (LinearLayout) rootView.findViewById(R.id.share_wei_xin_btn);
        this.share_peng_you_quan_btn = (LinearLayout) rootView.findViewById(R.id.share_peng_you_quan_btn);
        this.share_return_btn = (TextView) rootView.findViewById(R.id.share_return_btn);
        share_popup_layout_btn = (RelativeLayout) rootView.findViewById(R.id.share_popup_layout_btn);

        save_phone_btn.setOnClickListener(this);
        share_wei_xin_btn.setOnClickListener(this);
        share_peng_you_quan_btn.setOnClickListener(this);
        share_return_btn.setOnClickListener(this);
        share_popup_layout_btn.setOnClickListener(this);
    }


    private void showPopup() {
        Glide.with(this).load(imgUrl).into(share_img);

        popupWindow.setClippingEnabled(false);
        popupWindow.showAsDropDown(getLayoutInflater().inflate(R.layout.activity_answer_task, null));
    }

    private void dismissPopup() {
        popupWindow.dismiss();
    }

}
