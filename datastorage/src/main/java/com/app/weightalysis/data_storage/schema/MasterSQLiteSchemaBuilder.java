package com.app.weightalysis.data_storage.schema;

import java.util.ArrayList;

/**
 * Created by Aakash Singh on 24-11-2016.
 */

public class MasterSQLiteSchemaBuilder {

    public ArrayList<String> getPrimaryDBCreateTableQueries() {
        ArrayList<String> queries = new ArrayList();
        queries.add(WeightSchemaBuilder.CREATE_TABLE);
        return queries;
    }

    public ArrayList<String> getPrimaryDBDropTableQueries() {
        ArrayList<String> queries = new ArrayList();
        queries.add(WeightSchemaBuilder.DROP_TABLE);
        return queries;
    }

    public ArrayList<String> getUpdateDBQueries(int oldVersion, int newVersion) {
        ArrayList<String> queries = null;
        if (oldVersion <= newVersion) {
            queries = new ArrayList();
            queries.add(WeightSchemaBuilder.DROP_TABLE);
            queries.add(WeightSchemaBuilder.CREATE_TABLE);
        }
        return queries;
    }

}
