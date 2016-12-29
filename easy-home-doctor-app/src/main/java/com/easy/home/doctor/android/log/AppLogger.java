package com.easy.home.doctor.android.log;

import com.android.architecture.practice.log.Logger;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/12/29
 */

public class AppLogger {

    private static volatile Logger sLogger;

    public static Logger get() {
        if (sLogger == null) {
            synchronized (AppLogger.class) {
                if (sLogger == null) {
                    sLogger = new AppLoggerImpl();
                }
            }
        }
        return sLogger;
    }
}
