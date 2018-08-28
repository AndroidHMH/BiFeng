package com.coinwind.bifeng.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.config.LiuHaiHelp;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.NetWorkHelp;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 统一管理Fragment
 *
 * @param <T>
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    protected T presenter;
    private Fragment lastFragment;
    Unbinder unbinder;
    private ImmersionBar immersionBar;
    @BindView(R.id.net_work_error_btn)
    LinearLayout netWorkErrorBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initImmersionBar();
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
        if (NetWorkHelp.isNetWorkEnable(getContext())) {
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && immersionBar != null)
            immersionBar.init();
    }

    /**
     * 适配刘海屏
     */
    protected void setLiuHai(View inflate) {
        int padding = LiuHaiHelp.getStatusBarHeight(getContext());
        LogHelp.e("App", "系统的  = " + padding);
        if (LiuHaiHelp.hasNotchInScreen(getContext())) {
            int[] notchSize = LiuHaiHelp.getNotchSize(getContext());
            padding = notchSize[1];
        } else if (LiuHaiHelp.hasNotchInScreenAtOppo(getContext())) {

        } else if (LiuHaiHelp.hasNotchInScreenAtVoio(getContext())) {

        }
        View childAt = ((ViewGroup) inflate.findViewById(android.R.id.content)).getChildAt(0);
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
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        immersionBar = ImmersionBar.with(this);
        immersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).statusBarDarkFont(true).init();
    }

    private T getPresenter() {
        Type type = getClass().getGenericSuperclass();
        if (BaseFragment.class.equals(type)) {
            return null;
        }
        Type[] types = ((ParameterizedType) type).getActualTypeArguments();
        Class<T> tClass = (Class<T>) types[0];
        try {
            return tClass.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加载布局
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
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        String simpleName = fragmentClass.getSimpleName();
        Fragment fragment = fragmentManager.findFragmentByTag(simpleName);
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance();
                transaction.add(layoutId, fragment, simpleName);
            } catch (java.lang.InstantiationException e) {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (presenter != null) {
            presenter.unActualView();
        }
        if (immersionBar != null)
            immersionBar.destroy();
    }

}