package com.coinwind.bifeng.ui.task.contract;


import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.task.bean.NewTaskBean;
import com.coinwind.bifeng.ui.task.bean.TaskHallBean;

import java.util.List;

/**
 * 任务列表的契约类
 */
public interface TaskHallContract {
    interface View {
        void showBanner(List<HomeBannerBean.DataBean> banners);

        void showBannerError(List<Integer> defaultImgs);

        void showProgress(TaskHallBean.DataBean dataBean);

        void loginTimeOut();

        void showErrorProgress(String errorMsg);
    }

    interface Presenter extends BasePresenter<View> {
        void loadBanner();

        void loadProgress();
    }
}
