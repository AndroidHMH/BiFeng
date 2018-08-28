package com.coinwind.bifeng.ui.share.contract;

import com.coinwind.bifeng.base.BasePresenter;

public interface InvitationContract {
    interface View {
        void showImgLoad();

        void showImgError();
    }

    interface Presenter extends BasePresenter<View> {
        void loadImg(String imgUrl);
    }
}
