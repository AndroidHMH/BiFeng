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
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.ui.homepage.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends NoNetworkBaseActivity implements View.OnClickListener {

    @BindView(R.id.welcome_img)
    ImageView welcomeImg;
    Button welcomeGoMainBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {
        welcomeGoMainBtn = findViewById(R.id.welcome_go_main_btn);
        if (SpHelp.getClick()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                    overridePendingTransition(R.anim.welcome_come_transition, R.anim.welcome_go_transition);
                }
            }, 1500);
        } else {
            welcomeGoMainBtn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        SpHelp.putCount(true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(R.anim.welcome_come_transition, R.anim.welcome_go_transition);
    }
}
