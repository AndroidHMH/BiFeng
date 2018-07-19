package com.coinwind.bifeng.ui.answertask.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.ui.phototask.activity.PhotoTaskActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnswerTaskActivity extends BaseActivity {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.answer_task_img)
    ImageView answerTaskImg;
    @BindView(R.id.answer_task_title_tv)
    TextView answerTaskTitleTv;
    @BindView(R.id.answer_task_count_tv)
    TextView answerTaskCountTv;
    @BindView(R.id.answer_task_name_tv)
    TextView answerTaskNameTv;
    @BindView(R.id.answer_task_huisuo_img)
    ImageView answerTaskHuisuoImg;
    @BindView(R.id.answer_task_hui_suo_tv)
    TextView answerTaskHuiSuoTv;
    @BindView(R.id.answer_task_type_img)
    ImageView answerTaskTypeImg;
    @BindView(R.id.answer_task_type_tv)
    TextView answerTaskTypeTv;
    @BindView(R.id.answer_task_shengyu_img)
    ImageView answerTaskShengyuImg;
    @BindView(R.id.answer_task_shengyu_tv)
    TextView answerTaskShengyuTv;
    @BindView(R.id.answer_task_time_img)
    ImageView answerTaskTimeImg;
    @BindView(R.id.answer_task_time_tv)
    TextView answerTaskTimeTv;
    @BindView(R.id.answer_task_ren_wu_content_tv)
    TextView answerTaskRenWuContentTv;
    @BindView(R.id.answer_task_gu_zhu_content_tv)
    TextView answerTaskGuZhuContentTv;
    @BindView(R.id.answer_task_zheng_count_tv)
    TextView answerTaskZhengCountTv;
    @BindView(R.id.answer_task_fen_xiang_btn)
    LinearLayout answerTaskFenXiangBtn;
    @BindView(R.id.answer_task_ren_wu_tv)
    TextView answerTaskRenWuTv;
    @BindView(R.id.answer_task_ren_wu_btn)
    LinearLayout answerTaskRenWuBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_answer_task;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.answer_task_fen_xiang_btn, R.id.answer_task_ren_wu_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.answer_task_fen_xiang_btn:
                break;
            case R.id.answer_task_ren_wu_btn:
                Intent intent = new Intent(this, PhotoTaskActivity.class);
                startActivity(intent);
                break;
        }
    }
}
