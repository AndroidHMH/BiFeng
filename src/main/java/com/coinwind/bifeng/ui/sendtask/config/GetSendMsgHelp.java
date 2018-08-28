package com.coinwind.bifeng.ui.sendtask.config;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import com.coinwind.bifeng.ui.sendtask.activity.SendDiaoYanTaskActivity;
import com.coinwind.bifeng.ui.sendtask.activity.SendTaskActivity;

/**
 * 获取发布任务的信息
 */
public class GetSendMsgHelp {
    /**
     * 获取发布任务的标签
     *
     * @param sendTaskTitleFirstEt
     * @param sendTaskTitleSecondEt
     * @param sendTaskTitleThirdEt
     * @return
     */
    public static String getLabel(EditText sendTaskTitleFirstEt, EditText sendTaskTitleSecondEt, EditText sendTaskTitleThirdEt) {
        String firstLabel = nullOrEmpty(sendTaskTitleFirstEt);
        String secondLabel = nullOrEmpty(sendTaskTitleSecondEt);
        String thirdLabel = nullOrEmpty(sendTaskTitleThirdEt);
        if (!"".equals(firstLabel) && !"".equals(secondLabel) && !"".equals(thirdLabel)) {
            return firstLabel + "," + secondLabel + "," + thirdLabel;
        } else if (!"".equals(firstLabel) && !"".equals(secondLabel)) {
            return firstLabel + "," + secondLabel;
        } else if (!"".equals(secondLabel) && !"".equals(thirdLabel)) {
            return secondLabel + "," + thirdLabel;
        } else if (!"".equals(firstLabel) && !"".equals(thirdLabel)) {
            return firstLabel + "," + thirdLabel;
        } else if (!"".equals(firstLabel)) {
            return firstLabel;
        } else if (!"".equals(secondLabel)) {
            return secondLabel;
        } else {
            return thirdLabel;
        }
    }

    /**
     * 判断null或空
     *
     * @param editText
     * @return
     */
    private static String nullOrEmpty(EditText editText) {
        String content = editText.getText().toString().trim();
        if (null == content || "".equals(content)) {
            return "";
        } else {
            return content;
        }
    }


    /**
     * 转发任务跳过来的
     *
     * @param context
     * @param taskTitle   任务标题
     * @param taskContent 任务正文
     * @param taskType    转发任务任务类型
     * @param label       标签
     * @param imgUrl      配图Url
     * @param type        任务类型
     */
    public static void zhuanFaOpenSendTaskActivity(Context context, String taskTitle, String taskContent, String taskType, String label, String imgUrl, String type) {
        Intent intent = new Intent(context, SendTaskActivity.class);

        intent.putExtra("label", label);
        intent.putExtra("type", type);
        intent.putExtra("taskTitle", taskTitle);
        intent.putExtra("imgUrl", imgUrl);
        intent.putExtra("taskContent", taskContent);
        intent.putExtra("taskType", taskType);
        intent.putExtra("publicNum", "");
        intent.putExtra("publicImg", "");
        intent.putExtra("url", "");

        context.startActivity(intent);
    }

    /**
     * 注册(评论)任务跳过来的
     *
     * @param context
     * @param taskTitle
     * @param label
     * @param imgUrl
     * @param url       注册任务地址
     * @param type
     */
    public static void zhuCeOpenSendTaskActivity(Context context, String taskTitle, String imgUrl, String url, String label, String type) {
        Intent intent = new Intent(context, SendTaskActivity.class);

        intent.putExtra("label", label);
        intent.putExtra("type", type);
        intent.putExtra("taskTitle", taskTitle);
        intent.putExtra("imgUrl", imgUrl);
        intent.putExtra("taskContent", "");
        intent.putExtra("taskType", "");
        intent.putExtra("publicNum", "");
        intent.putExtra("publicImg", "");
        intent.putExtra("url", url);

        context.startActivity(intent);
    }

    /**
     * 调研（答题）任务跳过来的
     *
     * @param context
     * @param taskTitle
     * @param label
     * @param imgUrl
     * @param type
     */
    public static void diaoYanOpenSendTaskActivity(Context context, String taskTitle, String imgUrl, String label, String type) {
        Intent intent = new Intent(context, SendTaskActivity.class);

        intent.putExtra("label", label);
        intent.putExtra("type", type);
        intent.putExtra("taskTitle", taskTitle);
        intent.putExtra("imgUrl", imgUrl);
        intent.putExtra("taskContent", "");
        intent.putExtra("taskType", "");
        intent.putExtra("publicNum", "");
        intent.putExtra("publicImg", "");
        intent.putExtra("url", "");

        context.startActivity(intent);
    }

    /**
     * 涨粉任务跳过来的
     *
     * @param context
     * @param taskTitle
     * @param label
     * @param imgUrl
     * @param publicNum 涨粉任务公众号
     * @param type
     */
    public static void zhangFenOpenSendTaskActivity(Context context, String taskTitle, String imgUrl, String publicImg, String publicNum, String label, String type) {
        Intent intent = new Intent(context, SendTaskActivity.class);

        intent.putExtra("label", label);
        intent.putExtra("type", type);
        intent.putExtra("taskTitle", taskTitle);
        intent.putExtra("imgUrl", imgUrl);
        intent.putExtra("taskContent", "");
        intent.putExtra("taskType", "");
        intent.putExtra("publicNum", publicNum);
        intent.putExtra("publicImg", publicImg);
        intent.putExtra("url", "");

        context.startActivity(intent);
    }


    /**
     * 跳转发布调研任务界面
     *
     * @param context
     * @param taskTitle
     * @param imgUrl
     * @param label
     * @param type
     * @param share_score
     * @param all_shareNum
     * @param score
     * @param all_tasknum
     * @param end_time
     * @param startTime
     * @param task_intro
     * @param needCheck
     */
    public static void openSendDiaoYanTaskActivity(Context context, String taskTitle, String imgUrl, String label,
                                                   String type, String share_score, String all_shareNum, String score,
                                                   String all_tasknum, String end_time, String startTime, String task_intro,
                                                   String needCheck, Class<? extends Context> toContext) {

//        Intent intent = new Intent(context, SendDiaoYanTaskActivity.class);
        Intent intent = new Intent(context, toContext);

        intent.putExtra("label", label);
        intent.putExtra("type", type);
        intent.putExtra("taskTitle", taskTitle);
        intent.putExtra("imgUrl", imgUrl);
        intent.putExtra("taskContent", "");
        intent.putExtra("taskType", "");
        intent.putExtra("publicNum", "");
        intent.putExtra("publicImg", "");
        intent.putExtra("share_score", share_score);
        intent.putExtra("all_shareNum", all_shareNum);
        intent.putExtra("url", "");
        intent.putExtra("score", score);
        intent.putExtra("all_tasknum", all_tasknum);
        intent.putExtra("end_time", end_time);
        intent.putExtra("startTime", startTime);
        intent.putExtra("task_intro", task_intro);
        intent.putExtra("needCheck", needCheck);

        context.startActivity(intent);
    }
}
