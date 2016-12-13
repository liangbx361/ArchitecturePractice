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

public class Result<T> {

    public SourceType mSourceType;

    public T mData;

    public Result(SourceType type, T data) {
        mSourceType = type;
        mData = data;
    }
}
