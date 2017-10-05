package com.example.miikka.calorieswatcher;

/**
 * Created by ville on 10/5/2017.
 */

public class UserSettings {
    public int getStepLength() {
        return stepLength;
    }

    public void setStepLength(int stepLength) {
        this.stepLength = stepLength;
    }

    private int stepLength;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private int weight;

    public void UserSettings(){

    }
    public void UserSettings(int stepLength,int weight){
        this.stepLength=stepLength;
        this.weight=weight;
    }
}
