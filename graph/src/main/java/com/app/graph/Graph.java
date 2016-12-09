package com.app.graph;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public interface Graph {

    long insertData(String date, String month, String year);

    void queryData();

    void drawGraphByDate();

    void drawGraphByWeek();

    void drawGraphByMonth();
}
