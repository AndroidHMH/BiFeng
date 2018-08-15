package com.coinwind.bifeng.ui.sendtask.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.TimeUtils;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.sendtask.contract.SendTaskContract;
import com.coinwind.bifeng.ui.sendtask.presenter.SendTaskPresenter;

import org.feezu.liuli.timeselector.TimeSelector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发布任务的界面
 */
public class SendTaskActivity extends BaseActivity<SendTaskPresenter> implements SendTaskContract.View, TextWatcher {


    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.send_task_content_count_tv)
    TextView sendTaskContentCountTv;
    @BindView(R.id.send_fen_xiang_cc_et)
    EditText sendFenXiangCcEt;
    @BindView(R.id.send_all_fen_xiang_et)
    EditText sendAllFenXiangEt;
    @BindView(R.id.send_ren_wu_cc_tv)
    EditText sendRenWuCcTv;
    @BindView(R.id.send_all_ren_wu_et)
    EditText sendAllRenWuEt;
    @BindView(R.id.send_task_all_cc_count_tv)
    TextView sendTaskAllCcCountTv;
    @BindView(R.id.send_start_task_tv)
    TextView sendStartTaskTv;
    @BindView(R.id.send_task_start_task_btn)
    LinearLayout sendTaskStartTaskBtn;
    @BindView(R.id.send_task_end_time_tv)
    TextView sendTaskEndTimeTv;
    @BindView(R.id.send_task_end_time_btn)
    LinearLayout sendTaskEndTimeBtn;
    @BindView(R.id.send_task_yan_shou_btn)
    ImageView sendTaskYanShouBtn;
    @BindView(R.id.send_task_btn)
    LinearLayout sendTaskBtn;
    private TimeSelector timeSelector;
    private boolean isYan = false;
    private String needCheck;
    private int renWuCount;

    /**
     * 转发任务跳过来的
     *
     * @param context
     * @param taskTitle   任务标题
     * @param taskContent 任务正文
     * @param taskType    转发任务任务类型
     * @param label       标签
     * @param imgUrl      配图Url
     * @param type        任务类型
     */
    public static void zhuanFaOpenSendTaskActivity(Context context, String taskTitle, String taskContent, String taskType, String label, String imgUrl, String type) {
        Intent intent = new Intent(context, SendTaskActivity.class);
        intent.putExtra("taskTitle", taskTitle);
        intent.putExtra("taskContent", taskContent);
        intent.putExtra("taskType", taskType);
        intent.putExtra("label", label);
        intent.putExtra("imgUrl", imgUrl);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    /**
     * 注册(评论)任务跳过来的
     *
     * @param context
     * @param taskTitle
     * @param label
     * @param imgUrl
     * @param url       注册任务地址
     * @param type
     */
    public static void zhuCeOpenSendTaskActivity(Context context, String taskTitle, String imgUrl, String url, String label, String type) {
        Intent intent = new Intent(context, SendTaskActivity.class);
        intent.putExtra("taskTitle", taskTitle);
        intent.putExtra("url", url);
        intent.putExtra("label", label);
        intent.putExtra("imgUrl", imgUrl);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    /**
     * 涨粉任务跳过来的
     *
     * @param context
     * @param taskTitle
     * @param label
     * @param imgUrl
     * @param publicNum 涨粉任务公众号
     * @param type
     */
    public static void zhangFenOpenSendTaskActivity(Context context, String taskTitle, String imgUrl, String publicImg, String publicNum, String label, String type) {
        Intent intent = new Intent(context, SendTaskActivity.class);
        intent.putExtra("taskTitle", taskTitle);
        intent.putExtra("publicNum", publicNum);
        intent.putExtra("label", label);
        intent.putExtra("imgUrl", imgUrl);
        intent.putExtra("publicImg", publicImg);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_task;
    }

    @Override
    protected void init() {
        sendAllRenWuEt.addTextChangedListener(this);
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.title_layout_return_btn, R.id.send_task_start_task_btn, R.id.send_task_end_time_btn, R.id.send_task_yan_shou_btn,
            R.id.send_all_ren_wu_et, R.id.send_task_btn, R.id.send_fen_xiang_cc_et, R.id.send_all_fen_xiang_et, R.id.send_ren_wu_cc_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.send_task_start_task_btn:
                initTimeSelector(true);
                break;
            case R.id.send_task_end_time_btn:
                initTimeSelector(false);
                break;
            case R.id.send_task_yan_shou_btn:
                if (isYan) {
                    needCheck = "0";
                    sendTaskYanShouBtn.setImageResource(R.mipmap.yan_shou_unclick);
                } else {
                    needCheck = "1";
                    sendTaskYanShouBtn.setImageResource(R.mipmap.yan_shou_click);
                }
                isYan = !isYan;
                break;
            case R.id.send_fen_xiang_cc_et:
                sendFenXiangCcEt.setSelection(sendFenXiangCcEt.getText().length());
                break;
            case R.id.send_all_fen_xiang_et:
                sendAllFenXiangEt.setSelection(sendAllFenXiangEt.getText().length());
                break;
            case R.id.send_ren_wu_cc_tv:
                sendRenWuCcTv.setSelection(sendRenWuCcTv.getText().length());
                break;
            case R.id.send_all_ren_wu_et:
                if (presenter.isFenXiangCC(sendFenXiangCcEt) && presenter.isAllFenXiangCC(sendAllFenXiangEt) && presenter.isRenWuCC(sendRenWuCcTv)) {
                    sendAllRenWuEt.setFocusable(true);
                    sendAllRenWuEt.setCursorVisible(true);
                    sendAllRenWuEt.setFocusableInTouchMode(true);
                    sendAllRenWuEt.requestFocus();
                    sendAllRenWuEt.setSelection(sendAllRenWuEt.getText().length());
                }
                break;
            case R.id.send_task_btn:
                Intent intent = getIntent();

                String share_score = sendFenXiangCcEt.getText().toString().trim();
                String label = intent.getStringExtra("label");
                String type = intent.getStringExtra("type");
                String all_shareNum = sendAllFenXiangEt.getText().toString().trim();
                String score = sendRenWuCcTv.getText().toString().trim();
                String all_tasknum = sendAllRenWuEt.getText().toString().trim();
                String title = intent.getStringExtra("taskTitle");
                String img = intent.getStringExtra("imgUrl");
                String end_time = sendTaskEndTimeTv.getText().toString();
                String task_intro = "";
                String publicNum = "";//公众号
                String publicImg = "";//公众号二维码
                String url = "";//评论注册地址
                String content = intent.getStringExtra("taskContent");//转发正文
                String taskType = intent.getStringExtra("taskType");//任务类别，1项目动态,2新币上线,3技术周报,4最新公告,5热门活动,6品牌推广（转发任务需要）

                String startTime = sendStartTaskTv.getText().toString();


                presenter.sendTask(share_score, label, type, all_shareNum, score, all_tasknum, title, img, end_time, needCheck, task_intro, publicNum,
                        publicImg, url, content, taskType, "", "", startTime);
                break;
        }
    }

    /**
     * 初始化时间选择器
     *
     * @param startOrEnd true开始时间  false结束时间
     */
    private void initTimeSelector(final boolean startOrEnd) {
        long nowTime = System.currentTimeMillis();
        timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                if (startOrEnd) {
                    Toast.makeText(getApplicationContext(), "开始时间 ： " + time, Toast.LENGTH_LONG).show();
                    sendStartTaskTv.setText(time + ":00");
                } else {
                    Toast.makeText(getApplicationContext(), "结束时间 ： " + time, Toast.LENGTH_LONG).show();
                    sendTaskEndTimeTv.setText(time + ":00");
                }
                timeSelector.dismiss();
            }
        }, TimeUtils.long2String(nowTime), TimeUtils.long2String(nowTime + 31536000000l));
        timeSelector.show();
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

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // 输入的内容变化的监听
        LogHelp.e("sendtaskactivity", "输入中。。。。");
        renWuCount = getEtText(sendAllRenWuEt);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // 输入前的监听
        LogHelp.e("sendtaskactivity", "输入前。。。。。。。");

    }

    @Override
    public void afterTextChanged(Editable s) {
        // 输入后的监听
        LogHelp.e("sendtaskactivity", "输入后。。。。。。。。。。。");
        int fenXiangCC = getEtText(sendFenXiangCcEt);
        int fenXiangCount = getEtText(sendAllFenXiangEt);
        int renWuCC = getEtText(sendRenWuCcTv);

        sendTaskAllCcCountTv.setText(((fenXiangCC * fenXiangCount) + (renWuCC * renWuCount)) + "");
    }

    /**
     * 获取输入框中的内容转为数字
     *
     * @param editText
     * @return
     */
    private int getEtText(EditText editText) {
        String text = editText.getText().toString().trim();
        int num = 0;
        if ("".equals(text)) {
            return num;
        }
        try {
            num = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            num = 0;
            editText.setText("");
        }
        return num;
    }
}
