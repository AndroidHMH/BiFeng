package com.coinwind.bifeng.ui.my.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.my.bean.ChangeMessageBean;
import com.coinwind.bifeng.ui.my.bean.NewMyBean;
import com.coinwind.bifeng.ui.my.bean.WalletBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * 我的页面的业务方法
 */
public interface NewMyService {

    @GET(Urls.MY_INFO)
    Observable<NewMyBean> loadMyInfo(@Query("mTab") String mTab, @Query("userId") String userId, @Query("sign") String sign);

}
