package com.app.graph;

import android.database.Cursor;
import android.view.View;

import com.app.graph.inheritance.CustomDataPoint;
import com.app.graph.model.GraphType;
import com.app.weightalysis.data_storage.model.UserBean;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public interface GraphContract {
    void drawGraphByDate(GraphView graphView);

    void drawGraphByWeek(GraphView graphView);

    void drawGraphByMonth(GraphView graphView);

    UserBean createUserFromCursor(Cursor cursor);

    ArrayList<UserBean> getUserBeanArrayList();

    CustomDataPoint [] initializeDataPointsFromList(CustomDataPoint[] dataPoint, ArrayList<UserBean> userBeanArrayList, GraphType graphType);
}
