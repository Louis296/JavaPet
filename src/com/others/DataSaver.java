package com.others;

import java.io.Serializable;
import java.util.Calendar;

public class DataSaver implements Serializable {
    public String name;
    public String sex;
    public double age;
    public String hobby;
    public Boolean wantStudy;

    public int incubatePresent;
    public Calendar nextGoHUSTCalendar;
    public Calendar nextEatCalendar;

    public boolean haveRemindMessage;
    public String remindMessage;
    public int remindTime;
    public Calendar remindCalendar;
    public DataSaver(){
        name=Setting.name;
        sex=Setting.sex;
        age=Setting.age;
        hobby=Setting.hobby;
        wantStudy=Setting.wantStudy;
        incubatePresent=Setting.incubatePresent;
        nextGoHUSTCalendar =Setting.nextGoHUSTCalendar;
        nextEatCalendar =Setting.nextEatCalendar;
        haveRemindMessage=Setting.haveRemindMessage;
        remindMessage=Setting.remindMessage;
        remindTime=Setting.remindTime;
        remindCalendar=Setting.remindCalendar;
    }
}
