package com.example.projectmemory;

import java.util.Calendar;
import java.util.Date;

/**
 * Add days, months or years to dates
 */
public class DateManipulator {
    // Add days to a date in Java
    public Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    // Add months to a date in Java
    public Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    // Add years to a date in Java
    public Date addYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }
}
