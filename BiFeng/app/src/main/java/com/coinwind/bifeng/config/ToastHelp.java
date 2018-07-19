package com.coinwind.bifeng.config;

import android.content.Context;
import android.widget.Toast;

/**
 * 封装Toast
 */
public class ToastHelp {
    public static boolean isShow = true;

    public static void showShort(Context context, String content) {
        if (isShow) {
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
        }
    }

    public static void showShort(Context context, int content) {
        if (isShow) {
            Toast.makeText(context, content + "", Toast.LENGTH_SHORT).show();
        }
    }

    public static void showLong(Context context, String content) {
        if (isShow) {
            Toast.makeText(context, content, Toast.LENGTH_LONG).show();
        }
    }

    public static void showLong(Context context, int content) {
        if (isShow) {
            Toast.makeText(context, content + "", Toast.LENGTH_SHORT).show();
        }
    }
}
