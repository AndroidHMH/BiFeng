package com.coinwind.bifeng.ui.home.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.home.bean.HomeInfoBean;
import com.coinwind.bifeng.ui.home.bean.HomeItemCCBean;
import com.coinwind.bifeng.ui.home.bean.HomeUserInfoBean;

import java.util.List;

/**
 * 首页的契约类
 */
public interface NewHomeContract {
    interface View {
        void showBroadcast(String content);

        void showHomeInfo(HomeInfoBean.DataBean dataBean);

        void showError(String errorMsg);

        void loginTimeOut();

        void showUserInfo(HomeUserInfoBean.DataBean dataBean);

        void showCCList(List<HomeItemCCBean.DataBean.CcListBean> listBeans);

        void showReceiveCCResult(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void loadBroadcast();

        void loadHomeInfo();

        void loadHomeUserInfo();

        void loadCCList();

        void receiveCC(String cc, String buildTime);
    }
}