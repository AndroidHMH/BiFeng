package com.coinwind.bifeng.ui.my.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.my.bean.AlertsBean;
import com.coinwind.bifeng.ui.my.biz.AlertsService;
import com.coinwind.bifeng.ui.my.contract.AlertsContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 我的信息P层
 */
public class AlertsPresenter implements AlertsContract.Presenter {
    private AlertsContract.View view;
    private AlertsService service;

    public AlertsPresenter() {
        service = RetrofitHelp.getInstance().getService(AlertsService.class);
    }

    @Override
    public void loadAlerts() {
        service.loadAlerts(SpHelp.getUserInformation(SpHelp.ID), SpHelp.getSign())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AlertsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AlertsBean alertsBean) {
                        int code = alertsBean.getCode();
                        List<AlertsBean.DataBean> data = alertsBean.getData();
                        if (code == Codes.SUCCESS_CODE && !data.isEmpty()) {
                            view.showAlerts(data);
                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginOut();
                        } else {
                            view.showError();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void actualView(AlertsContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
