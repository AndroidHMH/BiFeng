package com.coinwind.bifeng.ui.bindphonenumber.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.bindphonenumber.entity.SendBindPhoneNumberEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 绑定手机号的Service
 */
public interface SendBindPhoneNumberService {

    @GET(Urls.BIND_PHONE)
    Observable<SendBindPhoneNumberEntity> bindPhone(@Query("phone") String phone, @Query("password") String password, @Query("para") String para, @Query("mTab") String mTab
            , @Query("taskId") String taskId, @Query("userId") String userId, @Query("sign") String sign, @Query("inviteCode") String inviteCode);
}
