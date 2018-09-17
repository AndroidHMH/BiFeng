package com.coinwind.bifeng.ui.my.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.my.bean.NewMyBean;

/**
 * 我的页面的契约类
 */
public interface NewMyContract {
    interface View {
        void showMyInfo(NewMyBean.DataBean dataBean);

        void showError(String errorMsg);

        void loginTimeOut();
    }

    interface Presenter extends BasePresenter<View> {
        void loadMyInfo();
    }
}
