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

public class ExerciseAdapter extends ArrayAdapter{
    private List<Exercise> exers;
    private DatabaseHelper dbHelper;

    public ExerciseAdapter(Context context, int resource, List<Exercise> exers, DatabaseHelper db) {
        super(context, resource, exers);
        this.dbHelper = db;
        this.exers = exers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View targetView = convertView;

        if(targetView == null){
            LayoutInflater li;
            li = LayoutInflater.from(getContext());
            targetView = li.inflate(R.layout.exerciseitems,null);
        }

        Exercise exe = exers.get(position);
        if (exers != null) {
            TextView tvName = (TextView) targetView.findViewById(R.id.name);
            tvName.setText(exe.getExerciseName());

        }
        return targetView;
    }
}
