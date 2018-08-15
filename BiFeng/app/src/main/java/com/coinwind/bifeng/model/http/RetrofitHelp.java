package com.coinwind.bifeng.model.http;

import android.util.Log;

import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.config.NetWorkHelp;
import com.coinwind.bifeng.config.Urls;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装单利Retrofit
 */
public class RetrofitHelp {
    private static RetrofitHelp retrofitUtils;
    private Retrofit retrofit;

    private RetrofitHelp() {

//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.addNetworkInterceptor(interceptor);
//        Cache cache = new Cache(BFApplication.context.getCacheDir(), 1024 * 1024 * 10);
//        builder.cache(cache);
        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(20000, TimeUnit.SECONDS).
                readTimeout(20000, TimeUnit.SECONDS).
                writeTimeout(20000, TimeUnit.SECONDS).build();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .build();
    }

    public static RetrofitHelp getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitHelp.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitHelp();
                }
            }
        }
        return retrofitUtils;
    }

    private Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkHelp.isNetWorkEnable(BFApplication.context)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response = chain.proceed(request);
            if (NetWorkHelp.isNetWorkEnable(BFApplication.context)) {
                int maxAge = 60 * 60 * 24; // 有网络时 设置缓存超时时间为0;

                response = response.newBuilder()
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            }
            return response;
        }
    };

    public <T> T getService(Class<T> tClass) {
        return retrofit.create(tClass);
    }

}
