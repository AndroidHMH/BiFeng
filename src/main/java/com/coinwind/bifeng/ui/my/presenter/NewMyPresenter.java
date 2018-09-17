package com.coinwind.bifeng.ui.my.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.model.http.SwitchThread;
import com.coinwind.bifeng.ui.my.bean.NewMyBean;
import com.coinwind.bifeng.ui.my.biz.NewMyService;
import com.coinwind.bifeng.ui.my.contract.NewMyContract;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 个人中心的P层
 */
public class NewMyPresenter implements NewMyContract.Presenter {
    private NewMyContract.View view;
    private NewMyService service;

    public NewMyPresenter() {
        service = RetrofitHelp.getInstance().getService(NewMyService.class);
    }

    @Override
    public void loadMyInfo() {
        service.loadMyInfo(SpHelp.getAndroidId(), SpHelp.getUserInformation(SpHelp.ID), SpHelp.getSign())
                .compose(SwitchThread.<NewMyBean>switchThread())
                .subscribe(new Observer<NewMyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewMyBean newMyBean) {
                        int code = newMyBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            if (newMyBean.isState()) {
                                view.showMyInfo(newMyBean.getData());
                            } else {
                                int resultCode = newMyBean.getData().getCode();
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
                        view.showError("请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void actualView(NewMyContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
