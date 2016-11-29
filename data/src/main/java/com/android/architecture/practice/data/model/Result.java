package com.android.architecture.practice.data.model;

import com.android.architecture.practice.data.DataSource;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/29
 */

public class Result<T> {

    public DataSource.DataSourceType mType;

    public T mData;

    public Result(DataSource.DataSourceType type, T data) {
        mType = type;
        mData = data;
    }
}
