package com.coinwind.bifeng.ui.login.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.login.bean.GetCodeBean;
import com.coinwind.bifeng.ui.login.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 登录页面的业务方法
 */
public interface LoginService {
    @GET(Urls.LOGIN_GET_CODE)
    Observable<GetCodeBean> getCode(@Query("phone") String phone);

    @GET(Urls.PSW_LOGIN)
    Observable<LoginBean> pswLogin(@Query("username") String username, @Query("password") String password);

    @GET(Urls.PHONE_LOGIN)
    Observable<LoginBean> phoneLogin(@Query("phone") String phone, @Query("shortMsgCode") String shortMsgCode, @Query("fromType") String fromType);

}
