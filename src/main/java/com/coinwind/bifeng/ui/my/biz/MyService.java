package com.coinwind.bifeng.ui.my.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.my.bean.ChangeMessageBean;
import com.coinwind.bifeng.ui.my.bean.NewWalletBean;
import com.coinwind.bifeng.ui.my.bean.WalletBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * 我的页面的业务方法
 */
public interface MyService {

    @GET(Urls.CHANGE_MSG)
    Observable<ChangeMessageBean> changeType(@Query("userId") String userId, @Query("field") String field, @Query("value") String value, @Header("sign") String sign);

    @GET(Urls.SELECT_CC)
    Observable<WalletBean> loadCC(@Query("userId") String userId, @Query("page") String page, @Query("size") String size, @Header("sign") String sign);
//, @Header("sign")String sign

    @GET(Urls.LOAD_WALLET)
    Observable<NewWalletBean> loadWall(@Query("userId") String userId, @Query("mTab") String mTab, @Query("sign") String sign);

}
