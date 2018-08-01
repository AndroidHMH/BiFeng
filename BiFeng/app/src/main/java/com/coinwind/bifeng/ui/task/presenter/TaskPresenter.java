package com.coinwind.bifeng.ui.task.presenter;

import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.home.bean.ListBean;
import com.coinwind.bifeng.ui.task.biz.TaskService;
import com.coinwind.bifeng.ui.task.contract.TaskContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TaskPresenter implements TaskContract.Presenter {

    private TaskService service;
    private TaskContract.View view;

    public TaskPresenter() {
        service = RetrofitHelp.getInstance().getService(TaskService.class);
    }

    @Override
    public void loadContentList(int page, String type, String taskTyp, String orderFiel, String orderSort) {
        LogHelp.e("TaskPresenter", "page = " + page);
        LogHelp.e("TaskPresenter", "type = " + type);
        LogHelp.e("TaskPresenter", "taskType = " + taskTyp);
        LogHelp.e("TaskPresenter", "orderFiel = " + orderFiel);
        LogHelp.e("TaskPresenter", "orderSort = " + orderSort);
//        if (taskTyp < 0) {
//            taskTyp = 0;
//        }
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
    public void actualView(TaskContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }


}
