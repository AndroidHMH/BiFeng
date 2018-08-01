package com.coinwind.bifeng.ui.my.contract;

import com.coinwind.bifeng.base.BasePresenter;

import java.io.File;
import java.util.List;

public interface PerfectInformationContract {
    interface View {
        void successUpdate(String imgUrl);

        void errorUpdate(String errorMsg);

    }

    interface Presenter extends BasePresenter<View> {
        void updateImgs(File imgFile);
    }
}
