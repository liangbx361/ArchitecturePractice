package com.android.architecture.practice.data.network;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>Title: 缓存控制器<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/12/13
 */

public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Boolean isCacheOnly = Boolean.valueOf(request.header("CacheOnly"));

        if(isCacheOnly) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        } else {
            request = request.newBuilder().removeHeader("CacheOnly").build();
        }

        return chain.proceed(request);
    }
}
