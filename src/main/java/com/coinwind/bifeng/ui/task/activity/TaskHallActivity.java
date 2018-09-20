package com.coinwind.bifeng.ui.task.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.task.bean.TaskHallBean;
import com.coinwind.bifeng.ui.task.contract.TaskHallContract;
import com.coinwind.bifeng.ui.task.presenter.TaskHallPresenter;
import com.coinwind.bifeng.view.TiltTextView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 任务列表页面
 */
public class TaskHallActivity extends BaseActivity<TaskHallPresenter> implements TaskHallContract.View {

    @BindView(R.id.task_hall_banner)
    FlyBanner taskHallBanner;
    @BindView(R.id.task_hall_return_btn)
    ImageView taskHallReturnBtn;
    @BindView(R.id.task_hall_go_chuang_shi_task_btn)
    ImageView taskHallGoChuangShiTaskBtn;
    @BindView(R.id.task_hall_go_zhong_ji_task_btn)
    ImageView taskHallGoZhongJiTaskBtn;
    @BindView(R.id.task_hall_go_zhong_ji_task_tv)
    TextView taskHallGoZhongJiTaskTv;
    @BindView(R.id.task_hall_go_gao_ji_task_btn)
    ImageView taskHallGoGaoJiTaskBtn;
    @BindView(R.id.task_hall_go_gao_ji_task_tv)
    TextView taskHallGoGaoJiTaskTv;
    @BindView(R.id.task_hall_go_task_btn)
    ImageView taskHallGoTaskBtn;
    @BindView(R.id.task_hall_go_task_tv)
    TextView taskHallGoTaskTv;
    @BindView(R.id.task_hall_chuang_shi_jin_du_tv)
    TiltTextView taskHallChuangShiJinDuTv;
    @BindView(R.id.task_hall_chuang_shi_btn)
    RelativeLayout taskHallChuangShiBtn;
    @BindView(R.id.task_hall_zhong_ji_jin_du_tv)
    TiltTextView taskHallZhongJiJinDuTv;
    @BindView(R.id.task_hall_zhong_ji_btn)
    RelativeLayout taskHallZhongJiBtn;
    @BindView(R.id.task_hall_gao_ji_jin_du_tv)
    TiltTextView taskHallGaoJiJinDuTv;
    @BindView(R.id.task_hall_gao_ji_btn)
    RelativeLayout taskHallGaoJiBtn;
    @BindView(R.id.task_hall_chao_ji_jin_du_tv)
    TiltTextView taskHallChaoJiJinDuTv;
    @BindView(R.id.task_hall_chao_ji_btn)
    RelativeLayout taskHallChaoJiBtn;
    private List<String> bannerUrls;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_hall;
    }

    @Override
    protected void init() {
        bannerUrls = new ArrayList<>();

    }

    @Override
    protected void loadDate() {
        presenter.loadBanner();
        presenter.loadProgress();
    }

    @Override
    protected void onResume() {
        super.onResume();
        netWorkError();
    }

    @Override
    public void showBanner(List<HomeBannerBean.DataBean> banners) {
        if (!bannerUrls.isEmpty()) {
            bannerUrls.clear();
        }
        for (HomeBannerBean.DataBean bannerBean : banners) {
            bannerUrls.add(bannerBean.getUrl());
        }
        taskHallBanner.setImagesUrl(bannerUrls);
    }

    @Override
    public void showBannerError(List<Integer> defaultImgs) {
        taskHallBanner.setImages(defaultImgs);
    }

    @Override
    public void showProgress(TaskHallBean.DataBean dataBean) {
        taskHallChuangShiJinDuTv.setText(dataBean.getNewX() + "%");
        taskHallZhongJiJinDuTv.setText(dataBean.getMiddle() + "%");
        if (dataBean.getMiddle() >= 50) {
            taskHallZhongJiJinDuTv.setVisibility(View.GONE);
            taskHallGoZhongJiTaskBtn.setVisibility(View.VISIBLE);
        }
        taskHallGaoJiJinDuTv.setText(dataBean.getHigh() + "%");
        taskHallChaoJiJinDuTv.setText(dataBean.getSuperX() + "%");
    }

    @Override
    public void loginTimeOut() {
        SpHelp.loginOut();
        LoginActivity.openLoginActivity(this);
    }

    @Override
    public void showErrorProgress(String errorMsg) {

    }

    @OnClick({R.id.task_hall_return_btn, R.id.task_hall_chuang_shi_btn, R.id.task_hall_zhong_ji_btn, R.id.task_hall_gao_ji_btn, R.id.task_hall_chao_ji_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.task_hall_return_btn:
                finish();
                break;
            case R.id.task_hall_chuang_shi_btn:
                startActivity(new Intent(this, NewTaskActivity.class));
                break;
            case R.id.task_hall_zhong_ji_btn:
                int progress = Integer.parseInt(taskHallChuangShiJinDuTv.getText().toString().split("%")[0]);
                if (progress < 50)
                    ToastHelp.showShort(this, "需要创世任务完成度大于50");
                else
                    IntermediateTaskActivity.openIntermediateTaskActivity(this);
                break;
            case R.id.task_hall_gao_ji_btn:
                ToastHelp.showShort(this, "高级任务未开放");
                break;
            case R.id.task_hall_chao_ji_btn:
                ToastHelp.showShort(this, "超级任务未开放");
                break;
        }
    }
}
