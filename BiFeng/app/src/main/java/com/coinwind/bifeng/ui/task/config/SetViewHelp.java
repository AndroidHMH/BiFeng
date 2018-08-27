package com.coinwind.bifeng.ui.task.config;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.homepage.bean.MessageEvent;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.my.config.InfoHelp;
import com.coinwind.bifeng.ui.submittask.activity.AnswerTheQuestionsActivity;
import com.coinwind.bifeng.ui.submittask.activity.SubmitDaTiActivity;
import com.coinwind.bifeng.ui.task.activity.PhotoTaskActivity;
import com.coinwind.bifeng.ui.task.activity.RegistTaskActivity;
import com.coinwind.bifeng.ui.task.activity.ZhangFenTaskActivity;
import com.coinwind.bifeng.ui.task.biz.ShareCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置UI
 */
public class SetViewHelp {
    /**
     * 获取跳转不同页面的Intent对象
     *
     * @param context
     * @param type
     * @return
     */
    public static Intent getIntent(Context context, String type) {
        Intent intent = new Intent();
        switch (Integer.parseInt(type)) {
            case 1:
                intent.setClass(context, ZhangFenTaskActivity.class);
                break;
//                return "涨粉任务";
            case 2:
                intent.setClass(context, RegistTaskActivity.class);
                break;
//                return "评论任务";
            case 3:
                intent.setClass(context, RegistTaskActivity.class);
                break;
//                return "注册任务";
            case 4:
                intent.setClass(context, PhotoTaskActivity.class);
                break;
//                return "转发任务";
            case 5:
                intent.setClass(context, AnswerTheQuestionsActivity.class);
                break;
//                return "调研任务";
            case 6:
                intent.setClass(context, SubmitDaTiActivity.class);

                break;
//                return "答题任务";
            case 7:
                intent.setClass(context, PhotoTaskActivity.class);
                break;
//                return "拍照任务";
        }
        return intent;
    }

    /**
     * 从首页跳转
     *
     * @param event
     * @param taskTypeSecondLayout
     * @return
     */
    public static String eventBusRes(MessageEvent event, LinearLayout taskTypeSecondLayout) {
        String type = event.getType();
        switch (type) {
            case "涨粉任务":
                type = "1";
                taskTypeSecondLayout.setVisibility(View.GONE);
                taskTypeSecondLayout.setEnabled(false);
                break;
//                return "涨粉任务";
            case "评论任务":
                type = "2";
                taskTypeSecondLayout.setVisibility(View.GONE);
                taskTypeSecondLayout.setEnabled(false);
                break;
//                return ;
            case "注册任务":
                type = "3";
                taskTypeSecondLayout.setVisibility(View.GONE);
                taskTypeSecondLayout.setEnabled(false);
                break;
//                return ;
            case "转发任务":
                type = "4";
                taskTypeSecondLayout.setVisibility(View.VISIBLE);
                taskTypeSecondLayout.setEnabled(true);
                break;
//                return ";
            case "调研任务":
                type = "5";
                taskTypeSecondLayout.setVisibility(View.GONE);
                taskTypeSecondLayout.setEnabled(false);
                break;
//                return ;
            case "答题任务":
                type = "6";
                taskTypeSecondLayout.setVisibility(View.GONE);
                taskTypeSecondLayout.setEnabled(false);
                break;
//                return ;
            case "拍照任务":
                type = "7";
                taskTypeSecondLayout.setVisibility(View.GONE);
                taskTypeSecondLayout.setEnabled(false);
                break;
            case "全部任务":
                type = "";
                taskTypeSecondLayout.setVisibility(View.GONE);
                taskTypeSecondLayout.setEnabled(false);
                break;
        }
        return type;
    }

    /**
     * 判断任务类型
     *
     * @param type
     * @return
     */
    public static String getType(String type) {
        switch (Integer.parseInt(type)) {
            case 1:
                return "涨粉任务";
            case 2:
                return "评论任务";
            case 3:
                return "注册任务";
            case 4:
                return "转发任务";
            case 5:
                return "调研任务";
            case 6:
                return "答题任务";
            case 7:

                return "拍照任务";
        }
        return null;
    }

    /**
     * 设置任务简介内容
     *
     * @param task_intro
     * @param type
     * @return
     */
    public static String taskContent(String task_intro, String type) {
        if (task_intro != null && !"".equals(task_intro)) {
            return task_intro;
        } else {
            switch (Integer.parseInt(type)) {
                case 1:
                    return "邀请您的好友来关注指定公众号。\n任务奖励会在雇主验收后统一发放。\n取消关注将导致任务无效。";
                case 2:
                    return "邀请您的好友来评论指定文章。\n任务奖励会在雇主验收后统一发放。\n评论内容胡乱瞎写将导致任务无效。";
                case 3:
                    return "邀请您的好友来链接里注册。\n任务奖励会在雇主验收后统一发放。\n一个群重复转发、撤回消息都将导致任务无效。";
                case 4:
                    return "把这个页面转发给您的微信群。\n任务奖励会在雇主验收后统一发放。\n一个群重复转发、撤回消息都将导致任务无效。";
                case 5:
                    return "调研任务";
                case 6:
                    return "答题任务";
                case 7:
                    return "拍照任务";
            }
        }
        return null;
    }

