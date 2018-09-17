package com.coinwind.bifeng.ui.my.config;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.ui.my.activity.MyTaskActivity;
import com.coinwind.bifeng.ui.my.fragment.MyTaskFragment;
import com.coinwind.bifeng.ui.setting.activity.ChangePaswActivity;
import com.coinwind.bifeng.ui.setting.activity.SetMessageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置UI的辅助类
 */
public class InfoHelp {
    /**
     * 获取跳转的Intent对象
     *
     * @param context
     * @param infoType
     * @return
     */
    public static Intent getPerfectInformationIntent(Context context, String infoType) {
        Intent intent = new Intent(context, ChangePaswActivity.class);
        switch (infoType) {
            case SpHelp.NICK_NAME:
                intent.putExtra("info", SpHelp.NICK_NAME);
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

    /**
     * 获取跳转我的任务界面的Intent
     *
     * @param context
     * @param type
     * @return
     */
    public static Intent getMyTaskIntent(Context context, boolean type) {
        Intent intent = new Intent(context, MyTaskActivity.class);
        ArrayList<String> titles = new ArrayList<>();
        if (type) {
            titles.add("未开始");
        }
        titles.add("已开始");
        titles.add("已结束");
        intent.putStringArrayListExtra("titles", titles);
        return intent;
    }

    /**
     * 创建Fragment集合
     *
     * @param fragmentSize
     * @return
     */
    public static List<Fragment> addFragment(int fragmentSize) {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < fragmentSize; i++) {
            MyTaskFragment myTaskFragment = new MyTaskFragment();
            Bundle bundle = new Bundle();
            if (fragmentSize == 3) {
                if (i == 0) {
                    bundle.putString("flag", "-1");
                } else if (i == 1) {
                    bundle.putString("flag", "0");
                } else {
                    bundle.putString("flag", "1");
                }
                bundle.putString("reqType", "2");
            } else {
                if (i == 0) {
                    bundle.putString("flag", "0");
                } else {
                    bundle.putString("flag", "1");
                }
                bundle.putString("reqType", "1");
            }
            myTaskFragment.setArguments(bundle);
            fragments.add(myTaskFragment);
        }
        return fragments;
    }

    /**
     * 设置我执行(发布)的任务页面的标题
     *
     * @param titles
     * @param title
     */
    public static void setTitle(ArrayList<String> titles, TextView title) {
        if (titles.size() == 2) {
            title.setText("我执行的任务");
        } else {
            title.setText("我发布的任务");
        }
    }
}
