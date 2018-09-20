package com.coinwind.bifeng.ui.homepage.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.NoNetworkBaseActivity;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.ui.home.fragment.NewHomeFragment;
import com.coinwind.bifeng.ui.homepage.bean.MessageEvent;
import com.coinwind.bifeng.ui.login.activity.LoginActivity;
import com.coinwind.bifeng.ui.my.fragment.NewMyFragment;
import com.coinwind.bifeng.ui.task.fragment.TaskFragment;
import com.coinwind.bifeng.ui.tradingfloor.fragment.TradingFloorFragment;
import com.coinwind.bifeng.ui.union.fragment.UnionFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.main_gong_hui_img)
    ImageView mainGongHuiImg;
    @BindView(R.id.main_gong_hui_tv)
    TextView mainGongHuiTv;
    @BindView(R.id.main_gong_hui_btn)
    LinearLayout mainGongHuiBtn;
    @BindView(R.id.main_my_img)
    ImageView mainMyImg;
    @BindView(R.id.main_my_tv)
    TextView mainMyTv;
    @BindView(R.id.main_my_btn)
    LinearLayout mainMyBtn;
    @BindView(R.id.main_jiao_yi_img)
    ImageView mainJiaoYiImg;
    @BindView(R.id.main_jiao_yi_tv)
    TextView mainJiaoYiTv;
    @BindView(R.id.main_jiao_yi_btn)
    LinearLayout mainJiaoYiBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        setHomeIcon();
        setCreateView(R.id.main_layout, NewHomeFragment.class);
    }

    @OnClick({R.id.main_home_btn, R.id.main_gong_hui_btn, R.id.main_my_btn, R.id.main_jiao_yi_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_home_btn:
                setHomeIcon();
                setCreateView(R.id.main_layout, NewHomeFragment.class);
                break;
            case R.id.main_gong_hui_btn:
                setGongHuiIcon();
                setCreateView(R.id.main_layout, UnionFragment.class);
                break;
            case R.id.main_jiao_yi_btn:
                setJiaoYiIcon();
                setCreateView(R.id.main_layout, TradingFloorFragment.class);
                break;
            case R.id.main_my_btn:
//                if (SpHelp.getLoginStatus()) {
                setMyIcon();
//                    setCreateView(R.id.main_layout, MyFragment.class);
                setCreateView(R.id.main_layout, NewMyFragment.class);
//                } else {
//                    LoginActivity.openLoginActivity(this);
//                }
                break;
        }
    }


    private void setMyIcon() {
        mainHomeImg.setImageResource(R.mipmap.home);
        mainHomeTv.setTextColor(getResources().getColor(R.color.black_333));
        mainGongHuiImg.setImageResource(R.mipmap.gong_hui);
        mainGongHuiTv.setTextColor(getResources().getColor(R.color.black_333));
        mainJiaoYiImg.setImageResource(R.mipmap.jiao_yi);
        mainJiaoYiTv.setTextColor(getResources().getColor(R.color.black_333));

        mainMyImg.setImageResource(R.mipmap.my_click);
        mainMyTv.setTextColor(getResources().getColor(R.color.blue_095a));
    }

    private void setGongHuiIcon() {
        mainHomeImg.setImageResource(R.mipmap.home);
        mainHomeTv.setTextColor(getResources().getColor(R.color.black_333));

        mainGongHuiImg.setImageResource(R.mipmap.gong_hui_click);
        mainGongHuiTv.setTextColor(getResources().getColor(R.color.blue_095a));

        mainJiaoYiImg.setImageResource(R.mipmap.jiao_yi);
        mainJiaoYiTv.setTextColor(getResources().getColor(R.color.black_333));
        mainMyImg.setImageResource(R.mipmap.my);
        mainMyTv.setTextColor(getResources().getColor(R.color.black_333));
    }

    private void setJiaoYiIcon() {
        mainHomeImg.setImageResource(R.mipmap.home);
        mainHomeTv.setTextColor(getResources().getColor(R.color.black_333));
        mainGongHuiImg.setImageResource(R.mipmap.gong_hui);
        mainGongHuiTv.setTextColor(getResources().getColor(R.color.black_333));

        mainJiaoYiImg.setImageResource(R.mipmap.jiao_yi_click);
        mainJiaoYiTv.setTextColor(getResources().getColor(R.color.blue_095a));

        mainMyImg.setImageResource(R.mipmap.my);
        mainMyTv.setTextColor(getResources().getColor(R.color.black_333));
    }

    private void setHomeIcon() {
        mainHomeImg.setImageResource(R.mipmap.home_click);
        mainHomeTv.setTextColor(getResources().getColor(R.color.blue_095a));

        mainGongHuiImg.setImageResource(R.mipmap.gong_hui);
        mainGongHuiTv.setTextColor(getResources().getColor(R.color.black_333));
        mainJiaoYiImg.setImageResource(R.mipmap.jiao_yi);
        mainJiaoYiTv.setTextColor(getResources().getColor(R.color.black_333));
        mainMyImg.setImageResource(R.mipmap.my);
        mainMyTv.setTextColor(getResources().getColor(R.color.black_333));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
