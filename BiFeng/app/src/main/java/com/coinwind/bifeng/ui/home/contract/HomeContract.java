package com.coinwind.bifeng.ui.home.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;

import java.util.List;

/**
 * 首页的契约类
 */
public interface HomeContract {
    interface View {
        void showBanner(List<HomeBannerBean.DataBean> banners);

        void showTuiJian(List<TaskBean> tuiJians);

        void showQiang(TaskBean taskBean);

        void showGuangBo(String content);

        void showBannerError(List<Integer> defaultImgs);

        void showGuangBoError(String errorContent);

    }

    interface Presenter extends BasePresenter<View> {
        void loadBanner();

        void loadQiang();

        void loadTuiJian(int page);

        void loadGuangBo();
    }
}
