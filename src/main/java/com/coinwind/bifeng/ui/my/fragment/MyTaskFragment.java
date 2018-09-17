package com.coinwind.bifeng.ui.my.fragment;


import android.media.ExifInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseFragment;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.ToastHelp;
import com.coinwind.bifeng.ui.home.adapter.HomeAdapter;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.my.adapter.NewMyTaskAdapter;
import com.coinwind.bifeng.ui.my.bean.MyTaskBean;
import com.coinwind.bifeng.ui.my.contract.MyTaskContract;
import com.coinwind.bifeng.ui.my.presenter.MyTaskPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我执行（发布）的页面
 */
public class MyTaskFragment extends BaseFragment<MyTaskPresenter> implements MyTaskContract.View {

    @BindView(R.id.my_task_list)
    RecyclerView myTaskList;
    private String flag;
    private List<MyTaskBean.DataBean.ListBean> listBeans;
    private NewMyTaskAdapter newMyTaskAdapter;
    //    private List<TaskBean> mList;
//    private HomeAdapter adapter;
//    private String reqType;
//    private int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_task;
    }

    @Override
    protected void init() {
        listBeans = new ArrayList<>();
        Bundle arguments = getArguments();
        flag = arguments.getString("flag");
        myTaskList.setLayoutManager(new LinearLayoutManager(getContext()));
        newMyTaskAdapter = new NewMyTaskAdapter(listBeans);
        myTaskList.setAdapter(newMyTaskAdapter);
//        reqType = arguments.getString("reqType");
//        mList = new ArrayList<>();
//        adapter = new HomeAdapter(mList, getContext());
//        myTaskList.setAdapter(adapter);
    }

    @Override
    protected void loadDate() {
        presenter.loadTask(flag);
//        presenter.loadTask(SpHelp.getUserInformation(SpHelp.ID), flag, page, reqType);
    }

    @Override
    protected void hideErrorView() {
        myTaskList.setVisibility(View.GONE);
    }

    @Override
    protected void showSuccessView() {
        myTaskList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSuccess(List<TaskBean> taskBeans) {
//        mList.addAll(taskBeans);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMsg) {
        ToastHelp.showShort(getContext(), errorMsg);
    }

    @Override
    public void loginOut() {
        SpHelp.loginOut();
        LoginActivity.openLoginActivity(getContext());
    }

    @Override
    public void showList(List<MyTaskBean.DataBean.ListBean> listBeans) {
        this.listBeans.addAll(listBeans);
        newMyTaskAdapter.notifyDataSetChanged();
    }
}
