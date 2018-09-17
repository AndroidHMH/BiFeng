package com.coinwind.bifeng.ui.recommended.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.ui.home.bean.ListBean;

import java.util.List;

/**
 * 推荐列表的契约类
 */
public interface RecommendedContract {
    interface View {
        void showRecommendedList(List<TaskBean> list);

        void showError(String errorMsg);

        void loginTimeOut();
    }

    interface Presenter extends BasePresenter<View> {
        void loadRecommendedList();
    }
}
