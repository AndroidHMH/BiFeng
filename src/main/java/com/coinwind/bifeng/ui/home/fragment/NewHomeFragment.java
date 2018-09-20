package com.coinwind.bifeng.ui.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.base.BaseFragment;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.bindphonenumber.activity.BindPhoneNumberActivity;
import com.coinwind.bifeng.ui.home.bean.HomeInfoBean;
import com.coinwind.bifeng.ui.home.bean.HomeItemCCBean;
import com.coinwind.bifeng.ui.home.bean.HomeUserInfoBean;
import com.coinwind.bifeng.ui.home.contract.NewHomeContract;
import com.coinwind.bifeng.ui.home.presenter.NewHomePresenter;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;
import com.coinwind.bifeng.ui.homepage.bean.MessageEvent;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.record.activity.RecordActivity;
import com.coinwind.bifeng.ui.share.activity.InvitationActivity;
import com.coinwind.bifeng.ui.task.activity.DoNewTaskActivity;
import com.coinwind.bifeng.ui.task.activity.NewTaskActivity;
import com.coinwind.bifeng.ui.task.activity.TaskHallActivity;
import com.coinwind.bifeng.view.ClosedCCView;
import com.coinwind.bifeng.view.homeview.MarqueeTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.coinwind.bifeng.config.Codes.OVERDUE_CODE;
import static com.coinwind.bifeng.config.Codes.OVERDUE_RESULT_CODE;
import static com.coinwind.bifeng.ui.task.activity.DoNewTaskActivity.PHONE_SUCCESS_CODE;
import static com.coinwind.bifeng.ui.task.activity.NewTaskActivity.PHONE_REQUEST_CODE;

/**
 * 新的首页
 */
public class NewHomeFragment extends BaseFragment<NewHomePresenter> implements NewHomeContract.View {

