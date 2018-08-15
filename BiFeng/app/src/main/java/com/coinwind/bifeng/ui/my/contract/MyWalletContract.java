package com.coinwind.bifeng.ui.my.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.my.bean.WalletBean;

import java.util.List;

/**
 * 我的钱包的契约类
 */
public interface MyWalletContract {
    interface View {
        void showSuccess(List<WalletBean.DataBean.BfCssLogBean.ListBean> dataBeans,int todayCC,int allCc);

        void showError(String errorMsg);
        void loginOut();
    }

    interface Presenter extends BasePresenter<View> {
        void loadRecord(String userId, int page);
    }
}
