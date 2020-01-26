package com.others;

public class Setting {
    public static String name;
    public static String sex;
    public static double age;
    public static String hobby;
    public static Boolean wantstudy;
    public static int incubatePresent;

    static{
        name="十九";
        sex="薯片";
        age=7;
        hobby="华农食堂";
    }

    public static double getAge(){
        return age;
    }
}
