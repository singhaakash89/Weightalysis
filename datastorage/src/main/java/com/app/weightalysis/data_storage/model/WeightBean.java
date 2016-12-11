package com.app.weightalysis.data_storage.model;

import android.content.ContentValues;

import com.app.weightalysis.data_storage.accessor.WeightAccessor;

/**
 * Created by Aakash Singh on 09-12-2016.
 */

public class WeightBean {
    private int weight;
    private int date;
    private int month;
    private int year;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ContentValues getContentValues() {
        ContentValues row = new ContentValues();
        row.put(WeightAccessor.WEIGHT, getWeight());
        row.put(WeightAccessor.DATE, getDate());
        row.put(WeightAccessor.MONTH, getMonth());
        row.put(WeightAccessor.YEAR, getYear());
        return row;
    }

}
