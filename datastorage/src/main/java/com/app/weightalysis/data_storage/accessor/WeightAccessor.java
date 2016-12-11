package com.app.weightalysis.data_storage.accessor;

/**
 * Created by Aakash Singh on 24-11-2016.
 */

public class WeightAccessor {
    public static final String ID = "id";
    public static final String WEIGHT = "weight";
    public static final String DATE = "date";
    public static final String MONTH = "month";
    public static final String YEAR = "year";

    public static String getID() {
        return ID;
    }

    public static String getWEIGHT() {
        return WEIGHT;
    }

    public static String getDATE() {
        return DATE;
    }

    public static String getYear() {
        return YEAR;
    }

    public static String getMONTH() {
        return MONTH;
    }

    public static String[] getTableProjection() {
        String[] projection = new String[]{
                getID(),
                getWEIGHT(),
                getDATE(),
                getYear(),
                getMONTH(),};
        return projection;
    }

}
