package com.coinwind.bifeng.model.http;

import com.coinwind.bifeng.config.Urls;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装单利Retrofit
 */
public class RetrofitHelp {
    private static RetrofitHelp retrofitUtils;
    private Retrofit retrofit;

    private RetrofitHelp() {
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Urls.BASE_URL)
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
