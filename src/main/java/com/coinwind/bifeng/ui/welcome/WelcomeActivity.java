package com.coinwind.bifeng.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends NoNetworkBaseActivity {

    @BindView(R.id.welcome_img)
    ImageView welcomeImg;
    @BindView(R.id.welcome_go_main_btn)
    Button welcomeGoMainBtn;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.welcome_go_main_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.welcome_go_main_btn:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.welcome_come_transition, R.anim.welcome_go_transition);
                break;
        }
    }
}
