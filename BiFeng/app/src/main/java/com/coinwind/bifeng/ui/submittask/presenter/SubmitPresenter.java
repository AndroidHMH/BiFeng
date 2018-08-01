package com.coinwind.bifeng.ui.submittask.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.submittask.bean.SubmitBean;
import com.coinwind.bifeng.ui.submittask.bean.SubmitImgBean;
import com.coinwind.bifeng.ui.submittask.biz.SubmitService;
import com.coinwind.bifeng.ui.submittask.contract.SubmitContract;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class SubmitPresenter implements SubmitContract.Presenter {
    private SubmitContract.View view;
    private final SubmitService service;

    public SubmitPresenter() {
        service = RetrofitHelp.getInstance().getService(SubmitService.class);
    }

    @Override
    public void updateImgs(File imgFile) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imgFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", imgFile.getName(), requestBody);
        service.upload(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubmitImgBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SubmitImgBean submitImgBean) {
                        String success = submitImgBean.getSuccess();
                        if ("1".equals(success)) {
                            view.successUpdate(submitImgBean.getUrl());
                        } else {
                            view.errorUpdate("上传图片失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.errorUpdate("上传图片失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void submitTask(String taskId, String userId, List<String> imgs, String explain) {
        String imgUrls = listToString(imgs);
        service.submitTask(taskId, userId, imgUrls, explain).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubmitBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SubmitBean submitBean) {
                        int code = submitBean.getCode();
                        if (Codes.SUCCESS_CODE == code) {
                            SubmitBean.DataBean data = submitBean.getData();
                            if (data.isState()) {
                                view.showSubmitSuccess();
                            } else {
                                view.showSubmitFailure(data.getMsg());
                            }
                        } else {
                            view.showSubmitFailure("提交任务失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showSubmitFailure("提交任务失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private String listToString(List<String> imgs) {
        if (imgs.size() == 1) {
            return imgs.get(0);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < imgs.size(); i++) {
            if (i == 0 || i == imgs.size() - 1) {
                sb.append(imgs.get(i));
            } else {
                sb.append("," + imgs.get(i) + ",");
            }
        }
        return sb.toString();
    }

    @Override
    public void actualView(SubmitContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
