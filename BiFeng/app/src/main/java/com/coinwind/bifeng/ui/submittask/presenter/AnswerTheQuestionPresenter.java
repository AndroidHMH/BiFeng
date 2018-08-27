package com.coinwind.bifeng.ui.submittask.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.sendtask.bean.DiaoYanBean;
import com.coinwind.bifeng.ui.sendtask.bean.SendTaskBean;
import com.coinwind.bifeng.ui.sendtask.biz.SendTaskService;
import com.coinwind.bifeng.ui.submittask.bean.SubmitDiaoYanBean;
import com.coinwind.bifeng.ui.submittask.biz.AnswerTheQuestionService;
import com.coinwind.bifeng.ui.submittask.contract.AnswerTheQuestionContract;
import com.coinwind.bifeng.ui.task.bean.SendDaTiBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class AnswerTheQuestionPresenter implements AnswerTheQuestionContract.Presenter {
    private AnswerTheQuestionContract.View view;
    private final AnswerTheQuestionService service;

    public AnswerTheQuestionPresenter() {
        service = RetrofitHelp.getInstance().getService(AnswerTheQuestionService.class);
    }

    @Override
    public void sendTask(String taskId, List<DiaoYanBean> diaoYanBeans, String type) {
        JSONObject jb = new JSONObject();
        try {
            jb.put("userId", SpHelp.getUserInformation(SpHelp.ID));
            jb.put("taskId", taskId);
            jb.put("type", type);

            JSONObject quesContent = new JSONObject();
            quesContent.put("totalNum", diaoYanBeans.size()+"");
            JSONArray ja = new JSONArray();
            for (DiaoYanBean daTiBean : diaoYanBeans) {
                JSONObject jo = new JSONObject();
                jo.put("num", daTiBean.getNum());
                jo.put("sub_answer", daTiBean.getTitle());
                jo.put("timu_content", daTiBean.getTimu_content());

                ja.put(jo);
            }
            quesContent.put("content", ja);

            jb.put("answer_content", quesContent);
            String s = jb.toString();

            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), s);

            service.sendTask(body, SpHelp.getSign())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SubmitDiaoYanBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(SubmitDiaoYanBean sendTaskBean) {
                            LogHelp.e("sendTaskPresenter", sendTaskBean.toString());
                            int code = sendTaskBean.getCode();
                            SubmitDiaoYanBean.DataBean data = sendTaskBean.getData();
                            if (data != null) {
                                if (code == Codes.SUCCESS_CODE) {
                                    //请求成功
                                    if (data.isState()) {
                                        view.sendSuccess("成功");
                                    } else {
                                        view.sendFailure(data.getEmsg());
                                    }
                                } else if (code == Codes.FAILURE_CODE) {
                                    //身份失效
                                    view.loginOut();
                                } else {
                                    //请求失败
                                    view.sendFailure(data.getEmsg());
                                }
                            } else {
                                //请求失败
                                view.sendFailure("做任务失败");
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            LogHelp.e("sendTaskPresenter", e.getMessage());
                            view.sendFailure("做任务失败");
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
    public void actualView(AnswerTheQuestionContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
