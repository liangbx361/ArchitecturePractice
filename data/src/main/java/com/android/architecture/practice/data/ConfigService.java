package com.android.architecture.practice.data;

/**
 * <p>Title: <／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) 2016<／p>
 * <p>Company: NetDragon<／p>
 *
 * @author liangbx
 * @version 2016/12/13
 */

public interface ConfigService {

    String getUserName();

    boolean saveUserName(String userName);

    String getPassword();

    boolean savePassword(String password);
}
