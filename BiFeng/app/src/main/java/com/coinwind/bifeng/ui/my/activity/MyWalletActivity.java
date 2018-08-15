package com.coinwind.bifeng.ui.my.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.my.adapter.MyWalletAdapter;
import com.coinwind.bifeng.ui.my.adapter.MyWalletRecyclerAdapter;
import com.coinwind.bifeng.ui.my.bean.WalletBean;
import com.coinwind.bifeng.ui.my.contract.MyWalletContract;
import com.coinwind.bifeng.ui.my.presenter.MyWalletPresenter;
import com.coinwind.bifeng.ui.setting.activity.GuanYuActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的钱包页面
 */
public class MyWalletActivity extends BaseActivity<MyWalletPresenter> implements MyWalletContract.View {

    @BindView(R.id.my_wallet_return_btn)
    LinearLayout myWalletReturnBtn;
    @BindView(R.id.my_wallet_today_cc_tv)
    TextView myWalletTodayCcTv;
    @BindView(R.id.my_wallet_all_cc_tv)
    TextView myWalletAllCcTv;
    @BindView(R.id.my_wallet_what_cc_btn)
    LinearLayout myWalletWhatCcBtn;
    @BindView(R.id.my_wallet_recycler)
    ListView myWalletRecycler;
//            RecyclerView myWalletRecycler;

    private List<WalletBean.DataBean.BfCssLogBean.ListBean> dataBeans;
    private int page = 1;
    private MyWalletAdapter myWalletAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void init() {
        dataBeans = new ArrayList<>();

//        myWalletRecycler.setAdapter(myWalletAdapter);
    }

    @Override
    protected void loadDate() {
        presenter.loadRecord(SpHelp.getUserInformation(SpHelp.ID), page);
    }

    @Override
    protected void hideErrorView() {
        myWalletRecycler.setVisibility(View.GONE);
    }

    @Override
    protected void showSuccessView() {
        myWalletRecycler.setVisibility(View.GONE);
    }

    @OnClick({R.id.my_wallet_return_btn, R.id.my_wallet_what_cc_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_wallet_return_btn:
                finish();
                break;
            case R.id.my_wallet_what_cc_btn:
                //什么是cc
                GuanYuActivity.openActivity(this, R.mipmap.cc_shuo_ming_icon);
                break;
        }
    }

    @Override
    public void showSuccess(List<WalletBean.DataBean.BfCssLogBean.ListBean> dataBeans, int todayCC, int allCc) {
        this.dataBeans.addAll(dataBeans);
        myWalletAdapter = new MyWalletAdapter(this.dataBeans, this);
        myWalletRecycler.setAdapter(myWalletAdapter);
        myWalletTodayCcTv.setText(todayCC + "");
        myWalletAllCcTv.setText(allCc + "");
//        myWalletAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMsg) {
        ToastHelp.showShort(this, errorMsg);
    }

    @Override
    public void loginOut() {
        SpHelp.loginOut();
        LoginActivity.openLoginActivity(this);
    }
}
