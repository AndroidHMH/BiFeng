package com.coinwind.bifeng.ui.my.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.my.bean.ChangeMessageBean;
import com.coinwind.bifeng.ui.my.biz.MyService;
import com.coinwind.bifeng.ui.my.contract.PerfectInformationContract;
import com.coinwind.bifeng.ui.submittask.bean.SubmitImgBean;
import com.coinwind.bifeng.ui.submittask.biz.SubmitService;

import java.io.File;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Query;

/**
 * 用户信息的P层
 */
public class PerfectInformationPresenter implements PerfectInformationContract.Presenter {

    private SubmitService service;
    private PerfectInformationContract.View view;
    private MyService myService;

    public PerfectInformationPresenter() {
        service = RetrofitHelp.getInstance().getService(SubmitService.class);
        myService = RetrofitHelp.getInstance().getService(MyService.class);
    }

    @Override
    public void updateHeadImg(File imgFile) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imgFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", imgFile.getName(), requestBody);
        service.upload(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubmitImgBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SubmitImgBean submitImgBean) {
                        LogHelp.e("imgurl", submitImgBean.toString());
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
    public void changeHeadImg(String userId, String imgUrl) {
        myService.changeType(userId, SpHelp.HEAD_IMG, imgUrl, SpHelp.getSign())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangeMessageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChangeMessageBean changeMessageBean) {
                        int code = changeMessageBean.getCode();
                        ChangeMessageBean.DataBean data = changeMessageBean.getData();
                        if (code == Codes.SUCCESS_CODE && data != null) {
                            if (data.isState()) {
                                view.changeSuccess("修改头像成功");
                            } else if (code == Codes.FAILURE_CODE) {
                                view.loginOut();
                            } else{
                                view.changeError("修改头像失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.changeError("修改头像失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void actualView(PerfectInformationContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }


}
