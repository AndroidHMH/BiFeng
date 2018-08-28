package com.coinwind.bifeng.ui.my.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.my.bean.AlertsBean;

import java.util.List;

/**
 * 我的信息的契约类
 */
public interface AlertsContract {
    interface View {
        void showAlerts(List<AlertsBean.DataBean> dataBeanList);

        void showError();

        void loginOut();
    }


    interface Presenter extends BasePresenter<View> {
        void loadAlerts();
    }
}
