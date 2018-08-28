package com.coinwind.bifeng.ui.task.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.home.bean.ListBean;
import com.coinwind.bifeng.ui.task.bean.RenZhengBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * 任务大厅的Service
 */
public interface TaskService {

    @GET(Urls.TASK_CONTENT)
    Observable<ListBean> loadContentList(@Query("page") int page, @Query("size") int size,
                                         @Query("type") String type, @Query("taskType") String taskTyp,
                                         @Query("orderField") String orderFiel, @Query("orderSort") String orderSort);

    @GET(Urls.REN_ZHENG)
    Observable<RenZhengBean> loadRenZheng(@Query("logoUrl") String logoUrl, @Query("namecardUrl") String namecardUrl,
                                          @Query("qiyeInfo") String qiyeInfo, @Query("gzName") String gzName, @Query("gzType") String gzType,
                                          @Query("userId") String userId, @Header("sign") String sign);
}
