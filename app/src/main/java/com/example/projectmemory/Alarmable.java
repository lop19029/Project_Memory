package com.example.projectmemory;

import android.provider.AlarmClock;

import java.util.Date;

public interface Alarmable {
    Alarm alarm = new Alarm();

    void setAlarm(String name);
    void ringAlarm();
}
