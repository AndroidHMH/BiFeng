package com.coinwind.bifeng.ui.task.presenter;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.model.http.SwitchThread;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.home.bean.ListBean;
import com.coinwind.bifeng.ui.submittask.bean.SubmitImgBean;
import com.coinwind.bifeng.ui.submittask.biz.SubmitService;
import com.coinwind.bifeng.ui.task.bean.RenZhengBean;
import com.coinwind.bifeng.ui.task.bean.TaskHallBean;
import com.coinwind.bifeng.ui.task.biz.TaskHallService;
import com.coinwind.bifeng.ui.task.biz.TaskService;
import com.coinwind.bifeng.ui.task.contract.TaskContract;
import com.coinwind.bifeng.ui.task.contract.TaskHallContract;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 任务列表的P层
 */
public class TaskHallPresenter implements TaskHallContract.Presenter {

    private TaskHallService service;
    private TaskHallContract.View view;

    public TaskHallPresenter() {
        service = RetrofitHelp.getInstance().getService(TaskHallService.class);
    }

    @Override
    public void loadBanner() {
        service.loadBanner().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBannerBean homeBannerBean) {
                        int code = homeBannerBean.getCode();
                        List<HomeBannerBean.DataBean> data = homeBannerBean.getData();
                        if (code == Codes.SUCCESS_CODE && data != null) {
                            view.showBanner(data);
                        } else {
                            view.showBannerError(setDefaultBannerImg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showBannerError(setDefaultBannerImg());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadProgress() {
        service.loadProgress(SpHelp.getAndroidId(), SpHelp.getUserInformation(SpHelp.ID), SpHelp.getSign())
                .compose(SwitchThread.<TaskHallBean>switchThread())
                .subscribe(new Observer<TaskHallBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TaskHallBean taskHallBean) {
                        int code = taskHallBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            TaskHallBean.DataBean data = taskHallBean.getData();
                            if (data.isState()) {

                                view.showProgress(data);
                            } else {
                                int resultCode = data.getCode();
                                if (resultCode == Codes.FAILURE_CODE) {
                                    view.loginTimeOut();
                                } else
                                    view.showErrorProgress("请求出错");
                            }
                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginTimeOut();
                        } else {
                            view.showErrorProgress("请求出错");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showErrorProgress("请求出错");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private List<Integer> setDefaultBannerImg() {
        ArrayList<Integer> defaultImgs = new ArrayList<>();
        defaultImgs.add(R.mipmap.default_banner1);
        defaultImgs.add(R.mipmap.default_banner2);
        defaultImgs.add(R.mipmap.default_banner3);
        return defaultImgs;
    }

    @Override
    public void actualView(TaskHallContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }


}
