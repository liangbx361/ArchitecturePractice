package com.android.architecture.practice.data;

import android.content.Context;

import com.android.architecture.practice.data.config.ConfigService;
import com.android.architecture.practice.data.config.ConfigServiceImpl;
import com.android.architecture.practice.data.network.NetworkService;
import com.android.architecture.practice.data.network.NetworkServiceImpl;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

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
                    sDataManager.initDbFlow(context);
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

    private void initDbFlow(Context context) {
        FlowManager.init(new FlowConfig.Builder(context).build());
//        FlowManager.initModule(com.raizlabs.android.dbflow.config.DataCacheDatabaseHolder.class);
    }
}
