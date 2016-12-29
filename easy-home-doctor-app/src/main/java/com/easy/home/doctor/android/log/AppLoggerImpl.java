package com.easy.home.doctor.android.log;

import com.android.architecture.practice.log.DefaultLoggerImpl;
import com.easy.home.doctor.android.BuildConfig;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/12/29
 */

public class AppLoggerImpl extends DefaultLoggerImpl {

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
