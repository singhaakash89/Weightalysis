package com.app.graph.data_storage;

import android.content.ContentValues;
import android.database.Cursor;

import com.app.weightalysis.data_storage.StandardStorageHelper;
import com.app.weightalysis.data_storage.StorageHelper;
import com.app.weightalysis.data_storage.accessor.WeightAccessor;
import com.app.weightalysis.data_storage.model.WeightBean;
import com.app.weightalysis.data_storage.schema.WeightSchemaBuilder;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public class GraphDataDataStorageProvider implements GraphDataStorageContract {
    private StorageHelper storageHelper = StandardStorageHelper.getInstance();

    public long insertData(int weight, int date, int month, int year) {
        WeightBean weightBean = new WeightBean();
        weightBean.setWeight(weight);
        weightBean.setDate(date);
        weightBean.setMonth(month);
        weightBean.setYear(year);
        ContentValues cv = weightBean.getContentValues();
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

    @Override
    public Cursor queryForSelectedCol(String tableName, String[] columns, String whereCol, int arg1) {
        Cursor cursor = storageHelper.queryForSelectedCol(WeightSchemaBuilder.TABLE_NAME, WeightAccessor.getTableProjection(), whereCol, arg1);
        if (null == cursor)
            throw new NullPointerException("Cursor is null");
        else
            return cursor;
    }

    @Override
    public Cursor queryForSelectedCols(String tableName, String[] columns, String whereCol1, String whereCol2, int arg1, int arg2) {
        Cursor cursor = storageHelper.queryForSelectedCols(WeightSchemaBuilder.TABLE_NAME, WeightAccessor.getTableProjection(), whereCol1, whereCol2, arg1, arg2);
        if (null == cursor)
            throw new NullPointerException("Cursor is null");
        else
            return cursor;

    }

    @Override
    public Cursor queryForWeek1(String tableName, String[] columns, String whereCol1, int arg1) {
        Cursor cursor = storageHelper.queryForWeek1(WeightSchemaBuilder.TABLE_NAME, WeightAccessor.getTableProjection(), whereCol1, arg1);
        if (null == cursor)
            throw new NullPointerException("Cursor is null");
        else
            return cursor;
    }

    @Override
    public Cursor queryForWeek2and3(String tableName, String[] columns, String whereCol1, int arg1, int arg2, int arg3) {
        Cursor cursor = storageHelper.queryForWeek2and3(WeightSchemaBuilder.TABLE_NAME, WeightAccessor.getTableProjection(), whereCol1, arg1, arg2, arg3);
        if (null == cursor)
            throw new NullPointerException("Cursor is null");
        else
            return cursor;
    }

    @Override
    public Cursor queryForWeek4(String tableName, String[] columns, String whereCol1, int arg1) {
        Cursor cursor = storageHelper.queryForWeek4(WeightSchemaBuilder.TABLE_NAME, WeightAccessor.getTableProjection(), whereCol1, arg1);
        if (null == cursor)
            throw new NullPointerException("Cursor is null");
        else
            return cursor;
    }


}
