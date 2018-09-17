package com.coinwind.bifeng.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.config.AndroidID;
import com.coinwind.bifeng.config.CrashHandler;
import com.coinwind.bifeng.config.ShareHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.util.concurrent.Executors;

import static com.coinwind.bifeng.config.SpHelp.ANDROID_ID;

/**
 * 程序入口
 */
public class BFApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ShareHelp.initUM(this);

        getAndroidId();


        // 异常处理，不需要处理时注释掉这两句即可！
//        CrashHandler crashHandler = CrashHandler.getInstance();
        // 注册crashHandler
//        crashHandler.init(getApplicationContext());
    }

    /**
     * 获取设备唯一标示
     */
    private void getAndroidId() {
        String deviceInfo = AndroidID.getUniquePsuedoID();
        getSharedPreferences(ANDROID_ID, Context.MODE_PRIVATE).edit().putString(ANDROID_ID, deviceInfo).commit();
    }


}
