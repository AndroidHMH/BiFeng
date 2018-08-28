package com.coinwind.bifeng.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends NoNetworkBaseActivity {

    @BindView(R.id.welcome_img)
    ImageView welcomeImg;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.welcome_come_transition, R.anim.welcome_go_transition);
            }
        }, 1000);

    }
}
