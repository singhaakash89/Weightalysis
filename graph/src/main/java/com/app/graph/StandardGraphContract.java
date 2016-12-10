package com.app.graph;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;

import com.app.graph.data_storage.GraphDataDataStorageProvider;
import com.app.graph.data_storage.GraphDataStorageContract;
import com.app.graph.inheritance.CustomDataPoint;
import com.app.graph.model.Date;
import com.app.graph.model.GraphType;
import com.app.graph.model.Month;
import com.app.graph.model.Weight;
import com.app.weightalysis.data_storage.accessor.WeightAccessor;
import com.app.weightalysis.data_storage.model.UserBean;
import com.app.weightalysis.logger.Logger;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public class StandardGraphContract implements GraphContract {

    private GraphDataStorageContract graphDataStorageContract;
    private ArrayList<UserBean> userBeanArrayList;
    private Context context;

    public StandardGraphContract(Context context) {
        this.context = context;
    }

    @Override
    public void drawGraphByDate(GraphView graphView) {
        //reseting graph
        graphView.removeAllSeries();
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter());

        GridLabelRenderer gridLabel = graphView.getGridLabelRenderer();
        userBeanArrayList = getUserBeanArrayList();

        CustomDataPoint[] customDataPoint = new CustomDataPoint[userBeanArrayList.size()];
        customDataPoint = initializeDataPointsFromList(customDataPoint, userBeanArrayList, GraphType.DAY);
        LineGraphSeries<CustomDataPoint> lineGraphSeries = new LineGraphSeries<>(customDataPoint);
        PointsGraphSeries<CustomDataPoint> pointLineGraphSeries = new PointsGraphSeries<>(customDataPoint);

        graphView.setTitle("Monthly Weight Chart");
        graphView.getViewport().setScrollable(true);
        gridLabel.setVerticalAxisTitle("Weight");
        gridLabel.setHorizontalAxisTitle("Day");
        gridLabel.setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        gridLabel.setGridColor(context.getResources().getColor(R.color.navy_blue_light));

        //X-AXIS
        graphView.getViewport().setXAxisBoundsManual(true);
        //TO SEE THE EXACT NUMBER OF LABELS ON X-AXIS AT ONCE
        graphView.getGridLabelRenderer().setNumHorizontalLabels(6);
        graphView.getViewport().setMinX(1);
        //TO SEE THE VIEW WINDOW CONTAINING UPTO 6 LABELS AT ONCE IN SINGLE WINDOW BUT MIGHT BE WITH NO DIGITS.
        graphView.getViewport().setMaxX(6);
        graphView.addSeries(lineGraphSeries);
        graphView.addSeries(pointLineGraphSeries);
        graphView.setVisibility(View.VISIBLE);

    }

    @Override
    public void drawGraphByWeek(GraphView graphView) {
        //reseting graph
        graphView.removeAllSeries();
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter());

        GridLabelRenderer gridLabel = graphView.getGridLabelRenderer();
        userBeanArrayList = getUserBeanArrayList();
        LineGraphSeries<DataPoint> lineGraphSeries = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(1, 45),
                new DataPoint(2, 50),
                new DataPoint(3, 60),
                new DataPoint(4, 80)
        });
        PointsGraphSeries<DataPoint> pointLineGraphSeries = new PointsGraphSeries<>(new DataPoint[]{
                new DataPoint(1, 45),
                new DataPoint(2, 50),
                new DataPoint(3, 60),
                new DataPoint(4, 80)
        });
        graphView.addSeries(lineGraphSeries);
        graphView.addSeries(pointLineGraphSeries);

        //X-AXIS
        graphView.getViewport().setXAxisBoundsManual(true);
        //TO SEE THE EXACT NUMBER OF LABELS ON X-AXIS
        graphView.getGridLabelRenderer().setNumHorizontalLabels(4);
        graphView.getViewport().setMinX(1);
        //TO SEE THE VIEW WINDOW CONTAINING UPTO 6 LABELS AT ONCE IN SINGLE WINDOW BUT MIGHT BE WITH NO DIGITS.
        graphView.getViewport().setMaxX(4);

        //settings
        graphView.getViewport().setScrollable(true);
        graphView.setTitle("Weekly Weight Chart");
        gridLabel.setVerticalAxisTitle("Weight");
        gridLabel.setHorizontalAxisTitle("Weeks");
        gridLabel.setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        gridLabel.setGridColor(context.getResources().getColor(R.color.navy_blue_light));

        //Label rendering
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
        staticLabelsFormatter.setHorizontalLabels(new String[]{"week1", "week2", "week3", "week4"});
        staticLabelsFormatter.setVerticalLabels(new String[]{"40", "50", "60", "70", "80", "90"});
        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graphView.setVisibility(View.VISIBLE);

    }

    @Override
    public void drawGraphByMonth(GraphView graphView) {
        //reseting graph
        graphView.removeAllSeries();
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter());

        GridLabelRenderer gridLabel = graphView.getGridLabelRenderer();
        userBeanArrayList = getUserBeanArrayList();

        CustomDataPoint[] customDataPoint = new CustomDataPoint[userBeanArrayList.size()];
        customDataPoint = initializeDataPointsFromList(customDataPoint, userBeanArrayList, GraphType.MONTH);
        LineGraphSeries<CustomDataPoint> lineGraphSeries = new LineGraphSeries<>(customDataPoint);
        PointsGraphSeries<CustomDataPoint> pointLineGraphSeries = new PointsGraphSeries<>(customDataPoint);

        graphView.setTitle("Monthly Weight Chart");
        graphView.getViewport().setScrollable(true);
        gridLabel.setVerticalAxisTitle("Weight");
        gridLabel.setHorizontalAxisTitle("Months");
        gridLabel.setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        gridLabel.setGridColor(context.getResources().getColor(R.color.navy_blue_light));

