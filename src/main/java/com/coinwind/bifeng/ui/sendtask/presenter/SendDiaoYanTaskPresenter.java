package com.coinwind.bifeng.ui.sendtask.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.sendtask.bean.DiaoYanBean;
import com.coinwind.bifeng.ui.sendtask.bean.SendTaskBean;
import com.coinwind.bifeng.ui.sendtask.biz.SendTaskService;
import com.coinwind.bifeng.ui.sendtask.contract.SendDiaoYanContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * 发送调研任务的P层
 */
public class SendDiaoYanTaskPresenter implements SendDiaoYanContract.Presenter {
    private SendDiaoYanContract.View view;
    private final SendTaskService service;

    public SendDiaoYanTaskPresenter() {
        service = RetrofitHelp.getInstance().getService(SendTaskService.class);
    }

    @Override
    public void sendTask(String share_score, String label, String type, String all_sharenum, String score, String all_tasknum,
                         String title, String img, String endTime, String needCheck, String task_intro, String publicNum,
                         String publicImg, String url, String content, String taskType, List<DiaoYanBean> diaoYanBeans, String startTime) {
        JSONObject jb = new JSONObject();
        try {
            jb.put("userId", SpHelp.getUserInformation(SpHelp.ID));
            jb.put("share_score", share_score);
            jb.put("label", label);
            jb.put("type", type);
            jb.put("all_sharenum", all_sharenum);
            jb.put("score", score);
            jb.put("all_tasknum", all_tasknum);
            jb.put("title", title);
            jb.put("img", img);
            jb.put("endTime", endTime);
            jb.put("needCheck", needCheck);
            jb.put("task_intro", task_intro);
            jb.put("publicNum", publicNum);
            jb.put("publicImg", publicImg);
            jb.put("url", url);
            jb.put("content", content);
            jb.put("taskType", taskType);
            jb.put("contentImgList", "");

            JSONObject quesContent = new JSONObject();
            quesContent.put("totalNum", diaoYanBeans.size()+"");
            JSONArray ja = new JSONArray();
            for (DiaoYanBean diaoYanBean : diaoYanBeans) {
                JSONObject jo = new JSONObject();
                jo.put("title", diaoYanBean.getTitle());
                jo.put("num", diaoYanBean.getNum());
                ja.put(jo);
            }
            quesContent.put("question", ja);

            jb.put("ques_content", quesContent);


            jb.put("startTime", startTime);
            String s = jb.toString();
            LogHelp.e("sendTaskPresenter", s);

            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), s);

            service.sendTask(body, SpHelp.getSign())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SendTaskBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(SendTaskBean sendTaskBean) {
                            LogHelp.e("sendTaskPresenter", sendTaskBean.toString());
                            int code = sendTaskBean.getCode();
                            SendTaskBean.DataBean data = sendTaskBean.getData();
                            if (code == Codes.SUCCESS_CODE) {
                                //请求成功
                                if (data.isState()) {
                                    view.sendSuccess(data.getMsg());
                                } else {
                                    view.sendFailure(data.getMsg());
                                }
                            } else if (code == Codes.FAILURE_CODE) {
                                //身份失效
                                view.loginOut();
                            } else {
                                //请求失败
                                view.sendFailure(data.getMsg());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogHelp.e("sendTaskPresenter", e.getMessage());
                            view.sendFailure("发布发任务失败");
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualView(SendDiaoYanContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
