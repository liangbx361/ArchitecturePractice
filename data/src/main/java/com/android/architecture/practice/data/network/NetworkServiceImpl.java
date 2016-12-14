package com.android.architecture.practice.data.network;

import android.content.Context;

import com.android.architecture.practice.data.model.LatestNews;
import com.liangbx.android.common.util.CacheUtil;

import okhttp3.Interceptor;
import rx.Observable;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/12/13
 */

public class NetworkServiceImpl implements NetworkService {

    private NetworkApi mNetworkApi;

    public NetworkServiceImpl(NetworkApi networkApi) {
        mNetworkApi = networkApi;
    }

    public NetworkServiceImpl(Context context) {
        mNetworkApi = ClientApiBuilder.build(
                NetworkApi.class,
                NetworkApi.BASE_URL,
                CacheUtil.getExternalCache(context),
                (Interceptor[]) null
        );
    }

    @Override
    public Observable<LatestNews> getLatestNews() {
        return mNetworkApi.getLastNews();
    }
}
