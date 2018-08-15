package com.coinwind.bifeng.ui.submittask.contract;

import com.coinwind.bifeng.base.BasePresenter;

import java.io.File;
import java.util.List;

/**
 * 提交任务的契约类
 */
public interface SubmitContract {
    interface View {
        void successUpdate(String imgUrl);

        void errorUpdate(String errorMsg);

        void showSubmitSuccess();

        void showSubmitFailure(String error);
    }

    interface Presenter extends BasePresenter<View> {
        void updateImgs(File imgFile);

        void submitTask(String taskId, String userId, List<String> imgs, String explain);

        boolean checkPhone(String phone);
    }
}
