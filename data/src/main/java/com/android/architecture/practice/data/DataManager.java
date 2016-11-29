package com.android.architecture.practice.data;

import com.android.architecture.practice.data.cache.DatabaseCache;
import com.android.architecture.practice.data.cache.MemoryCache;
import com.android.architecture.practice.data.cache.PreferencesCache;
import com.android.architecture.practice.data.model.LatestNews;
import com.android.architecture.practice.data.model.Result;
import com.android.architecture.practice.data.remote.ClientApi;
import com.android.architecture.practice.data.remote.ClientApiBuilder;
import com.android.architecture.practice.data.remote.ServiceManager;

import retrofit.RequestInterceptor;
import retrofit.client.OkClient;
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

    private static volatile DataManager sDataManager;

    private ServiceManager mServiceManager;
    private DatabaseCache mDatabaseManager;
    private PreferencesCache mPreferencesManager;
    private MemoryCache mMemoryManager;

    public static DataManager getInstance() {
        if (sDataManager == null) {
            synchronized (DataManager.class) {
                ClientApi clientApi = ClientApiBuilder.buildApi(ClientApi.class, ClientApi.BASE_URL, new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {

                    }
                }, new OkClient());
                sDataManager = new DataManager(
                        new ServiceManager(clientApi),
                        new DatabaseCache(),
                        new PreferencesCache(),
                        new MemoryCache()
                );
            }
        }

        return sDataManager;
    }

    public DataManager(ServiceManager serviceManager, DatabaseCache databaseManager,
                       PreferencesCache preferencesManager, MemoryCache memoryManager) {
        mServiceManager = serviceManager;
        mDatabaseManager = databaseManager;
        mPreferencesManager = preferencesManager;
        mMemoryManager = memoryManager;
    }

    public Observable<Result<LatestNews>> getLatestNews3() {
        return mServiceManager.getLatestNews();
    }

    public LatestNews getLatestNews4() {
        return mServiceManager.getLatestNews2();
    }

    public Observable<LatestNews> getLatestNews() {
//        return Observable.concat(
//                mDatabaseManager.getLatestNews(),
//                mServiceManager.getLatestNews())
//                .filter(latestNewsResult -> latestNewsResult != null && latestNewsResult.mData != null)
//                .doOnNext(latestNewsResult -> {
//                    if (latestNewsResult.mType == DataSource.DataSourceType.NETWORK) {
//                        //TODO 缓存数据
//                    }
//                })
//                .flatMap(new Func1<Result<LatestNews>, Observable<LatestNews>>() {
//                    @Override
//                    public Observable<LatestNews> call(Result<LatestNews> latestNewsResult) {
//                        System.out.println("flatMap -->");
//                        return Observable.just(latestNewsResult.mData);
//                    }
//                });

        return mServiceManager.getLatestNews()
                .flatMap(new Func1<Result<LatestNews>, Observable<LatestNews>>() {
                    @Override
                    public Observable<LatestNews> call(Result<LatestNews> latestNewsResult) {
                        System.out.println("flatMap -->");
                        return Observable.just(latestNewsResult.mData);
                    }
                });
    }

    /**
     * @return
     */
    public Observable<LatestNews> getLastNews2() {
        return Observable.zip(
                mMemoryManager.getLatestNews(),
                mDatabaseManager.getLatestNews(),
                mPreferencesManager.getLatestNews(),
                (latestNewsResult, latestNewsResult2, latestNewsResult3) ->
                        isNotEmpty(latestNewsResult) ? latestNewsResult :
                                isNotEmpty(latestNewsResult2) ? latestNewsResult2 :
                                        latestNewsResult3
        ).concatWith(mServiceManager.getLatestNews())
                .filter(latestNewsResult -> latestNewsResult != null && latestNewsResult.mData != null)
                .doOnNext(latestNewsResult -> {
                    if (latestNewsResult.mType == DataSource.DataSourceType.NETWORK) {
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

    private boolean isNotEmpty(Result result) {
        return result != null && result.mData != null;
    }
}
