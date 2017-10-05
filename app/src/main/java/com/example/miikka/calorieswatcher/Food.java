package com.example.miikka.calorieswatcher;

/**
 * Created by ville on 9/28/2017.
 */

public class Food {
    public Food(){

    }
    public Food(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }
    public Food(String name, int calories,int id) {
        this.name = name;
        this.calories = calories;
        this.id = id;
    }

    private String name;
    private int calories;//per 100g
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getId(){return id;}

    public void setId(int id){this.id = id;}
}
