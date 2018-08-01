package com.coinwind.bifeng.ui.setting.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.my.bean.ChangeMessageBean;
import com.coinwind.bifeng.ui.my.biz.MyService;
import com.coinwind.bifeng.ui.setting.config.SetMessageHelp;
import com.coinwind.bifeng.ui.setting.contract.SetMessageContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SetMessagePresenter implements SetMessageContract.Presenter {
    private SetMessageContract.View view;
    private final MyService service;

    public SetMessagePresenter() {
        service = RetrofitHelp.getInstance().getService(MyService.class);
    }
//    public SetMessagePresenter(SetMessageContract.View view) {
//        service = RetrofitHelp.getInstance().getService(MyService.class);
//        this.view = view;
//    }

    @Override
    public void changeMessage(String userId, final String field, final String value) {
        service.changeType(userId, field, value)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ChangeMessageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChangeMessageBean changeMessageBean) {
                        int code = changeMessageBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
//                            SpHelp.putUserInformation(field, value);
                            view.showSuccess("修改成功",field, value);
                        } else {
                            view.showError("修改失败");
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
    public void actualView(SetMessageContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
