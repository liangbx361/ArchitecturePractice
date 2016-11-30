package com.android.architecture.practice.data.remote;

import com.android.architecture.practice.data.model.LatestNews;

import retrofit2.http.GET;
import rx.Observable;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/28
 */

public interface ZhiHuApi {

    String BASE_URL = "http://news-at.zhihu.com/api/";

    @GET("4/news/latest")
    Observable<LatestNews> getLastNews();
}
