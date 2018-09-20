package com.coinwind.bifeng.ui.record.biz;

import com.coinwind.bifeng.config.Urls;
import com.coinwind.bifeng.ui.record.bean.RecordBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 矿产记录的Service
 */
public interface RecordService {
    @GET(Urls.RECORD)
    Observable<RecordBean> loadRecord(@Query("mTab") String mTab, @Query("userId") String userId, @Query("sign") String sign,
                                      @Query("page") String page, @Query("size") String size);
}
