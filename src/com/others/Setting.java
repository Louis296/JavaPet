package com.others;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Setting {
    public static String name;
    public static String sex;
    public static double age;
    public static String hobby;
    public static Boolean wantStudy;
    public static int incubatePresent;

    static{
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
            name="？？？";
            sex="薯片";
            age=-1;
            hobby="华农食堂";
        }

    }

    public static double getAge(){
        return age;
    }
}
