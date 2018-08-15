package com.coinwind.bifeng.ui.my.contract;

import com.coinwind.bifeng.base.BasePresenter;

import java.io.File;
import java.util.List;

/**
 * 用户信息的契约类
 */
public interface PerfectInformationContract {
    interface View {
        void successUpdate(String imgUrl);

        void errorUpdate(String errorMsg);

        void changeSuccess(String message);

        void changeError(String errorMsg);

        void loginOut();
    }

    interface Presenter extends BasePresenter<View> {
        void updateHeadImg(File imgFile);

        void changeHeadImg(String userId, String imgUrl);
    }
}
