package com.android.architecture.practice.data;

import com.android.architecture.practice.data.model.LatestNews;
import com.android.architecture.practice.data.model.Result;

import rx.Observable;

/**
 * <p>Title: 数据源<／p>
 * <p>Description: 指定获取数据的接口<／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/28
 */

public interface DataSource {

    enum DataSourceType {
        NETWORK,
        CACHE
    }

    /**
     * 获取知乎最新消息
     * TODO 或者可使用注解定义缓存的层级
     */
    Observable<Result<LatestNews>> getLatestNews();
}
