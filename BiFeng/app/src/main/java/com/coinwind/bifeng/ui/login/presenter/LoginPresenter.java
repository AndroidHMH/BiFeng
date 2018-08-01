package com.coinwind.bifeng.ui.login.presenter;

import android.text.TextUtils;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.login.bean.GetCodeBean;
import com.coinwind.bifeng.ui.login.bean.LoginBean;
import com.coinwind.bifeng.ui.login.biz.LoginService;
import com.coinwind.bifeng.ui.login.contract.LoginContract;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginService service;
    private LoginContract.View view;

    public LoginPresenter() {
        service = RetrofitHelp.getInstance().getService(LoginService.class);
    }

    @Override
    public void changeLogin(String isLogin) {
        if ("使用密码登录".equals(isLogin)) {
            view.usePswLogin();
        } else {
            view.usePhoneLogin();
        }
    }

    @Override
    public void getCode(String phone) {
        service.getCode(phone).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetCodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetCodeBean getCodeBean) {
                        int code = getCodeBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            view.showCode("获取验证码成功");
                        } else {
                            view.showCode("获取验证码失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showCode("获取验证码失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public boolean checkPhone(String phone) {
        if (phone == null) {
            view.showCode("手机号为空");
            return false;
        }
        if (TextUtils.isEmpty(phone)) {
            view.showCode("手机号为空");
            return false;
        }
        String pattern = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$";
        if (!phone.matches(pattern)) {
            view.showCode("手机号格式错误");
            return false;
        }
        return true;
    }

    @Override
    public boolean checkPaw(String psw) {
        if (psw == null) {
            view.showCode("密码为空");
            return false;
        }
        if (TextUtils.isEmpty(psw)) {
            view.showCode("密码为空");
            return false;
        }
        return true;
    }

    @Override
    public void pswLogin(String userName, String password) {
        service.pswLogin(userName, password).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        int code = loginBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            LoginBean.DataBean data = loginBean.getData();
                            if (data.isState()) {
                                view.loginSuccessful(data.getUser());
                            } else {
                                view.loginFailed(data.getMsg());
                            }

                        }
                        LogHelp.e("LoginPresenter", loginBean.getData().toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogHelp.e("LoginPresenter", e.getMessage());
                        view.loginFailed("登录失败，请核实信息!");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void phoneLogin(String phone, String shortMsgCode) {

        service.phoneLogin(phone, shortMsgCode, Codes.FROM_TYPE).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        int code = loginBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            LoginBean.DataBean data = loginBean.getData();
                            if (data.isState()) {
                                view.loginSuccessful(data.getUser());
                            } else {
                                view.loginFailed(data.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.loginFailed("登录失败，请核实信息!");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void actualView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
