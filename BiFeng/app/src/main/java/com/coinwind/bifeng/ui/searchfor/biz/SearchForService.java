package com.coinwind.bifeng.ui.searchfor.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.home.bean.ListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 搜索界面的Service
 */
public interface SearchForService {
    @GET(Urls.SEARCH_FOR)
    Observable<ListBean> loadSearchFor(@Query("keywords") String keywords, @Query("page") String page, @Query("size") String size);
}
