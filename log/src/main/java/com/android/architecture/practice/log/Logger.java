package com.android.architecture.practice.log;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: 网龙公司<／p>
 *
 * @author liangbx
 * @version 1.0
 * @data 2016/5/31.
 */
public interface Logger {

    void v(String tag, String message);

    void d(String tag, String message);

    void i(String tag, String message);

    void w(String tag, String message);

    void w(Throwable throwable);

    void e(String tag, String message);

    void e(Throwable throwable);

    boolean isDebug();

}
