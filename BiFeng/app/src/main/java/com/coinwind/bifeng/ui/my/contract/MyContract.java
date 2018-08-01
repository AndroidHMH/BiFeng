package com.coinwind.bifeng.ui.my.contract;

import com.coinwind.bifeng.base.BasePresenter;

public interface MyContract {
    interface View {
        void showSuccess(String msg,int type);

        void showError(String errorMsg);

        void showCC(String todayCC,String allCC);
    }

    interface Presenter extends BasePresenter<View> {
        void changeType(String userId, String field, String value);

        void loadCC(String userId);
    }
}
