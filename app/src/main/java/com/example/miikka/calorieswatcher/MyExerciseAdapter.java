package com.example.miikka.calorieswatcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Miikka on 9/25/2017.
 */

public class MyExerciseAdapter extends ArrayAdapter{
    private List<MyExercise> exers;
    private DatabaseHelper dbHelper;
    private String name, time;
    private int caloriesBurned;


    public MyExerciseAdapter(Context context, int resource, List<MyExercise> exers, DatabaseHelper db) {
        super(context, resource, exers);
        this.dbHelper = db;
        this.exers = exers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View targetView = convertView;

        if(targetView == null){
            LayoutInflater li;
            li = LayoutInflater.from(this.getContext());
            targetView = li.inflate(R.layout.exerciseitems,null);
        }

        MyExercise exe = exers.get(position);
        if (exers != null) {
            TextView tvInfo = (TextView) targetView.findViewById(R.id.info);
            int eid = exe.getEid();
            name = dbHelper.getNameByEid(eid);
            caloriesBurned = exe.getCaloriesBurnt();
            time = exe.getTime().toString();
            tvInfo.setText(""+name+" Calories used: "+caloriesBurned+"\n "+time);
        }
        return targetView;
    }
}
