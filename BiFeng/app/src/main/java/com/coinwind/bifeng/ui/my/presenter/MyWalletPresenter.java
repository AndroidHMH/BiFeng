package com.coinwind.bifeng.ui.my.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.my.bean.WalletBean;
import com.coinwind.bifeng.ui.my.biz.MyService;
import com.coinwind.bifeng.ui.my.contract.MyWalletContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
/**
 *我的钱包的P层
 */
public class MyWalletPresenter implements MyWalletContract.Presenter {
    private MyWalletContract.View view;
    private final MyService service;

    public MyWalletPresenter() {
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
                                view.showSuccess(list,data.getTodayCss(),data.getCurrentCss());
                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginOut();
                        } else{
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
    public void actualView(MyWalletContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
