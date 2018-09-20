package com.coinwind.bifeng.ui.task.activity;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.config.Urls;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoNewTaskActivity extends BaseActivity {

    @BindView(R.id.do_new_task_web_view)
    WebView doNewTaskWebView;
    private String url;
    private String userId;
    private String sign;
    private String taskId;
    private String mTab;
    private String img;
    public static final int CANCEL_CODE = 1000;
    public static final int PHONE_SUCCESS_CODE = 2000;
    public static final int PHONE_SUCCESS_LOGIN_CODE = 2001;
    public static final int NAME_SUCCESS = 3000;
    public static final int PUBLIC_SUCCESS = 4000;
    public static final int STRATEGY_SUCCESS = 4000;
    public static final int INVITE_SUCCESS = 4000;

    public static void bindPhoneOrSetUpNickNameOrPublicNum(Activity context, String url, String userId, String sign, String taskId, String mTab, int requestCode) {
        Intent intent = new Intent(context, DoNewTaskActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("userId", userId);
        intent.putExtra("sign", sign);
        intent.putExtra("taskId", taskId);
        intent.putExtra("mTab", mTab);
        context.startActivityForResult(intent, requestCode);
    }

    public static void bindWallet(Activity context, String url, String userId, String sign, String taskId, String mTab, String img, int requestCode) {
        Intent intent = new Intent(context, DoNewTaskActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("userId", userId);
        intent.putExtra("sign", sign);
        intent.putExtra("taskId", taskId);
        intent.putExtra("mTab", mTab);
        intent.putExtra("img", img);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_do_new_task;
    }

    @Override
    protected void init() {
        getParams();
        doNewTaskWebView.getSettings().setJavaScriptEnabled(true);//支持js
        doNewTaskWebView.getSettings().setTextZoom(100);
        doNewTaskWebView.getSettings().setBlockNetworkImage(false);
        doNewTaskWebView.getSettings().setDomStorageEnabled(true);
        doNewTaskWebView.setWebChromeClient(new WebChromeClient());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            doNewTaskWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        String url = this.url + "?userId=" + userId + "&sign=" + sign + "&taskId=" + taskId + "&mTab=" + mTab;
        if (img != null && !"".equals(img)) {
            url += "&img=" + img;
        }
        doNewTaskWebView.addJavascriptInterface(new A(), "sign");
        doNewTaskWebView.loadUrl(url);
        doNewTaskWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("urls", "url = " + url);

                if (!TextUtils.isEmpty(url) && url.contains("_cancel_")) {//所有的取消信号
                    setResult(CANCEL_CODE);
                    DoNewTaskActivity.this.finish();
                    return true;
                } else if (!TextUtils.isEmpty(url) && url.contains("_success_")) {
//                    window.location.href = "https://_success_&" + res.data.userId +"_" + res.data.sign
                    //绑定手机号成功  获取userId和sign
                    String[] split = url.split("__");
                    String userId = split[2];
                    String sign = split[3].substring(0, split[3].length() - 1);
                    Intent intent = getIntent();
                    intent.putExtra("userId", userId);
                    intent.putExtra("sign", sign);
                    setResult(PHONE_SUCCESS_CODE, intent);
                    DoNewTaskActivity.this.finish();
                    return true;
                } else if (!TextUtils.isEmpty(url) && url.contains("_1014_")) {//改用户已存在
                    setResult(PHONE_SUCCESS_LOGIN_CODE);
                    finish();
                } else if (!TextUtils.isEmpty(url) && url.contains("_namesuccess_")) {//设置昵称成功
                    //昵称
                    setResult(NAME_SUCCESS);
                    finish();
                    return true;
                } else if (!TextUtils.isEmpty(url) && url.contains("_iscopy_")) {
                    //要复制到剪切板的内容"https://_isCopy_" + document.getElementById("pid").innerHTML
                    String[] split = url.split("_");
                    copyContent(split[2].substring(0, split[2].length() - 1));
                    return true;
                } else if (!TextUtils.isEmpty(url) && url.contains("_strategy_")) {//创世攻略成功
                    setResult(STRATEGY_SUCCESS);
                    return true;
                } else if (!TextUtils.isEmpty(url) && url.contains("_invitesuccess_")) {//提交邀请码
                    setResult(INVITE_SUCCESS);
                }
//                else if (!TextUtils.isEmpty(url) && url.contains("_strategy_")) {//创世攻略成功
//                    setResult(STRATEGY_SUCCESS);
//                    return true;
//                }
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

    public void getParams() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        userId = intent.getStringExtra("userId");
        sign = intent.getStringExtra("sign");
        taskId = intent.getStringExtra("taskId");
        mTab = intent.getStringExtra("mTab");
        img = intent.getStringExtra("img");
    }

    class A {
        @JavascriptInterface
        public void getSign() {

        }
    }
}
