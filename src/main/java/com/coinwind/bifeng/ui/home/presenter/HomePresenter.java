package com.coinwind.bifeng.ui.home.presenter;

import android.annotation.SuppressLint;

import com.coinwind.bifeng.R;
import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.LogHelp;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.home.bean.BroadcastBean;
import com.coinwind.bifeng.ui.home.bean.HomeBannerBean;
import com.coinwind.bifeng.ui.home.bean.HomeQiangBean;
import com.coinwind.bifeng.ui.home.bean.IsLoginBean;
import com.coinwind.bifeng.ui.home.bean.ListBean;
import com.coinwind.bifeng.ui.home.bean.QianDaoBean;
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
                .subscribe(new Observer<BroadcastBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BroadcastBean broadcastBean) {
                        int code = broadcastBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            StringBuffer sb = new StringBuffer();
                            List<BroadcastBean.DataBean> data = broadcastBean.getData();
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
    public void sendQianDao() {
        homeService.sendQianDao(SpHelp.getUserInformation(SpHelp.ID), SpHelp.getSign())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QianDaoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QianDaoBean qianDaoBean) {
                        LogHelp.e("LoginPresenter", qianDaoBean.toString());
                        int code = qianDaoBean.getCode();
                        QianDaoBean.DataBean data = qianDaoBean.getData();
                        if (code == Codes.SUCCESS_CODE) {
                            if (data.isState()) {
                                view.qianDaoSuccess(data.getMsg(), Integer.parseInt(data.getCheckType()));
                            } else {
                                view.qianDaoError(data.getMsg());
                            }
                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginOut();
                        } else {
                            view.qianDaoError(data.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.qianDaoError("签到失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void isLogin() {
        homeService.isLogin(SpHelp.getUserInformation(SpHelp.ID), SpHelp.getSign())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IsLoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IsLoginBean isLoginBean) {
                        int code = isLoginBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            if (isLoginBean.getData().isState()) {
                                view.login();
                            } else {
                                view.loginOut();
                            }
                        } else {
                            view.loginOut();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.loginOut();

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
