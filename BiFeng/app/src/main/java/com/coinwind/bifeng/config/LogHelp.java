package com.coinwind.bifeng.config;

import android.util.Log;

/**
 * 封装Log
 */
public class LogHelp {
    public static final boolean IS_LOG = true;

    public static void i(String tag, String msg) {
        if (IS_LOG) {
            Log.i(tag, msg);
        }
    }


    /**
     * 打印verbose日志
     *
     * @param tag 标签
     * @param msg 日志信息
     */
    public static void v(String tag, String msg) {
        if (IS_LOG) {
            Log.v(tag, msg);
        }
    }


    /**
     * 打印debug信息
     *
     * @param tag 标签信息
     * @param msg 日志信息
     */
    public static void d(String tag, String msg) {
        if (IS_LOG) {
            Log.d(tag, msg);
        }
    }


    /**
     * 打印warn日志
     *
     * @param tag 标签信息
     * @param msg 日志信息
     */
    public static void w(String tag, String msg) {
        if (IS_LOG) {
            Log.w(tag, msg);
        }
    }

    /**
     * 打印error日志
     *
     * @param tag 标签信息
     * @param msg 日志信息
     */
    public static void e(String tag, String msg) {
        if (IS_LOG) {
            Log.e(tag, msg);
        }
    }
}
