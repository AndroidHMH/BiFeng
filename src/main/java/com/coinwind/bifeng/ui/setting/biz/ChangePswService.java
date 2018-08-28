package com.coinwind.bifeng.ui.setting.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.setting.bean.ChangePswBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 修改密码的Service
 */
public interface ChangePswService {
    @GET(Urls.CHANGE_PSW)
    Observable<ChangePswBean> changePsw(@Query("phone") String phone, @Query("newpwd") String newpwd, @Query("shortMsgCode") String shortMsgCode);
}
