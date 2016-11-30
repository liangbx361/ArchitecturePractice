package com.android.architecture.practice.data.remote;

import com.android.architecture.practice.data.model.ApiDataSource;
import com.android.architecture.practice.data.model.ApiResult;
import com.android.architecture.practice.data.model.LatestNews;

import rx.Observable;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/30
 */

public class ZhiHuService {

    private ZhiHuApi mZhiHuApi;

    public ZhiHuService(ZhiHuApi zhiHuApi) {
        mZhiHuApi = zhiHuApi;
    }

    Observable<ApiResult<LatestNews>> getLatestNews() {
        return mZhiHuApi.getLastNews()
                .map(latestNews -> new ApiResult<>(ApiDataSource.Network, latestNews));
    }
}
