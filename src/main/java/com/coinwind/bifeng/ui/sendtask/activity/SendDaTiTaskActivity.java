package com.coinwind.bifeng.ui.sendtask.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.sendtask.contract.SendDaTiContract;
import com.coinwind.bifeng.ui.sendtask.presenter.SendDaTiTaskPresenter;
import com.coinwind.bifeng.ui.sendtask.presenter.SendDiaoYanTaskPresenter;
import com.coinwind.bifeng.ui.task.bean.SendDaTiBean;
import com.coinwind.bifeng.view.SendDaTiView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SendDaTiTaskActivity extends BaseActivity<SendDaTiTaskPresenter> implements SendDaTiContract.View, View.OnClickListener {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.send_da_ti_layout)
    SendDaTiView sendDaTiLayout;
    @BindView(R.id.send_diao_yan_add_item_tv)
    TextView sendDiaoYanAddItemTv;
    @BindView(R.id.send_da_ti_task_psswnum_tv)
    TextView sendDaTiTaskPsswnumTv;
    @BindView(R.id.send_da_ti_task_psswnum_layout)
    LinearLayout sendDaTiTaskPsswnumLayout;
    @BindView(R.id.send_diao_yan_add_item_btn)
    RelativeLayout sendDiaoYanAddItemBtn;
    @BindView(R.id.send_diao_yan_task_btn)
    LinearLayout sendDiaoYanTaskBtn;
    private PopupWindow popupWindow;

    private String passNum = "1";

    public TextView sendDaTiTaskQualifiedPopup1;
    public TextView sendDaTiTaskQualifiedPopup2;
    public TextView sendDaTiTaskQualifiedPopup3;
    public TextView sendDaTiTaskQualifiedPopup4;
    public TextView sendDaTiTaskQualifiedPopup5;
    public TextView sendDaTiTaskQualifiedPopup6;
    public TextView sendDaTiTaskQualifiedPopup7;
    public TextView sendDaTiTaskQualifiedPopup8;
    public TextView sendDaTiTaskQualifiedPopup9;
    public TextView sendDaTiTaskQualifiedPopup10;
    public TextView sendDaTiTaskQualifiedReturn;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_send_da_ti_task);
//        ButterKnife.bind(this);
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_da_ti_task;
    }

    @Override
    protected void init() {
        initPopup();
    }


    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.title_layout_return_btn, R.id.send_diao_yan_add_item_btn, R.id.send_diao_yan_task_btn, R.id.send_da_ti_task_psswnum_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.send_da_ti_task_psswnum_layout:
                showPopup();
                break;
            case R.id.send_diao_yan_add_item_btn:
                sendDaTiLayout.addView();
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
                task_intro, publicNum, publicImg, url, taskContent, taskType, sendDaTiLayout.getDate(), passNum, startTime);
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


    private void initPopup() {
        View inflate = getLayoutInflater().inflate(R.layout.send_da_ti_task_qualified_popup, null);
        initViews(inflate);
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setOutsideTouchable(true);
    }

    private void initViews(View rootView) {
        sendDaTiTaskQualifiedPopup1 = (TextView) rootView.findViewById(R.id.send_da_ti_task_qualified_popup1);
        sendDaTiTaskQualifiedPopup2 = (TextView) rootView.findViewById(R.id.send_da_ti_task_qualified_popup2);
        sendDaTiTaskQualifiedPopup3 = (TextView) rootView.findViewById(R.id.send_da_ti_task_qualified_popup3);
        sendDaTiTaskQualifiedPopup4 = (TextView) rootView.findViewById(R.id.send_da_ti_task_qualified_popup4);
        sendDaTiTaskQualifiedPopup5 = (TextView) rootView.findViewById(R.id.send_da_ti_task_qualified_popup5);
        sendDaTiTaskQualifiedPopup6 = (TextView) rootView.findViewById(R.id.send_da_ti_task_qualified_popup6);
        sendDaTiTaskQualifiedPopup7 = (TextView) rootView.findViewById(R.id.send_da_ti_task_qualified_popup7);
        sendDaTiTaskQualifiedPopup8 = (TextView) rootView.findViewById(R.id.send_da_ti_task_qualified_popup8);
        sendDaTiTaskQualifiedPopup9 = (TextView) rootView.findViewById(R.id.send_da_ti_task_qualified_popup9);
        sendDaTiTaskQualifiedPopup10 = (TextView) rootView.findViewById(R.id.send_da_ti_task_qualified_popup10);
        sendDaTiTaskQualifiedReturn = (TextView) rootView.findViewById(R.id.send_da_ti_task_qualified_return);

        sendDaTiTaskQualifiedPopup1.setOnClickListener(this);
        sendDaTiTaskQualifiedPopup2.setOnClickListener(this);
        sendDaTiTaskQualifiedPopup3.setOnClickListener(this);
        sendDaTiTaskQualifiedPopup4.setOnClickListener(this);
        sendDaTiTaskQualifiedPopup5.setOnClickListener(this);
        sendDaTiTaskQualifiedPopup6.setOnClickListener(this);
        sendDaTiTaskQualifiedPopup7.setOnClickListener(this);
        sendDaTiTaskQualifiedPopup8.setOnClickListener(this);
        sendDaTiTaskQualifiedPopup9.setOnClickListener(this);
        sendDaTiTaskQualifiedPopup10.setOnClickListener(this);
        sendDaTiTaskQualifiedReturn.setOnClickListener(this);
    }

    private void showPopup() {
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_send_da_ti_task, null), Gravity.BOTTOM, 0, 0);
    }

    private void dismissPopup(String passNum) {
        this.passNum = passNum;
        sendDaTiTaskPsswnumTv.setText(passNum + "道题");
        popupWindow.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_da_ti_task_qualified_popup1:
                dismissPopup("1");
                break;
            case R.id.send_da_ti_task_qualified_popup2:
                dismissPopup("2");
                break;
            case R.id.send_da_ti_task_qualified_popup3:
                dismissPopup("3");
                break;
            case R.id.send_da_ti_task_qualified_popup4:
                dismissPopup("4");
                break;
            case R.id.send_da_ti_task_qualified_popup5:
                dismissPopup("5");
                break;
            case R.id.send_da_ti_task_qualified_popup6:
                dismissPopup("6");
                break;
            case R.id.send_da_ti_task_qualified_popup7:
                dismissPopup("7");
                break;
            case R.id.send_da_ti_task_qualified_popup8:
                dismissPopup("8");
                break;
            case R.id.send_da_ti_task_qualified_popup9:
                dismissPopup("9");
                break;
            case R.id.send_da_ti_task_qualified_popup10:
                dismissPopup("10");
                break;
            case R.id.send_da_ti_task_qualified_return:
                dismissPopup("1");
                break;
        }
    }
}
