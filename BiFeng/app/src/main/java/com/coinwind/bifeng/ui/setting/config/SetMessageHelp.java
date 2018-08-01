package com.coinwind.bifeng.ui.setting.config;

import android.widget.EditText;
import android.widget.TextView;

import com.coinwind.bifeng.config.SpHelp;

public class SetMessageHelp {
    public static final int NICK_NAME_REQUEST_CODE = 200;
    public static final int QIYE_INFO_REQUEST_CODE = 300;

    /**
     * 设置标题
     *
     * @param info
     * @param titleTv
     */
    public static void setTitle(String info, TextView titleTv) {
        String title = "";
        switch (info) {
            case SpHelp.NICK_NAME:
                title = "更换昵称";
                break;
            case SpHelp.QIYE_INFO:
                title = "设置企业介绍";
                break;
        }
        titleTv.setText(title);
    }

    /**
     * 设置默认提示内容
     *
     * @param info
     * @param editText
     */
    public static void setHintText(String info, EditText editText) {
        String hint = "";
        switch (info) {
            case SpHelp.NICK_NAME:
                hint = "请输入昵称......";
                break;
            case SpHelp.QIYE_INFO:
                hint = "请输入企业介绍......";
                break;
        }
        editText.setHint(hint);
    }

    /**
     * 获取相应码
     *
     * @param info
     * @return
     */
    public static int getRequestCode(String info) {
        if (info.equals(SpHelp.NICK_NAME))
            return NICK_NAME_REQUEST_CODE;
        else
            return QIYE_INFO_REQUEST_CODE;
    }
}
