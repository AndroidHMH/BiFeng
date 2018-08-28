package com.coinwind.bifeng.ui.sendtask.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.sendtask.contract.SendDiaoYanContract;
import com.coinwind.bifeng.ui.sendtask.presenter.SendDiaoYanTaskPresenter;
import com.coinwind.bifeng.view.SendProblemView;

import butterknife.BindView;
import butterknife.OnClick;

public class SendDiaoYanTaskActivity extends BaseActivity<SendDiaoYanTaskPresenter> implements SendDiaoYanContract.View {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;


    @BindView(R.id.send_diao_yan_add_item_btn)
    RelativeLayout sendDiaoYanAddItemBtn;

    @BindView(R.id.send_diao_yan_problem)
    SendProblemView sendDiaoYanProblem;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_diao_yan_task;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadDate() {

    }


    @OnClick({R.id.title_layout_return_btn, R.id.send_diao_yan_add_item_btn, R.id.send_diao_yan_task_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.send_diao_yan_add_item_btn:
                sendDiaoYanProblem.addItemView();
                break;
            case R.id.send_diao_yan_task_btn:
                sendTask();
                break;
        }
    }

    private void sendTask() {
        Intent intent = getIntent();

        String label = intent.getStringExtra("label");
        String type = intent.getStringExtra("type");
        String taskTitle = intent.getStringExtra("taskTitle");
        String imgUrl = intent.getStringExtra("imgUrl");
        String taskContent = intent.getStringExtra("taskContent");
        String taskType = intent.getStringExtra("taskType");
        String publicNum = intent.getStringExtra("publicNum");
        String publicImg = intent.getStringExtra("publicImg");
        String share_score = intent.getStringExtra("share_score");
        String all_shareNum = intent.getStringExtra("all_shareNum");
        String score = intent.getStringExtra("score");
        String all_tasknum = intent.getStringExtra("all_tasknum");
        String end_time = intent.getStringExtra("end_time");
        String startTime = intent.getStringExtra("startTime");
        String task_intro = intent.getStringExtra("task_intro");
        String needCheck = intent.getStringExtra("needCheck");
        String url = intent.getStringExtra("url");

        presenter.sendTask(share_score, label, type, all_shareNum, score, all_tasknum, taskTitle, imgUrl, end_time, needCheck,
                task_intro, publicNum, publicImg, url, taskContent, taskType, sendDiaoYanProblem.getData(), startTime);
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
