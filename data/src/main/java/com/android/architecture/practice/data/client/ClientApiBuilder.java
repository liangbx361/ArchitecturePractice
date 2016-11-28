package com.android.architecture.practice.data.client;


import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.converter.Converter;
import retrofit.converter.JacksonConverter;

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

    public static <T> T build(Class<T> clazz,
                              String baseUrl,
                              RequestInterceptor interceptor,
                              Client client) {
        return buildApi(clazz,
                client,
                baseUrl,
                new DefaultLogger(),
                new JacksonConverter(),
                interceptor,
                new NetworkErrorHandler());
    }

    public static <T> T buildApi(Class<T> clazz,
                                 Client client,
                                 String baseUrl,
                                 RestAdapter.Log logger,
                                 Converter converter,
                                 RequestInterceptor interceptor,
                                 retrofit.ErrorHandler errorHandler) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setLog(logger)
                .setConverter(converter)
                .setClient(client)
                .setRequestInterceptor(interceptor)
                .setErrorHandler(errorHandler)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return adapter.create(clazz);
    }

    private static class DefaultLogger implements RestAdapter.Log {

        @Override
        public void log(String message) {

        }
    }
}
