package com.coinwind.bifeng.ui.login.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.login.bean.LoginBean;

/**
 * 登录页面的契约类
 */
public interface LoginContract {
    interface View {
        void showCode(String message);

        void usePhoneLogin();

        void usePswLogin();

        void loginSuccessful(LoginBean.DataBean.UserBean user,String sign);

        void loginFailed(String errorMsg);
    }

    interface Presenter extends BasePresenter<View> {
        void changeLogin(String isLogin);

        void getCode(String phone);

        boolean checkPhone(String phone);

        boolean checkPaw(String psw);

        void pswLogin(String userName, String password);

        void phoneLogin(String phone, String shortMsgCode);
    }
}