//        //Label rendering
//        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
//        staticLabelsFormatter.setHorizontalLabels(new Month().getMonths());
//        staticLabelsFormatter.setVerticalLabels(new Weight().getWeights());
//        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        //X-AXIS
        graphView.getViewport().setXAxisBoundsManual(true);
        //TO SEE THE EXACT NUMBER OF LABELS ON X-AXIS AT ONCE
        graphView.getGridLabelRenderer().setNumHorizontalLabels(6);
        graphView.getViewport().setMinX(1);
        //TO SEE THE VIEW WINDOW CONTAINING UPTO 6 LABELS AT ONCE IN SINGLE WINDOW BUT MIGHT BE WITH NO DIGITS.
        graphView.getViewport().setMaxX(6);
        graphView.addSeries(lineGraphSeries);
        graphView.addSeries(pointLineGraphSeries);
        graphView.setVisibility(View.VISIBLE);

    }

    @Override
    public UserBean createUserFromCursor(Cursor cursor) {
        UserBean userBean = new UserBean();
        String weight = cursor.getString(cursor.getColumnIndex(WeightAccessor.WEIGHT));
        String date = cursor.getString(cursor.getColumnIndex(WeightAccessor.DATE));
        String week = cursor.getString(cursor.getColumnIndex(WeightAccessor.WEEK));
        String month = cursor.getString(cursor.getColumnIndex(WeightAccessor.MONTH));
        userBean.setWeight(weight);
        userBean.setDate(date);
        userBean.setWeek(week);
        userBean.setMonth(month);
        return userBean;
    }

    @Override
    public ArrayList<UserBean> getUserBeanArrayList() {
        userBeanArrayList = new ArrayList<>();
        graphDataStorageContract = new GraphDataDataStorageProvider();
        Cursor cursor = graphDataStorageContract.queryData();
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                UserBean userBean = createUserFromCursor(cursor);
                userBeanArrayList.add(userBean);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return userBeanArrayList;
    }

    @Override
    public CustomDataPoint[] initializeDataPointsFromList(CustomDataPoint[] dataPoint, ArrayList<UserBean> userBeanArrayList, GraphType graphType) {
        int index = 0;
        if (graphType.equals(GraphType.DAY)) {
            for (UserBean userBean : userBeanArrayList) {
                dataPoint[index++] = new CustomDataPoint(userBean.getDate(), Double.parseDouble(userBean.getWeight()));
            }
        } else if (graphType.equals(GraphType.WEEK)) {
            for (UserBean userBean : userBeanArrayList) {
                dataPoint[index++] = new CustomDataPoint(userBean.getWeek(), Double.parseDouble(userBean.getWeight()));
            }
        } else if (graphType.equals(GraphType.MONTH)) {
            for (UserBean userBean : userBeanArrayList) {
                dataPoint[index++] = new CustomDataPoint(userBean.getMonth(), Double.parseDouble(userBean.getWeight()));
            }
        }
        return dataPoint;
    }
}
