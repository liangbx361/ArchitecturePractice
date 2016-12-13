package com.android.architecture.practice.log;

import android.util.Log;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/10/8
 */

public class DefaultLoggerImpl implements Logger {

    @Override
    public void v(String tag, String message) {
        Log.v(tag, message);
    }

    @Override
    public void d(String tag, String message) {
        if(isDebug()) {
            Log.d(tag, message);
        }
    }

    @Override
    public void i(String tag, String message) {
        Log.i(tag, message);
    }

    @Override
    public void w(String tag, String message) {
        Log.w(tag, message);
    }

    @Override
    public void w(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void e(String tag, String message) {
        Log.e(tag, message);
    }

    @Override
    public void e(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public boolean isDebug() {
        return false;
    }
}
