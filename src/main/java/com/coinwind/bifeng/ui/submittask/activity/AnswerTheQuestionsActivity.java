package com.coinwind.bifeng.ui.submittask.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.sendtask.bean.DiaoYanBean;
import com.coinwind.bifeng.ui.submittask.adapter.AnswerTheQuestionsAdapter;
import com.coinwind.bifeng.ui.submittask.bean.AnswerTheQuestionsBean;
import com.coinwind.bifeng.ui.submittask.contract.AnswerTheQuestionContract;
import com.coinwind.bifeng.ui.submittask.presenter.AnswerTheQuestionPresenter;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 做调研任务的界面
 */
public class AnswerTheQuestionsActivity extends BaseActivity<AnswerTheQuestionPresenter> implements AnswerTheQuestionContract.View {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.answer_the_questions_recycler)
    RecyclerView answerTheQuestionsRecycler;
    @BindView(R.id.answer_the_questions_submit_btn)
    TextView answerTheQuestionsSubmitBtn;

    private List<DiaoYanBean> list;
    private TaskBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_answer_the_questions;
    }

    @Override
    protected void init() {
        bean = (TaskBean) getIntent().getSerializableExtra("bean");

        String ques_content = bean.getQues_content();
        AnswerTheQuestionsBean answerTheQuestionsBean = new Gson().fromJson(ques_content, AnswerTheQuestionsBean.class);

        list = answerTheQuestionsBean.getQuestion();
        AnswerTheQuestionsAdapter answerTheQuestionsAdapter = new AnswerTheQuestionsAdapter(list);

        answerTheQuestionsRecycler.setLayoutManager(new LinearLayoutManager(this));
        answerTheQuestionsRecycler.setAdapter(answerTheQuestionsAdapter);
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.title_layout_return_btn, R.id.answer_the_questions_submit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.answer_the_questions_submit_btn:
                presenter.sendTask(bean.getId(), getDate(), bean.getType());
                break;
        }
    }

    /**
     * 获取数据集合
     *
     * @return
     */
    private List<DiaoYanBean> getDate() {
        List<DiaoYanBean> list = new ArrayList<>();
        for (int i = 0; i < answerTheQuestionsRecycler.getChildCount(); i++) {
            LinearLayout childAt = (LinearLayout) answerTheQuestionsRecycler.getChildAt(i);

            TextView answerTheQuestionsRecyclerItemQidTv = (TextView) childAt.findViewById(R.id.answer_the_questions_recycler_item_qid_tv);
            TextView answerTheQuestionsRecyclerItemTitleTv = (TextView) childAt.findViewById(R.id.answer_the_questions_recycler_item_title_tv);
            EditText answerTheQuestionsRecyclerItemAnswerEt = (EditText) childAt.findViewById(R.id.answer_the_questions_recycler_item_answer_et);

            DiaoYanBean diaoYanBean = new DiaoYanBean();
            diaoYanBean.setNum(answerTheQuestionsRecyclerItemQidTv.getText().toString());
            diaoYanBean.setTitle(answerTheQuestionsRecyclerItemTitleTv.getText().toString());
            diaoYanBean.setTimu_content(answerTheQuestionsRecyclerItemAnswerEt.getText().toString().trim());

            list.add(diaoYanBean);
        }
        return list;
    }

    @Override
    public void sendSuccess(String msg) {
        ToastHelp.showShort(this, msg);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void sendFailure(String failureMsg) {
        ToastHelp.showShort(this, failureMsg);
    }

    @Override
    public void loginOut() {
        ToastHelp.showShort(this, "身份失效，请重新登录!");
        SpHelp.loginOut();
        LoginActivity.openLoginActivity(this);
    }
}
