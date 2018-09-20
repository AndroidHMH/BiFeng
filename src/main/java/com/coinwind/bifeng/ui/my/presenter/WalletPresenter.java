package com.coinwind.bifeng.ui.my.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.model.http.SwitchThread;
import com.coinwind.bifeng.ui.my.bean.NewWalletBean;
import com.coinwind.bifeng.ui.my.bean.WalletBean;
import com.coinwind.bifeng.ui.my.biz.MyService;
import com.coinwind.bifeng.ui.my.contract.WalletContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 我的钱包的P层
 */
public class WalletPresenter implements WalletContract.Presenter {
    private WalletContract.View view;
    private final MyService service;

    public WalletPresenter() {
        service = RetrofitHelp.getInstance().getService(MyService.class);

    }

    @Override
    public void loadRecord(String userId, int page) {
        service.loadCC(userId, page + "", "5", SpHelp.getSign())
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
                            List<WalletBean.DataBean.BfCssLogBean.ListBean> list = data.getBfCssLog().getList();
                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginOut();
                        } else {
                            view.showError("获取钱包失败");
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
    public void loadWallet() {
        service.loadWall(SpHelp.getUserInformation(SpHelp.ID), SpHelp.getAndroidId(), SpHelp.getSign())
                .compose(SwitchThread.<NewWalletBean>switchThread())
                .subscribe(new Observer<NewWalletBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewWalletBean newWalletBean) {
                        int code = newWalletBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            NewWalletBean.DataBean data = newWalletBean.getData();
                            if (data.isState()) {
                                view.showWallet(data);
                            } else {
                                int resultCode = data.getCode();
                                if (resultCode == Codes.FAILURE_CODE) {
                                    view.loginOut();
                                } else {
                                    view.showError("获取失败");
                                }
                            }
                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginOut();
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
    public void actualView(WalletContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
