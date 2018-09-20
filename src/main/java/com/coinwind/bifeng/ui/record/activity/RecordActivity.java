package com.coinwind.bifeng.ui.record.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.record.adapter.RecordAdapter;
import com.coinwind.bifeng.ui.record.bean.RecordBean;
import com.coinwind.bifeng.ui.record.contract.RecordContract;
import com.coinwind.bifeng.ui.record.presenter.RecordPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 矿产记录的页面
 */
public class RecordActivity extends BaseActivity<RecordPresenter> implements RecordContract.View, AbsListView.OnScrollListener {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.record_all_cc_tv)
    TextView recordAllCcTv;
    @BindView(R.id.record_recycler)
    ListView recordRecycler;
    private List<RecordBean.DataBean.CcLogBean.ListBean> list;
    private RecordAdapter recordAdapter;
    private int page = 1;
    private boolean loadFinishFlag = true;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record;
    }

    @Override
    protected void init() {
        list = new ArrayList<>();
        recordAdapter = new RecordAdapter(list, this);
        recordRecycler.setAdapter(recordAdapter);
        recordRecycler.setOnScrollListener(this);
    }

    @Override
    protected void loadDate() {
        presenter.loadRecord(page);
    }

    @OnClick(R.id.title_layout_return_btn)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showRecord(RecordBean.DataBean.CcLogBean ccLog) {
        recordAllCcTv.setText(ccLog.getAllcc() + "");
        List<RecordBean.DataBean.CcLogBean.ListBean> list = ccLog.getList();
        loadFinishFlag = true;
        this.list.addAll(list);
        if (list.size() == 0) {
            loadFinishFlag = false;
        }
        recordAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMsg) {
        ToastHelp.showShort(this, errorMsg);
    }

    @Override
    public void loginTimeOut() {
        SpHelp.loginOut();
        LoginActivity.openLoginActivity(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //获取屏幕最后Item的ID
        int lastVisibleItem = recordRecycler.getLastVisiblePosition();
        if (lastVisibleItem + 1 == totalItemCount) {
            if (loadFinishFlag) {
                //标志位，防止多次加载
                loadFinishFlag = false;
                page++;
                presenter.loadRecord(page);
            }
        }
    }
}