    /**
     * 设置雇主叮嘱内容
     *
     * @param exhort
     * @param viewGroup
     * @param textView
     */
    public static void guZhu(String exhort, ViewGroup viewGroup, TextView textView) {
        if (exhort != null && !"".equals(exhort)) {
            if (viewGroup.getVisibility() == View.GONE) {
                viewGroup.setVisibility(View.VISIBLE);
            }
            textView.setText(exhort);
        } else {
            viewGroup.setVisibility(View.GONE);
        }
    }

    /**
     * 设置任务标题
     *
     * @param answerTaskTypeImg
     * @param type
     */
    public static void setTaskTypeIcon(ImageView answerTaskTypeImg, String type) {
        switch (Integer.parseInt(type)) {
            case 1:
                answerTaskTypeImg.setImageResource(R.mipmap.zhang_fen_task_img);
                break;
//                return "涨粉任务";
            case 2:
                answerTaskTypeImg.setImageResource(R.mipmap.ping_lun_task_img);
                break;
//                return "评论任务";
            case 3:
                answerTaskTypeImg.setImageResource(R.mipmap.zhu_ce_task_img);
                break;
//                return "注册任务";
            case 4:
                answerTaskTypeImg.setImageResource(R.mipmap.zhuan_fa_task_img);
//                answerTaskTypeImg.setImageResource(R.mipmap.);
                break;
//                return "转发任务";
            case 5:
                answerTaskTypeImg.setImageResource(R.mipmap.diao_yan_task_icon);
                break;
//                return "调研任务";
            case 6:
                answerTaskTypeImg.setImageResource(R.mipmap.answer_type);
                break;
//                return "答题任务";
            case 7:
                answerTaskTypeImg.setImageResource(R.mipmap.pai_zhao_task_img);
                break;
//                return "拍照任务";
        }

    }

    /**
     * 设置分享奖励
     *
     * @param share_score
     * @return
     */
    public static int shareScores(Integer share_score) {
        if (null == share_score) {
            return 20;
        } else {
            return share_score;
        }
    }

    /**
     * 设置内容  4个步骤的
     *
     * @param type
     * @param firstTv
     * @param secondTv
     * @param thirdTv
     * @param fourthTv
     * @param defaultImg
     */
    public static void setContent(String type, TextView firstTv, TextView secondTv, TextView
            thirdTv, TextView fourthTv, ImageView defaultImg) {
        switch (Integer.parseInt(type)) {
            case 1:
                firstTv.setText("步   复制微信号");
                secondTv.setText("步   关注公众号；");
                thirdTv.setText("步   发送您的币风昵称到公众号，并截图；");
                fourthTv.setText("步   回到本页面点击下一步提交任务，并上传任务截图；");
                defaultImg.setImageResource(R.mipmap.zhang_fen_default_img);
//                return "涨粉任务";
                break;

            case 3:
//                return "注册任务";
                break;

            case 5:
//                return "调研任务";
                break;
            case 6:
//                return "答题任务";
                break;

        }

    }

    /**
     * 设置内容  3个步骤的
     *
     * @param type
     * @param firstTv
     * @param secondTv
     * @param thirdTv
     * @param defaultImg
     */
    public static void setContent(String type, TextView firstTv, TextView secondTv, TextView
            thirdTv, ImageView defaultImg) {
        switch (Integer.parseInt(type)) {
            case 2:
                firstTv.setText("步   打开任务链接；");
                secondTv.setText("步   在链接内完成评论，并截图；");
                thirdTv.setText("步   回到本页面点击下一步提交任务，并上传任务截图；");
                defaultImg.setImageResource(R.mipmap.ping_lun_default_img);
                break;
            case 3:
//                return "注册任务";
                break;
            case 4:
                firstTv.setText("步   进入正文，将正文页面分享给微信群；");
                secondTv.setText("步   将分享的界面截图；");
                thirdTv.setText("步   回到本页面点击下一步提交任务，并上传任务截图；");
                defaultImg.setImageResource(R.mipmap.zhuan_fa_default_img);
                break;
            case 5:
//                return "调研任务";
                break;
            case 6:
//                return "答题任务";
                break;
            case 7:
                firstTv.setText("步   按照雇主要求到达指定位置；");
                secondTv.setText("步   按照雇主要求拍摄指定照片；");
                thirdTv.setText("步   提交截图；");
                defaultImg.setImageResource(R.mipmap.bai_zhao_default_img);
                break;
        }

    }

    /**
     * 设置链接
     *
     * @param url
     * @param urlTextView
     */
    public static void setUrl(String url, TextView urlTextView) {
        if (url != null) {
            urlTextView.setText(url);
        } else {
            urlTextView.setText("https://www.baidu.com");
        }
    }

