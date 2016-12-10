package com.app.graph.model;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public class Date {

    private String[] dates = new String[15];

    public String[] getDates() {
        setDates();
        return dates;
    }

    public void setDates() {
        int index = 0;
        for (int i = 1; i <= 31; i+=10) {
            dates[index++] = String.valueOf(i);
        }
    }
}
