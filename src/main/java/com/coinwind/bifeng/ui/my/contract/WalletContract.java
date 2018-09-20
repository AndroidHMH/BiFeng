package com.coinwind.bifeng.ui.my.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.my.bean.NewWalletBean;
import com.coinwind.bifeng.ui.my.bean.WalletBean;

import java.util.List;

/**
 * 我的钱包的契约类
 */
public interface WalletContract {
    interface View {

        void showError(String errorMsg);

        void loginOut();

        void showWallet(NewWalletBean.DataBean data);
    }

    interface Presenter extends BasePresenter<View> {
        void loadRecord(String userId, int page);

        void loadWallet();
    }
}
