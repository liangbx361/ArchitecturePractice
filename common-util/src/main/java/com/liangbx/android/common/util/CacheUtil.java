package com.liangbx.android.common.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/12/13
 */

public class CacheUtil {

    public static String getExternalCache(Context context) {

        if(!isMounted()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        // In some case, even the sd card is mounted,
        // getExternalCacheDir will return null
        // may be it is nearly full.
        File file = context.getExternalCacheDir();

        if (file != null) {
            sb.append(file.getAbsolutePath()).append(File.separator);
        } else {
            sb.append(Environment.getExternalStorageDirectory().getPath())
                    .append("/Android/data/").append(context.getPackageName()).append("/cache/");
        }

        return sb.toString();
    }

    /**
     * Check if the primary "external" storage device is available.
     *
     * @return
     */
    public static boolean isMounted() {
        String state = Environment.getExternalStorageState();
        return state != null && state.equals(Environment.MEDIA_MOUNTED);
    }
}
