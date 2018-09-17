package com.coinwind.bifeng.ui.recommended.presenter;

import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.model.http.SwitchThread;
import com.coinwind.bifeng.ui.home.bean.ListBean;
import com.coinwind.bifeng.ui.recommended.biz.RecommendedService;
import com.coinwind.bifeng.ui.recommended.contract.RecommendedContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 推荐列表的P层
 */
public class RecommendedPresenter implements RecommendedContract.Presenter {
    private RecommendedContract.View view;
    private final RecommendedService service;

    public RecommendedPresenter() {
        service = RetrofitHelp.getInstance().getService(RecommendedService.class);
    }

    @Override
    public void loadRecommendedList() {
        service.loadRecommendedList()
                .compose(SwitchThread.<ListBean>switchThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean listBean) {
                        int code = listBean.getCode();
                        if (code == Codes.SUCCESS_CODE) {
                            List<TaskBean> data = listBean.getData();
                            if (data != null) {
                                view.showRecommendedList(data);
                            } else {
                                int resultCode = listBean.getCode();
                                if (resultCode == Codes.FAILURE_CODE) {
                                    view.loginTimeOut();
                                } else
                                    view.showError("请求失败");
                            }
                        } else {
                            view.showError("请求失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError("请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void actualView(RecommendedContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
