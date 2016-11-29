package com.android.architecture.practice.data.remote;

import com.android.architecture.practice.data.DataSource;
import com.android.architecture.practice.data.model.LatestNews;
import com.android.architecture.practice.data.model.Result;

import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/29
 */

public class ServiceManager implements DataSource {

    private ClientApi mClientApi;

    public ServiceManager(ClientApi clientApi) {
        mClientApi = clientApi;
    }

    @Override
    public Observable<Result<LatestNews>> getLatestNews() {
        return mClientApi.getLatestNews()
                .flatMap(new Func1<LatestNews, Observable<Result<LatestNews>>>() {
                    @Override
                    public Observable<Result<LatestNews>> call(LatestNews latestNews) {
                        return Observable.just(new Result<>(DataSourceType.NETWORK, latestNews));
                    }
                });
    }
}
