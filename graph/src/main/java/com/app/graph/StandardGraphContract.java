package com.app.graph;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import com.app.graph.data_storage.GraphDataDataStorageProvider;
import com.app.graph.data_storage.GraphDataStorageContract;
import com.app.graph.inheritance.CustomDataPoint;
import com.app.graph.interpreter.MonthInterpreter;
import com.app.graph.model.GraphType;
import com.app.graph.model.Month;
import com.app.weightalysis.data_storage.accessor.WeightAccessor;
import com.app.weightalysis.data_storage.model.UserBean;
import com.app.weightalysis.data_storage.schema.WeightSchemaBuilder;
import com.app.weightalysis.logger.Logger;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public class StandardGraphContract implements GraphContract {

    private static final String TAG = StandardGraphContract.class.getSimpleName();
    private GraphDataStorageContract graphDataStorageContract;
    private ArrayList<UserBean> userBeanArrayList;
    private Context context;

    public StandardGraphContract(Context context) {
        this.context = context;
    }

    @Override
    public void drawGraphByDay(GraphView graphView, String month, String year) {
        //reseting graph
        graphView.removeAllSeries();
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter());

        int monthInInteger = new MonthInterpreter().getMonthInNumber(month);
        Logger.putInDebugLog(TAG, "Month : " + month, " Year : " + year);

        Cursor cursor = getDayCursor(monthInInteger, Integer.parseInt(year));
        userBeanArrayList = getUserBeanArrayList(cursor);

        CustomDataPoint[] customDataPoint = new CustomDataPoint[userBeanArrayList.size()];
        customDataPoint = initializeDataPointsFromList(customDataPoint, userBeanArrayList, GraphType.DAY);
        LineGraphSeries<CustomDataPoint> lineGraphSeries = new LineGraphSeries<>(customDataPoint);
        PointsGraphSeries<CustomDataPoint> pointLineGraphSeries = new PointsGraphSeries<>(customDataPoint);

//        graphView.setTitle("Weight - Day Chart");
        graphView.getViewport().setScrollable(true);
        GridLabelRenderer gridLabel = graphView.getGridLabelRenderer();
//        gridLabel.setVerticalAxisTitle("Weight");
//        gridLabel.setHorizontalAxisTitle("Day");
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
    public void drawGraphByWeek(GraphView graphView, String month, String year) {
        //reseting graph
        graphView.removeAllSeries();
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter());

        GridLabelRenderer gridLabel = graphView.getGridLabelRenderer();
        //userBeanArrayList = getUserBeanArrayList();
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
    public void drawGraphByMonth(GraphView graphView, String year) {
        //reseting graph
        graphView.removeAllSeries();
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter());

        Logger.putInDebugLog(TAG, " Year : ", year);
        userBeanArrayList = getMonthDataPoint(Integer.parseInt(year));

        CustomDataPoint[] customDataPoint = new CustomDataPoint[userBeanArrayList.size()];
        customDataPoint = initializeDataPointsFromList(customDataPoint, userBeanArrayList, GraphType.MONTH);
        LineGraphSeries<CustomDataPoint> lineGraphSeries = new LineGraphSeries<>(customDataPoint);
        PointsGraphSeries<CustomDataPoint> barGraphSeries = new PointsGraphSeries<>(customDataPoint);

        GridLabelRenderer gridLabel = graphView.getGridLabelRenderer();
//        graphView.setTitle("Monthly Weight Chart");
        graphView.getViewport().setScrollable(true);
//        gridLabel.setVerticalAxisTitle("Weight");
//        gridLabel.setHorizontalAxisTitle("Months");
        gridLabel.setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        gridLabel.setGridColor(context.getResources().getColor(R.color.navy_blue_light));

//        //Label rendering
//        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView);
//        staticLabelsFormatter.setHorizontalLabels(new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"});
//        graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        //X-AXIS
        graphView.getViewport().setXAxisBoundsManual(true);
        //TO SEE THE EXACT NUMBER OF LABELS ON X-AXIS AT ONCE
        graphView.getGridLabelRenderer().setNumHorizontalLabels(6);
        graphView.getViewport().setMinX(1);
        //TO SEE THE VIEW WINDOW CONTAINING UPTO 6 LABELS AT ONCE IN SINGLE WINDOW BUT MIGHT BE WITH NO DIGITS.
        graphView.getViewport().setMaxX(6);
        graphView.addSeries(lineGraphSeries);
        graphView.addSeries(barGraphSeries);
        graphView.setVisibility(View.VISIBLE);

    }

    @Override
    public ArrayList<UserBean> getUserBeanArrayList(Cursor cursor) {
        Logger.putInDebugLog(TAG, "Inside", "getUserBeanArrayList");
        userBeanArrayList = new ArrayList<>();
        Logger.putInDebugLog(TAG, "cursor.getCount() : ", "" + cursor.getCount() + " -null");
        if (cursor != null && cursor.getCount() > 0) {
            Logger.putInDebugLog(TAG, "Inside", "if - cursor");
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Logger.putInDebugLog(TAG, "Inside", "while - cursor");
                UserBean userBean = createUserFromCursor(cursor);
                userBeanArrayList.add(userBean);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return userBeanArrayList;
    }

    @Override
    public UserBean createUserFromCursor(Cursor cursor) {
        Logger.putInDebugLog(TAG, "Inside", "createUserFromCursor");
        UserBean userBean = new UserBean();
        int weight = cursor.getInt(cursor.getColumnIndex(WeightAccessor.WEIGHT));
        int date = cursor.getInt(cursor.getColumnIndex(WeightAccessor.DATE));
        int month = cursor.getInt(cursor.getColumnIndex(WeightAccessor.MONTH));
        int year = cursor.getInt(cursor.getColumnIndex(WeightAccessor.YEAR));
        Logger.putInDebugLog(TAG, "Weight : " + weight + " -  null", " Date : " + date + " - null");
        Logger.putInDebugLog(TAG, "Weight : " + weight + " -  null", " Month : " + month + " - null");
        userBean.setWeight(weight);
        userBean.setDate(date);
        userBean.setMonth(month);
        userBean.setYear(year);
        return userBean;
    }

    @Override
    public CustomDataPoint[] initializeDataPointsFromList(CustomDataPoint[] dataPoint, ArrayList<UserBean> userBeanArrayList, GraphType graphType) {
        int index = 0;
        if (graphType.equals(GraphType.DAY)) {
            for (UserBean userBean : userBeanArrayList) {
                dataPoint[index++] = new CustomDataPoint(userBean.getDate(), userBean.getWeight());
            }
        } else if (graphType.equals(GraphType.WEEK)) {
            for (UserBean userBean : userBeanArrayList) {
                dataPoint[index++] = new CustomDataPoint(userBean.getYear(), userBean.getWeight());
            }
        } else if (graphType.equals(GraphType.MONTH)) {
            for (UserBean userBean : userBeanArrayList) {
                dataPoint[index++] = new CustomDataPoint(userBean.getMonth(), userBean.getWeight());
            }
        }
        return dataPoint;
    }

    @Override
    public Cursor getDayCursor(int month, int year) {
        Logger.putInDebugLog(TAG, "Inside", "getDayCursor");
        graphDataStorageContract = new GraphDataDataStorageProvider();
        Cursor cursor = graphDataStorageContract.queryForSelectedCols(WeightSchemaBuilder.TABLE_NAME, WeightAccessor.getTableProjection(), WeightAccessor.MONTH, WeightAccessor.YEAR, month, year);
        Logger.putInDebugLog(TAG, "cursor : ", "" + cursor + " - null");
        return cursor;
    }

    @Override
    public Cursor getWeekCursor(int month, int year) {
        graphDataStorageContract = new GraphDataDataStorageProvider();
        Cursor cursor = graphDataStorageContract.queryForSelectedCols(WeightSchemaBuilder.TABLE_NAME, WeightAccessor.getTableProjection(), WeightAccessor.MONTH, WeightAccessor.YEAR, month, year);
        return cursor;
    }

    @Override
    public Cursor getMonthCursor(int month, int year) {
        Logger.putInDebugLog(TAG, "Inside", "getMonthCursor");
        graphDataStorageContract = new GraphDataDataStorageProvider();
        Cursor cursor = graphDataStorageContract.queryForSelectedCols(WeightSchemaBuilder.TABLE_NAME, WeightAccessor.getTableProjection(), WeightAccessor.MONTH, WeightAccessor.YEAR, month, year);
        return cursor;
    }

    @Override
    public ArrayList<UserBean> getMonthDataPoint(int year) {
        Logger.putInDebugLog(TAG, "Inside", "getMonthDataPoint");
        ArrayList<UserBean> userBeanArrayList = new ArrayList<>();
        UserBean userBean = null;
        int month = 0;
        int totalWeight = 0;
        int cursorCount = 0;
        Cursor cursor = null;
        for (int i = 1; i <= 12; i++) {
            //Reseting totalWeight when every new month starts
            totalWeight = 0;
            cursor = getMonthCursor(i, year);
            Logger.putInDebugLog(TAG, "cursor before if : ", "" + cursor + " null");
            Logger.putInDebugLog(TAG, "cursor before if : cursorCount", "" + cursor.getCount());
            if (cursor != null && cursor.getCount() > 0) {
                Logger.putInDebugLog(TAG, "Inside", "for");
                cursorCount = cursor.getCount();
                Logger.putInDebugLog(TAG, "cursorCount", "" + cursorCount);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Logger.putInDebugLog(TAG, "Inside", "while");
                    Logger.putInDebugLog(TAG, "Inside while - weight :", "" + Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeightAccessor.WEIGHT))) + " null");
                    totalWeight += Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeightAccessor.WEIGHT)));
                    Logger.putInDebugLog(TAG, "totalWeight :", "" + totalWeight + " null");
                    month = cursor.getInt(cursor.getColumnIndex(WeightAccessor.MONTH));
                    cursor.moveToNext();
                }
                cursor.close();
                userBean = new UserBean();
                Logger.putInDebugLog(TAG, "month : ", "" + month);
                userBean.setMonth(month);
                Logger.putInDebugLog(TAG, "avgWeight : ", String.valueOf(getMonthAvgWeight(totalWeight, cursorCount)));
                userBean.setWeight(getMonthAvgWeight(totalWeight, cursorCount));
                userBeanArrayList.add(userBean);
            }
        }
        return userBeanArrayList;
    }

    @Override
    public int getMonthAvgWeight(int totalWeight, int count) {
        Logger.putInDebugLog(TAG, "totalWeight :" + totalWeight, " count : " + count + " null");
        int avgWeight = 0;
        avgWeight = (totalWeight / count);
        return avgWeight;
    }

}
