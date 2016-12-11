package com.app.graph;

import android.database.Cursor;
import android.view.View;

import com.app.graph.data_storage.GraphDataDataStorageProvider;
import com.app.graph.inheritance.CustomDataPoint;
import com.app.graph.model.GraphType;
import com.app.weightalysis.data_storage.accessor.WeightAccessor;
import com.app.weightalysis.data_storage.model.UserBean;
import com.app.weightalysis.data_storage.schema.WeightSchemaBuilder;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public interface GraphContract {
    void drawGraphByDay(GraphView graphView, String month, String year);

    void drawGraphByWeek(GraphView graphView, String month, String year);

    void drawGraphByMonth(GraphView graphView, String year);

    UserBean createUserFromCursor(Cursor cursor);

    ArrayList<UserBean> getUserBeanArrayList(Cursor cursor);

    CustomDataPoint[] initializeDataPointsFromList(CustomDataPoint[] dataPoint, ArrayList<UserBean> userBeanArrayList, GraphType graphType);

    public Cursor getDayCursor(int month, int year);

    public Cursor getWeekCursor(int month, int year);

    public Cursor getMonthCursor(int month, int year);

    public ArrayList<UserBean> getMonthDataPoint(int year);

    public int getMonthAvgWeight(int totalWeight, int count);
}
