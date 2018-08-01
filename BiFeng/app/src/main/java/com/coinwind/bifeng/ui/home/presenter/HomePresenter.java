package com.coinwind.bifeng.ui.home.presenter;

import android.annotation.SuppressLint;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.home.bean.GuangBoBean;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.home.bean.HomeQiangBean;
import com.coinwind.bifeng.ui.home.bean.ListBean;
import com.coinwind.bifeng.ui.home.biz.HomeService;
import com.coinwind.bifeng.ui.home.contract.HomeContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页的P层
 */
@SuppressLint("CheckResult")
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
                .subscribe(new Observer<HomeBannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBannerBean homeBannerBean) {
                        int code = homeBannerBean.getCode();
                        List<HomeBannerBean.DataBean> data = homeBannerBean.getData();
                        if (code == Codes.SUCCESS_CODE && data != null) {
                            view.showBanner(data);
                        } else {
                            view.showBannerError(setDefaultBannerImg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showBannerError(setDefaultBannerImg());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private List<Integer> setDefaultBannerImg() {
        ArrayList<Integer> defaultImgs = new ArrayList<>();
        defaultImgs.add(R.mipmap.default_banner1);
        defaultImgs.add(R.mipmap.default_banner2);
        defaultImgs.add(R.mipmap.default_banner3);
        return defaultImgs;
    }

    @Override
    public void loadQiang() {
        homeService.loadQiang().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeQiangBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeQiangBean homeQiangBean) {
                        int code = homeQiangBean.getCode();
                        TaskBean data = homeQiangBean.getData();
                        if (code == Codes.SUCCESS_CODE && data != null) {
                            view.showQiang(data);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void loadGuangBo() {
        homeService.loadGuangBo().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GuangBoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GuangBoBean guangBoBean) {
                        int code = guangBoBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            StringBuffer sb = new StringBuffer();
                            List<GuangBoBean.DataBean> data = guangBoBean.getData();
                            for (int i = 0; i < data.size(); i++) {
                                if (i == 0 || i == data.size() - 1) {
                                    sb.append(data.get(i).getBroadcast_title());
                                } else {
                                    sb.append("   " + data.get(i).getBroadcast_title() + "   ");
                                }
                            }
                            view.showGuangBo(sb.toString());
                        } else {
                            view.showGuangBoError("更多任务等你来抢");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showGuangBoError("更多任务等你来抢");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadTuiJian(int page) {

        homeService.loadList(page, 10).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ListBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ListBean listBean) {
                LogHelp.e("HomePresenter", listBean.toString());

                int code = listBean.getCode();
                List<TaskBean> data = listBean.getData();
                if (code == Codes.SUCCESS_CODE && data != null) {
                    view.showTuiJian(data);
                }
            }

            @Override
            public void onError(Throwable e) {
                LogHelp.e("HomePresenter", e.getMessage());
            }

            @Override
            public void onComplete() {

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
