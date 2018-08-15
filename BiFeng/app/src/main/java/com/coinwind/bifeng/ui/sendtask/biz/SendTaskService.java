package com.coinwind.bifeng.ui.sendtask.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.sendtask.bean.SendTaskBean;

import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * 发布任务的业务方法
 * <p>
 * share_score:分享奖励
 * label:标签
 * type:任务类型 1 涨粉任务 2评论任务 3 注册任务 4 转发任务，5 调研，6 答题， 7 拍照
 * all_sharenum:总分享次数
 * score:做一次任务所得积分
 * all_tasknum:任务分享总次数
 * title:任务标题
 * img:任务大图
 * endTime:结束时间  格式：nn-yy-rr hh:ff:ss
 * needCheck:是否需要验证
 * task_intro:任务简介
 * publicNum:公众号（涨粉任务所需）
 * publicImg:公众号二维码（涨粉任务所需）
 * url:注册/评论任务的地址链接
 * content:转发正文（转发任务所需）
 * taskType:任务类别，1项目动态,2新币上线,3技术周报,4最新公告,5热门活动,6品牌推广（转发任务需要）
 * contentImgList:转发任务中的图片链接，用“,”分割（转发任务）
 * ques_content:调研/答题任务的题目内容
 * startTime:开始时间
 */
public interface SendTaskService {
    //    @FormUrlEncoded
//    @POST(Urls.SEND_TASK)
//    Observable<SendTaskBean> sendTask(@Field("userId") String userId, @Field("share_score") String share_score,
//                                      @Field("label") String label, @Field("type") String type,
//                                      @Field("all_sharenum") String all_sharenum, @Field("score") String score,
//                                      @Field("all_tasknum") String all_tasknum, @Field("title") String title,
//                                      @Field("img") String img, @Field("endTime") String endTime,
//                                      @Field("needCheck") String needCheck, @Field("task_intro") String task_intro,
//                                      @Field("publicNum") String publicNum, @Field("publicImg") String publicImg,
//                                      @Field("url") String url, @Field("content") String content,
//                                      @Field("taskType") String taskType, @Field("contentImgList") String contentImgList,
//                                      @Field("ques_content") String ques_content, @Field("startTime") String startTime,
//                                      @Header("sign") String sign, @Header("Content-Type") String content_type);
    @POST(Urls.SEND_TASK)
    Observable<SendTaskBean> sendTask(@Body RequestBody key, @Header("sign") String sign);
}
