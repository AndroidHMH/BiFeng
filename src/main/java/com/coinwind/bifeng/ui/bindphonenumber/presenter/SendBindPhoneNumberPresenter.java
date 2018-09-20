package com.coinwind.bifeng.ui.bindphonenumber.presenter;

import android.text.TextUtils;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.model.http.SwitchThread;
import com.coinwind.bifeng.ui.bindphonenumber.biz.SendBindPhoneNumberService;
import com.coinwind.bifeng.ui.bindphonenumber.contract.SendBindPhoneNumberContract;
import com.coinwind.bifeng.ui.bindphonenumber.entity.SendBindPhoneNumberEntity;
import com.coinwind.bifeng.ui.login.bean.GetCodeBean;
import com.coinwind.bifeng.ui.login.biz.LoginService;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SendBindPhoneNumberPresenter implements SendBindPhoneNumberContract.Presenter {
    private SendBindPhoneNumberContract.View view;
    private LoginService loginService;
    private SendBindPhoneNumberService service;

    public SendBindPhoneNumberPresenter() {
        loginService = RetrofitHelp.getInstance().getService(LoginService.class);
        service = RetrofitHelp.getInstance().getService(SendBindPhoneNumberService.class);
    }

    @Override
    public void sendCode(String phone) {
        loginService.getCode(phone)
                .compose(SwitchThread.<GetCodeBean>switchThread())
                .subscribe(new Observer<GetCodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetCodeBean getCodeBean) {
                        int code = getCodeBean.getCode();
                        GetCodeBean.DataBean data = getCodeBean.getData();
                        if (code == Codes.SUCCESS_CODE && data.isState()) {
                            view.showBindMsg("获取验证码成功");
                        } else {
                            view.showBindMsg("获取验证码失败");
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
    public void sendBindPhoneNumber(String phone, String pasw, String code, String yaoQingCode) {
        service.bindPhone(phone, pasw, code, SpHelp.getAndroidId(), "100", SpHelp.getUserInformation(SpHelp.ID), SpHelp.getSign(), yaoQingCode)
                .compose(SwitchThread.<SendBindPhoneNumberEntity>switchThread())
                .subscribe(new Observer<SendBindPhoneNumberEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SendBindPhoneNumberEntity sendBindPhoneNumberEntity) {
                        int code = sendBindPhoneNumberEntity.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            SendBindPhoneNumberEntity.DataBean data = sendBindPhoneNumberEntity.getData();
                            if (data.isState()) {
                                view.showBindSuccess(data);
                            } else {
                                int resultCode = data.getCode();
                                if (resultCode == 1014) {
                                    view.goToLogin(data.getMsg());
                                } else {
                                    view.showBindMsg(data.getMsg());
                                }
                            }
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
    public boolean checkPaw(String psw) {
        if (psw == null) {
            view.showBindMsg("密码为空");
            return false;
        }
        if (TextUtils.isEmpty(psw)) {
            view.showBindMsg("密码为空");
            return false;
        }
        return true;
    }

    @Override
    public boolean checkCode(String code) {
        if (code == null) {
            view.showBindMsg("验证码为空");
            return false;
        }
        if (TextUtils.isEmpty(code)) {
            view.showBindMsg("验证码为空");
            return false;
        }
        return true;
    }

    @Override
    public void actualView(SendBindPhoneNumberContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
