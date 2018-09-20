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
import com.coinwind.bifeng.ui.my.bean.NewWalletBean;
import com.coinwind.bifeng.ui.my.contract.WalletContract;
import com.coinwind.bifeng.ui.my.presenter.WalletPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 钱包页面
 */
public class WalletActivity extends BaseActivity<WalletPresenter> implements WalletContract.View {


    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.wallet_yuan)
    TextView walletYuan;
    @BindView(R.id.wallet_shi_fang_tv)
    TextView walletShiFangTv;
    @BindView(R.id.wallet_all_cc_tv)
    TextView walletAllCcTv;
    @BindView(R.id.my_wallet_what_cc_btn)
    LinearLayout myWalletWhatCcBtn;
    @BindView(R.id.wallet_come_in_tv)
    TextView walletComeInTv;
    @BindView(R.id.wallet_come_in_line)
    View walletComeInLine;
    @BindView(R.id.wallet_come_in_btn)
    LinearLayout walletComeInBtn;
    @BindView(R.id.wallet_go_out_tv)
    TextView walletGoOutTv;
    @BindView(R.id.wallet_go_out_line)
    View walletGoOutLine;
    @BindView(R.id.wallet_go_out_btn)
    LinearLayout walletGoOutBtn;
    @BindView(R.id.my_wallet_list)
    ListView myWalletList;
    private WalletAdapter walletAdapter;

    private List<NewWalletBean.DataBean.CcLoginBean> ccLogin;
    private List<NewWalletBean.DataBean.CcLoginBean> ccLogout;
    private List<NewWalletBean.DataBean.CcLoginBean> showLists;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void init() {
        titleTitleTv.setText("资产管理");
        showLists = new ArrayList<>();
        ccLogin = new ArrayList<>();
        ccLogout = new ArrayList<>();
        walletAdapter = new WalletAdapter(showLists, this);
        myWalletList.setAdapter(walletAdapter);
    }

    @Override
    protected void loadDate() {
        presenter.loadWallet();
    }

    @OnClick({R.id.title_layout_return_btn, R.id.my_wallet_what_cc_btn, R.id.wallet_come_in_btn, R.id.wallet_go_out_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.my_wallet_what_cc_btn:
//                GuanYuActivity.openActivity(this, R.mipmap.cc_shuo_ming_icon);
                break;
            case R.id.wallet_come_in_btn:
                walletGoOutLine.setVisibility(View.INVISIBLE);
                walletComeInLine.setVisibility(View.VISIBLE);
                walletComeInTv.setTextColor(getResources().getColor(R.color.blue_095a));
                walletGoOutTv.setTextColor(getResources().getColor(R.color.black_333));
                //发送请求
                setData(ccLogin);
                break;
            case R.id.wallet_go_out_btn:
                walletGoOutLine.setVisibility(View.VISIBLE);
                walletComeInLine.setVisibility(View.INVISIBLE);
                walletComeInTv.setTextColor(getResources().getColor(R.color.black_333));
                walletGoOutTv.setTextColor(getResources().getColor(R.color.blue_095a));
                //发送请求
                setData(ccLogout);
                break;
        }
    }

    private void setData(List<NewWalletBean.DataBean.CcLoginBean> showLists) {
        this.showLists.clear();
        if (showLists != null && !showLists.isEmpty()) {
            this.showLists.addAll(showLists);
            walletAdapter.notifyDataSetChanged();
        }
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
    public void showWallet(NewWalletBean.DataBean data) {
        this.ccLogin.addAll(data.getCcLogin());
        this.ccLogout.addAll(data.getCcLogout());
        setData(ccLogin);
        walletAdapter.notifyDataSetChanged();
        walletShiFangTv.setText(data.getRelease_cc() + "");
        walletAllCcTv.setText(data.getCurrent_cc() + "");
        walletYuan.setText(data.getCurrent_yuan() + "");
    }
}
