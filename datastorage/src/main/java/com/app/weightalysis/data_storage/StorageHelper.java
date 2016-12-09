package com.app.weightalysis.data_storage;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Aakash Singh on 24-11-2016.
 */

public interface StorageHelper {

    public long insertToDB(String table_name, ContentValues contentValues);

    public long updateDB();

    public Cursor queryFromDB(String tableName, String[] columns);

    public int deleteFromDB();

}
