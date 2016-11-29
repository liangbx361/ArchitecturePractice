package com.android.architecture.practice.data;

import com.android.architecture.practice.data.local.DatabaseManager;
import com.android.architecture.practice.data.local.MemoryManager;
import com.android.architecture.practice.data.local.PreferencesManager;
import com.android.architecture.practice.data.model.LatestNews;
import com.android.architecture.practice.data.model.Result;
import com.android.architecture.practice.data.remote.ServiceManager;

import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Title: 数据仓库<／p>
 * <p>Description: FIXME 随着项目的增大，会造成这个类很大<／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/28
 */

public class DataManager {

    private ServiceManager mServiceManager;
    private DatabaseManager mDatabaseManager;
    private PreferencesManager mPreferencesManager;
    private MemoryManager mMemoryManager;

    public DataManager(ServiceManager serviceManager, DatabaseManager databaseManager,
                       PreferencesManager preferencesManager, MemoryManager memoryManager) {
        mServiceManager = serviceManager;
        mDatabaseManager = databaseManager;
        mPreferencesManager = preferencesManager;
        mMemoryManager = memoryManager;
    }

    public Observable<LatestNews> getLatestNews() {
        return Observable.concat(
                mDatabaseManager.getLatestNews(),
                mServiceManager.getLatestNews())
                .filter(latestNewsResult -> latestNewsResult != null && latestNewsResult.mData != null)
                .doOnNext(latestNewsResult -> {
                    if(latestNewsResult.mType == DataSource.DataSourceType.NETWORK) {
                        //TODO 缓存数据
                    }
                })
                .flatMap(new Func1<Result<LatestNews>, Observable<LatestNews>>() {
                    @Override
                    public Observable<LatestNews> call(Result<LatestNews> latestNewsResult) {
                        return Observable.just(latestNewsResult.mData);
                    }
                });
    }
}
