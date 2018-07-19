package com.coinwind.bifeng.ui.home.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.home.bean.ListBean;

import java.util.List;

/**
 * 首页的契约类
 */
public interface HomeContract {
    interface View {
        void showBanner(List<HomeBannerBean> bannerBeans);

        void showList(List<ListBean> listBeans);
    }

    interface Presenter extends BasePresenter<View> {
        void loadBanner();

        void loadList(int page);
    }
}
