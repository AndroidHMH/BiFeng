package com.coinwind.bifeng.ui.my.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.my.bean.ChangeMessageBean;
import com.coinwind.bifeng.ui.my.bean.WalletBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 我的页面的业务方法
 */
public interface MyService {

    @GET(Urls.CHANGE_MSG)
    Observable<ChangeMessageBean> changeType(@Query("userId") String userId, @Query("field") String field, @Query("value") String value);

    @GET(Urls.SELECT_CC)
    Observable<WalletBean> loadCC(@Query("userId") String userId, @Query("page") String page, @Query("size") String size);


}
