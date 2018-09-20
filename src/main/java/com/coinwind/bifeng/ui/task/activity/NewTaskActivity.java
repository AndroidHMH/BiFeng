package com.coinwind.bifeng.ui.task.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.bindphonenumber.activity.BindPhoneNumberActivity;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.setting.activity.ChangeHeadImgActivity;
import com.coinwind.bifeng.ui.showh5.activity.ShowWebViewActivity;
import com.coinwind.bifeng.ui.task.adapter.NewTaskAdapter;
import com.coinwind.bifeng.ui.task.bean.NewTaskBean;
import com.coinwind.bifeng.ui.task.contract.NewTaskContract;
import com.coinwind.bifeng.ui.task.presenter.NewTaskPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.coinwind.bifeng.ui.setting.activity.ChangeHeadImgActivity.HEAD_IMG_SUCCESS;
import static com.coinwind.bifeng.ui.task.activity.DoNewTaskActivity.INVITE_SUCCESS;
import static com.coinwind.bifeng.ui.task.activity.DoNewTaskActivity.NAME_SUCCESS;
import static com.coinwind.bifeng.ui.task.activity.DoNewTaskActivity.PHONE_SUCCESS_CODE;
import static com.coinwind.bifeng.ui.task.activity.DoNewTaskActivity.PHONE_SUCCESS_LOGIN_CODE;

/**
 * 新手任务页面
 * 页面列表item ------------    new_task_recycler_item
 */
public class NewTaskActivity extends BaseActivity<NewTaskPresenter> implements NewTaskContract.View {

    @BindView(R.id.new_task_return_btn)
    ImageView newTaskReturnBtn;
    @BindView(R.id.new_task_record_btn)
    TextView newTaskRecordBtn;
    @BindView(R.id.new_task_now_feng_li_tv)
    TextView newTaskNowFengLiTv;
    @BindView(R.id.new_task_now_leading_tv)
    TextView newTaskNowLeadingTv;
    @BindView(R.id.new_task_will_do_recycler)
    RecyclerView newTaskWillDoRecycler;
    @BindView(R.id.new_task_hight_li_recycler)
    RecyclerView newTaskHightLiRecycler;
    @BindView(R.id.new_task_gao_layout)
    LinearLayout newTaskGaoLayout;
    @BindView(R.id.new_task_gao_head_layout)
    LinearLayout newTaskGaoHeadLayout;
    @BindView(R.id.new_task_ji_chu_feng_li_tv)
    TextView newTaskJiChuFengLiTv;
    @BindView(R.id.new_task_feng_su_tv)
    TextView newTaskFengSuTv;
    @BindView(R.id.new_task_feng_li_ji_lu_btn)
    LinearLayout newTaskFengLiJiLuBtn;
    @BindView(R.id.show_move_btn)
    LinearLayout showMoveBtn;
    @BindView(R.id.show_height_move_btn)
    LinearLayout showHeightMoveBtn;
    private List<NewTaskBean.DataBean.MustDataBean> newList;
    private List<NewTaskBean.DataBean.MustDataBean> heightList;
    private NewTaskAdapter newTaskAdapter;
    private NewTaskAdapter heightTaskAdapter;
    private List<NewTaskBean.DataBean.MustDataBean> newTaskList;
    private List<NewTaskBean.DataBean.MustDataBean> heightTaskList;

