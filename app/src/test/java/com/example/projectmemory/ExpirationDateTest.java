package com.example.projectmemory;

import org.junit.Before;
import org.junit.Test;


import java.util.Calendar;
import java.util.Date;
import static org.junit.Assert.*;


public class ExpirationDateTest {
    private FoodItem underTest;

    @Before
    void setUp(){
        underTest = new FoodItem();
    }
    @Test
    public void date_IsCorrect(){
        //Get current date
        Date currentDate = Calendar.getInstance().getTime();

        //Checks if the expiration date entered in FoodItem is after today
        Boolean correctDate = currentDate.before(underTest.expirationDate);
        assertTrue(correctDate);}
}
