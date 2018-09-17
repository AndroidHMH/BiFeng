package com.coinwind.bifeng.ui.setting.contract;

import com.coinwind.bifeng.base.BasePresenter;

import java.io.File;

/**
 * 更换头像的契约类
 */
public interface ChangeHeadImgContract {
    interface View {
        void successUpdate(String imgUrl);

        void errorUpdate(String errorMsg);

        void loginTimeOut();

        void doHeadImgTaskSuccess(String imgUrl);

    }

    interface Presenter extends BasePresenter<View> {
        void updateImage(File imgFile);

        void doHeadImgTask(String taskId, String para);
    }
}
