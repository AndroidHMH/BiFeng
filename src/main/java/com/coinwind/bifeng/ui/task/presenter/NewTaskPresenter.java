package com.coinwind.bifeng.ui.task.presenter;

import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.model.http.SwitchThread;
import com.coinwind.bifeng.ui.home.bean.ListBean;
import com.coinwind.bifeng.ui.submittask.bean.SubmitImgBean;
import com.coinwind.bifeng.ui.submittask.biz.SubmitService;
import com.coinwind.bifeng.ui.task.bean.NewTaskBean;
import com.coinwind.bifeng.ui.task.bean.RenZhengBean;
import com.coinwind.bifeng.ui.task.biz.NewTaskService;
import com.coinwind.bifeng.ui.task.biz.TaskService;
import com.coinwind.bifeng.ui.task.contract.NewTaskContract;
import com.coinwind.bifeng.ui.task.contract.TaskContract;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 创世任务的P层
 */
public class NewTaskPresenter implements NewTaskContract.Presenter {

    private NewTaskContract.View view;
    private final NewTaskService service;

    public NewTaskPresenter() {
        service = RetrofitHelp.getInstance().getService(NewTaskService.class);
    }

    @Override
    public void loadNewTaskList() {
        service.loadContentList(SpHelp.getAndroidId(), SpHelp.getUserInformation(SpHelp.ID), SpHelp.getSign())
                .compose(SwitchThread.<NewTaskBean>switchThread())
                .subscribe(new Observer<NewTaskBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewTaskBean listBean) {
                        int code = listBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            NewTaskBean.DataBean data = listBean.getData();
                            if (data.isState()) {
                                List<NewTaskBean.DataBean.MustDataBean> mustData = data.getMustData();
                                if (mustData != null) {
                                    view.showNewTaskList(mustData,data.getC_power()+"");
                                } else {
                                    view.showError("新手任务请求失败");
                                }
                                List<NewTaskBean.DataBean.MustDataBean> choiceDate = data.getChoiceDate();
                                if (choiceDate != null) {
                                    view.showHeightTaskList(choiceDate,data.getC_power()+"");
                                } else {
                                    view.showError("高算力任务请求失败");
                                }
//                                view.showUserInfo(homeUserInfoBean.getData());
                            } else {
                                int resultCode = listBean.getData().getCode();
                                if (resultCode == Codes.FAILURE_CODE) {
                                    view.loginTimeOut();
                                } else
                                    view.showError("请求失败");
                            }
                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginTimeOut();
                        } else {
                            view.showError("请求失败");
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
    public void actualView(NewTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }


}
