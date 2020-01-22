package com.others;

public class Setting {
    public static String name;
    public static String sex;
    public static int age;
    public static String hobby;
    public static Boolean wantstudy;

    static{
        name="火柴人";
        sex="火柴人";
        age=-1;
        hobby="华农食堂";
    }

    public static int getAge(){
        return age;
    }
}
