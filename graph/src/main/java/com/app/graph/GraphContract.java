package com.app.graph;

import android.database.Cursor;

import com.app.graph.inheritance.CustomDataPoint;
import com.app.graph.model.GraphType;
import com.app.weightalysis.data_storage.model.WeightBean;
import com.jjoe64.graphview.GraphView;

import java.util.ArrayList;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public interface GraphContract {
    void resetGraph(GraphView graphView);

    void drawGraphByDay(GraphView graphView, String month, String year);

    void drawGraphByWeek(GraphView graphView, String month, String year);

    void drawGraphByMonth(GraphView graphView, String year);

    WeightBean createUserFromCursor(Cursor cursor);

    ArrayList<WeightBean> getUserBeanArrayList(Cursor cursor);

    CustomDataPoint[] initializeDataPointsFromList(CustomDataPoint[] dataPoint, ArrayList<WeightBean> weightBeanArrayList, GraphType graphType);

    public Cursor getDayCursor(int month, int year);

    public Cursor getWeek1Cursor(int month);

    public Cursor getWeek2and3Cursor(int month, int date1, int date2);

    public Cursor getWeek4Cursor(int month);

    public Cursor getMonthCursor(int month, int year);

    public ArrayList<WeightBean> getMonthDataPoint(int year);

    public int getAvgWeight(int totalWeight, int count);
}
