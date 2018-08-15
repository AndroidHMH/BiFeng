package com.coinwind.bifeng.ui.searchfor.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.base.TaskBean;

import java.util.List;

import retrofit2.http.Query;

/**
 * 搜索界面的契约类
 */
public interface SearchForContract {
    interface View {
        void showSearchFor(List<TaskBean> taskBeans);

        void showError();
    }

    interface Presenter extends BasePresenter<View> {
        void showSearchForList(String keywords, int page);
    }
}
