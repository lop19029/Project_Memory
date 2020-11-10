package com.example.projectmemory;

import java.util.Date;

public class FoodItem implements Alarmable{
    Date expirationDate = new Date();


    @Override
    public void setAlarm(String name) {
        name = alarm.name;
        this.expirationDate = alarm.date;

    }

    @Override
    public void ringAlarm() {

    }
}
