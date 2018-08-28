package com.coinwind.bifeng.app;

import android.app.Application;
import android.content.Context;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.config.CrashHandler;
import com.coinwind.bifeng.config.ShareHelp;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * 程序入口
 */
public class BFApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ShareHelp.initUM(this);
        // 异常处理，不需要处理时注释掉这两句即可！
//        CrashHandler crashHandler = CrashHandler.getInstance();
        // 注册crashHandler
//        crashHandler.init(getApplicationContext());
    }
}
