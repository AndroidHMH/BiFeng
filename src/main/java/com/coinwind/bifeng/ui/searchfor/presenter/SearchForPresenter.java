package com.coinwind.bifeng.ui.searchfor.presenter;

import com.coinwind.bifeng.base.TaskBean;
import com.coinwind.bifeng.config.Codes;
import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.home.bean.ListBean;
import com.coinwind.bifeng.ui.searchfor.biz.SearchForService;
import com.coinwind.bifeng.ui.searchfor.contract.SearchForContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Query;

/**
 * 搜索界面的P层
 */
public class SearchForPresenter implements SearchForContract.Presenter {
    private SearchForContract.View view;
    private SearchForService service;

    public SearchForPresenter() {
        service = RetrofitHelp.getInstance().getService(SearchForService.class);
    }

    @Override
    public void showSearchForList(String keywords, int page) {
        service.loadSearchFor(keywords, page + "", "5")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean listBean) {
                        int code = listBean.getCode();
                        List<TaskBean> data = listBean.getData();
                        if (code == Codes.SUCCESS_CODE && !data.isEmpty()) {
                            view.showSearchFor(data);
                        } else {
                            view.showError();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void actualView(SearchForContract.View view) {
        this.view = view;
    }

    @Override
    public void unActualView() {
        this.view = null;
    }
}
