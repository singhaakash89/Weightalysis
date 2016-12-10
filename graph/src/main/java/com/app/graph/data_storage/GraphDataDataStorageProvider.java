package com.app.graph.data_storage;

import android.content.ContentValues;
import android.database.Cursor;

import com.app.weightalysis.data_storage.StandardStorageHelper;
import com.app.weightalysis.data_storage.StorageHelper;
import com.app.weightalysis.data_storage.accessor.WeightAccessor;
import com.app.weightalysis.data_storage.model.UserBean;
import com.app.weightalysis.data_storage.schema.WeightSchemaBuilder;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public class GraphDataDataStorageProvider implements GraphDataStorageContract {
    private StorageHelper storageHelper = StandardStorageHelper.getInstance();

    public long insertData(String weight, String date, String month, String week) {
        UserBean userBean = new UserBean();
        userBean.setWeight(weight);
        userBean.setDate(date);
        userBean.setMonth(month);
        userBean.setWeek(week);
        ContentValues cv = userBean.getContentValues();
        long id = storageHelper.insertToDB(WeightSchemaBuilder.TABLE_NAME, cv);
        return id;
    }

    public Cursor queryData() {
        Cursor cursor = storageHelper.queryFromDB(WeightSchemaBuilder.TABLE_NAME, WeightAccessor.getTableProjection());
        if (null == cursor)
            throw new NullPointerException("Cursor is null");
        else
            return cursor;
    }
}
