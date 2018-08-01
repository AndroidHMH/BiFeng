package com.coinwind.bifeng.ui.setting.contract;

import com.coinwind.bifeng.base.BasePresenter;

public interface SetMessageContract {
    interface View {
        void showSuccess(String successMsg,String field,String value);

        void showError(String errorMsg);

    }

    interface Presenter extends BasePresenter<View> {
        void changeMessage(String userId, String field, String value);
    }
}
