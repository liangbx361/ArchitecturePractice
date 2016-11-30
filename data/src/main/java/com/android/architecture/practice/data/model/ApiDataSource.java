package com.android.architecture.practice.data.model;

/**
 * <p>Title: API 数据源类型<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/30
 */

public enum ApiDataSource {
    /**
     * 网络类型表示较新的数据
     */
    Network,

    /**
     * 缓存类型表示旧的数据
     */
    CACHE
}
