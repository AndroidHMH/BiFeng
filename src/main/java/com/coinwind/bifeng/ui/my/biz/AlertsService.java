package com.coinwind.bifeng.ui.my.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.my.bean.AlertsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * 信息列表Service
 */
public interface AlertsService {
    @GET(Urls.ALERTS_LIST)
    Observable<AlertsBean> loadAlerts(@Query("userId") String userId, @Header("sign") String sign);
}
