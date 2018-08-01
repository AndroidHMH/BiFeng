package com.coinwind.bifeng.ui.task.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.home.bean.ListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TaskService {

    @GET(Urls.TASK_CONTENT)
    Observable<ListBean> loadContentList(@Query("page")int page,@Query("size")int size,
                                         @Query("type")String type,@Query("taskType")String taskTyp,
                                         @Query("orderField")String orderFiel,@Query("orderSort")String orderSort);

}
