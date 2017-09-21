package Classes;

/**
 Class for creating Exercise objects.
 */

public class Exercise {
    private String exerciseName;
    private int intensity,duration,burnedCalories,eid;

    public Exercise(String exerciseName,int intensity, int duration, int burnedCalories, int eid) {
        this.exerciseName = exerciseName;
        this.intensity = intensity;
        this.duration = duration;
        this.burnedCalories = burnedCalories;
        this.eid = eid;
    }
    //Getters and setters
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(int burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }
}
