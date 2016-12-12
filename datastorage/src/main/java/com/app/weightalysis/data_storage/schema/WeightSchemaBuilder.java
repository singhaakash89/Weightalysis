package com.app.weightalysis.data_storage.schema;

import static com.app.weightalysis.data_storage.accessor.WeightAccessor.DATE;
import static com.app.weightalysis.data_storage.accessor.WeightAccessor.ID;
import static com.app.weightalysis.data_storage.accessor.WeightAccessor.MONTH;
import static com.app.weightalysis.data_storage.accessor.WeightAccessor.YEAR;
import static com.app.weightalysis.data_storage.accessor.WeightAccessor.WEIGHT;

/**
 * Created by Aakash Singh on 24-11-2016.
 */

public class WeightSchemaBuilder {

    public static final String TABLE_NAME = "weight_table";

    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            WEIGHT + " INTEGER NOT NULL , " +
            DATE + " INTEGER NOT NULL , " +
            MONTH + " INTEGER NOT NULL , " +
            YEAR + " INTEGER NOT NULL ," +
            "UNIQUE(" + DATE + "," + MONTH + "));";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getCreateTable() {
        return CREATE_TABLE;
    }

    public static String getDropTable() {
        return DROP_TABLE;
    }
}
