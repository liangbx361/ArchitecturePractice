package com.android.architecture.practice.data.remote;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/28
 */

public class NetworkErrorHandler implements ErrorHandler {

    @Override
    public Throwable handleError(RetrofitError cause) {

        switch (cause.getKind()) {
            case NETWORK:
                break;

            case HTTP:
                break;

            case CONVERSION:
                break;

            case UNEXPECTED:
            default:
                break;
        }

        return null;
    }
}
