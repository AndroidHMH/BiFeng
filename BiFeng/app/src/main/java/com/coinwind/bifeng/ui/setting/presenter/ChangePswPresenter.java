package com.coinwind.bifeng.ui.setting.presenter;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.login.bean.GetCodeBean;
import com.coinwind.bifeng.ui.login.biz.LoginService;
import com.coinwind.bifeng.ui.setting.bean.ChangePswBean;
import com.coinwind.bifeng.ui.setting.biz.ChangePswService;
import com.coinwind.bifeng.ui.setting.contract.ChangePswContract;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 修改密码的P层
 */
public class ChangePswPresenter implements ChangePswContract.Presenter {
    private ChangePswContract.View view;
    private ChangePswService service;
    private LoginService loginService;

    public ChangePswPresenter() {
        service = RetrofitHelp.getInstance().getService(ChangePswService.class);
        loginService = RetrofitHelp.getInstance().getService(LoginService.class);

    }

    @Override
    public void getCode(String phone) {
        loginService.getCode(phone).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetCodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetCodeBean getCodeBean) {
                        int code = getCodeBean.getCode();
                        GetCodeBean.DataBean data = getCodeBean.getData();
                        if (code == Codes.SUCCESS_CODE && data.isState()) {
                            view.changeError("验证码获取成功");
                        } else {
                            view.changeError("验证码获取失败");
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
    public void changePsw(String phone, String newpwd, String shortMsgCode) {
        service.changePsw(phone, newpwd, shortMsgCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangePswBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ChangePswBean changePswBean) {
                        int code = changePswBean.getCode();
                        ChangePswBean.DataBean data = changePswBean.getData();
                        if (code == Codes.SUCCESS_CODE && data.isState()) {
                            view.changeSuccess();
                        } else {
                            view.changeError(data.getEmsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.changeError("修改密码失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public boolean isNewPsw(String newPsw) {
        if (null == newPsw) {
            view.changeError("新密码为空");
            return false;
        }
        if ("".equals(newPsw)) {
            view.changeError("新密码为空");
            return false;
        }
        return true;
    }

    @Override
    public boolean isShortMsgCode(String shortMsgCode) {
        if (null == shortMsgCode) {
            view.changeError("验证码为空");
            return false;
        }
        if ("".equals(shortMsgCode)) {
            view.changeError("验证码为空");
            return false;
        }
        return true;
    }

    @Override
    public void actualView(ChangePswContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
