package com.coinwind.bifeng.ui.task.contract;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.base.TaskBean;

import java.io.File;
import java.util.List;

import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * 任务大厅的契约类
 */
public interface TaskContract {
    interface View {
        void showContentList(List<TaskBean> taskBeans);

        void successUpdate(String imgUrl);

        void errorUpdate(String errorMsg);

        void renZhengSuccess();

        void renZhengError(String errorMsg);

    }

    interface Presenter extends BasePresenter<View> {
        void loadContentList(int page, String type, String taskTyp, String orderFiel, String orderSort);

        void updateImg(File imgFile);

        void loadRenZheng(String logoUrl, String namecardUrl,
                          String qiyeInfo, String gzName, String gzType);

        boolean isLogoUrl(String logoUrl);

        boolean isNameCardUrl(String namecardUrl);

        boolean isQiyeInfo(String qiyeInfo);

        boolean isGzName(String gzName);

        boolean isGzType(String gzType);
    }
}
