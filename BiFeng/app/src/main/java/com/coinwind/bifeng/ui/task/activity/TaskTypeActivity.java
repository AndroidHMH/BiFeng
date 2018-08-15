package com.coinwind.bifeng.ui.task.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.ui.sendtask.activity.SendZhangFenTaskActivity;
import com.coinwind.bifeng.ui.sendtask.activity.SendZhuCeTaskActivity;
import com.coinwind.bifeng.ui.sendtask.activity.SendZhuanFaTaskActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发布任务是选择类别
 */
public class TaskTypeActivity extends NoNetworkBaseActivity {


    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.task_type_zhuan_fa_btn)
    LinearLayout taskTypeZhuanFaBtn;
    @BindView(R.id.task_type_zhu_ce_btn)
    LinearLayout taskTypeZhuCeBtn;
    @BindView(R.id.task_type_ping_lun_btn)
    LinearLayout taskTypePingLunBtn;
    @BindView(R.id.task_type_zhang_fen_btn)
    LinearLayout taskTypeZhangFenBtn;
    @BindView(R.id.task_type_diao_yan_btn)
    LinearLayout taskTypeDiaoYanBtn;
    @BindView(R.id.task_type_da_ti_btn)
    LinearLayout taskTypeDaTiBtn;
    @BindView(R.id.task_type_pai_zhao_btn)
    LinearLayout taskTypePaiZhaoBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_type;
    }

    @Override
    protected void init() {

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.title_layout_return_btn, R.id.task_type_zhuan_fa_btn, R.id.task_type_zhu_ce_btn, R.id.task_type_ping_lun_btn, R.id.task_type_zhang_fen_btn, R.id.task_type_diao_yan_btn, R.id.task_type_da_ti_btn, R.id.task_type_pai_zhao_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.task_type_zhuan_fa_btn:
                //转发任务
                SendZhuanFaTaskActivity.openSendZhuanFaTaskActivity(this, "4");
                break;
            case R.id.task_type_zhu_ce_btn:
                SendZhuCeTaskActivity.openSendZhuCeTaskActivity(this, "3");
                break;
            case R.id.task_type_ping_lun_btn:
                SendZhuCeTaskActivity.openSendZhuCeTaskActivity(this, "2");
                break;
            case R.id.task_type_zhang_fen_btn:
                SendZhangFenTaskActivity.openSendZhangFenTaskActivity(this, "1");
                break;
            case R.id.task_type_diao_yan_btn:
                break;
            case R.id.task_type_da_ti_btn:
                break;
            case R.id.task_type_pai_zhao_btn:
                break;
        }
    }
}
