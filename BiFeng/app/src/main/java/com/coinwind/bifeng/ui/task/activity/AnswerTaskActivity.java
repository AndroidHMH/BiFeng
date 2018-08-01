package com.coinwind.bifeng.ui.task.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.ui.task.bean.TaskIdsBean;
import com.coinwind.bifeng.ui.task.config.SetViewHelp;
import com.coinwind.bifeng.ui.task.contract.AnswerTaskContract;
import com.coinwind.bifeng.ui.task.presenter.AnswerTaskPresenter;
import com.coinwind.bifeng.view.VerticalTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AnswerTaskActivity extends BaseActivity<AnswerTaskPresenter> implements AnswerTaskContract.View {


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
    VerticalTextView answerTaskNameTv;
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
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.answer_task_gu_zhu_layout)
    LinearLayout answerTaskGuZhuLayout;
    private TaskBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_answer_task;
    }

    @Override
    protected void init() {
        bean = (TaskBean) getIntent().getSerializableExtra("bean");
        setView();
    }

    public void setView() {
        titleTitleTv.setText(SetViewHelp.getType(bean.getType()));
        Glide.with(this).load(bean.getImg()).into(answerTaskImg);
        answerTaskTitleTv.setText(bean.getTitle());
        answerTaskCountTv.setText(bean.getScore() + "");
        answerTaskRenWuTv.setText(bean.getScore() + "");
        answerTaskHuiSuoTv.setText(bean.getGz_name());
        answerTaskTypeTv.setText(SetViewHelp.getType(bean.getType()));
        answerTaskShengyuTv.setText(bean.getShengyu_num() + "");
        answerTaskTimeTv.setText(bean.getEnd_time().substring(0, 10));
        answerTaskRenWuContentTv.setText(SetViewHelp.taskContent(bean.getTask_intro(), bean.getType()));
        SetViewHelp.guZhu(bean.getExhort(),answerTaskGuZhuLayout,answerTaskGuZhuContentTv);

        Integer share_score = bean.getShare_score();
        answerTaskZhengCountTv.setText(SetViewHelp.shareScores(share_score)+"");

        presenter.loadTaskIds(bean.getId());
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.answer_task_fen_xiang_btn, R.id.answer_task_ren_wu_btn, R.id.title_layout_return_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.answer_task_fen_xiang_btn:
                break;
            case R.id.answer_task_ren_wu_btn:
                Intent intent = SetViewHelp.getIntent(this, bean.getType());
//                Intent intent = new Intent(this, PhotoTaskActivity.class);
                intent.putExtra("bean",bean);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showTaskIds(List<TaskIdsBean.DataBean> data) {
        ArrayList<String> names = new ArrayList<>();
        for (TaskIdsBean.DataBean datum : data) {
            names.add(datum.getBroadcast_title());
        }
        answerTaskNameTv.setTextList(names);
        answerTaskNameTv.setText(12, 0, Color.parseColor("#999999"));//设置属性,具体跟踪源码
        answerTaskNameTv.setTextStillTime(3000);//设置停留时长间隔
        answerTaskNameTv.setAnimTime(1000);//设置进入和退出的时间间隔
        answerTaskNameTv.startAutoScroll();
    }

    @Override
    public void showTaskIdsError() {

    }
}
