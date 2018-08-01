package com.coinwind.bifeng.ui.home.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.home.bean.GuangBoBean;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.home.bean.HomeQiangBean;
import com.coinwind.bifeng.ui.home.bean.ListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 首页的业务方法
 */
public interface HomeService {

    @GET(Urls.HOME_BANNER)
    Observable<HomeBannerBean> loadBanner();

    @GET(Urls.HOME_GUANG_BO)
    Observable<GuangBoBean> loadGuangBo();

    @GET(Urls.HOME_QINAG)
    Observable<HomeQiangBean> loadQiang();

    @GET(Urls.HOME_LIST)
    Observable<ListBean> loadList(@Query("page") int page, @Query("size") int size);
}
