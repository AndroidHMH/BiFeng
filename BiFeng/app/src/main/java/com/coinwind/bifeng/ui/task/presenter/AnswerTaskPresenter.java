package com.coinwind.bifeng.ui.task.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.task.bean.TaskIdsBean;
import com.coinwind.bifeng.ui.task.biz.AnswerTaskService;
import com.coinwind.bifeng.ui.task.contract.AnswerTaskContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 任务详情页面的P层
 */
public class AnswerTaskPresenter implements AnswerTaskContract.Presenter {

    private AnswerTaskService service;
    private AnswerTaskContract.View view;

    public AnswerTaskPresenter() {
        service = RetrofitHelp.getInstance().getService(AnswerTaskService.class);
    }

    @Override
    public void loadTaskIds(String taskId) {
        service.loadTaskIds(taskId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskIdsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TaskIdsBean taskIdsBean) {
                        int code = taskIdsBean.getCode();
                        List<TaskIdsBean.DataBean> data = taskIdsBean.getData();
                        if (code == Codes.SUCCESS_CODE && data != null) {
                            if (data.size() != 0) {
                                view.showTaskIds(data);
                            } else {
                                view.showTaskIdsError();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showTaskIdsError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void actualView(AnswerTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
