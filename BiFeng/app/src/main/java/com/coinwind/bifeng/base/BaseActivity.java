package com.coinwind.bifeng.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.config.LiuHaiHelp;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.NetWorkHelp;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 统一管理有网络请求的Activity
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    private Fragment lastFragment;
    protected T presenter;
    protected ImmersionBar immersionBar;
    @BindView(R.id.net_work_error_btn)
    LinearLayout netWorkErrorBtn;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        BFApplication.context = this;
        ButterKnife.bind(this);
        immersionBar = ImmersionBar.with(this).statusBarDarkFont(true);
        immersionBar.init();
        presenter = getPresenter();
        if (presenter != null) {
            presenter.actualView(this);
        }
        init();
        netWorkError();
    }

    /**
     * 判断网络状态进行网络请求
     */
    protected void netWorkError() {
        if (NetWorkHelp.isNetWorkEnable(this)) {
            showSuccessView();
            netWorkErrorBtn.setVisibility(View.GONE);
            loadDate();
        } else {
            hideErrorView();
            netWorkErrorBtn.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 无网络状态隐藏布局
     */
    protected void hideErrorView() {
    }

    /**
     * 有网络显示布局
     */
    protected void showSuccessView() {
    }

    @OnClick(R.id.net_work_error_btn)
    public void onViewClicked() {
        netWorkError();
    }

    /**
     * 适配刘海屏
     */
    protected void setLiuHai() {
        int padding = LiuHaiHelp.getStatusBarHeight(this);
        LogHelp.e("App", "系统的  = " + padding);
        if (LiuHaiHelp.hasNotchInScreen(this)) {
            int[] notchSize = LiuHaiHelp.getNotchSize(this);
            padding = notchSize[1];
        } else if (LiuHaiHelp.hasNotchInScreenAtOppo(this)) {

        } else if (LiuHaiHelp.hasNotchInScreenAtVoio(this)) {

        }
        View childAt = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        if (childAt instanceof RelativeLayout) {
            RelativeLayout rl = (RelativeLayout) childAt;
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.topMargin = padding;
            rl.setLayoutParams(params);
        } else if (childAt instanceof LinearLayout) {
            LinearLayout rl = (LinearLayout) childAt;
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.topMargin = padding;
            rl.setLayoutParams(params);
        }

    }

    /**
     * 获取Presenter对象
     *
     * @return
     */
    protected T getPresenter() {

        Type type = getClass().getGenericSuperclass();
        if (BaseActivity.class.equals(type)) {
            return null;
        }
        Type[] arguments = ((ParameterizedType) type).getActualTypeArguments();

        Class<T> tClass = (Class<T>) arguments[0];
        try {
            return tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter = getPresenter();
        BFApplication.context = this;
        if (presenter != null) {
            presenter.actualView(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        BFApplication.context = null;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        if (presenter != null) {
            presenter.unActualView();
        }
    }

    /**
     * 统一加载布局
     *
     * @return 要加载的布局文件
     */
    protected abstract int getLayoutId();

    /**
     * 统一初始化数据
     */
    protected abstract void init();

    /**
     * 统一加载数据
     */
    protected abstract void loadDate();

    /**
     * 切换Fragment
     *
     * @param layoutId      Fragment要显示的布局id
     * @param fragmentClass 要显示的Fragment
     */
    protected void setCreateView(int layoutId, Class<? extends BaseFragment> fragmentClass) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        String simpleName = fragmentClass.getSimpleName();
        Fragment fragment = fragmentManager.findFragmentByTag(simpleName);
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance();
                transaction.add(layoutId, fragment, simpleName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (lastFragment != null) {
            transaction.hide(lastFragment);
        }
        transaction.show(fragment);
        lastFragment = fragment;
        transaction.commit();
    }


}