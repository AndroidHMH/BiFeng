package com.coinwind.bifeng.ui.sendtask.presenter;

import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.sendtask.contract.SendZhuanFaTaskContract;
import com.coinwind.bifeng.ui.submittask.bean.SubmitImgBean;
import com.coinwind.bifeng.ui.submittask.biz.SubmitService;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SendZhuanFaTaskPresenter implements SendZhuanFaTaskContract.Presenter {
    private SendZhuanFaTaskContract.View view;
    private SubmitService service;

    public SendZhuanFaTaskPresenter() {
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
    public void actualView(SendZhuanFaTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
