package com.coinwind.bifeng.ui.setting.contract;

import com.coinwind.bifeng.base.BasePresenter;

/**
 * 修改个人信息的契约类
 */
public interface SetMessageContract {
    interface View {
        void showSuccess(String successMsg, String field, String value);

        void showError(String errorMsg);

        void loginOut();

    }

    interface Presenter extends BasePresenter<View> {
        void changeMessage(String userId, String field, String value);
    }
}
