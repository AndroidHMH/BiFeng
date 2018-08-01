package com.coinwind.bifeng.model.http;

import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.config.Urls;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装单利Retrofit
 */
public class RetrofitHelp {
    private static RetrofitHelp retrofitUtils;
    private Retrofit retrofit;

    private RetrofitHelp() {
//        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
//        try {
//            setCertificates(clientBuilder,BFApplication.context.getAssets().open("bifengssl.pfx"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Urls.BASE_URL)
//                .client(clientBuilder.build())
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

    public <T> T getService(Class<T> tClass) {
        return retrofit.create(tClass);
    }

}
