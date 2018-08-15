package com.coinwind.bifeng.ui.task.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.DrawableContainer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coinwind.bifeng.R;
import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.ShareHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.task.bean.TaskIdsBean;
import com.coinwind.bifeng.ui.task.biz.ShareCallback;
import com.coinwind.bifeng.ui.task.config.SetViewHelp;
import com.coinwind.bifeng.ui.task.contract.AnswerTaskContract;
import com.coinwind.bifeng.ui.task.presenter.AnswerTaskPresenter;
import com.coinwind.bifeng.view.VerticalTextView;
import com.gyf.barlibrary.ImmersionBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 任务详情的页面
 */
public class AnswerTaskActivity extends BaseActivity<AnswerTaskPresenter> implements AnswerTaskContract.View, View.OnClickListener {


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
    @BindView(R.id.answer_task_all_layout)
    LinearLayout answerTaskAllLayout;
    @BindView(R.id.answer_task_ren_wu_tv)
    TextView answerTaskRenWuTv;
    @BindView(R.id.answer_task_ren_wu_btn)
    LinearLayout answerTaskRenWuBtn;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.answer_task_gu_zhu_layout)
    LinearLayout answerTaskGuZhuLayout;
    public ImageView share_img;
    public LinearLayout save_phone_btn;
    public LinearLayout share_wei_xin_btn;
    public LinearLayout share_peng_you_quan_btn;
    public TextView share_return_btn;


    private TaskBean bean;
    public static final int REQUEST_PERMISSION_CODE = 100;
    public static final int SHARE_WEIXIN_CIRCLE_PERMISSION_CODE = 300;
    public static final int SAVE_REQUEST_PERMISSION_CODE = 200;
    private PopupWindow popupWindow;
    private String imgUrl;
    private RelativeLayout share_popup_layout_btn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_answer_task;
    }

    @Override
    protected void init() {
        bean = (TaskBean) getIntent().getSerializableExtra("bean");
        setView();
        initPopup();
    }


    @Override
    protected void loadDate() {

    }

    @Override
    protected void hideErrorView() {
        answerTaskAllLayout.setVisibility(View.GONE);
    }

    @Override
    protected void showSuccessView() {
        answerTaskAllLayout.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.answer_task_fen_xiang_btn, R.id.answer_task_ren_wu_btn, R.id.title_layout_return_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.answer_task_fen_xiang_btn:
                if (SpHelp.getLoginStatus()) {
                    presenter.loadShareImg(bean.getId(), SpHelp.getUserInformation(SpHelp.ID));
                } else {
                    LoginActivity.openLoginActivity(this);
                }
                break;
            case R.id.answer_task_ren_wu_btn:
                Intent intent = SetViewHelp.getIntent(this, bean.getType());
                intent.putExtra("bean", bean);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_phone_btn:
                //保存到本地
                SetViewHelp.saveLocalPermission(this, SAVE_REQUEST_PERMISSION_CODE, new ShareCallback() {
                    @Override
                    public void share() {
                        presenter.loadImg(imgUrl);
                    }
                });
                break;
            case R.id.share_wei_xin_btn:
                SetViewHelp.applySharePermission(this, REQUEST_PERMISSION_CODE, new ShareCallback() {
                    @Override
                    public void share() {
                        ShareHelp.shareImage(AnswerTaskActivity.this, imgUrl);
                    }
                });
                dismissPopup();
                break;
            case R.id.share_peng_you_quan_btn:
                //分享朋友圈
                SetViewHelp.applySharePermission(this, SHARE_WEIXIN_CIRCLE_PERMISSION_CODE, new ShareCallback() {
                    @Override
                    public void share() {
                        ShareHelp.shareCircleImage(AnswerTaskActivity.this, imgUrl);
                    }
                });
                dismissPopup();
                break;
            case R.id.share_return_btn:
                dismissPopup();
                break;
            case R.id.share_popup_layout_btn:
                dismissPopup();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                SetViewHelp.sharePermissionResult(this, grantResults, new ShareCallback() {
                    @Override
                    public void share() {
                        ShareHelp.shareImage(AnswerTaskActivity.this, imgUrl);
                    }
                });
                break;
            case SHARE_WEIXIN_CIRCLE_PERMISSION_CODE:
                SetViewHelp.sharePermissionResult(this, grantResults, new ShareCallback() {
                    @Override
                    public void share() {
                        ShareHelp.shareCircleImage(AnswerTaskActivity.this, imgUrl);
                    }
                });
                break;
            case SAVE_REQUEST_PERMISSION_CODE:
                SetViewHelp.saveLocalPermissionResult(this, grantResults, new ShareCallback() {
                    @Override
                    public void share() {
                        presenter.loadImg(imgUrl);
                    }
                });
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

    @Override
    public void showSuccess(String imgUrl) {
        this.imgUrl = imgUrl;
        Glide.with(this).load(imgUrl).into(share_img);
        showPopup();
    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void showImgLoad() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dismissPopup();
                ToastHelp.showShort(AnswerTaskActivity.this, "下载成功");
            }
        });
    }

    @Override
    public void showImgError() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dismissPopup();
                ToastHelp.showShort(AnswerTaskActivity.this, "下载失败");
            }
        });
    }

    private void initPopup() {
        View inflate = getLayoutInflater().inflate(R.layout.share_popup_layout, null);
        initViews(inflate);
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        popupWindow.setOutsideTouchable(true);
    }

    private void initViews(View rootView) {
        this.share_img = (ImageView) rootView.findViewById(R.id.share_img);
        this.save_phone_btn = (LinearLayout) rootView.findViewById(R.id.save_phone_btn);
        this.share_wei_xin_btn = (LinearLayout) rootView.findViewById(R.id.share_wei_xin_btn);
        this.share_peng_you_quan_btn = (LinearLayout) rootView.findViewById(R.id.share_peng_you_quan_btn);
        this.share_return_btn = (TextView) rootView.findViewById(R.id.share_return_btn);
        share_popup_layout_btn = (RelativeLayout) rootView.findViewById(R.id.share_popup_layout_btn);

        save_phone_btn.setOnClickListener(this);
        share_wei_xin_btn.setOnClickListener(this);
        share_peng_you_quan_btn.setOnClickListener(this);
        share_return_btn.setOnClickListener(this);
        share_popup_layout_btn.setOnClickListener(this);
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
        SetViewHelp.guZhu(bean.getExhort(), answerTaskGuZhuLayout, answerTaskGuZhuContentTv);
        SetViewHelp.setTaskTypeIcon(answerTaskTypeImg, bean.getType());

        Integer share_score = bean.getShare_score();
        answerTaskZhengCountTv.setText(SetViewHelp.shareScores(share_score) + "");

        presenter.loadTaskIds(bean.getId());
    }

    private void showPopup() {
        popupWindow.setClippingEnabled(false);
        popupWindow.showAsDropDown(getLayoutInflater().inflate(R.layout.activity_answer_task, null));
    }

    private void dismissPopup() {
        popupWindow.dismiss();
    }

//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        popupWindow.dismiss();
//    }
}