    @BindView(R.id.new_home_all_cc_count_tv)
    TextView newHomeAllCcCountTv;
    @BindView(R.id.new_home_now_user_count_tv)
    TextView newHomeNowUserCountTv;
    @BindView(R.id.new_home_now_jin_du_count_tv)
    TextView newHomeNowJinDuCountTv;
    @BindView(R.id.new_home_now_feng_su_tv)
    TextView newHomeNowFengSuTv;
    @BindView(R.id.new_home_closed_cc_view)
    ClosedCCView newHomeClosedCcView;
    @BindView(R.id.new_home_kuang_chan_btn)
    LinearLayout newHomeKuangChanBtn;
    @BindView(R.id.new_home_suan_li_btn)
    LinearLayout newHomeSuanLiBtn;
    @BindView(R.id.new_home_yao_qing_btn)
    LinearLayout newHomeYaoQingBtn;
    @BindView(R.id.home_guang_bo_content_tv)
    TextView homeGuangBoContentTv;
    @BindView(R.id.new_home_do_recommended_task_btn)
    LinearLayout newHomeDoRecommendedBtn;
    @BindView(R.id.new_home_do_recommended_task_count_tv)
    TextView newHomeDoRecommendedTaskCountTv;
    @BindView(R.id.new_home_do_recommended_task_all_count_tv)
    TextView newHomeDoRecommendedTaskAllCountTv;
    @BindView(R.id.new_home_ren_min_bi_count_tv)
    TextView newHomeRenMinBiCountTv;
    @BindView(R.id.new_home_do_task_btn)
    Button newHomeDoTaskBtn;
    private List<HomeItemCCBean.DataBean.CcListBean> listBeans;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_home;
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        listBeans = new ArrayList<>();
        newHomeClosedCcView.setItemClick(new ClosedCCView.ItemClick() {
            @Override
            public void itemClick(HomeItemCCBean.DataBean.CcListBean listBean) {
                presenter.receiveCC(listBean.getCc() + "", listBean.getBuild_time() + "");
            }
        });
    }

    @Override
    protected void loadDate() {
        presenter.loadBroadcast();
        presenter.loadHomeInfo();
        presenter.loadHomeUserInfo();
        presenter.loadCCList();
    }

    @OnClick({R.id.new_home_kuang_chan_btn, R.id.new_home_suan_li_btn, R.id.new_home_yao_qing_btn, R.id.new_home_do_task_btn, R.id.new_home_do_recommended_task_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.new_home_kuang_chan_btn:
                startActivity(new Intent(getContext(), RecordActivity.class));
                break;
            case R.id.new_home_suan_li_btn:
                startActivity(new Intent(getContext(), NewTaskActivity.class));
                break;
            case R.id.new_home_yao_qing_btn:
                if (SpHelp.getIsVisit() == 0) {
                    startActivity(new Intent(getContext(), InvitationActivity.class));
                } else {
                    Intent bindIntent = new Intent(getActivity(), BindPhoneNumberActivity.class);
                    startActivityForResult(bindIntent, PHONE_REQUEST_CODE);
                    ToastHelp.showShort(getContext(), "您当前身份为游客，无法邀请好友");
                }
                break;
            case R.id.new_home_do_recommended_task_btn:
                //推荐任务

                break;
            case R.id.new_home_do_task_btn:
                startActivity(new Intent(getContext(), TaskHallActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHONE_REQUEST_CODE) {
            if (resultCode == PHONE_SUCCESS_CODE) {//绑定手机号成功
                ToastHelp.showShort(getContext(), "绑定手机号成功");
                MainActivity activity = (MainActivity) getActivity();
                activity.reLoadFragView(R.id.main_layout, NewHomeFragment.class);
            }
        } else if (requestCode == OVERDUE_CODE && resultCode == OVERDUE_RESULT_CODE) {
            BFApplication.context = getContext();
            netWorkError();
        }
    }

    @Override
    public void showBroadcast(String content) {
        homeGuangBoContentTv.setText(content);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(String type) {
        if (type != null && "refresh".equals(type)) {
            netWorkError();
        }
    }


    @Override
    public void showHomeInfo(HomeInfoBean.DataBean dataBean) {
        newHomeAllCcCountTv.setText(dataBean.getOne_num() + "");
        newHomeNowUserCountTv.setText(dataBean.getUser_num() + "");
        newHomeNowJinDuCountTv.setText(dataBean.getProcess() + "");
    }

    @Override
    public void showError(String errorMsg) {
        ToastHelp.showLong(getContext(), errorMsg);
    }

    @Override
    public void loginTimeOut() {
        SpHelp.loginOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra("refresh", "refresh");
        startActivityForResult(intent, OVERDUE_CODE);
    }

    @Override
    public void showUserInfo(HomeUserInfoBean.DataBean dataBean) {
        BFApplication.context = getContext();
        SpHelp.putIsVisit(dataBean.getIs_visit());

        newHomeDoRecommendedTaskCountTv.setText(dataBean.getDone_num() + "");
        newHomeDoRecommendedTaskAllCountTv.setText("/" + dataBean.getAllTopCount());
        newHomeNowFengSuTv.setText(dataBean.getC_power() + "");
        newHomeRenMinBiCountTv.setText(dataBean.getToday_money() + "");
    }

    @Override
    public void showCCList(List<HomeItemCCBean.DataBean.CcListBean> listBeans) {
        if (!this.listBeans.isEmpty()) {
            this.listBeans.clear();
        }
        this.listBeans.addAll(listBeans);
        LogHelp.e("NewHomeFragment", "cc集合的大小   =   " + this.listBeans.size());

        List<HomeItemCCBean.DataBean.CcListBean> clickList = new ArrayList<>();
        List<HomeItemCCBean.DataBean.CcListBean> unClickList = new ArrayList<>();

        for (int i = 0; i < this.listBeans.size(); i++) {
            HomeItemCCBean.DataBean.CcListBean ccListBean = this.listBeans.get(i);
            int djs = ccListBean.getDjs();
            if (djs == 0) {
                clickList.add(ccListBean);
            } else {
                unClickList.add(ccListBean);
            }
        }
        LogHelp.d("NewHomeFragment", "点击集合的大小   =   " + clickList.size());

        List<List<HomeItemCCBean.DataBean.CcListBean>> clickLists = new ArrayList<>();
        int clickListCount = clickList.size() / 10;
        clickListCount = clickList.size() % 10 == 0 ? clickListCount : clickListCount + 1;
        if (clickListCount == 1 && clickList.size() < 10) {
            List<HomeItemCCBean.DataBean.CcListBean> list = new ArrayList<>();
            for (HomeItemCCBean.DataBean.CcListBean ccListBean : clickList) {
                list.add(ccListBean);
            }
            clickLists.add(list);
        } else if (clickListCount == 1 && clickList.size() == 10) {
            List<HomeItemCCBean.DataBean.CcListBean> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(clickList.get(i));
            }
            clickLists.add(list);
        } else {
            int index = 0;
            for (int i = 0; i < clickListCount; i++) {
                List<HomeItemCCBean.DataBean.CcListBean> list = new ArrayList<>();
                if (i == clickListCount - 1) {
                    for (int j = index; j < clickList.size() % 10; j++, index++) {
                        list.add(clickList.get(index));
                    }
                } else {
                    int count = index;
                    LogHelp.d("NewHomeFragment", "count   =   " + count);

                    for (int j = index; j < count + 10; j++, index++) {
                        list.add(clickList.get(index));
                    }
                }
                clickLists.add(list);
            }
        }

        newHomeClosedCcView.setList(clickLists, unClickList);
    }

    @Override
    public void showReceiveCCResult(String msg) {
        ToastHelp.showShort(getContext(), msg);
        presenter.loadHomeUserInfo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
