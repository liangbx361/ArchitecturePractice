package com.android.architecture.practice.data;

import com.android.architecture.practice.data.remote.ClientApiBuilder;
import com.android.architecture.practice.data.remote.ZhiHuApi;
import com.android.architecture.practice.data.remote.ZhiHuService;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/30
 */

public class DataManager {

    private static volatile DataManager sDataManager;

    private ZhiHuService mZhiHuService;

    public DataManager(ZhiHuService zhiHuService) {
        mZhiHuService = zhiHuService;
    }

    public static DataManager getInstance(String cacheDir) {
        if (sDataManager == null) {
            synchronized (DataManager.class) {
                ZhiHuApi zhiHuApi = ClientApiBuilder.build(
                        ZhiHuApi.class,
                        ZhiHuApi.BASE_URL,
                        cacheDir,
                        null);
                sDataManager = new DataManager(new ZhiHuService(zhiHuApi));
            }
        }

        return sDataManager;
    }

    public ZhiHuService getZhiHuService() {
        return mZhiHuService;
    }
}
