package com.app.weightalysis.data_storage.model;

import android.content.ContentValues;

import com.app.weightalysis.data_storage.accessor.WeightAccessor;

/**
 * Created by Aakash Singh on 09-12-2016.
 */

public class User {

    private String id;
    private String weight;
    private String date;
    private String month;
    private String year;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
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
