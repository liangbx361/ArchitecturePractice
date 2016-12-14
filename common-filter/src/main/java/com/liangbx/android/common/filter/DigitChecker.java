package com.liangbx.android.common.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/10/31
 */

public class DigitChecker {

    public static boolean isDigit(String content) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(content);
        return m.matches();
    }
}
