package com.coinwind.bifeng.ui.sendtask.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.ui.sendtask.bean.DiaoYanBean;

import java.util.List;

public interface SendDiaoYanContract {
    interface View {
        void sendSuccess(String msg);

        void sendFailure(String failureMsg);

        void loginOut();
    }

    interface Presenter extends BasePresenter<View> {
        void sendTask(String share_score, String label, String type, String all_sharenum, String score, String all_tasknum,
                      String title, String img, String endTime, String needCheck, String task_intro, String publicNum, String publicImg,
                      String url, String content, String taskType, List<DiaoYanBean> diaoYanBeans, String startTime);

    }
}
