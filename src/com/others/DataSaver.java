package com.others;

import java.io.Serializable;

public class DataSaver implements Serializable {
    public String name;
    public String sex;
    public double age;
    public String hobby;
    public Boolean wantStudy;
    public int incubatePresent;

    public DataSaver(){
        name=Setting.name;
        sex=Setting.sex;
        age=Setting.age;
        hobby=Setting.hobby;
        wantStudy=Setting.wantStudy;
        incubatePresent=Setting.incubatePresent;
    }
}