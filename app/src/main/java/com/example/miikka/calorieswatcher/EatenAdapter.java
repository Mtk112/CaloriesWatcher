package com.example.miikka.calorieswatcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Miikka on 9/25/2017.
 */

public class EatenAdapter extends ArrayAdapter {
    private List<Eaten> foods;
    private DatabaseHelper dbHelper;

    public EatenAdapter(Context context, int resource, List<Eaten> foods, DatabaseHelper db) {
        super(context, resource, foods);
        this.dbHelper = db;
        this.foods = foods;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View targetView = convertView;

        if(targetView == null){
            LayoutInflater li;
            li = LayoutInflater.from(getContext());
            targetView = li.inflate(R.layout.exerciseitems,null);
        }
        //Sets the text in correct position in the list
        Eaten ate = foods.get(position);
        if (foods != null) {
            TextView tvInfo = (TextView) targetView.findViewById(R.id.info);
            int fid = ate.getFid();
            Food tempFood = dbHelper.getFoodByFid(fid);
            String name = tempFood.getName();
            int caloriesPer100g = tempFood.getCalories();
            int amount = ate.getAmount();
            int calories = amount*caloriesPer100g/100;
            Timestamp time = ate.getTime();

            tvInfo.setText(""+name+" \nCalories gained: "+calories+"\n"+time);

        }
        return targetView;
    }
}
