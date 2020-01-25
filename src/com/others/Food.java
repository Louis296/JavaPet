package com.others;

public class Food {
    private String name;
    private String description;
    private double growAge;
    public Food(String n,String d,double g){
        name=n;
        description=d;
        growAge=g;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public double getGrowAge(){
        return growAge;
    }
}
