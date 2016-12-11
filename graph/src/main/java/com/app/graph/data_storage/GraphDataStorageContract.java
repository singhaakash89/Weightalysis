package com.app.graph.data_storage;

import android.database.Cursor;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public interface GraphDataStorageContract {

    public long insertData(int weight, int date, int month, int year);

    Cursor queryData();

    Cursor queryForSelectedCol(String tableName, String[] columns, String whereCol, int arg1);

    Cursor queryForSelectedCols(String tableName, String[] columns, String whereCol1, String whereCol2, int arg1, int arg2);
}
