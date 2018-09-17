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
        void showNewTaskList(List<NewTaskBean.DataBean.MustDataBean> dataBeans, String cPower);

        void showHeightTaskList(List<NewTaskBean.DataBean.MustDataBean> dataBeans, String cPower);

        void loginTimeOut();

        void showError(String errorMsg);
    }

    interface Presenter extends BasePresenter<View> {
        void loadNewTaskList();
    }
}
