package com.android.architecture.practice.data.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.architecture.practice.data.log.DataLogger;
import com.android.architecture.practice.log.Logger;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/28
 */
public class ClientApiBuilder {

    private final static String defaultCacheName = "ClientApi";
    private final static long defaultCacheSize = 1024 * 1024 * 50;
    private final static TimeoutConfig defaultTimeoutConfig = new TimeoutConfig(2000, 2000, 2000);
    private final static boolean defaultRetryOnConnectionFailure = false;

    /**
     * 精简版本的构建器
     */
    public static <T> T build(
            @NonNull Class<T> clazz,
            @NonNull String baseUrl,
            @NonNull String cacheDir,
            @Nullable Interceptor... interceptors) {
        return build(
                clazz,
                baseUrl,
                cacheDir,
                DataLogger.get(),
                defaultCacheName,
                defaultCacheSize,
                defaultTimeoutConfig,
                defaultRetryOnConnectionFailure,
                interceptors);
    }

    /**
     * 完整参数的构建起
     */
    public static <T> T build(
            @NonNull Class<T> clazz,
            @NonNull String baseUrl,
            @NonNull String cacheDir,
            @NonNull Logger logger,
            @NonNull String cacheName,
            long cacheSize,
            @Nullable TimeoutConfig timeoutConfig,
            boolean retryOnConnectionFailure,
            @Nullable Interceptor... interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //日志配置
        if(logger.isDebug()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        //缓存配置
        File cacheFile = new File(cacheDir, cacheName);
        Cache cache = new Cache(cacheFile, cacheSize);
        builder.cache(cache);

        //拦截器配置
        if(interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        //超时时间
        if(timeoutConfig != null) {
            builder.connectTimeout(timeoutConfig.connectTimeout, TimeUnit.MILLISECONDS);
            builder.readTimeout(timeoutConfig.readTimeout, TimeUnit.MILLISECONDS);
            builder.writeTimeout(timeoutConfig.writeTimeout, TimeUnit.MILLISECONDS);
        }

        //错误重连
        builder.retryOnConnectionFailure(retryOnConnectionFailure);

        OkHttpClient okHttpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(clazz);
    }

    public static class TimeoutConfig {
        public long connectTimeout;
        public long readTimeout;
        public long writeTimeout;

        public TimeoutConfig(long connectTimeout, long readTimeout, long writeTimeout) {
            this.connectTimeout = connectTimeout;
            this.readTimeout = readTimeout;
            this.writeTimeout = writeTimeout;
        }
    }
}
