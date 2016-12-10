package com.app.graph.data_storage;

import android.database.Cursor;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public interface GraphDataStorageContract {

    long insertData(String weight, String date, String month, String week);

    Cursor queryData();
}
