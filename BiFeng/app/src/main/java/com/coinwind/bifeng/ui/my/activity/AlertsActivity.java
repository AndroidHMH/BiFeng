package com.coinwind.bifeng.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.my.adapter.AlertsAdapter;
import com.coinwind.bifeng.ui.my.bean.AlertsBean;
import com.coinwind.bifeng.ui.my.contract.AlertsContract;
import com.coinwind.bifeng.ui.my.presenter.AlertsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消息通知界面
 */
public class AlertsActivity extends BaseActivity<AlertsPresenter> implements AlertsContract.View {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    @BindView(R.id.alerts_recycler)
    RecyclerView alertsRecycler;
    private List<AlertsBean.DataBean> dataBeanList;
    private AlertsAdapter alertsAdapter;

    public static void openActivity(Context context) {
        Intent intent = new Intent(context, AlertsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_alerts;
    }

    @Override
    protected void init() {
        titleTitleTv.setText("消息通知");
        dataBeanList = new ArrayList<>();
        alertsRecycler.setLayoutManager(new LinearLayoutManager(this));
        alertsAdapter = new AlertsAdapter(dataBeanList);
        alertsRecycler.setAdapter(alertsAdapter);
    }

    @Override
    protected void loadDate() {
        presenter.loadAlerts();
    }

    @Override
    protected void hideErrorView() {
        alertsRecycler.setVisibility(View.GONE);
    }

    @Override
    protected void showSuccessView() {
        alertsRecycler.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.title_layout_return_btn)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showAlerts(List<AlertsBean.DataBean> dataBeanList) {
        this.dataBeanList.addAll(dataBeanList);
        alertsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void loginOut() {
        SpHelp.loginOut();
        LoginActivity.openLoginActivity(this);
    }
}
