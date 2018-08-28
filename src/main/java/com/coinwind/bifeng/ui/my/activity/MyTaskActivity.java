package com.coinwind.bifeng.ui.my.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.ui.my.adapter.MyTaskAdapter;
import com.coinwind.bifeng.ui.my.config.InfoHelp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我发布（执行）的任务界面
 */
public class MyTaskActivity extends NoNetworkBaseActivity {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    @BindView(R.id.my_task_tab_layout)
    TabLayout myTaskTabLayout;
    @BindView(R.id.my_task_pager)
    ViewPager myTaskPager;
    private List<String> mTitle;
    private List<Fragment> mFragments;
    private MyTaskAdapter myTaskAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_task;
    }

    @Override
    protected void init() {
        mTitle = new ArrayList<>();
        Intent intent = getIntent();
        ArrayList<String> titles = intent.getStringArrayListExtra("titles");
        mTitle.addAll(titles);
        InfoHelp.setTitle(titles, titleTitleTv);
        mFragments = new ArrayList<>();
        myTaskAdapter = new MyTaskAdapter(getSupportFragmentManager(), mTitle, mFragments);
        myTaskTabLayout.setupWithViewPager(myTaskPager);
        myTaskPager.setAdapter(myTaskAdapter);
        mFragments.addAll(InfoHelp.addFragment(mTitle.size()));
        myTaskAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.title_layout_return_btn)
    public void onViewClicked() {
        finish();
    }
}
