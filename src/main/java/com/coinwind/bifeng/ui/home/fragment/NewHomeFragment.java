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
import com.coinwind.bifeng.base.BaseFragment;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.home.bean.HomeInfoBean;
import com.coinwind.bifeng.ui.home.bean.HomeItemCCBean;
import com.coinwind.bifeng.ui.home.bean.HomeUserInfoBean;
import com.coinwind.bifeng.ui.home.contract.NewHomeContract;
import com.coinwind.bifeng.ui.home.presenter.NewHomePresenter;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.share.activity.InvitationActivity;
import com.coinwind.bifeng.ui.task.activity.NewTaskActivity;
import com.coinwind.bifeng.ui.task.activity.TaskHallActivity;
import com.coinwind.bifeng.view.ClosedCCView;
import com.coinwind.bifeng.view.homeview.MarqueeTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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

    public static void refresh(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("refresh", "refresh");
        context.startActivity(intent);
//        netWorkError();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_home;
    }

    @Override
    protected void init() {
        listBeans = new ArrayList<>();
        newHomeClosedCcView.setItemClick(new ClosedCCView.ItemClick() {
            @Override
            public void itemClick(HomeItemCCBean.DataBean.CcListBean listBean) {
                presenter.receiveCC(listBean.getCc() + "", listBean.getBuild_time() + "");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        String refresh = getActivity().getIntent().getStringExtra("refresh");
//        if (refresh != null && !"".equals(refresh)) {
//        netWorkError();
//        }
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
//                List<Float> list = new ArrayList<>();
//                list.add(0.001f);
//                list.add(0.12423f);
//                list.add(1.0000467f);
//                list.add(0.0000002445f);
//                list.add(0.65432345654f);
//                newHomeClosedCcView.setList(list);
                break;
            case R.id.new_home_suan_li_btn:
                startActivity(new Intent(getContext(), NewTaskActivity.class));
                break;
            case R.id.new_home_yao_qing_btn:
                if (SpHelp.getIsVisit() == 0) {
                    startActivity(new Intent(getContext(), InvitationActivity.class));
                } else {
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
    public void showBroadcast(String content) {
        Log.e("NewHomeFragment", content);
//        homeGuangBoContentTv.addViewInQueue(content);
//        homeGuangBoContentTv.setScrollSpeed(50);
//        homeGuangBoContentTv.setScrollDirection(MarqueeTextView.RIGHT_TO_LEFT);
        homeGuangBoContentTv.setText(content);
//        homeGuangBoContentTv.startScroll();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
//            netWorkError();
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
        LoginActivity.openLoginActivity(getContext());
    }

    @Override
    public void showUserInfo(HomeUserInfoBean.DataBean dataBean) {
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

        List<HomeItemCCBean.DataBean.CcListBean> clickList = new ArrayList<>();
        List<HomeItemCCBean.DataBean.CcListBean> unClickList = new ArrayList<>();
        for (HomeItemCCBean.DataBean.CcListBean listBean : this.listBeans) {
            if (listBean.getDjs() == 0) {
                clickList.add(listBean);
            } else {
                unClickList.add(listBean);
            }
        }

        List<List<HomeItemCCBean.DataBean.CcListBean>> clickLists = new ArrayList<>();
        int clickListCount = clickList.size() / 10;
        clickListCount = clickList.size() % 10 == 0 ? clickListCount : clickListCount + 1;
        int index = 0;
        for (int i = 0; i < clickListCount; i++) {
            List<HomeItemCCBean.DataBean.CcListBean> list = new ArrayList<>();
            if (clickListCount == 1 && clickList.size() < 10) {
                for (HomeItemCCBean.DataBean.CcListBean ccListBean : clickList) {
                    list.add(ccListBean);
                }
            } else {
                for (int j = index; j < 10; j++, index++) {
                    list.add(clickList.get(index));
                }
            }

            clickLists.add(list);
        }

//        newHomeClosedCcView.setList(listBeans);
        newHomeClosedCcView.setList(clickLists, unClickList);
    }

    @Override
    public void showReceiveCCResult(String msg) {
        ToastHelp.showShort(getContext(), msg);
    }
}
