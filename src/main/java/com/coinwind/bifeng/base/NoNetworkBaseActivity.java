package com.coinwind.bifeng.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.coinwind.bifeng.app.BFApplication;
import com.coinwind.bifeng.config.LiuHaiHelp;
import com.coinwind.bifeng.config.LogHelp;
import com.gyf.barlibrary.ImmersionBar;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.ButterKnife;


/**
 * 统一管理无网络请求的Activity
 */
public abstract class NoNetworkBaseActivity extends AppCompatActivity {
    private Fragment lastFragment;
    protected ImmersionBar immersionBar;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        BFApplication.context = this;
        ButterKnife.bind(this);
        immersionBar = ImmersionBar.with(this).statusBarDarkFont(true);
        immersionBar.init();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BFApplication.context = this;

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

    /*重新加载布局*/
    public void reLoadFragView(int layoutId, Class<? extends BaseFragment> fragmentClass) {
        /*从FragmentManager中移除*/
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

        String simpleName = fragmentClass.getSimpleName();
        Fragment fragment = supportFragmentManager.findFragmentByTag(simpleName);
        if (fragment != null) {
            fragmentTransaction.remove(fragment).commit();
        }
        /*重新创建*/
        try {
            fragment = fragmentClass.newInstance();
            fragmentTransaction.add(layoutId, fragment, simpleName);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (lastFragment != null) {
            fragmentTransaction.hide(lastFragment);
        }
        fragmentTransaction.show(fragment);
        lastFragment = fragment;
        fragmentTransaction.commit();

    }
}