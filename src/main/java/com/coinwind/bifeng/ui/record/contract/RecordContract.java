package com.coinwind.bifeng.ui.record.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.record.bean.RecordBean;

/**
 * 矿产记录的Contract
 */
public interface RecordContract {
    interface View {
        void showRecord(RecordBean.DataBean.CcLogBean ccLog);

        void showError(String errorMsg);

        void loginTimeOut();
    }

    interface Presenter extends BasePresenter<View> {
        void loadRecord(int page);
    }
}