    public static final int PHONE_REQUEST_CODE = 100;
    public static final int WALL_REQUEST_CODE = 200;
    public static final int NAME_REQUEST_CODE = 300;
    public static final int PUBLIC_REQUEST_CODE = 400;
    public static final int HEAD_IMG_REQUEST_CODE = 500;
    public static final int STRATEGY_REQUEST_CODE = 600;
    public static final int SINVITE_CODE_REQUEST_CODE = 700;
    private int position;
    private int heightPosition;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_task;
    }

    @Override
    protected void init() {
        newList = new ArrayList<>();
        heightList = new ArrayList<>();
        newTaskWillDoRecycler.setLayoutManager(new LinearLayoutManager(this));
        newTaskHightLiRecycler.setLayoutManager(new LinearLayoutManager(this));
        newTaskWillDoRecycler.setNestedScrollingEnabled(false);
        newTaskHightLiRecycler.setNestedScrollingEnabled(false);

        newTaskAdapter = new NewTaskAdapter(newList);
        newTaskWillDoRecycler.setAdapter(newTaskAdapter);
        newTaskAdapter.setOnItemClickListener(new NewTaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                NewTaskActivity.this.position = position;
                NewTaskBean.DataBean.MustDataBean mustDataBean = newList.get(position);
                if (mustDataBean.getIs_use() == 1) {
                    int type = mustDataBean.getType();
                    int is_done = mustDataBean.getIs_done();
                    if (is_done == 0) {
                        if ((newList.get(0).getIs_done() == 0) && type != 1) {
                            ToastHelp.showShort(NewTaskActivity.this, "请先绑定手机号");
                        } else {
                            switch (type) {
                                case 1://绑定手机号
                                    Intent bindIntent = new Intent(NewTaskActivity.this, BindPhoneNumberActivity.class);
                                    startActivityForResult(bindIntent, PHONE_REQUEST_CODE);
                                    break;
                                case 2:
                                    //中级任务教程

                                    break;
                                case 3://昵称
                                    DoNewTaskActivity.bindPhoneOrSetUpNickNameOrPublicNum(NewTaskActivity.this, mustDataBean.getUrl(),
                                            SpHelp.getUserInformation(SpHelp.ID), SpHelp.getSign(), mustDataBean.getId(), SpHelp.getAndroidId(), NAME_REQUEST_CODE);
                                    break;
                                case 4://更换头像
                                    Intent intent = new Intent(NewTaskActivity.this, ChangeHeadImgActivity.class);
                                    intent.putExtra("where", "NewTaskActivity");
                                    intent.putExtra("taskId", mustDataBean.getId());
                                    startActivityForResult(intent, HEAD_IMG_REQUEST_CODE);
                                    break;
                                case 5://生成钱包
                                    DoNewTaskActivity.bindWallet(NewTaskActivity.this, mustDataBean.getUrl(), SpHelp.getUserInformation(SpHelp.ID),
                                            SpHelp.getSign(), mustDataBean.getId(), SpHelp.getAndroidId(), SpHelp.getUserInformation(SpHelp.HEAD_IMG), WALL_REQUEST_CODE);
                                    break;
                            }
                        }
                    } else {
                        ToastHelp.showShort(NewTaskActivity.this, "您已做过该任务");
                    }
                } else {
                    ToastHelp.showShort(NewTaskActivity.this, "该任务未开放");
                }
            }
        });
        heightTaskAdapter = new NewTaskAdapter(heightList);
        newTaskHightLiRecycler.setAdapter(heightTaskAdapter);
        heightTaskAdapter.setOnItemClickListener(new NewTaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                NewTaskBean.DataBean.MustDataBean mustDataBean = heightList.get(position);
                //高风速任务
                heightPosition = position;
                if (mustDataBean.getIs_use() == 1) {
                    int is_done = mustDataBean.getIs_done();
                    if (is_done == 0) {
                        if ((newList.get(0).getIs_done() == 0)) {
                            ToastHelp.showShort(NewTaskActivity.this, "请先绑定手机号");
                        } else {
                            int type = mustDataBean.getType();
                            switch (type) {
                                case 6://关注公众号
                                    DoNewTaskActivity.bindPhoneOrSetUpNickNameOrPublicNum(NewTaskActivity.this, mustDataBean.getUrl(), SpHelp.getUserInformation(SpHelp.ID),
                                            SpHelp.getSign(), mustDataBean.getId(), SpHelp.getAndroidId(), PUBLIC_REQUEST_CODE);
                                    break;
                                case 10:
                                    //创世攻略
                                    DoNewTaskActivity.bindPhoneOrSetUpNickNameOrPublicNum(NewTaskActivity.this, mustDataBean.getUrl(), SpHelp.getUserInformation(SpHelp.ID),
                                            SpHelp.getSign(), mustDataBean.getId(), SpHelp.getAndroidId(), STRATEGY_REQUEST_CODE);
                                    break;
                                case 11://提交邀请码
                                    DoNewTaskActivity.bindPhoneOrSetUpNickNameOrPublicNum(NewTaskActivity.this, mustDataBean.getUrl(), SpHelp.getUserInformation(SpHelp.ID),
                                            SpHelp.getSign(), mustDataBean.getId(), SpHelp.getAndroidId(), SINVITE_CODE_REQUEST_CODE);
                                    break;
                            }
                        }
                    } else {
                        ToastHelp.showShort(NewTaskActivity.this, "您已做过该任务");
                    }
                } else {
                    ToastHelp.showShort(NewTaskActivity.this, "该任务未开放");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHONE_REQUEST_CODE) {//绑定手机回调
            if (PHONE_SUCCESS_LOGIN_CODE == resultCode) {
                LoginActivity.openLoginActivity(this);
            }
            if (resultCode == PHONE_SUCCESS_CODE) {
                BFApplication.context = this;
                String userId = data.getStringExtra("userId");
                String sign = data.getStringExtra("sign");
                SpHelp.putUserInformation(SpHelp.ID, userId);
                SpHelp.putSign(sign);
                //更新ui
                ToastHelp.showShort(this, "绑定手机号成功");
                changeUI();
                SpHelp.putIsVisit(0);
            }

        } else if (requestCode == NAME_REQUEST_CODE && resultCode == NAME_SUCCESS) {//设置昵称回调
            ToastHelp.showShort(this, "昵称成功");
            changeUI();
        } else if (requestCode == HEAD_IMG_REQUEST_CODE) {//上传头像回调
            if (resultCode == HEAD_IMG_SUCCESS) {
                ToastHelp.showShort(this, "上传头像成功");
                changeUI();
            } else
                ToastHelp.showShort(this, "上传头像失败");
        } else if (requestCode == PUBLIC_REQUEST_CODE && resultCode == NAME_SUCCESS) {//关注公众号回调

        } else if (requestCode == SINVITE_CODE_REQUEST_CODE && resultCode == INVITE_SUCCESS) {//提交邀请码回调
            ToastHelp.showShort(this, "提交邀请码成功");
            changeUI();
        }

    }

    private void changeUI() {
        BFApplication.context = this;
        netWorkError();
    }

    @Override
    protected void loadDate() {
        presenter.loadNewTaskList();
    }

    @OnClick({R.id.new_task_return_btn, R.id.new_task_record_btn, R.id.new_task_feng_li_ji_lu_btn, R.id.show_move_btn, R.id.show_height_move_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.new_task_return_btn:
                finish();
                break;
            case R.id.new_task_record_btn:
                //创世攻略
                ShowWebViewActivity.openActivity(this, "https://m.coinwind.com/share/chuangshi.html", "创世攻略");
                break;
            case R.id.new_task_feng_li_ji_lu_btn:
                //风力记录
                break;
            case R.id.show_move_btn:
                //显示更多
                if (newList != null && !newList.isEmpty()) {
                    for (int i = 5; i < newTaskList.size(); i++) {
                        newList.add(newTaskList.get(i));
                    }
                }
                newTaskAdapter.notifyDataSetChanged();
                showMoveBtn.setVisibility(View.GONE);
                break;
            case R.id.show_height_move_btn:
                //显示更多
                if (newList != null && !newList.isEmpty()) {
                    for (int i = 2; i < heightTaskList.size(); i++) {
                        heightList.add(heightTaskList.get(i));
                    }
                }
                heightTaskAdapter.notifyDataSetChanged();
                showHeightMoveBtn.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void loginTimeOut() {
        SpHelp.loginOut();
        LoginActivity.openLoginActivity(this);
    }

    @Override
    public void showError(String errorMsg) {
        ToastHelp.showShort(this, errorMsg);
    }

    @Override
    public void showNewTaskList(NewTaskBean.DataBean data) {
        newTaskNowFengLiTv.setText(data.getC_power() + "");
        newTaskFengSuTv.setText(data.getC_speed() + "");

        List<NewTaskBean.DataBean.MustDataBean> mustData = data.getMustData();
        this.newTaskList = mustData;
        for (int i = 0; i < 5; i++) {
            newList.add(newTaskList.get(i));
        }
        newTaskAdapter.notifyDataSetChanged();

        List<NewTaskBean.DataBean.MustDataBean> choiceDate = data.getChoiceDate();
        heightTaskList = choiceDate;
        for (int i = 0; i < 2; i++) {
            heightList.add(heightTaskList.get(i));
        }
        heightTaskAdapter.notifyDataSetChanged();
    }
}
