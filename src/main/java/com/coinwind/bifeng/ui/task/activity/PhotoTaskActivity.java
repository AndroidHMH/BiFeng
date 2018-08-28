package com.coinwind.bifeng.ui.task.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.ui.share.activity.ShareActivity;
import com.coinwind.bifeng.ui.submittask.activity.SubmitCommentsActivity;
import com.coinwind.bifeng.ui.task.config.SetViewHelp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 操作步骤  纯3个步骤
 */
public class PhotoTaskActivity extends NoNetworkBaseActivity {


    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.bu_zou_thread_first_tv)
    TextView buZouThreadFirstTv;
    @BindView(R.id.bu_zou_thread_second_tv)
    TextView buZouThreadSecondTv;
    @BindView(R.id.bu_zou_thread_chang_img)
    ImageView buZouThreadChangImg;
    @BindView(R.id.bu_zou_thread_duan_img)
    ImageView buZouThreadDuanImg;
    @BindView(R.id.bu_zou_thread_third_tv)
    TextView buZouThreadThirdTv;
    @BindView(R.id.bu_zou_thread_next_btn)
    LinearLayout buZouThreadNextBtn;
    @BindView(R.id.bu_zou_thread_chang_layout)
    LinearLayout buZouThreadChangLayout;
    @BindView(R.id.bu_zou_thread_duan_layout)
    LinearLayout buZouThreadDuanLayout;
    private TaskBean bean;
    private String type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_task;
    }

    @Override
    protected void init() {
        bean = (TaskBean) getIntent().getSerializableExtra("bean");
        setViews();
    }

    private void setViews() {
        type = SetViewHelp.getType(bean.getType());
        titleTitleTv.setText(type);
        if ("拍照任务".equals(type)) {
            buZouThreadDuanLayout.setVisibility(View.VISIBLE);
            buZouThreadChangLayout.setVisibility(View.GONE);
            SetViewHelp.setContent(bean.getType(), buZouThreadFirstTv, buZouThreadSecondTv, buZouThreadThirdTv, buZouThreadDuanImg);
        } else if ("转发任务".equals(type)) {
            buZouThreadDuanLayout.setVisibility(View.GONE);
            buZouThreadChangLayout.setVisibility(View.VISIBLE);
            SetViewHelp.setContent(bean.getType(), buZouThreadFirstTv, buZouThreadSecondTv, buZouThreadThirdTv, buZouThreadChangImg);
        }

    }

    @OnClick({R.id.title_layout_return_btn, R.id.bu_zou_thread_next_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_layout_return_btn:
                finish();
                break;
            case R.id.bu_zou_thread_next_btn:
                if ("拍照任务".equals(type)) {
                    Intent intent = new Intent(this, SubmitCommentsActivity.class);
                    intent.putExtra("bean", bean);
                    intent.putExtra("type", type);
                    startActivity(intent);
                } else if ("转发任务".equals(type)) {
                    Intent intent = new Intent(this, ShareActivity.class);
                    intent.putExtra("bean", bean);
                    startActivity(intent);
                }
                break;
        }
    }


}
