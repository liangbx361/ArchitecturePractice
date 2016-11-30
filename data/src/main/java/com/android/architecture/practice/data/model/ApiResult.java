package com.android.architecture.practice.data.model;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/29
 */

public class ApiResult<T> {

    public ApiDataSource mApiDataSource;

    public T mData;

    public ApiResult(ApiDataSource type, T data) {
        mApiDataSource = type;
        mData = data;
    }
}
