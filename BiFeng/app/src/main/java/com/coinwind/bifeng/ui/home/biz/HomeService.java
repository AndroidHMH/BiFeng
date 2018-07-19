package com.coinwind.bifeng.ui.home.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.home.bean.ListBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 首页的业务方法
 */
public interface HomeService {
    @GET(Urls.HOME_BANNER)
    Observable<List<HomeBannerBean>> loadBanner();

    @GET(Urls.HOME_LIST)
    Observable<List<ListBean>> loadList(@Query("page") int page, @Query("size") int size);
}
