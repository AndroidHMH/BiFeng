package com.coinwind.bifeng.ui.submittask.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.my.activity.WalletActivity;
import com.coinwind.bifeng.ui.submittask.adapter.SubmitDaTiAdapter;
import com.coinwind.bifeng.ui.submittask.bean.SendDaTiBean;
import com.coinwind.bifeng.ui.submittask.bean.SubmitDaTiBean;
import com.coinwind.bifeng.ui.submittask.contract.SubmitDaTiContract;
import com.coinwind.bifeng.ui.submittask.fragment.SubmitDaTiFragment;
import com.coinwind.bifeng.ui.submittask.presenter.SubmitDaTiPresenter;
import com.coinwind.bifeng.view.ViewPagerSlide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 提交答题任务界面
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
@SuppressLint("ResourceType")
public class SubmitDaTiActivity extends BaseActivity<SubmitDaTiPresenter> implements SubmitDaTiFragment.ClickItem, SubmitDaTiContract.View {

    @BindView(R.id.submit_da_ti_now_qid_tv)
    TextView submitDaTiNowQidTv;
    @BindView(R.id.submit_da_ti_all_qid_tv)
    TextView submitDaTiAllQidTv;
    @BindView(R.id.submit_da_ti_content_pager)
    ViewPagerSlide submitDaTiContentPager;
    @BindView(R.id.submit_da_ti_next_or_submit_btn)
    Button submitDaTiNextOrSubmitBtn;
    @BindView(R.id.submit_da_ti_right_layout)
    LinearLayout submitDaTiRightLayout;
    @BindView(R.id.submit_da_ti_right_tv)
    TextView submitDaTiRightTv;
    @BindView(R.id.submit_da_ti_all_layout)
    LinearLayout submitDaTiAllLayout;
    @BindView(R.id.submit_da_ti_results_tv)
    TextView submitDaTiResultsTv;
    @BindView(R.id.submit_da_ti_all_problem_tv)
    TextView submitDaTiAllProblemTv;
    @BindView(R.id.submit_da_ti_pass_num_problem_tv)
    TextView submitDaTiPassNumProblemTv;
    @BindView(R.id.submit_da_ti_right_problem_tv)
    TextView submitDaTiRightProblemTv;
    @BindView(R.id.submit_through_tv)
    TextView submitThroughTv;
    @BindView(R.id.submit_through_layout)
    LinearLayout submitThroughLayout;
    @BindView(R.id.submit_not_through_layout)
    TextView submitNotThroughLayout;
    @BindView(R.id.submit_da_ti_results_btn)
    TextView submitDaTiResultsBtn;
    @BindView(R.id.submit_da_ti_results_layout)
    LinearLayout submitDaTiResultsLayout;
    private TaskBean bean;
    private SubmitDaTiBean submitDaTiBean;
    private List<Fragment> fragments;

