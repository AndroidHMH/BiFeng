package com.coinwind.bifeng.ui.my.config;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.ui.setting.activity.SetMessageActivity;

public class InfoHelp {
    /**
     * 获取跳转的Intent对象
     *
     * @param context
     * @param infoType
     * @return
     */
    public static Intent getPerfectInformationIntent(Context context, String infoType) {
        Intent intent = new Intent(context, SetMessageActivity.class);
        switch (infoType) {
            case SpHelp.NICK_NAME:
                intent.putExtra("info", SpHelp.NICK_NAME);
                break;
            case SpHelp.PHONE:
                intent.putExtra("info", SpHelp.PHONE);
                break;
            case SpHelp.HEAD_IMG:
                intent.putExtra("info", SpHelp.HEAD_IMG);
                break;
            case SpHelp.QIYE_INFO:
                intent.putExtra("info", SpHelp.QIYE_INFO);
                break;
        }
        return intent;
    }

    /**
     * 向Intent存值
     *
     * @param data
     * @return
     */
    public static String putUserInfo(Intent data) {
        String field = data.getStringExtra("field");
        String value = data.getStringExtra("value");
        SpHelp.putUserInformation(field, value);
        return value;
    }

    /**
     * 是否显示企业介绍
     *
     * @param perfectInformationMyBtn
     * @param perfectInformationMyTv
     */
    public static void isShowQiYe(RelativeLayout perfectInformationMyBtn, TextView perfectInformationMyTv) {
        String userType = SpHelp.getUserType();
        if (SpHelp.EMPLOYERS.equals(userType)) {
            perfectInformationMyBtn.setVisibility(View.VISIBLE);
            String qi_ye_info = SpHelp.getUserInformation(SpHelp.QIYE_INFO);
            if (qi_ye_info != null && !"".equals(qi_ye_info)) {
                perfectInformationMyTv.setText(qi_ye_info);
            }
        } else {
            perfectInformationMyBtn.setVisibility(View.GONE);
        }


    }
}
