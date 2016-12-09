package com.app.weightalysis.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by AA333093 on 05-Aug-16.
 */
public class DateFormatter {
    public static String getCurrentDate()
    {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

        //get current date time with Date()
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        //get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));

        return dateFormat.format(date);
    }
}