    private List<SendDaTiBean> list;
    private SendDaTiBean sendDaTiBean;
    private List<SubmitDaTiBean.QuestionBean> question;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit_da_ti;
    }

    @Override
    protected void init() {
        list = new ArrayList<>();
        fragments = new ArrayList<>();
        bean = (TaskBean) getIntent().getSerializableExtra("bean");
        submitDaTiBean = new Gson().fromJson(bean.getQues_content(), SubmitDaTiBean.class);
        submitDaTiAllQidTv.setText(submitDaTiBean.getTotalNum() + "");
        question = submitDaTiBean.getQuestion();
        for (SubmitDaTiBean.QuestionBean questionBean : question) {
            SubmitDaTiFragment submitDaTiFragment = new SubmitDaTiFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("item", questionBean);
            submitDaTiFragment.setArguments(bundle);
            submitDaTiFragment.setClickItem(this);
            fragments.add(submitDaTiFragment);
        }
        SubmitDaTiAdapter submitDaTiAdapter = new SubmitDaTiAdapter(getSupportFragmentManager(), fragments);
        submitDaTiContentPager.setAdapter(submitDaTiAdapter);
        submitDaTiContentPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                // arg0是当前选中的页面的Position
//                Log.e("SubmitDaTiActivity", "onPageSelected------>" + arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // arg0 :当前页面，及你点击滑动的页面；arg1:当前页面偏移的百分比；arg2:当前页面偏移的像素位置
//                Log.e("SubmitDaTiActivity", "onPageScrolled------>arg0：" + arg0 + "\nonPageScrolled------>arg1:" + arg1 + "\nonPageScrolled------>arg2:" + arg2);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                //arg0 ==1的时表示正在滑动，arg0==2的时表示滑动完毕了，arg0==0的时表示什么都没做。
                if (arg0 == 0) {
                    Log.e("SubmitDaTiActivity", "onPageScrollStateChanged------>0");
                    if (itemCount == submitDaTiBean.getTotalNum()) {
                        submitDaTiNowQidTv.setText(itemCount + "");
                        submitDaTiNextOrSubmitBtn.setText("提交");
                    } else {
                        submitDaTiNowQidTv.setText(itemCount + "");
                    }
                } else if (arg0 == 1) {
                    Log.e("SubmitDaTiActivity", "onPageScrollStateChanged------>1");
                } else if (arg0 == 2) {
                    Log.e("SubmitDaTiActivity", "onPageScrollStateChanged------>2");

                }

            }

        });

        sendDaTiBean = new SendDaTiBean();
    }

    @Override
    protected void loadDate() {

    }

    private int itemCount = 1;

    @OnClick({R.id.submit_da_ti_next_or_submit_btn, R.id.submit_da_ti_results_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit_da_ti_next_or_submit_btn:
                isFirst = true;
                list.add(sendDaTiBean);

                if ("提交".equals(submitDaTiNextOrSubmitBtn.getText().toString())) {
                    ToastHelp.showShort(this, "提交了任务");
                    for (SendDaTiBean daTiBean : list) {
                        LogHelp.e("SubmitDaTiActivity", daTiBean.toString());
                    }
                    presenter.sendTask(bean.getId(), list, bean.getType(), submitDaTiBean.getPassNum());
                } else {
                    sendDaTiBean = new SendDaTiBean();
                    submitDaTiContentPager.setCurrentItem(itemCount);
                    submitDaTiNextOrSubmitBtn.setEnabled(false);
                    submitDaTiNextOrSubmitBtn.setBackground(new ColorDrawable(Color.parseColor("#DEDEDE")));
                    ++itemCount;
                }
                break;
            case R.id.submit_da_ti_results_btn:
                //查看还是分享
                startActivity(new Intent(this, WalletActivity.class));
                finish();
                break;
        }

    }

    private boolean isFirst = true;

    @Override
    public void clickItem(String problem, String content, String right, String num) {
        String[] split = content.split("、");
        submitDaTiRightTv.setText(right);

        sendDaTiBean.setTimu_content(problem);
        sendDaTiBean.setRight_answer(right);
        sendDaTiBean.setNum(num);
        if (isFirst) {
            sendDaTiBean.setSub_answer(split[0]);
            sendDaTiBean.setIs_right(right.equals(split[0]) ? "1" : "0");
            isFirst = false;
        }
        if (right.equals(split[0])) {
            submitDaTiNextOrSubmitBtn.setEnabled(true);
            submitDaTiNextOrSubmitBtn.setBackground(getResources().getDrawable(R.drawable.submit_da_ti_btn_shape));
            submitDaTiRightLayout.setVisibility(View.GONE);
        } else {
            submitDaTiNextOrSubmitBtn.setEnabled(false);
            submitDaTiNextOrSubmitBtn.setBackground(new ColorDrawable(Color.parseColor("#DEDEDE")));
            submitDaTiRightLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void through(String num) {
        //通过
        setResultsView(num);

        submitThroughLayout.setVisibility(View.VISIBLE);
        submitNotThroughLayout.setVisibility(View.GONE);
        submitThroughTv.setText(bean.getScore() + "");

        submitDaTiResultsTv.setText(getResources().getString(R.string.submit_da_ti_through));
        submitDaTiResultsBtn.setText(getResources().getString(R.string.submit_da_ti_see));
    }


    @Override
    public void notThrough(String num) {
        //未通过
        setResultsView(num);

        submitNotThroughLayout.setVisibility(View.VISIBLE);
        submitThroughLayout.setVisibility(View.GONE);

        submitDaTiResultsTv.setText(getResources().getString(R.string.submit_da_ti_not_through));
        submitDaTiResultsBtn.setText(getResources().getString(R.string.submit_da_ti_share));
    }

    private void setResultsView(String num) {
        submitDaTiAllLayout.setVisibility(View.GONE);

        submitDaTiResultsLayout.setVisibility(View.VISIBLE);

        submitDaTiAllProblemTv.setText(question.size() + "");
        submitDaTiRightProblemTv.setText(num);
        submitDaTiPassNumProblemTv.setText(submitDaTiBean.getPassNum());
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
