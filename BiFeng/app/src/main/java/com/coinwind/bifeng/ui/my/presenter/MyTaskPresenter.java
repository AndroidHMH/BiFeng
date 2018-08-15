package com.coinwind.bifeng.ui.my.presenter;

import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.home.bean.ListBean;
import com.coinwind.bifeng.ui.my.biz.MyTaskService;
import com.coinwind.bifeng.ui.my.contract.MyTaskContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 我发布(执行)的任务的P层
 */
public class MyTaskPresenter implements MyTaskContract.Presenter {
    private MyTaskContract.View view;
    private final MyTaskService service;

    public MyTaskPresenter() {
        service = RetrofitHelp.getInstance().getService(MyTaskService.class);
    }

    @Override
    public void loadTask(String userId, String flag, int page, String reqType) {
        service.loadTask(userId, flag, page, 5, reqType, SpHelp.getSign())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean listBean) {
                        int code = listBean.getCode();
                        List<TaskBean> data = listBean.getData();
                        if (code == Codes.SUCCESS_CODE && data != null) {
                            view.showSuccess(data);
                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginOut();
                        } else {
                            view.showError("请求失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError("请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void actualView(MyTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
