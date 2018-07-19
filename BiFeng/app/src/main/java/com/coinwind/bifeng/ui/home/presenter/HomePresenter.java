package com.coinwind.bifeng.ui.home.presenter;

import android.util.Log;

import com.coinwind.bifeng.base.BasePresenter;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.home.bean.ListBean;
import com.coinwind.bifeng.ui.home.biz.HomeService;
import com.coinwind.bifeng.ui.home.contract.HomeContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 首页的P层
 */
public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private final HomeService homeService;

    public HomePresenter() {
        homeService = RetrofitHelp.getInstance().getService(HomeService.class);
    }

    @Override
    public void loadBanner() {

        homeService.loadBanner().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<HomeBannerBean>>() {
                    @Override
                    public void accept(List<HomeBannerBean> bannerBeans) throws Exception {
                        view.showBanner(bannerBeans);
                    }
                });
    }

    @Override
    public void loadList(int page) {
        homeService.loadList(page, 5).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<ListBean>>() {
            @Override
            public void accept(List<ListBean> listBeans) throws Exception {
                view.showList(listBeans);
            }
        });
    }

    @Override
    public void actualView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
