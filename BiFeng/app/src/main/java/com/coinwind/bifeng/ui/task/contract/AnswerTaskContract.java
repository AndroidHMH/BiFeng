package com.coinwind.bifeng.ui.task.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.task.bean.TaskIdsBean;

import java.util.List;

public interface AnswerTaskContract {
    interface View{
        void showTaskIds(List<TaskIdsBean.DataBean> data );

        void showTaskIdsError();
    }
    interface Presenter extends BasePresenter<View>{
        void loadTaskIds(String taskId);
    }
}
