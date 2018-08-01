package com.coinwind.bifeng.ui.homepage.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.BaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.ui.home.fragment.HomeFragment;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.my.fragment.MyFragment;
import com.coinwind.bifeng.ui.task.fragment.TaskFragment;
import com.gyf.barlibrary.ImmersionBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主页
 */
@SuppressLint("ResourceAsColor")
public class MainActivity extends BaseActivity {
    @BindView(R.id.main_home_img)
    ImageView mainHomeImg;
    @BindView(R.id.main_home_tv)
    TextView mainHomeTv;
    @BindView(R.id.main_home_btn)
    LinearLayout mainHomeBtn;
    @BindView(R.id.main_task_img)
    ImageView mainTaskImg;
    @BindView(R.id.main_task_tv)
    TextView mainTaskTv;
    @BindView(R.id.main_task_btn)
    LinearLayout mainTaskBtn;
    @BindView(R.id.main_my_img)
    ImageView mainMyImg;
    @BindView(R.id.main_my_tv)
    TextView mainMyTv;
    @BindView(R.id.main_my_btn)
    LinearLayout mainMyBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

//    @Override
//    protected View getBar() {
//        return getLayoutInflater().inflate(R.layout.title_layout, null);
//    }

    @Override
    protected void init() {
        setHomeIcon();
        setCreateView(R.id.main_layout, HomeFragment.class);
    }

    @Override
    protected void loadDate() {

    }

    @OnClick({R.id.main_home_btn, R.id.main_task_btn, R.id.main_my_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_home_btn:
                setHomeIcon();
                setCreateView(R.id.main_layout, HomeFragment.class);
                break;
            case R.id.main_task_btn:
                setTaskIcon();
                setCreateView(R.id.main_layout, TaskFragment.class);
                break;
            case R.id.main_my_btn:
                if (SpHelp.getLoginStatus()) {
                    setMyIcon();
                    setCreateView(R.id.main_layout, MyFragment.class);
                } else {
                    LoginActivity.openLoginActivity(this);
                }
                break;
        }
    }

    private void setMyIcon() {
        mainHomeImg.setImageResource(R.mipmap.home);
        mainHomeTv.setTextColor(getResources().getColor(R.color.black_333));
        mainTaskImg.setImageResource(R.mipmap.task);
        mainTaskTv.setTextColor(getResources().getColor(R.color.black_333));

        mainMyImg.setImageResource(R.mipmap.my_click);
        mainMyTv.setTextColor(getResources().getColor(R.color.orange_f9f0));
    }

    private void setTaskIcon() {
        mainHomeImg.setImageResource(R.mipmap.home);
        mainHomeTv.setTextColor(getResources().getColor(R.color.black_333));

        mainTaskImg.setImageResource(R.mipmap.task_click);
        mainTaskTv.setTextColor(getResources().getColor(R.color.orange_f9f0));

        mainMyImg.setImageResource(R.mipmap.my);
        mainMyTv.setTextColor(getResources().getColor(R.color.black_333));
    }

    private void setHomeIcon() {
        mainHomeImg.setImageResource(R.mipmap.home_click);
        mainHomeTv.setTextColor(getResources().getColor(R.color.orange_f9f0));

        mainTaskImg.setImageResource(R.mipmap.task);
        mainTaskTv.setTextColor(getResources().getColor(R.color.black_333));
        mainMyImg.setImageResource(R.mipmap.my);
        mainMyTv.setTextColor(getResources().getColor(R.color.black_333));
    }
}
