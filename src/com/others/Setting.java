package com.others;

import javax.swing.*;
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
    public static Calendar nextGoHUSTCalendar;
    public static Calendar nextEatCalendar;

    public static boolean haveRemindMessage;
    public static String remindMessage;
    public static int remindTime;
    public static Calendar remindCalendar;

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
            nextGoHUSTCalendar =data.nextGoHUSTCalendar;
            nextEatCalendar =data.nextEatCalendar;
            haveRemindMessage=data.haveRemindMessage;
            remindMessage=data.remindMessage;
            remindTime=data.remindTime;
            remindCalendar=data.remindCalendar;
            objectInputStream.close();
        }catch (Exception e){
            name="？？？";
            sex="？？？";
            age=18;
            hobby="？？？";
            wantStudy=false;
            incubatePresent=0;
            nextGoHUSTCalendar =Calendar.getInstance();
            nextEatCalendar =Calendar.getInstance();
            haveRemindMessage=false;
        }
        if (haveRemindMessage){
            new Thread(()->{
                try{
                    while (true){
                        Thread.sleep(60000);
                        Calendar current=Calendar.getInstance();
                        if (current.after(Setting.remindCalendar)){
                            JOptionPane.showMessageDialog(null,"麻麻麻麻，刚刚你让我提醒你\n    "+Setting.remindMessage+"\n记得不要忘记哦！");
                            Setting.haveRemindMessage=false;
                            Setting.remindCalendar=null;
                            Setting.remindMessage="";
                            break;
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static double getAge(){
        return age;
    }
}
