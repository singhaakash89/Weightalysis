package com.app.graph;

import android.content.ContentValues;
import android.widget.Toast;

import com.app.weightalysis.data_storage.StandardStorageHelper;
import com.app.weightalysis.data_storage.StorageHelper;
import com.app.weightalysis.data_storage.model.User;
import com.app.weightalysis.data_storage.schema.WeightSchemaBuilder;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public class StandardGraph implements Graph {
    private StorageHelper storageHelper;

    @Override
    public long insertData(String date, String month, String year) {
        User user = new User();
        user.setDate(date);
        user.setMonth(month);
        user.setYear(year);
        ContentValues cv = user.getContentValues();
        storageHelper = StandardStorageHelper.getInstance();
        long id = storageHelper.insertToDB(WeightSchemaBuilder.TABLE_NAME,cv);
        return id;
    }

    @Override
    public void queryData() {

    }

    @Override
    public void drawGraphByDate() {

    }

    @Override
    public void drawGraphByWeek() {

    }

    @Override
    public void drawGraphByMonth() {

    }


}
