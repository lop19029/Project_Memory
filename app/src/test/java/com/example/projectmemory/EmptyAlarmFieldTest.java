package com.example.projectmemory;

import android.provider.AlarmClock;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class EmptyAlarmFieldTest {
    private Alarm underTest;

    @Before
    void setUp(){
        underTest = new Alarm();
    }

    @Test

    public void AlarmFieldIsEmpty(){
        //Should remain false
        Boolean empty = false;

        //Checks for empty variables in Alarm
        if(underTest.name.length() == 0){
            empty = true;
        }
        if(underTest.date == null){
            empty = true;
        }

        //Checks empty
        assertFalse(empty);
    }
}
