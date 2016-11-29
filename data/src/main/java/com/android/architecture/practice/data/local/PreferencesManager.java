package com.android.architecture.practice.data.local;

import com.android.architecture.practice.data.DataSource;
import com.android.architecture.practice.data.model.LatestNews;
import com.android.architecture.practice.data.model.Result;

import rx.Observable;

/**
 * <p>Title: 文件缓存管理器<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/29
 */

public class PreferencesManager implements DataSource {

    @Override
    public Observable<Result<LatestNews>> getLatestNews() {
        return null;
    }
}
