package com.coinwind.bifeng.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.ui.login.bean.LoginBean;

import java.security.Key;

/**
 * 封装SharedPreferences
 */
public class SpHelp {
    //登录的sp
    public static final String IS_LOGIN = "login";
    //用户的sp
    public static final String USER = "user";
    //sign sp
    public static final String SIGN = "sign";
    //用户头像
    public static final String HEAD_IMG = "head_img";
    //用户手机号
    public static final String PHONE = "phone";
    //用户昵称
    public static final String NICK_NAME = "nick_name";
    //用户ID
    public static final String ID = "id";
    //用户签到次数
    public static final String LAST_CHECK_TYPE = "last_check_type";
    //用户余额
    public static final String CURRENT_CSS = "current_css";
    //企业介绍
    public static final String QIYE_INFO = "qiye_info";
    //是否认证 0未认证  1认证
    public static final String AUTH_FLAG = "auth_flag";
    //用户身份的sp
    public static final String USER_TYPE = "user_type";
    //用户身份
    public static final String TYPE = "type";
    //用户身份：服务商
    public static final String SERVICE_PROVIDERS = "service_providers";
    //用户身份：雇主
    public static final String EMPLOYERS = "employers";

    /**
     * 获取登录sp
     *
     * @return
     */
    private static SharedPreferences.Editor getSp(String spName) {
        SharedPreferences login = BFApplication.context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = login.edit();
        return edit;
    }

    /**
     * 记录登录状态
     *
     * @return
     */
    public static boolean login() {
        boolean commit = getSp(IS_LOGIN).putBoolean(IS_LOGIN, true).commit();
        return commit;
    }

    /**
     * 退出登录状态
     *
     * @return
     */
    public static boolean loginOut() {
        getSp(USER).clear().commit();
        getSp(USER_TYPE).clear().commit();
        getSp(SIGN).clear().commit();
        boolean commit = getSp(IS_LOGIN).putBoolean(IS_LOGIN, false).commit();
        return commit;
    }

    /**
     * 获取登录状态
     *
     * @return
     */
    public static boolean getLoginStatus() {
        SharedPreferences login = BFApplication.context.getSharedPreferences(IS_LOGIN, Context.MODE_PRIVATE);
        return login.getBoolean(IS_LOGIN, false);
    }


    /**
     * 保存用户信息
     *
     * @param user
     */
    public static void putUserInformation(LoginBean.DataBean.UserBean user) {
        SharedPreferences.Editor userSp = getSp(USER);
        userSp.putString(HEAD_IMG, user.getHead_img());
        userSp.putString(PHONE, user.getPhone());
        userSp.putString(NICK_NAME, user.getNick_name());
        userSp.putString(ID, user.getId());
        userSp.putString(LAST_CHECK_TYPE, user.getLast_check_type());
        userSp.putString(CURRENT_CSS, user.getCurrent_css() + "");
        String type = user.getType();
        userSp.putString(TYPE, type);
        userSp.putString(QIYE_INFO, user.getQiye_info());
        //是否认证
        Integer auth_flag = user.getAuth_flag();
        if (auth_flag == null || auth_flag == 0) {
            userSp.putString(AUTH_FLAG, 0 + "");
        } else {
            userSp.putString(AUTH_FLAG, 1 + "");
        }
        userSp.commit();

        if (type == null || "".equals(type)) {
            return;
        }
        if (Integer.parseInt(type) == 1) {
            putUserType(SpHelp.SERVICE_PROVIDERS);
        } else if (Integer.parseInt(type) == 2) {
            putUserType(SpHelp.EMPLOYERS);
        }
    }

    /**
     * 获取用户信息
     *
     * @param userKey
     * @return
     */
    public static String getUserInformation(String userKey) {
        SharedPreferences userSp = BFApplication.context.getSharedPreferences(USER, Context.MODE_PRIVATE);
        return userSp.getString(userKey, "");
    }

    /**
     * 获取用户信息
     *
     * @param userKey
     * @return
     */
    public static void putUserInformation(String userKey, String userValue) {
        SharedPreferences.Editor userSp = getSp(USER);
        userSp.putString(userKey, userValue).commit();
    }


    /**
     * 存储用户身份
     *
     * @param userType
     */
    public static void putUserType(String userType) {
        SharedPreferences.Editor userTypeSp = getSp(USER_TYPE);
        userTypeSp.putString(USER_TYPE, userType);
        userTypeSp.commit();
    }

    /**
     * 获取用户身份
     */
    public static String getUserType() {
        SharedPreferences userTypeSp = BFApplication.context.getSharedPreferences(USER_TYPE, Context.MODE_PRIVATE);
        return userTypeSp.getString(USER_TYPE, "");
    }

    /**
     * 存储sign
     */
    public static void putSign(String sign) {
        Log.i("sign", "存储的sign = " + sign);
        getSp(SIGN).putString(SIGN, sign).commit();
    }

    private static int i = 0;

    /**
     * 获取Sing
     *
     * @return
     */
    public static String getSign() {
        SharedPreferences signSp = BFApplication.context.getSharedPreferences(SIGN, Context.MODE_PRIVATE);
        String sign = signSp.getString(SIGN, "");
        Log.i("sign", "第" + i + "次获取的sign = " + sign);
        i++;
        return sign;
    }
}
