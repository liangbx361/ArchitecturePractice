package com.android.architecture.practice.data.db;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = DataCacheDatabase.NAME, version = DataCacheDatabase.VERSION)
public class DataCacheDatabase {

    static final String NAME = "DataCache";
    static final int VERSION = 1;

}
