package com.coinwind.bifeng.ui.my.presenter;

import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.my.bean.ChangeMessageBean;
import com.coinwind.bifeng.ui.my.bean.WalletBean;
import com.coinwind.bifeng.ui.my.biz.MyService;
import com.coinwind.bifeng.ui.my.contract.MyContract;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 个人中心的P层
 */
public class MyPresenter implements MyContract.Presenter {

    private MyService service;
    private MyContract.View view;

    public MyPresenter() {
        service = RetrofitHelp.getInstance().getService(MyService.class);
    }

    @Override
    public void changeType(String userId, final String field, final String value) {
        service.changeType(userId, field, value, SpHelp.getSign())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ChangeMessageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChangeMessageBean changeMessageBean) {
                        int code = changeMessageBean.getCode();
                        ChangeMessageBean.DataBean data = changeMessageBean.getData();
                        if (code == Codes.SUCCESS_CODE && data != null) {
                            SpHelp.putUserInformation(field, value);
                            view.showSuccess("修改成功", Integer.parseInt(value));
                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginOut();
                        } else {
                            view.showError("身份失效,请重新登。");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError("修改失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadCC(String userId) {
        service.loadCC(userId, "1", "1", SpHelp.getSign())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<WalletBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WalletBean walletBean) {
                        int code = walletBean.getCode();
                        WalletBean.DataBean data = walletBean.getData();
                        if (code == Codes.SUCCESS_CODE && data != null) {
                            view.showCC(data.getTodayCss() + "", data.getCurrentCss() + "");
                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginOut();
                        } else {
                            view.showError("身份失效,请重新登。");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError("获取钱包失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void actualView(MyContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
