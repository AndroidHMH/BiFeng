package com.coinwind.bifeng.ui.phototask.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.ui.share.activity.ShareActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoTaskActivity extends BaseActivity {

    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.photo_task_next_btn)
    LinearLayout photoTaskNextBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_task;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadDate() {

    }

    @OnClick(R.id.photo_task_next_btn)
    public void onViewClicked() {
        Intent intent = new Intent(this, ShareActivity.class);
        startActivity(intent);
    }
}
