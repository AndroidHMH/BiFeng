package com.coinwind.bifeng.ui.sendtask.config;

import android.widget.EditText;

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

    private static String nullOrEmpty(EditText editText) {
        String content = editText.getText().toString().trim();
        if (null == content || "".equals(content)) {
            return "";
        } else {
            return content;
        }
    }
}
