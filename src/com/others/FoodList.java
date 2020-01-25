package com.others;

import java.util.ArrayList;

public class FoodList<E> extends ArrayList<E> {
    private String name;

    public FoodList(String n){
        super();
        name=n;
    }

    public String getName() {
        return name;
    }
}
