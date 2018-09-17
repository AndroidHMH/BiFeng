package com.coinwind.bifeng.ui.my.activity;

import android.content.Context;
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
import com.coinwind.bifeng.ui.my.fragment.MyTaskFragment;

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
    private List<Fragment> mFragments;
    private MyTaskAdapter myTaskAdapter;
    private ArrayList<String> titles;
    private int index;

    public static void openMyTaskActivity(Context context, int index) {
        Intent intent = new Intent(context, MyTaskActivity.class);
        intent.putExtra("index", index);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_task;
    }

    @Override
    protected void init() {
        titleTitleTv.setText("全部任务");
        initTitle();
        mFragments = new ArrayList<>();
        myTaskAdapter = new MyTaskAdapter(getSupportFragmentManager(), titles, mFragments);
        myTaskTabLayout.setupWithViewPager(myTaskPager);
        myTaskPager.setAdapter(myTaskAdapter);
        initFragment();
        myTaskAdapter.notifyDataSetChanged();

        index = getIntent().getIntExtra("index", -4);
        if (index != -1 && index != -4) {
            myTaskPager.setCurrentItem(index);
        }
    }

    private void initFragment() {
        for (int i = 0; i < titles.size(); i++) {
            MyTaskFragment myTaskFragment = new MyTaskFragment();
            Bundle bundle = new Bundle();
            bundle.putString("flag", (i + 1) + "");
            myTaskFragment.setArguments(bundle);
            mFragments.add(myTaskFragment);
        }
    }

    private String getFlag() {
        String flag = "";
        switch (index) {
            case -1:
                flag = "1";
                break;
            case 0:
                flag = "1";
                break;
            case 1:
                flag = "2";
                break;
            case 2:
                flag = "3";
                break;
            case 3:
                flag = "4";
                break;
            case 4:
                flag = "5";
                break;
        }
        return flag;

    }

    private void initTitle() {
        titles = new ArrayList<>();
        titles.add("已接受");
        titles.add("进行中");
        titles.add("待审核");
        titles.add("已结束");
        titles.add("已过期");
    }

    @OnClick(R.id.title_layout_return_btn)
    public void onViewClicked() {
        finish();
    }
}
