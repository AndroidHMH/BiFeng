package com.coinwind.bifeng.ui.setting.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.model.http.SwitchThread;
import com.coinwind.bifeng.ui.login.bean.GetCodeBean;
import com.coinwind.bifeng.ui.login.biz.LoginService;
import com.coinwind.bifeng.ui.setting.bean.ChangePswBean;
import com.coinwind.bifeng.ui.setting.bean.SubmitHeadImgBean;
import com.coinwind.bifeng.ui.setting.biz.ChangePswService;
import com.coinwind.bifeng.ui.setting.contract.ChangeHeadImgContract;
import com.coinwind.bifeng.ui.setting.contract.ChangePswContract;
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

/**
 * 更换头像的P层
 */
public class ChangeHeadImgPresenter implements ChangeHeadImgContract.Presenter {
    private ChangeHeadImgContract.View view;
    private SubmitService service;

    public ChangeHeadImgPresenter() {
        service = RetrofitHelp.getInstance().getService(SubmitService.class);
    }

    @Override
    public void updateImage(File imgFile) {
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
    public void doHeadImgTask(String taskId, final String para) {
        service.submitHeadImg(taskId, SpHelp.getUserInformation(SpHelp.ID), SpHelp.getAndroidId(), para, SpHelp.getSign())
                .compose(SwitchThread.<SubmitHeadImgBean>switchThread())
                .subscribe(new Observer<SubmitHeadImgBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SubmitHeadImgBean submitHeadImgBean) {
                        int code = submitHeadImgBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            SubmitHeadImgBean.DataBeanX data = submitHeadImgBean.getData();
                            if (data.isState()) {
                                view.doHeadImgTaskSuccess(para);
                            } else {
                                int resultCode = data.getCode();
                                if (resultCode == Codes.FAILURE_CODE) {
                                    view.loginTimeOut();
                                } else {
                                    view.errorUpdate("上传失败");
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.errorUpdate("网络错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void actualView(ChangeHeadImgContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }

}
