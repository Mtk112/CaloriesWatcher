package com.example.miikka.calorieswatcher;

/**
 Class for creating Exercise objects.
 */

public class Exercise {
    private String exerciseName;
    private int id;

    //Constructors
    public Exercise(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public Exercise(String exerciseName,int id) {
        this.exerciseName = exerciseName;
        this.id = id;
    }
    public Exercise(){

    }

    //Getter and setter
    public String getExerciseName() {

        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {

        this.exerciseName = exerciseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
