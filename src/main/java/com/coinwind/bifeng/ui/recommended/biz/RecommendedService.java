package com.coinwind.bifeng.ui.recommended.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.home.bean.ListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 推荐列表里的Service
 */
public interface RecommendedService {

    @GET(Urls.TASK_CONTENT)
    Observable<ListBean> loadRecommendedList();
}
