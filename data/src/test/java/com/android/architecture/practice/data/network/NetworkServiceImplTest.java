package com.android.architecture.practice.data.network;

import com.android.architecture.practice.data.log.DataLogger;
import com.android.architecture.practice.log.DefaultLoggerImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import okhttp3.Interceptor;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/12/13
 */
public class NetworkServiceImplTest {

    private NetworkService mNetworkService;

    @BeforeClass
    public static void beforeClass() {
        DataLogger.set(new DefaultLoggerImpl() {

            @Override
            public boolean isDebug() {
                return true;
            }
        });
    }

    @Before
    public void setUp() throws Exception {
        mNetworkService = new NetworkServiceImpl(
                ClientApiBuilder.build(
                        NetworkApi.class,
                        NetworkApi.BASE_URL,
                        "build/cache",
                        (Interceptor) null
                ));
    }

    @Test
    public void getLatestNews() throws Exception {
        mNetworkService.getLatestNews()
                .subscribe(latestNews -> {
                    Assert.assertNotEquals(null, latestNews);
                }, Throwable::printStackTrace);
    }

}