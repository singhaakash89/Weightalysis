package com.app.weightalysis.data_storage.model;

import android.content.ContentValues;

import com.app.weightalysis.data_storage.accessor.WeightAccessor;

/**
 * Created by Aakash Singh on 09-12-2016.
 */

public class UserBean {
    private String weight;
    private String date;
    private String week;
    private String month;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public ContentValues getContentValues() {
        ContentValues row = new ContentValues();
        row.put(WeightAccessor.WEIGHT, getWeight());
        row.put(WeightAccessor.DATE, getDate());
        row.put(WeightAccessor.WEEK, getWeek());
        row.put(WeightAccessor.MONTH, getMonth());
        return row;
    }

}
