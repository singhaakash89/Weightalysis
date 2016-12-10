package com.app.graph.model;

/**
 * Created by Aakash Singh on 10-12-2016.
 */

public class Weight {

    private String[] weights = new String[10];

    public String[] getWeights() {
        setWeights();
        return weights;
    }

    public void setWeights() {
        int index = 0;
        for (int i = 10; i <= 100; i+=10) {
            weights[index++] = String.valueOf(i);
        }
    }
}
