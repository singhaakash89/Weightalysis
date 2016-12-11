package com.app.graph.inheritance;

import com.jjoe64.graphview.series.DataPointInterface;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public class CustomDataPoint implements DataPointInterface, Serializable {
    private static final long serialVersionUID = 1428263322645L;

    private int x;
    private double y;

    public CustomDataPoint(int x, double y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}
