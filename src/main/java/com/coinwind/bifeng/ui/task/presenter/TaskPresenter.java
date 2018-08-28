package com.coinwind.bifeng.ui.task.presenter;

import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.home.bean.ListBean;
import com.coinwind.bifeng.ui.submittask.bean.SubmitImgBean;
import com.coinwind.bifeng.ui.submittask.biz.SubmitService;
import com.coinwind.bifeng.ui.task.bean.RenZhengBean;
import com.coinwind.bifeng.ui.task.biz.TaskService;
import com.coinwind.bifeng.ui.task.contract.TaskContract;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 任务大厅的P层
 */
public class TaskPresenter implements TaskContract.Presenter {

    private TaskService service;
    private TaskContract.View view;
    private SubmitService submitService;

    public TaskPresenter() {
        service = RetrofitHelp.getInstance().getService(TaskService.class);
        submitService = RetrofitHelp.getInstance().getService(SubmitService.class);
    }

    @Override
    public void loadContentList(int page, String type, String taskTyp, String orderFiel, String orderSort) {
        service.loadContentList(page, 5, type, taskTyp, orderFiel, orderSort)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean listBean) {
                        int code = listBean.getCode();
                        List<TaskBean> data = listBean.getData();
                        if (code == Codes.SUCCESS_CODE && data != null) {
                            view.showContentList(data);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updateImg(File imgFile) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imgFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", imgFile.getName(), requestBody);
        submitService.upload(body)
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
    public void loadRenZheng(String logoUrl, String namecardUrl, String qiyeInfo, String gzName, String gzType) {
        service.loadRenZheng(logoUrl, namecardUrl, qiyeInfo, gzName, gzType, SpHelp.getUserInformation(SpHelp.ID), SpHelp.getSign())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RenZhengBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RenZhengBean renZhengBean) {
                        int code = renZhengBean.getCode();
                        RenZhengBean.DataBean data = renZhengBean.getData();
                        if (code == Codes.SUCCESS_CODE && data != null) {
                            if (data.isState()) {
                                view.renZhengSuccess();
                            } else {
                                view.renZhengError(data.getEMsg());
                            }
                        } else {
                            view.renZhengError("认证失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.renZhengError("认证失败");

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public boolean isLogoUrl(String logoUrl) {
        if (null == logoUrl) {
            view.renZhengError("上传LOGO失败");
            return false;
        }
        if ("".equals(logoUrl)) {
            view.renZhengError("上传LOGO失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean isNameCardUrl(String namecardUrl) {
        if (null == namecardUrl) {
            view.renZhengError("上传名片失败");
            return false;
        }
        if ("".equals(namecardUrl)) {
            view.renZhengError("上传名片失败");
            return false;
        }
        return true;
    }

    @Override
    public boolean isQiyeInfo(String qiyeInfo) {
        if (null == qiyeInfo) {
            view.renZhengError("企业介绍为空");
            return false;
        }
        if ("".equals(qiyeInfo)) {
            view.renZhengError("企业介绍为空");
            return false;
        }
        return true;
    }

    @Override
    public boolean isGzName(String gzName) {
        if (null == gzName) {
            view.renZhengError("请输入雇主名称");
            return false;
        }
        if ("".equals(gzName)) {
            view.renZhengError("请输入雇主名称");
            return false;
        }
        return true;
    }

    @Override
    public boolean isGzType(String gzType) {
        if (null == gzType) {
            view.renZhengError("请选择雇主类型");
            return false;
        }
        if ("".equals(gzType)) {
            view.renZhengError("请选择雇主类型");
            return false;
        }
        return true;
    }

    @Override
    public void actualView(TaskContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }


}
