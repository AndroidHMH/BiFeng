package com.coinwind.bifeng.ui.submittask.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.sendtask.bean.DiaoYanBean;

import java.util.List;

/**
 * 做调研任务的契约类
 */
public interface AnswerTheQuestionContract {
    interface View {
        void sendSuccess(String msg);

        void sendFailure(String failureMsg);

        void loginOut();
    }

    interface Presenter extends BasePresenter<View> {
        void sendTask(String taskId, List<DiaoYanBean> list, String type);
    }
}
