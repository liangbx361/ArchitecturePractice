package com.android.architecture.practice.data.remote;

import com.android.architecture.practice.data.DataManager;
import com.android.architecture.practice.data.log.DataLogger;
import com.android.architecture.practice.data.model.ApiResult;
import com.android.architecture.practice.data.model.LatestNews;
import com.android.architecture.practice.log.DefaultLoggerImpl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import rx.functions.Action1;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/30
 */
public class ZhiHuServiceTest {

    @BeforeClass
    public static void setUp() throws Exception {
        DataLogger.set(new DefaultLoggerImpl() {
            @Override
            public boolean isDebug() {
                return true;
            }
        });
    }

    @Test
    public void getLatestNews() throws Exception {
        DataManager dataManager = DataManager.getInstance("./build/cache");
        ZhiHuService zhiHuService = dataManager.getZhiHuService();
        zhiHuService.getLatestNews()
                .subscribe(new Action1<ApiResult<LatestNews>>() {
                    @Override
                    public void call(ApiResult<LatestNews> latestNewsApiResult) {
                        Assert.assertNotEquals(null, latestNewsApiResult);
                    }
                });
    }

}