package com.coinwind.bifeng.config;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.app.BFApplication;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import static com.umeng.socialize.bean.SHARE_MEDIA.WEIXIN_FAVORITE;

/**
 * 封装友盟分享
 */
public class ShareHelp {
    /**
     * 初始化友盟分享
     *
     * @param context
     */
    public static void initUM(Context context) {
        UMConfigure.init(context, context.getResources().getString(R.string.um_appkey), "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        PlatformConfig.setWeixin(context.getResources().getString(R.string.weixin_appid), context.getResources().getString(R.string.weixin_appkey));
        UMConfigure.setLogEnabled(true);
        //初始化统计
        MobclickAgent.setScenarioType(context, MobclickAgent.EScenarioType.E_UM_NORMAL);
    }

    /**
     * 分享纯文本
     *
     * @param activity
     */
    public static void shareText(final Activity activity, String content) {
        new ShareAction(activity).withText(content).setPlatform(SHARE_MEDIA.WEIXIN)
                .setCallback(new UMShareListener() {
                    /**
                     * @descrption 分享开始的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                    }

                    /**
                     * @descrption 分享成功的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onResult(SHARE_MEDIA platform) {
                        Toast.makeText(activity, "成功了", Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @descrption 分享失败的回调
                     * @param platform 平台类型
                     * @param t 错误原因
                     */
                    @Override
                    public void onError(SHARE_MEDIA platform, Throwable t) {
                        Toast.makeText(activity, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @descrption 分享取消的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        Toast.makeText(activity, "取消了", Toast.LENGTH_LONG).show();
                    }
                }).open();
    }

    /**
     * 分享纯图片
     *
     * @param activity
     */
    public static void shareImage(final Activity activity, String imgUrl) {
//, SHARE_MEDIA.WEIXIN_CIRCLE
        UMImage image = new UMImage(activity, imgUrl);//网络图片
        new ShareAction(activity).withMedia(image).setPlatform(SHARE_MEDIA.WEIXIN)
                .setCallback(new UMShareListener() {
                    /**
                     * @descrption 分享开始的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                    }

                    /**
                     * @descrption 分享成功的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onResult(SHARE_MEDIA platform) {
                        Toast.makeText(activity, "成功了", Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @descrption 分享失败的回调
                     * @param platform 平台类型
                     * @param t 错误原因
                     */
                    @Override
                    public void onError(SHARE_MEDIA platform, Throwable t) {
                        Toast.makeText(activity, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @descrption 分享取消的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        Toast.makeText(activity, "取消了", Toast.LENGTH_LONG).show();
                    }
                }).share();
    }

    /**
     * 分享纯图片到朋友圈
     *
     * @param activity
     * @param imgUrl
     */
    public static void shareCircleImage(final Activity activity, String imgUrl) {
        UMImage image = new UMImage(activity, imgUrl);//网络图片
        new ShareAction(activity).withMedia(image).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(new UMShareListener() {
                    /**
                     * @descrption 分享开始的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                    }

                    /**
                     * @descrption 分享成功的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onResult(SHARE_MEDIA platform) {
                        Toast.makeText(activity, "成功了", Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @descrption 分享失败的回调
                     * @param platform 平台类型
                     * @param t 错误原因
                     */
                    @Override
                    public void onError(SHARE_MEDIA platform, Throwable t) {
                        Toast.makeText(activity, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @descrption 分享取消的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        Toast.makeText(activity, "取消了", Toast.LENGTH_LONG).show();
                    }
                }).share();
    }

    /**
     * 分享链接
     *
     * @param activity
     * @param url
     * @param content
     */
    public static void shareLink(final Activity activity, String url, String headImg, String content) {
        UMWeb web = new UMWeb(url);
//        web.setTitle(title);//标题
        UMImage image;
        if ("".equals(headImg)) {
            image = new UMImage(activity, headImg);
        } else {

            image = new UMImage(activity, R.mipmap.ic_launcher);//网络图片
        }
        web.setThumb(image);  //缩略图
        web.setDescription(content);//描述
        new ShareAction(activity).withMedia(web).setPlatform(SHARE_MEDIA.WEIXIN)
                .setCallback(new UMShareListener() {
                    /**
                     * @descrption 分享开始的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                    }

                    /**
                     * @descrption 分享成功的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onResult(SHARE_MEDIA platform) {
                        Toast.makeText(activity, "成功了", Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @descrption 分享失败的回调
                     * @param platform 平台类型
                     * @param t 错误原因
                     */
                    @Override
                    public void onError(SHARE_MEDIA platform, Throwable t) {
                        Toast.makeText(activity, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @descrption 分享取消的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        Toast.makeText(activity, "取消了", Toast.LENGTH_LONG).show();
                    }
                }).share();
    }

    /**
     * 分享链接
     *
     * @param activity
     * @param url
     * @param content
     */
    public static void shareLink(final Activity activity, String url, String title, String headImg, String content) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        UMImage image = new UMImage(activity, headImg);
        web.setThumb(image);  //缩略图
        web.setDescription(content);//描述
        new ShareAction(activity).withMedia(web).setPlatform(SHARE_MEDIA.WEIXIN)
                .setCallback(new UMShareListener() {
                    /**
                     * @descrption 分享开始的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                    }

                    /**
                     * @descrption 分享成功的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onResult(SHARE_MEDIA platform) {
                        Toast.makeText(activity, "成功了", Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @descrption 分享失败的回调
                     * @param platform 平台类型
                     * @param t 错误原因
                     */
                    @Override
                    public void onError(SHARE_MEDIA platform, Throwable t) {
                        Toast.makeText(activity, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @descrption 分享取消的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        Toast.makeText(activity, "取消了", Toast.LENGTH_LONG).show();
                    }
                }).share();
    }

    /**
     * 分享链接
     *
     * @param activity
     * @param url
     * @param title
     * @param content
     */
    public static void shareCircleLink(final Activity activity, String url, String title, String content) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        UMImage image = new UMImage(activity, R.mipmap.ic_launcher);//网络图片
        web.setThumb(image);  //缩略图
        web.setDescription(content);//描述
        new ShareAction(activity).withMedia(web).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(new UMShareListener() {
                    /**
                     * @descrption 分享开始的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                    }

                    /**
                     * @descrption 分享成功的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onResult(SHARE_MEDIA platform) {
                        Toast.makeText(activity, "成功了", Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @descrption 分享失败的回调
                     * @param platform 平台类型
                     * @param t 错误原因
                     */
                    @Override
                    public void onError(SHARE_MEDIA platform, Throwable t) {
                        Toast.makeText(activity, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @descrption 分享取消的回调
                     * @param platform 平台类型
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform) {
                        Toast.makeText(activity, "取消了", Toast.LENGTH_LONG).show();
                    }
                }).share();
    }
}
