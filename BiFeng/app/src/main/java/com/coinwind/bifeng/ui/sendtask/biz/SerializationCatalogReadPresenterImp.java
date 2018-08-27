package com.coinwind.bifeng.ui.sendtask.biz;



import com.coinwind.bifeng.model.http.RetrofitHelp;
import com.coinwind.bifeng.ui.setting.biz.ChangePswService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SerializationCatalogReadPresenterImp  {
//    serializationCatalogReadContract.serializationCatalogReadView serializationCatalogReadView;

    public void getSerializationCatalogReadBean(String catalogId) {
        Map<String, String> Headermap = new HashMap<>();
        Headermap.put("accessToken","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MzI1MDQyMTAsInN1YiI6IntcInVzZXJJZFwiOjI1NSxcInJvbGVUeXBlXCI6bnVsbCxcInNlc3Npb25JZFwiOlwiMTNEMUE1RjUxNDM1QURBODNFMkJFNUJDNzUzOTc0OTFcIixcInVzZXJBZ2VudFwiOlwiWk1DYXJ0b29uLzEuMCAoaVBob25lOyBpT1MgMTEuMC4zOyBTY2FsZS8yLjAwKVwiLFwiaW5kZXhcIjowLFwicmVmcmVzaFRva2VuXCI6ZmFsc2V9IiwiZXhwIjoxNTY0MDQwMjEwfQ.URYD_U8GudpDBWgllZewA6wex_CN16hHHzgq1LZA3KI");
        Map<String, String> map = new HashMap<>();
        map.put("catalogId", catalogId);
      RetrofitHelp.getInstance()
              .getService(ChangePswService.class)
              .changePsw("","","")
              .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
//
//    public void getSerializationCatalogBean(String PgcId) {
//        Map<String, String> Headermap = new HashMap<>();
//        Headermap.put("accessToken","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MzI1MDQyMTAsInN1YiI6IntcInVzZXJJZFwiOjI1NSxcInJvbGVUeXBlXCI6bnVsbCxcInNlc3Npb25JZFwiOlwiMTNEMUE1RjUxNDM1QURBODNFMkJFNUJDNzUzOTc0OTFcIixcInVzZXJBZ2VudFwiOlwiWk1DYXJ0b29uLzEuMCAoaVBob25lOyBpT1MgMTEuMC4zOyBTY2FsZS8yLjAwKVwiLFwiaW5kZXhcIjowLFwicmVmcmVzaFRva2VuXCI6ZmFsc2V9IiwiZXhwIjoxNTY0MDQwMjEwfQ.URYD_U8GudpDBWgllZewA6wex_CN16hHHzgq1LZA3KI");
//        Map<String, String> map = new HashMap<>();
//        map.put("pgcId", PgcId);
//        RetrofitUtils.getInstance().getserializationCatalogReadService().GetSerializationCatalogBean(Headermap,map).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<SerializationCatalogBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//                    @Override
//                    public void onNext(SerializationCatalogBean userBean) {
//                        if (userBean.getState() == 0) {
//                            serializationCatalogReadView.showError(userBean.getMsg());
//                            serializationCatalogReadView.showSerializationCatalogBean(userBean);
//                        } else {
//                            serializationCatalogReadView.showError(userBean.getMsg());
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//
//    public void getSerializationDetailsBean(String PgcId) {
//        Map<String, String> Headermap = new HashMap<>();
//        Headermap.put("accessToken","eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MzI1MDQyMTAsInN1YiI6IntcInVzZXJJZFwiOjI1NSxcInJvbGVUeXBlXCI6bnVsbCxcInNlc3Npb25JZFwiOlwiMTNEMUE1RjUxNDM1QURBODNFMkJFNUJDNzUzOTc0OTFcIixcInVzZXJBZ2VudFwiOlwiWk1DYXJ0b29uLzEuMCAoaVBob25lOyBpT1MgMTEuMC4zOyBTY2FsZS8yLjAwKVwiLFwiaW5kZXhcIjowLFwicmVmcmVzaFRva2VuXCI6ZmFsc2V9IiwiZXhwIjoxNTY0MDQwMjEwfQ.URYD_U8GudpDBWgllZewA6wex_CN16hHHzgq1LZA3KI");
//        Map<String, String> map = new HashMap<>();
//        map.put("pgcId", PgcId);
//        RetrofitUtils.getInstance().getSerializationDetailsService().GetSerializationDetailsBean(Headermap,map).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<SerializationDetailsBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//                    @Override
//                    public void onNext(SerializationDetailsBean userBean) {
//                        if (userBean.getState() == 0) {
//                            serializationCatalogReadView.showError(userBean.getMsg());
//                            serializationCatalogReadView.showSerializationDetailsBean(userBean);
//                        } else {
//                            serializationCatalogReadView.showError(userBean.getMsg());
//                        }
//                    }
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

}
