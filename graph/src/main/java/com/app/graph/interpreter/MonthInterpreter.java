package com.app.graph.interpreter;

/**
 * Created by Aakash Singh on 11-12-2016.
 */

public class MonthInterpreter {

    public int getMonthInNumber(String monthName) {
        if (monthName.equalsIgnoreCase("jan"))
            return 1;
        else if (monthName.equalsIgnoreCase("feb"))
            return 2;
        else if (monthName.equalsIgnoreCase("mar"))
            return 3;
        else if (monthName.equalsIgnoreCase("apr"))
            return 4;
        else if (monthName.equalsIgnoreCase("may"))
            return 5;
        else if (monthName.equalsIgnoreCase("jun"))
            return 6;
        else if (monthName.equalsIgnoreCase("jul"))
            return 7;
        else if (monthName.equalsIgnoreCase("aug"))
            return 8;
        else if (monthName.equalsIgnoreCase("sep"))
            return 9;
        else if (monthName.equalsIgnoreCase("oct"))
            return 10;
        else if (monthName.equalsIgnoreCase("nov"))
            return 11;
        else if (monthName.equalsIgnoreCase("dec"))
            return 12;
        return 0;
    }
}
