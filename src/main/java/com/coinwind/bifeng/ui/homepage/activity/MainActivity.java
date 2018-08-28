package com.coinwind.bifeng.ui.homepage.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.ui.home.fragment.HomeFragment;
import com.coinwind.bifeng.ui.homepage.bean.MessageEvent;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.my.fragment.MyFragment;
import com.coinwind.bifeng.ui.task.fragment.TaskFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主页
 */
@SuppressLint("ResourceAsColor")
public class MainActivity extends NoNetworkBaseActivity {
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

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        setHomeIcon();
        setCreateView(R.id.main_layout, HomeFragment.class);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventBus(String type) {
        EventBus.getDefault().post(new MessageEvent(type));
        setTaskIcon();
        setCreateView(R.id.main_layout, TaskFragment.class);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
