package com.coinwind.bifeng.ui.home.presenter;

import android.annotation.SuppressLint;

import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.config.SpHelp;
import com.coinwind.bifeng.config.TimeUtils;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.model.http.SwitchThread;
import com.coinwind.bifeng.ui.home.bean.BroadcastBean;
import com.coinwind.bifeng.ui.home.bean.HomeInfoBean;
import com.coinwind.bifeng.ui.home.bean.HomeItemCCBean;
import com.coinwind.bifeng.ui.home.bean.HomeUserInfoBean;
import com.coinwind.bifeng.ui.home.bean.ReceiveCC;
import com.coinwind.bifeng.ui.home.biz.NewHomeService;
import com.coinwind.bifeng.ui.home.contract.NewHomeContract;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页的P层
 */
@SuppressLint("CheckResult")
public class NewHomePresenter implements NewHomeContract.Presenter {
    private NewHomeContract.View view;
    private final NewHomeService homeService;

    public NewHomePresenter() {
        homeService = RetrofitHelp.getInstance().getService(NewHomeService.class);
    }


    @Override
    public void loadBroadcast() {
        homeService.loadBroadcast().subscribeOn(Schedulers.newThread())
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
                            view.showBroadcast(sb.toString());
                        } else {
                            view.showBroadcast("更多任务等你来抢");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showBroadcast("更多任务等你来抢");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadHomeInfo() {
        homeService.loadHomeInfo()
                .compose(SwitchThread.<HomeInfoBean>switchThread())
                .subscribe(new Observer<HomeInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeInfoBean homeInfoBean) {
                        int code = homeInfoBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            if (homeInfoBean.isState()) {
                                view.showHomeInfo(homeInfoBean.getData());

                            } else {
                                int resultCode = homeInfoBean.getCode();
                                if (resultCode == Codes.FAILURE_CODE) {
                                    view.loginTimeOut();
                                } else
                                    view.showError("基本信息请求失败");
                            }
                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginTimeOut();
                        } else {
                            view.showError("基本信息请求失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError("网络错误，请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadHomeUserInfo() {
        homeService.loadHomeUserInfo(SpHelp.getAndroidId(), SpHelp.getUserInformation(SpHelp.ID), SpHelp.getSign())
                .compose(SwitchThread.<HomeUserInfoBean>switchThread())
                .subscribe(new Observer<HomeUserInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeUserInfoBean homeUserInfoBean) {
                        int code = homeUserInfoBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            if (homeUserInfoBean.isState()) {
                                view.showUserInfo(homeUserInfoBean.getData());
                            } else {
                                int resultCode = homeUserInfoBean.getData().getCode();
                                if (resultCode == Codes.FAILURE_CODE) {
                                    view.loginTimeOut();
                                } else
                                    view.showError("用户信息请求失败");
                            }
                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginTimeOut();
                        } else {
                            view.showError("请求失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError("网络错误，请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadCCList() {
        homeService.loadCCList(SpHelp.getAndroidId(), SpHelp.getUserInformation(SpHelp.ID), SpHelp.getSign())
                .compose(SwitchThread.<HomeItemCCBean>switchThread())
                .subscribe(new Observer<HomeItemCCBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeItemCCBean homeItemCCBean) {
                        int code = homeItemCCBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            HomeItemCCBean.DataBean data = homeItemCCBean.getData();
                            if (data != null) {
                                if (data.isState()) {
                                    List<HomeItemCCBean.DataBean.CcListBean> ccList = data.getCcList();
                                    if (ccList != null) {
                                        view.showCCList(ccList);
                                    } else {
                                        view.showError("cc集合请求失败");
                                    }
                                } else {
                                    int resultCode = data.getCode();
                                    if (resultCode == Codes.FAILURE_CODE) {
                                        view.loginTimeOut();
                                    } else
                                        view.showError("cc集合请求失败");
                                }
                            }
                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginTimeOut();
                        } else {
                            view.showError("cc集合请求失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError("网络错误，请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void receiveCC(String cc, String buildTime) {
        homeService.receiveCC(SpHelp.getAndroidId(), SpHelp.getUserInformation(SpHelp.ID), SpHelp.getSign(), cc, buildTime)
                .compose(SwitchThread.<ReceiveCC>switchThread())
                .subscribe(new Observer<ReceiveCC>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReceiveCC receiveCC) {
                        int code = receiveCC.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            ReceiveCC.DataBeanX data = receiveCC.getData();
                            if (data != null) {
                                if (data.isState()) {
                                    String msg = data.getMsg();
                                    view.showReceiveCCResult(msg);
                                } else {
                                    int resultCode = data.getCode();
                                    if (resultCode == Codes.FAILURE_CODE) {
                                        view.loginTimeOut();
                                    } else
                                        view.showError("cc领取失败:" + data.getMsg());
                                }
                            }

                        } else if (code == Codes.FAILURE_CODE) {
                            view.loginTimeOut();
                        } else {
                            view.showError("cc领取失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError("网络错误，cc领取失败");

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void actualView(NewHomeContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
