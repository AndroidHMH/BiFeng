package com.coinwind.bifeng.ui.submittask.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.sendtask.bean.SendTaskBean;
import com.coinwind.bifeng.ui.submittask.bean.SubmitDiaoYanBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * 提交调研任务的业务方法
 */
public interface AnswerTheQuestionService {
    @POST(Urls.SUBMIT_DA_TI)
    Observable<SubmitDiaoYanBean> sendTask(@Body RequestBody key, @Header("sign") String sign);

}
