package com.coinwind.bifeng.ui.task.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.task.bean.TaskIdsBean;

import java.util.List;

/**
 * 提交任务的契约类
 */
public interface AnswerTaskContract {
    interface View {
        void showTaskIds(List<TaskIdsBean.DataBean> data);

        void showTaskIdsError();

        void showSuccess(String imgUrl);

        void showError(String errorMsg);

        void showImgLoad();

        void showImgError();
    }

    interface Presenter extends BasePresenter<View> {
        void loadTaskIds(String taskId);

        void loadImg(String imgUrl);

        void loadShareImg(String taskId, String userId);
    }
}
