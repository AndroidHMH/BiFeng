package com.coinwind.bifeng.ui.sendtask.contract;

import android.widget.EditText;

import com.coinwind.bifeng.base.BasePresenter;

import retrofit2.http.Field;
import retrofit2.http.Header;

/**
 * 发布任务的契约类
 */
public interface SendTaskContract {
    interface View {
        void sendSuccess(String msg);

        void sendFailure(String failureMsg);

        void loginOut();
    }

    interface Presenter extends BasePresenter<View> {
        void sendTask(String share_score, String label, String type, String all_sharenum, String score, String all_tasknum,
                      String title, String img, String endTime, String needCheck, String task_intro, String publicNum, String publicImg,
                      String url, String content, String taskType, String contentImgList, String ques_content, String startTime);

        boolean isFenXiangCC(EditText fenXiangCC);

        boolean isAllFenXiangCC(EditText allFenXiangCC);

        boolean isRenWuCC(EditText renWuCC);
    }
}
