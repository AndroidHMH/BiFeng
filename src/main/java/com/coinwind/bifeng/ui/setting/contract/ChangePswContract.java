package com.coinwind.bifeng.ui.setting.contract;

import com.coinwind.bifeng.base.BasePresenter;

/**
 * 修改密码的契约类
 */
public interface ChangePswContract {
    interface View {

        void changeSuccess();

        void changeError(String errorMsg);
    }

    interface Presenter extends BasePresenter<View> {
        void getCode(String phone);

        void changePsw(String phone, String newpwd, String shortMsgCode);

        boolean isNewPsw(String newPsw);

        boolean isShortMsgCode(String shortMsgCode);
    }
}
