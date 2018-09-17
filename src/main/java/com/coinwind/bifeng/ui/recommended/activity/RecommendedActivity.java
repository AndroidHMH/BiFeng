package com.coinwind.bifeng.ui.recommended.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.ui.recommended.contract.RecommendedContract;
import com.coinwind.bifeng.ui.recommended.presenter.RecommendedPresenter;
import com.coinwind.bifeng.ui.task.adapter.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 推荐任务列表
 */
public class RecommendedActivity extends BaseActivity<RecommendedPresenter> implements RecommendedContract.View {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.recommended_recycler)
    ListView recommendedRecycler;
    private List<TaskBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recommended;
    }

    @Override
    protected void init() {
        titleTitleTv.setText("推荐任务");
        list = new ArrayList<>();
        TaskAdapter taskAdapter = new TaskAdapter(list, this);

    }

    @Override
    protected void loadDate() {
        presenter.loadRecommendedList();

    }

    @Override
    public void showRecommendedList(List<TaskBean> list) {

    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void loginTimeOut() {

    }

    @OnClick(R.id.title_layout_return_btn)
    public void onViewClicked() {
        finish();
    }
}
