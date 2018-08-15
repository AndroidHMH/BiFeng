package com.coinwind.bifeng.ui.sendtask.contract;

import com.coinwind.bifeng.base.BasePresenter;

import java.io.File;

public interface SendZhuanFaTaskContract {
    interface View {
        void successUpdate(String imgUrl);

        void errorUpdate(String errorMsg);
    }

    interface Presenter extends BasePresenter<View> {

        void updateImgs(File imgFile);
    }
}
