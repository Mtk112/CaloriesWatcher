package com.example.miikka.calorieswatcher;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miikka on 9/18/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static int dbVersion = 1;
    private static String dbName = "caloriesDB";
    //Basic Constructor
    public DatabaseHelper(Context context){
        super (context,dbName,null,dbVersion);
    }

    //Database Creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating Pedometer Setting table that contains Users weight, Steps per Mile and walking Speed.
        String sql = "CREATE TABLE settings (weight INTEGER, sLength INTEGER);";
        db.execSQL(sql);
        //Creates Exercises table that contains the name, intensity level and duration of the exercise.
        sql = "CREATE TABLE exercises (id INTEGER PRIMARY KEY, exercise TEXT);";
        db.execSQL(sql);
        sql = "CREATE TABLE myExercise (id INTEGER PRIMARY KEY , intensity INTEGER, duration INTEGER, caloriesBurnt INTEGER, cTime TEXT," +
                " eid INTEGER, FOREIGN KEY(eid) REFERENCES exercise(id) );";
        db.execSQL(sql);
        //Creates Food and Eaten tables that contain data about what the user have eaten and how many calories.
        sql = "CREATE TABLE food (foodName TEXT, calories INT, id INTEGER PRIMARY KEY );";
        db.execSQL(sql);
        sql = "CREATE TABLE eaten (id INTEGER PRIMARY KEY , amount INTEGER, cTime TEXT, fid INTEGER, FOREIGN KEY(fid) REFERENCES food(id) );";
        db.execSQL(sql);
        //Inserts Default Data.
        sql = "INSERT INTO settings (weight, sLength) VALUES (\"80.5\",\"100\");";
        db.execSQL(sql);
        sql = "INSERT INTO exercises (id,exercise) VALUES (1,\"Walking\");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    //Method for inserting settings data to database
    public void insertSettings(int weight,int stepLength){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("INSERT INTO settings (weight,sLength) VALUES("+weight+", "+stepLength+")");
        statement.execute();
        Log.d("Inserting",statement.toString());
        statement.close();
        db.close();
    }

    //Method for inserting exercise to the database.
    public void insertExercise(String exerciseName){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("INSERT INTO exercises (exercise) VALUES(\""+exerciseName+"\");");
        statement.execute();
        statement.close();
        db.close();
    }

    //Method for inserting myExercise information to the database.
    public void insertMyExercise(int intensity, int duration, int caloriesBurnt, int eid, Timestamp time){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("INSERT INTO myExercise (intensity, duration, caloriesBurnt, cTime, eid)" +
                " VALUES("+intensity+", "+duration+", " +
                ""+caloriesBurnt+", \"" + time + "\", "+eid+");");
        statement.execute();
        Log.d("Inserting",statement.toString());
        statement.close();
        db.close();
    }
    //inserts food to database
    public void insertFood(Food food){
        String name = food.getName();
        int calories = food.getCalories();
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("INSERT INTO food (foodName, calories) VALUES(\""+name+"\",\""+calories+"\");");
        statement.execute();
        statement.close();
        db.close();
    }
    //inserts eaten data to database
    public void insertEaten(Eaten eaten){
        int amount = eaten.getAmount();
        Timestamp time = eaten.getTime();
        int fid = eaten.getFid();
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("INSERT INTO eaten (amount,cTime,fid) VALUES ("+amount+",\""+time+"\","+fid+");");
        statement.execute();
        statement.close();
        db.close();
    }

    //Method for searching the Exercise id to be used in myExercise (for linking myExercise with exercise)
    public int getEidByName(String exerciseName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id FROM exercises WHERE exercise = \""+exerciseName+"\";";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int result = cursor.getInt(0);
        db.close();
        return result;
    }
    //Finds the name of exercise by exercise id(eid)
    public String getNameByEid(int eid){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT exercise FROM exercises WHERE id = "+eid+";";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        String result = cursor.getString(0);
        db.close();
        return result;
    }
    //Gets the FoodID by food name. Used for linking food table with eaten.
    public int getFidByName(String foodName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id FROM food WHERE foodName = \""+foodName+"\";";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int result = cursor.getInt(0);
        db.close();
        return result;
    }
    //Gets the food that is linked to given fid
    public Food getFoodByFid(int fid){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT foodName,calories,id FROM food WHERE id = "+fid+";";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        Food tempFood = new Food();
        tempFood.setName(cursor.getString(0));
        tempFood.setCalories(cursor.getInt(1));
        tempFood.setId(cursor.getInt(2));
        db.close();
        return tempFood;
    }
    //Gets intensity and duration and returns them when loading exercise.(Yes, it always loads the first exercise given eid)
    public List getData(int eid){
        List values = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT intensity,duration FROM myExercise WHERE eid = "+eid+";";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            int result = cursor.getInt(0);
            int result2 = cursor.getInt(1);
            values.add(result);
            values.add(result2);
        }
        db.close();
        return values;
    }

    //Gets all MyExercises and returns them as a list.
        public List<MyExercise> getMyExercies(){
        List myExercises = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id,intensity,duration,caloriesBurnt,eid,cTime from myExercise ORDER BY id DESC";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            MyExercise someExercise = new MyExercise();
            someExercise.setId(cursor.getInt(0));
            someExercise.setIntensity(cursor.getInt(1));
            someExercise.setDuration(cursor.getInt(2));
            someExercise.setCaloriesBurnt(cursor.getInt(3));
            someExercise.setEid(cursor.getInt(4));
            someExercise.setTime(Timestamp.valueOf(cursor.getString(5)));
            myExercises.add(someExercise);
        }
        db.close();
        return myExercises;
    }
    //Returns ArrayList of all the Eaten objects to populate Eaten History List.
    public List<Eaten> getEaten(){
        List eaten = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id,amount,cTime,fid FROM eaten ORDER BY id DESC";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            Eaten someFood = new Eaten();
            someFood.setId(cursor.getInt(0));
            someFood.setAmount(cursor.getInt(1));
            someFood.setTime(Timestamp.valueOf(cursor.getString(2)));
            someFood.setFid(cursor.getInt(3));
            eaten.add(someFood);
        }
        db.close();
        return eaten;
    }
    //Gets user settings to be displayed in the settings.
    public UserSettings getUserSettings(){
        UserSettings userSettings= new UserSettings();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT weight,sLength from settings";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()) {
            userSettings.setWeight(cursor.getInt(0));
            userSettings.setStepLength(cursor.getInt(1));
        }
        db.close();
        return userSettings;
    }
    //Returns the total of Calories Gained by Eating to be used in Graph.
    public int CaloriesGained(){
        int totalGained = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT amount,fid FROM eaten ORDER BY id DESC";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            int eatenAmount = cursor.getInt(0);
            int fid = cursor.getInt(1);
            Food food = this.getFoodByFid(fid);
            int caloriesPer100g = food.getCalories();
            int gained = eatenAmount*caloriesPer100g/100;
            totalGained = totalGained + gained;
        }
        return totalGained;
    }
    //Returns the total of Calories Burnt by doing Exercies to be used in Graph.
    public int CaloriesBurned(){
        int totalBurnt = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT caloriesBurnt FROM myExercise ORDER BY id DESC";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            int burnt = cursor.getInt(0);
            totalBurnt = totalBurnt + burnt;
        }
        return totalBurnt;
    }
}

