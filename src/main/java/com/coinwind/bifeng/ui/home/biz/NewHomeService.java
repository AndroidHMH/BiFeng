package com.coinwind.bifeng.ui.home.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.home.bean.BroadcastBean;
import com.coinwind.bifeng.ui.home.bean.HomeInfoBean;
import com.coinwind.bifeng.ui.home.bean.HomeItemCCBean;
import com.coinwind.bifeng.ui.home.bean.HomeUserInfoBean;
import com.coinwind.bifeng.ui.home.bean.ReceiveCC;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * 首页的业务方法
 */
public interface NewHomeService {

    @GET(Urls.HOME_GUANG_BO)
    Observable<BroadcastBean> loadBroadcast();

    @GET(Urls.HOME_INFO)
    Observable<HomeInfoBean> loadHomeInfo();


    @GET(Urls.HOME_USER_INFO)
    Observable<HomeUserInfoBean> loadHomeUserInfo(@Query("mTab") String mTab, @Query("userId") String userId, @Query("sign") String sign);

    @GET(Urls.HOME_CC_LIST)
    Observable<HomeItemCCBean> loadCCList(@Query("mTab") String mTab, @Query("userId") String userId, @Query("sign") String sign);

    @GET(Urls.RECEIVE_CC)
    Observable<ReceiveCC> receiveCC(@Query("mTab") String mTab, @Query("userId") String userId, @Query("sign") String sign,
                                    @Query("cc") String cc, @Query("buildTime") String buildTime);


}
