package com.android.architecture.practice.data;

import android.content.Context;

import com.android.architecture.practice.data.config.ConfigServiceImpl;
import com.android.architecture.practice.data.network.NetworkServiceImpl;

/**
 * <p>Title: 负责数据管理<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/30
 */

public class DataManager {

    private static volatile DataManager sDataManager;

    private NetworkService mNetworkService;
    private ConfigService mConfigService;

    public static DataManager getInstance(Context context) {
        if(sDataManager == null) {
            synchronized (DataManager.class) {
                if(sDataManager == null) {
                    sDataManager = new DataManager(
                        new NetworkServiceImpl(context),
                        new ConfigServiceImpl()
                    );
                }
            }
        }

        return sDataManager;
    }

    public DataManager(NetworkService networkService, ConfigService configService) {
        mNetworkService = networkService;
        mConfigService = configService;
    }

    public NetworkService getNetworkService() {
        return mNetworkService;
    }

    public ConfigService getConfigService() {
        return mConfigService;
    }
}
