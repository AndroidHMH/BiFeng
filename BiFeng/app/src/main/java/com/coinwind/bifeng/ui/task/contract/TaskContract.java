package com.coinwind.bifeng.ui.task.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.base.TaskBean;

import java.util.List;

import retrofit2.http.Query;

public interface TaskContract {
    interface View {
        void showContentList(List<TaskBean> taskBeans);
    }

    interface Presenter extends BasePresenter<View> {
        void loadContentList(int page, String type, String taskTyp, String orderFiel, String orderSort);
    }
}
