package com.coinwind.bifeng.ui.record.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.model.http.SwitchThread;
import com.coinwind.bifeng.ui.record.bean.RecordBean;
import com.coinwind.bifeng.ui.record.biz.RecordService;
import com.coinwind.bifeng.ui.record.contract.RecordContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RecordPresenter implements RecordContract.Presenter {
    private RecordContract.View view;
    private RecordService service;

    public RecordPresenter() {
        service = RetrofitHelp.getInstance().getService(RecordService.class);
    }

    @Override
    public void loadRecord(int page) {
        service.loadRecord(SpHelp.getAndroidId(), SpHelp.getUserInformation(SpHelp.ID), SpHelp.getSign(), page + "", "5")
                .compose(SwitchThread.<RecordBean>switchThread())
                .subscribe(new Observer<RecordBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecordBean recordBean) {
                        int code = recordBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            RecordBean.DataBean data = recordBean.getData();
                            if (data.isState()) {
                                view.showRecord(data.getCcLog());
                            } else {
                                int resultCode = data.getCode();
                                if (resultCode == Codes.FAILURE_CODE) {
                                    view.loginTimeOut();
                                } else {
                                    view.showError("请求错误");
                                }
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
    public void actualView(RecordContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