    //全部任务
    public static final int TYPE_ONE = 1;
    //全部分类
    public static final int TYPE_TWO = 2;
    //酬劳
    public static final int TYPE_THREE = 3;

    /**
     * 返回popupWindow显示的集合
     *
     * @param type
     * @return
     */
    public static List<String> getList(int type) {
        List<String> titles = new ArrayList<>();
        switch (type) {
            case TYPE_ONE:
                titles.add("全部任务");
                titles.add("涨粉任务");
                titles.add("评论任务");
                titles.add("注册任务");
                titles.add("转发任务");
                titles.add("调研任务");
                titles.add("答题任务");
                titles.add("拍照任务");
                break;
            case TYPE_TWO:
                titles.add("全部分类");
                titles.add("项目动态");
                titles.add("新币上线");
                titles.add("技术周报");
                titles.add("最新公告");
                titles.add("热门活动");
                titles.add("品牌介绍");
                break;
            case TYPE_THREE:
                titles.add("最新发布");
                titles.add("酬劳最高");
                titles.add("赏金剩余最多");
                titles.add("赏金剩余最少");
                break;
        }
        return titles;
    }

    /**
     * 获取orderField参数的值
     *
     * @param position
     * @return
     */
    public static String getOrderField(int position) {
        String orderField = "";
        switch (position) {
            case 0:
                orderField = "a.lrrq";
                break;
            case 1:
                orderField = "a.score";
                break;
            case 2:
                orderField = "a.current_score";
                break;
            case 3:
                orderField = "a.current_score";
                break;
        }
        return orderField;
    }

    /**
     * 获取orderSort参数的值
     *
     * @param position
     * @return
     */
    public static String getOrderSort(int position) {
        String orderSort = "";

        if (position == 3) {
            orderSort = "ASC";
        } else {
            orderSort = "DESC";
        }
        return orderSort;
    }

    /**
     * 申请分享权限
     *
     * @param context
     * @param requestPermissionCode
     */
    public static void applySharePermission(Activity context,
                                            int requestPermissionCode, ShareCallback shareCallback) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(context, mPermissionList, requestPermissionCode);
        } else {
            shareCallback.share();
        }
    }

    /**
     * 分享需要的权限回调
     *
     * @param context
     * @param grantResults
     */
    public static void sharePermissionResult(Context context, int[] grantResults, ShareCallback shareCallback) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //有权限直接分享
            shareCallback.share();
        } else {
            ToastHelp.showShort(context, "您求权限未授权");
        }
    }

    /**
     * 申请保存到本地的权限
     *
     * @param context
     * @param requestPermissionCode
     */
    public static void saveLocalPermission(Activity context, int requestPermissionCode, ShareCallback shareCallback ) {
        //使用兼容库就无需判断系统版本
        int hasWriteStoragePermission = ContextCompat.checkSelfPermission(context.getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int hasMountStoragePermission = ContextCompat.checkSelfPermission(context.getApplication(), Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS);
        if (hasWriteStoragePermission == PackageManager.PERMISSION_GRANTED && hasMountStoragePermission == PackageManager.PERMISSION_GRANTED) {
            //拥有权限，执行操作
            //下载到本地
            shareCallback.share();
        } else {
            //没有权限，向用户请求权限
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, requestPermissionCode);
        }
    }

    /**
     * 分享需要的权限回调
     *
     * @param context
     * @param grantResults
     */
    public static void saveLocalPermissionResult(Context context, int[] grantResults,  ShareCallback shareCallback) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //有权限直接分享
            //下载到本地
            shareCallback.share();
        } else {
            ToastHelp.showShort(context, "您求权限未授权");
        }
    }

    /**
     * 跳转发布任务界面
     *
     * @param context
     */
    public static void startFaBo(Context context) {
        if (SpHelp.getLoginStatus()) {
            if (SpHelp.getUserType().equals(SpHelp.EMPLOYERS)) {
                if (Integer.parseInt(SpHelp.getUserInformation(SpHelp.AUTH_FLAG)) == 0) {
                    ToastHelp.showShort(context, "请前往认证");
                } else {
                    Intent myTaskIntent = InfoHelp.getMyTaskIntent(context, true);
                    context.startActivity(myTaskIntent);
                }
            } else {
                ToastHelp.showShort(context, "您当前身份为服务商，请切换任务。再来尝试");
            }
        } else {
            LoginActivity.openLoginActivity(context);
        }
    }

    /**
     * 跳转执行任务界面
     *
     * @param context
     */
    public static void startZhiXing(Context context) {
        if (SpHelp.getLoginStatus()) {
            if (SpHelp.getUserType().equals(SpHelp.SERVICE_PROVIDERS)) {
                Intent myTaskIntent = InfoHelp.getMyTaskIntent(context, false);
                context.startActivity(myTaskIntent);
            } else {
                ToastHelp.showShort(context, "您当前身份为雇主，请切换任务。再来尝试");
            }
        } else {
            LoginActivity.openLoginActivity(context);
        }
    }
}
