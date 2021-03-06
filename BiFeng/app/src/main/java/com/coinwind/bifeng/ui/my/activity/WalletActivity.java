package com.coinwind.bifeng.ui.my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.my.adapter.WalletAdapter;
import com.coinwind.bifeng.ui.my.bean.WalletBean;
import com.coinwind.bifeng.ui.my.contract.WalletContract;
import com.coinwind.bifeng.ui.my.presenter.WalletPresenter;
import com.coinwind.bifeng.ui.setting.activity.GuanYuActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 钱包页面
 */
public class WalletActivity extends BaseActivity<WalletPresenter> implements WalletContract.View, AbsListView.OnScrollListener {

    @BindView(R.id.my_wallet_return_btn)
    LinearLayout myWalletReturnBtn;
    @BindView(R.id.my_wallet_today_cc_tv)
    TextView myWalletTodayCcTv;
    @BindView(R.id.my_wallet_all_cc_tv)
    TextView myWalletAllCcTv;
    @BindView(R.id.my_wallet_what_cc_btn)
    LinearLayout myWalletWhatCcBtn;
    @BindView(R.id.my_wallet_list)
    ListView myWalletList;

    private List<WalletBean.DataBean.BfCssLogBean.ListBean> dataBeans;
    private WalletAdapter walletAdapter;

    private int page = 1;
    private boolean loadFinishFlag = false;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void init() {
        dataBeans = new ArrayList<>();
        walletAdapter = new WalletAdapter(dataBeans, this);
        myWalletList.setAdapter(walletAdapter);
        myWalletList.setOnScrollListener(this);
    }

    @Override
    protected void loadDate() {
        presenter.loadRecord(SpHelp.getUserInformation(SpHelp.ID), page);
    }

    @OnClick({R.id.my_wallet_return_btn, R.id.my_wallet_what_cc_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_wallet_return_btn:
                finish();
                break;
            case R.id.my_wallet_what_cc_btn:
                GuanYuActivity.openActivity(this, R.mipmap.cc_shuo_ming_icon);
                break;
        }
    }

    @Override
    public void showSuccess(List<WalletBean.DataBean.BfCssLogBean.ListBean> dataBeans, int todayCC, int allCc) {
        loadFinishFlag = true;
        this.dataBeans.addAll(dataBeans);
        if (dataBeans.size() == 0) {
            loadFinishFlag = false;
            ToastHelp.showShort(this, "没有更多数据");
        }

        walletAdapter.notifyDataSetChanged();
        myWalletTodayCcTv.setText(todayCC + "");
        myWalletAllCcTv.setText(allCc + "");
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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //获取屏幕最后Item的ID
        int lastVisibleItem = myWalletList.getLastVisiblePosition();
        if (lastVisibleItem + 1 == totalItemCount) {
            if (loadFinishFlag) {
                //标志位，防止多次加载
                loadFinishFlag = false;
                page++;
                presenter.loadRecord(SpHelp.getUserInformation(SpHelp.ID), page);
            }
        }
    }
}
