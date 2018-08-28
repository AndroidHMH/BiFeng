package com.coinwind.bifeng.ui.my.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.home.bean.ListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
/**
 *我执行（发布）的任务的service
 */
public interface MyTaskService {
    @GET(Urls.MY_TASK_LIST)
    Observable<ListBean> loadTask(@Query("userId") String userId, @Query("flag") String flag,
                                  @Query("page") int page, @Query("size") int size,
                                  @Query("reqType") String reqType, @Header("sign")String sign);
}
