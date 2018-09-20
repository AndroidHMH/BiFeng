package com.coinwind.bifeng.ui.task.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.ui.task.bean.NewTaskBean;

import java.io.File;
import java.util.List;

/**
 * 创世任务的契约类
 */
public interface NewTaskContract {
    interface View {

        void loginTimeOut();

        void showError(String errorMsg);

        void showNewTaskList(NewTaskBean.DataBean data);
    }

    interface Presenter extends BasePresenter<View> {
        void loadNewTaskList();
    }
}
