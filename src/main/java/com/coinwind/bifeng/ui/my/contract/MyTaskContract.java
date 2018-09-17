package com.coinwind.bifeng.ui.my.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.ui.my.bean.MyTaskBean;

import java.util.List;

/**
 * 我执行（发布）的任务的契约类
 */
public interface MyTaskContract {
    interface View {
        void showSuccess(List<TaskBean> taskBeans);

        void showError(String errorMsg);

        void loginOut();

        void showList(List<MyTaskBean.DataBean.ListBean> listBeans);
    }

    interface Presenter extends BasePresenter<View> {
        void loadTask(String flag);
    }
}
