package com.example.vanahel.currencyexchangeapplication.util.dataupdateservice;


import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by Liza Kaliada on 02.12.17.
 */

public class ExecutionWindowCalculator {

    public int calculateExecutionWindow (int timeToSet){

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Europe/Minsk"));
        long currentTimeInMillis = cal.getTimeInMillis();

        int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);

        if (hourOfDay > 12) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        cal.set(Calendar.HOUR_OF_DAY,timeToSet);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);

        Date dateToSet = cal.getTime();

        long timeToSetTimeInMil = dateToSet.getTime();

        return (int) (timeToSetTimeInMil - currentTimeInMillis);

    }
}
