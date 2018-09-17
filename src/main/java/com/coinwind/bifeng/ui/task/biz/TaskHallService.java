package com.coinwind.bifeng.ui.task.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.task.bean.TaskHallBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 任务列表
 */
public interface TaskHallService {
    @GET(Urls.HOME_BANNER)
    Observable<HomeBannerBean> loadBanner();

    @GET(Urls.TASK_PROGRESS)
    Observable<TaskHallBean> loadProgress(@Query("mTab") String mTab, @Query("userId") String userId, @Query("sign") String sign);


}
