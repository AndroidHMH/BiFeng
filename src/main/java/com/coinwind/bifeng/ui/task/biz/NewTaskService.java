package com.coinwind.bifeng.ui.task.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.home.bean.ListBean;
import com.coinwind.bifeng.ui.task.bean.NewTaskBean;
import com.coinwind.bifeng.ui.task.bean.RenZhengBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * 创世任务的Service
 */
public interface NewTaskService {

    @GET(Urls.NEW_TASK)
    Observable<NewTaskBean> loadContentList(@Query("mTab") String mTab, @Query("userId") String userId, @Query("sign") String sign);
}
