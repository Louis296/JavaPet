package com.others;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Calendar;

public class Setting {
    public static Boolean programLoading;
    public static String name;
    public static String sex;
    public static double age;
    public static String hobby;
    public static Boolean wantStudy;
    public static int incubatePresent;
    public static Calendar lastGoHUSTCalendar;
    public static Calendar lastEatCalendar;
    static{
        programLoading=true;
        try{
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("Data.class"));
            DataSaver data=(DataSaver) objectInputStream.readObject();
            name=data.name;
            sex=data.sex;
            age=data.age;
            hobby=data.hobby;
            wantStudy=data.wantStudy;
            incubatePresent=data.incubatePresent;
            lastGoHUSTCalendar=data.lastGoHUSTCalendar;
            lastEatCalendar=data.lastEatCalendar;
            objectInputStream.close();
        }catch (Exception e){
            name="？？？";
            sex="？？？";
            age=-1;
            hobby="？？？";
            wantStudy=false;
            incubatePresent=0;
            lastGoHUSTCalendar=Calendar.getInstance();
            lastGoHUSTCalendar.add(Calendar.DATE,-7);
            lastEatCalendar=Calendar.getInstance();
            lastEatCalendar.add(Calendar.HOUR_OF_DAY,-3);
        }

    }

    public static double getAge(){
        return age;
    }
}
