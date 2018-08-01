package com.coinwind.bifeng.ui.task.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.task.bean.TaskIdsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 任务详情页面的业务方法
 */
public interface AnswerTaskService {

    @GET(Urls.TASK_ID)
    Observable<TaskIdsBean> loadTaskIds(@Query("taskId") String taskId);
}
