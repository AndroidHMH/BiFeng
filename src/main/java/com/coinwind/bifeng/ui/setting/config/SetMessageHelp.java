package com.coinwind.bifeng.ui.setting.config;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.config.SpHelp;

/**
 * 设置页面的辅助类
 */
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
            case "修改密码":
                title = "修改密码";
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
    public static void setHintText(String info, EditText editText, LinearLayout setMessageCountLayout, EditText setMessageQiYiEt) {
        String hint = "";
        switch (info) {
            case SpHelp.NICK_NAME:
                editText.setVisibility(View.VISIBLE);
                setMessageCountLayout.setVisibility(View.VISIBLE);
                setMessageQiYiEt.setVisibility(View.GONE);
                hint = "请输入昵称......";
                setMessageQiYiEt.setText(SpHelp.getUserInformation(SpHelp.NICK_NAME));
                break;
            case SpHelp.QIYE_INFO:
                editText.setVisibility(View.GONE);
                setMessageCountLayout.setVisibility(View.GONE);
                setMessageQiYiEt.setVisibility(View.VISIBLE);
                setMessageQiYiEt.setText(SpHelp.getUserInformation(SpHelp.QIYE_INFO));
                setMessageQiYiEt.setSelection(setMessageQiYiEt.getText().length());
                break;
            case "修改密码":
                hint = "请输入密码......";
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
