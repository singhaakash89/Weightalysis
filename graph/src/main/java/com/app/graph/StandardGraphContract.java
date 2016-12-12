package com.app.graph;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

import com.app.graph.data_storage.GraphDataDataStorageProvider;
import com.app.graph.data_storage.GraphDataStorageContract;
import com.app.graph.inheritance.CustomDataPoint;
import com.app.graph.interpreter.MonthInterpreter;
import com.app.graph.model.GraphType;
import com.app.graph.toast_manager.ToastManager;
import com.app.weightalysis.data_storage.accessor.WeightAccessor;
import com.app.weightalysis.data_storage.model.WeightBean;
import com.app.weightalysis.data_storage.schema.WeightSchemaBuilder;
import com.app.weightalysis.logger.Logger;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public class StandardGraphContract implements GraphContract {

    private static final String TAG = StandardGraphContract.class.getSimpleName();
    private GraphDataStorageContract graphDataStorageContract;
    private ArrayList<WeightBean> weightBeanArrayList;
    private Context context;

    public StandardGraphContract(Context context) {
        this.context = context;
        ToastManager.createInstance(context);
    }

    @Override
    public void drawGraphByDay(GraphView graphView, String month, String year) {
        //reseting graph
        graphView.removeAllSeries();
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter());

        int monthInInteger = new MonthInterpreter().getMonthInNumber(month);
        Logger.putInDebugLog(TAG, "Month : " + month, " Year : " + year);

        Cursor cursor = getDayCursor(monthInInteger, Integer.parseInt(year));
        weightBeanArrayList = getUserBeanArrayList(cursor);

        //Sorting on the basis of dates
        Collections.sort(weightBeanArrayList);

        for (WeightBean w : weightBeanArrayList) {
            Logger.putInDebugLog(TAG, "date : " + w.getDate() + " value", " weight : " + w.getWeight() + " value");
        }

        if (weightBeanArrayList.size() != 0) {
            CustomDataPoint[] customDataPoint = new CustomDataPoint[weightBeanArrayList.size()];
            customDataPoint = initializeDataPointsFromList(customDataPoint, weightBeanArrayList, GraphType.DAY);
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
            graphView.getGridLabelRenderer().setNumHorizontalLabels(4);
            graphView.getViewport().setMinX(1);
            //TO SEE THE VIEW WINDOW CONTAINING UPTO 6 LABELS AT ONCE IN SINGLE WINDOW BUT MIGHT BE WITH NO DIGITS.
            graphView.getViewport().setMaxX(4);
            graphView.addSeries(lineGraphSeries);
            graphView.addSeries(pointLineGraphSeries);
        } else {
            ToastManager.getInstance().showSimpleToastShort("No Data Found");
        }
        graphView.setVisibility(View.VISIBLE);
    }

    @Override
    public void drawGraphByWeek(GraphView graphView, String month, String year) {
        //reseting graph
        graphView.removeAllSeries();
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter());

        int monthInInteger = new MonthInterpreter().getMonthInNumber(month);
        Logger.putInDebugLog(TAG, "Month : " + monthInInteger, " Year : " + year);

        weightBeanArrayList = getWeekDataPoint(monthInInteger, Integer.parseInt(year));
        Logger.putInDebugLog(TAG, "weightBeanArrayList.size()", "" + weightBeanArrayList.size());

        if (weightBeanArrayList.get(0).getWeight() != 0) {
            CustomDataPoint[] customDataPoint = new CustomDataPoint[weightBeanArrayList.size()];
            customDataPoint = initializeDataPointsFromList(customDataPoint, weightBeanArrayList, GraphType.WEEK);
            LineGraphSeries<CustomDataPoint> lineGraphSeries = new LineGraphSeries<>(customDataPoint);
            PointsGraphSeries<CustomDataPoint> pointsGraphSeries = new PointsGraphSeries<>(customDataPoint);

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
            graphView.getGridLabelRenderer().setNumHorizontalLabels(4);
            graphView.getViewport().setMinX(1);
            //TO SEE THE VIEW WINDOW CONTAINING UPTO 6 LABELS AT ONCE IN SINGLE WINDOW BUT MIGHT BE WITH NO DIGITS.
            graphView.getViewport().setMaxX(4);
            graphView.addSeries(lineGraphSeries);
            graphView.addSeries(pointsGraphSeries);
        } else {
            ToastManager.getInstance().showSimpleToastShort("No Data Found");
        }
        graphView.setVisibility(View.VISIBLE);
    }

    @Override
    public void drawGraphByMonth(GraphView graphView, String year) {
        //reseting graph
        graphView.removeAllSeries();
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter());

        Logger.putInDebugLog(TAG, " Year : ", year);
        weightBeanArrayList = getMonthDataPoint(Integer.parseInt(year));

        if (weightBeanArrayList.size() != 0) {
            CustomDataPoint[] customDataPoint = new CustomDataPoint[weightBeanArrayList.size()];
            customDataPoint = initializeDataPointsFromList(customDataPoint, weightBeanArrayList, GraphType.MONTH);
            LineGraphSeries<CustomDataPoint> lineGraphSeries = new LineGraphSeries<>(customDataPoint);
            PointsGraphSeries<CustomDataPoint> pointsGraphSeries = new PointsGraphSeries<>(customDataPoint);

            GridLabelRenderer gridLabel = graphView.getGridLabelRenderer();
//        graphView.setTitle("Monthly Weight Chart");
            graphView.getViewport().setScrollable(true);
//        gridLabel.setVerticalAxisTitle("Weight");
//        gridLabel.setHorizontalAxisTitle("Months");
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
            graphView.addSeries(pointsGraphSeries);
        } else {
            ToastManager.getInstance().showSimpleToastShort("No Data Found");
        }
        graphView.setVisibility(View.VISIBLE);
    }

    @Override
    public ArrayList<WeightBean> getUserBeanArrayList(Cursor cursor) {
        Logger.putInDebugLog(TAG, "Inside", "getUserBeanArrayList");
        weightBeanArrayList = new ArrayList<>();
        Logger.putInDebugLog(TAG, "cursor.getCount() : ", "" + cursor.getCount() + " -null");
        if (cursor != null && cursor.getCount() > 0) {
            Logger.putInDebugLog(TAG, "Inside", "if - cursor");
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Logger.putInDebugLog(TAG, "Inside", "while - cursor");
                WeightBean weightBean = createUserFromCursor(cursor);
                weightBeanArrayList.add(weightBean);
                cursor.moveToNext();
            }
            cursor.close();
        }
        Logger.putInDebugLog(TAG, "weightBeanArrayList.size()", "" + weightBeanArrayList.size() + " value");
        return weightBeanArrayList;
    }

    @Override
    public WeightBean createUserFromCursor(Cursor cursor) {
        Logger.putInDebugLog(TAG, "Inside", "createUserFromCursor");
        WeightBean weightBean = new WeightBean();
        int weight = cursor.getInt(cursor.getColumnIndex(WeightAccessor.WEIGHT));
        int date = cursor.getInt(cursor.getColumnIndex(WeightAccessor.DATE));
        int month = cursor.getInt(cursor.getColumnIndex(WeightAccessor.MONTH));
        int year = cursor.getInt(cursor.getColumnIndex(WeightAccessor.YEAR));
        Logger.putInDebugLog(TAG, "Weight : " + weight + " -  null", " Date : " + date + "/" + month + "/" + year + " - null");
        weightBean.setWeight(weight);
        weightBean.setDate(date);
        weightBean.setMonth(month);
        weightBean.setYear(year);
        return weightBean;
    }

    @Override
    public CustomDataPoint[] initializeDataPointsFromList(CustomDataPoint[] dataPoint, ArrayList<WeightBean> weightBeanArrayList, GraphType graphType) {
        int index = 0;
        if (graphType.equals(GraphType.DAY)) {
            for (WeightBean weightBean : weightBeanArrayList) {
                dataPoint[index] = new CustomDataPoint(weightBean.getDate(), weightBean.getWeight());
                Logger.putInDebugLog(TAG, "date : " + weightBean.getDate() + " value", " weight : " + weightBean.getWeight() + ", at index :" + index + " value");
                index++;
            }
        } else if (graphType.equals(GraphType.WEEK)) {
            int i = 1;
            for (WeightBean weightBean : weightBeanArrayList) {
                dataPoint[index++] = new CustomDataPoint(i++, weightBean.getWeight());
            }
        } else if (graphType.equals(GraphType.MONTH)) {
            for (WeightBean weightBean : weightBeanArrayList) {
                dataPoint[index] = new CustomDataPoint(weightBean.getMonth(), weightBean.getWeight());
                index++;
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
        Logger.putInDebugLog(TAG, "cursor count: ", "" + cursor.getCount() + " - null");
        return cursor;
    }

    @Override
    public Cursor getWeek1Cursor(int month) {
        graphDataStorageContract = new GraphDataDataStorageProvider();
        Cursor cursor = graphDataStorageContract.queryForWeek1(WeightSchemaBuilder.TABLE_NAME, WeightAccessor.getTableProjection(), WeightAccessor.MONTH, month);
        return cursor;
    }

    @Override
    public Cursor getWeek2and3Cursor(int month, int date1, int date2) {
        graphDataStorageContract = new GraphDataDataStorageProvider();
        Cursor cursor = graphDataStorageContract.queryForWeek2and3(WeightSchemaBuilder.TABLE_NAME, WeightAccessor.getTableProjection(), WeightAccessor.MONTH, month, date1, date2);
        return cursor;
    }

    @Override
    public Cursor getWeek4Cursor(int month) {
        graphDataStorageContract = new GraphDataDataStorageProvider();
        Cursor cursor = graphDataStorageContract.queryForWeek4(WeightSchemaBuilder.TABLE_NAME, WeightAccessor.getTableProjection(), WeightAccessor.MONTH, month);
        return cursor;
    }

    public ArrayList<WeightBean> getWeekDataPoint(int monthInInteger, int year) {
        Logger.putInDebugLog(TAG, "Inside", "getWeekDataPoint");
        ArrayList<WeightBean> weightBeanArrayList = new ArrayList<>();
        WeightBean weightBean = null;
        Cursor[] cursorArray = {getWeek1Cursor(monthInInteger), getWeek2and3Cursor(monthInInteger, 8, 14), getWeek2and3Cursor(monthInInteger, 15, 21), getWeek4Cursor(monthInInteger)};

        for (Cursor cursor : cursorArray) {
            weightBean = getWeeklyBean(cursor, monthInInteger);
            if (0 != weightBean.getWeight()) {
                weightBeanArrayList.add(weightBean);
            }
        }
        return weightBeanArrayList;
    }

    private WeightBean getWeeklyBean(Cursor cursor, int monthInInteger) {
        Logger.putInDebugLog(TAG, "Inside : ", "getWeeklyBean");
        Logger.putInDebugLog(TAG, "month : ", "" + monthInInteger);
        WeightBean weightBean = new WeightBean();
        int totalWeight = 0;
        int cursorCount = 0;
        Logger.putInDebugLog(TAG, "cursor before if : ", "" + cursor + " null");
        Logger.putInDebugLog(TAG, "cursor before if : cursorCount", "" + cursor.getCount());
        if (cursor != null && cursor.getCount() > 0) {
            Logger.putInDebugLog(TAG, "Inside", "if");
            cursorCount = cursor.getCount();
            Logger.putInDebugLog(TAG, "cursorCount", "" + cursorCount);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Logger.putInDebugLog(TAG, "Inside", "while");
                Logger.putInDebugLog(TAG, "Inside while - weight :", "" + Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeightAccessor.WEIGHT))) + " null");
                totalWeight += Integer.parseInt(cursor.getString(cursor.getColumnIndex(WeightAccessor.WEIGHT)));
                Logger.putInDebugLog(TAG, "totalWeight :", "" + totalWeight + " null");
                cursor.moveToNext();
            }
            cursor.close();
            if (0 != getAvgWeight(totalWeight, cursorCount)) {
                Logger.putInDebugLog(TAG, "Inside if", "getAvgWeight > 0");
                weightBean = new WeightBean();
                weightBean.setWeight(getAvgWeight(totalWeight, cursorCount));
            }
        }
        return weightBean;
    }

    @Override
    public Cursor getMonthCursor(int month, int year) {
        Logger.putInDebugLog(TAG, "Inside", "getMonthCursor");
        graphDataStorageContract = new GraphDataDataStorageProvider();
        Cursor cursor = graphDataStorageContract.queryForSelectedCols(WeightSchemaBuilder.TABLE_NAME, WeightAccessor.getTableProjection(), WeightAccessor.MONTH, WeightAccessor.YEAR, month, year);
        return cursor;
    }

    @Override
    public ArrayList<WeightBean> getMonthDataPoint(int year) {
        Logger.putInDebugLog(TAG, "Inside", "getMonthDataPoint");
        ArrayList<WeightBean> weightBeanArrayList = new ArrayList<>();
        WeightBean weightBean = null;
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
                    Logger.putInDebugLog(TAG, "month : ", "" + month);
                    cursor.moveToNext();
                }
                cursor.close();
                if (0 != getAvgWeight(totalWeight, cursorCount)) {
                    Logger.putInDebugLog(TAG, "Inside if", "getAvgWeight > 0");
                    weightBean = new WeightBean();
                    weightBean.setMonth(month);
                    weightBean.setWeight(getAvgWeight(totalWeight, cursorCount));
                    weightBeanArrayList.add(weightBean);
                }
            }
        }
        return weightBeanArrayList;
    }

    @Override
    public int getAvgWeight(int totalWeight, int count) {
        Logger.putInDebugLog(TAG, "Inside ", "getAvgWeight");
        Logger.putInDebugLog(TAG, "totalWeight :" + totalWeight, " count : " + count + " null");
        int avgWeight = 0;
        avgWeight = (totalWeight / count);
        Logger.putInDebugLog(TAG, "avgWeight : ", "" + avgWeight);
        return avgWeight;
    }

}
