package com.android.architecture.practice.data.cache;

import com.android.architecture.practice.data.DataSource;
import com.android.architecture.practice.data.model.LatestNews;
import com.android.architecture.practice.data.model.ApiResult;

import rx.Observable;

/**
 * <p>Title: 内存缓存管理器<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/29
 */

public class MemoryCache implements DataSource{

    @Override
    public Observable<ApiResult<LatestNews>> getLatestNews() {
        return null;
    }
}
