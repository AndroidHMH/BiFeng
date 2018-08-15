package com.coinwind.bifeng.ui.submittask.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.submittask.bean.SubmitBean;
import com.coinwind.bifeng.ui.submittask.bean.SubmitImgBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
/**
 *提交任务的Service
 */
public interface SubmitService {
    @Multipart
    @POST(Urls.UPDATE_IMGS)
    Observable<SubmitImgBean> upload(@Part MultipartBody.Part imgs);

    @GET(Urls.SUBMIT_TASK)
    Observable<SubmitBean> submitTask(@Query("taskId") String taskId, @Query("userId") String userId, @Query("imgs") String imgs, @Query("explain") String explain, @Header("sign")String sign);
}
