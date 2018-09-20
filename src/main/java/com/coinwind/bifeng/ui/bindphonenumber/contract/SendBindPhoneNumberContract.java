package com.coinwind.bifeng.ui.bindphonenumber.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.bindphonenumber.entity.SendBindPhoneNumberEntity;

public interface SendBindPhoneNumberContract {
    interface View {

        void showBindSuccess(SendBindPhoneNumberEntity.DataBean data);

        void showBindMsg(String msg);

        void goToLogin(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void sendCode(String phone);

        void sendBindPhoneNumber(String phone, String pasw, String code, String yaoQingCode);

        boolean checkPaw(String psw);

        boolean checkCode(String code);

    }

}
