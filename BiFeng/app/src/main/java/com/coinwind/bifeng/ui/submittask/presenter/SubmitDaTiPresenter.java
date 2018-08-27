package com.coinwind.bifeng.ui.submittask.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.sendtask.bean.DiaoYanBean;
import com.coinwind.bifeng.ui.submittask.bean.SendDaTiBean;
import com.coinwind.bifeng.ui.submittask.bean.SubmitDiaoYanBean;
import com.coinwind.bifeng.ui.submittask.biz.AnswerTheQuestionService;
import com.coinwind.bifeng.ui.submittask.contract.AnswerTheQuestionContract;
import com.coinwind.bifeng.ui.submittask.contract.SubmitDaTiContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class SubmitDaTiPresenter implements SubmitDaTiContract.Presenter {
    private SubmitDaTiContract.View view;
    private final AnswerTheQuestionService service;

    public SubmitDaTiPresenter() {
        service = RetrofitHelp.getInstance().getService(AnswerTheQuestionService.class);
    }

    @Override
    public void sendTask(String taskId, List<SendDaTiBean> diaoYanBeans, String type, String passNum) {
        JSONObject jb = new JSONObject();
        try {
            int rightNum = 0;
            JSONObject quesContent = new JSONObject();
            quesContent.put("totalNum", diaoYanBeans.size() + "");
            JSONArray ja = new JSONArray();
            for (SendDaTiBean daTiBean : diaoYanBeans) {
                if ("1".equals(daTiBean.getIs_right())) {
                    rightNum++;
                }

                JSONObject jo = new JSONObject();
                jo.put("num", daTiBean.getNum());
                jo.put("sub_answer", daTiBean.getSub_answer());
                jo.put("timu_content", daTiBean.getTimu_content());
                jo.put("right_answer", daTiBean.getRight_answer());
                jo.put("is_right", daTiBean.getIs_right());

                ja.put(jo);
            }
            quesContent.put("content", ja);
            String isPass = "0";
            if (!(rightNum < Integer.parseInt(passNum))) {
                isPass = "1";
            }

            jb.put("userId", SpHelp.getUserInformation(SpHelp.ID));
            jb.put("taskId", taskId);
            jb.put("type", type);
            jb.put("isPass", isPass + "");


            jb.put("answer_content", quesContent);
            String s = jb.toString();

            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), s);

            final String finalIsPass = isPass;
            final int finalRightNum = rightNum;
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
                            if (code == Codes.SUCCESS_CODE) {
                                //请求成功
                                if (data.isState()) {
//                                    if (finalIsPass.equals("0")) {
//                                        view.notThrough(finalRightNum + "");
//                                    } else {
//                                        view.through(finalRightNum + "");
//                                    }
                                    view.through(finalRightNum + "");
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
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogHelp.e("sendTaskPresenter", e.getMessage());
                            view.sendFailure("做任务失败");
                        }

                        @Override
                        public void onComplete() {
                            byte[] bytes = new byte[1024];
                            int len = 0;
                            try {
                                FileInputStream is = new FileInputStream(new File(""));
                                while ((len = is.read(bytes)) != -1) {

                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void actualView(SubmitDaTiContract.View view) {
        this.view = view;
    }


    @Override
    public void unActualView() {
        this.view = null;
    }

}
