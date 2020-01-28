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
            objectInputStream.close();
        }catch (Exception e){
            name="十九";
            sex="薯片";
            age=4.99;
            hobby="华农食堂";
            wantStudy=false;
            incubatePresent=0;
            lastGoHUSTCalendar=Calendar.getInstance();
            lastGoHUSTCalendar.add(Calendar.DATE,-7);
        }

    }

    public static double getAge(){
        return age;
    }
}
