package com.coinwind.bifeng.ui.task.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.login.bean.GetCodeBean;
import com.coinwind.bifeng.ui.task.bean.ShareImgBean;
import com.coinwind.bifeng.ui.task.bean.TaskIdsBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 任务详情页面的业务方法
 */
public interface AnswerTaskService {

    @GET(Urls.TASK_ID)
    Observable<TaskIdsBean> loadTaskIds(@Query("taskId") String taskId);

    @GET(Urls.SHARE_IMG)
    Observable<ShareImgBean> loadShareImg(@Query("taskId") String taskId, @Query("userId") String userId, @Query("content") String content, @Header("sign") String sign);

    @Streaming
    @GET
    Observable<ResponseBody> loadImg(@Url String url);

    @GET(Urls.GET_CODE)
    Observable<GetCodeBean> loadMa(@Query("userId") String userId, @Query("sign") String sign);

}
