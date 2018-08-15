package com.coinwind.bifeng.ui.setting.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关于我们页面
 */
public class GuanYuActivity extends NoNetworkBaseActivity {

    @BindView(R.id.guan_yu_img)
    ImageView guanYuImg;
    @BindView(R.id.title_title_tv)
    TextView titleTitleTv;
    @BindView(R.id.title_layout_return_btn)
    LinearLayout titleLayoutReturnBtn;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    private int imgId;

    public static void openActivity(Context context, int imgId) {
        Intent intent = new Intent(context, GuanYuActivity.class);
        intent.putExtra("imgId", imgId);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_guan_yu;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        imgId = intent.getIntExtra("imgId", 0);
        guanYuImg.setImageResource(imgId);
    }


    @OnClick(R.id.title_layout_return_btn)
    public void onViewClicked() {
        finish();
    }
}
