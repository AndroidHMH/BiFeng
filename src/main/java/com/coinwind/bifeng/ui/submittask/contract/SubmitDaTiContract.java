package com.coinwind.bifeng.ui.submittask.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.sendtask.bean.DiaoYanBean;
import com.coinwind.bifeng.ui.submittask.bean.SendDaTiBean;

import java.util.List;

/**
 * 做答题任务的契约类
 */
public interface SubmitDaTiContract {
    interface View {

        void through(String num);

        void notThrough(String num);

        void sendFailure(String failureMsg);

        void loginOut();
    }

    interface Presenter extends BasePresenter<View> {
        void sendTask(String taskId, List<SendDaTiBean> list, String type, String passNum);
    }
}
