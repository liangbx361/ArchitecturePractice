package com.android.architecture.practice.data.log;

import com.android.architecture.practice.log.DefaultLoggerImpl;
import com.android.architecture.practice.log.Logger;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/11/30
 */

public class DataLogger {

    public static volatile Logger sLogger;

    public static Logger get() {
        if (sLogger == null) {
            synchronized (DataLogger.class) {
                if (sLogger == null) {
                    sLogger = new DefaultLoggerImpl();
                }
            }
        }
        return sLogger;
    }

    public static synchronized void set(Logger logger) {
        sLogger = logger;
    }
}
