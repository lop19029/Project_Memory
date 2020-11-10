package com.example.projectmemory;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class AlarmDateIsBeforeTodayTest {

    private Alarm underTest;

    @Before
    void setUp(){
        underTest = new Alarm();
    }

    @Test
    public void AlarmDateTest(){
        //Get current date
        Date currentDate = Calendar.getInstance().getTime();

        //Checks if the alarm date entered is after today
        Boolean correctDate = currentDate.before(underTest.date);
        assertTrue(correctDate);}

    }

