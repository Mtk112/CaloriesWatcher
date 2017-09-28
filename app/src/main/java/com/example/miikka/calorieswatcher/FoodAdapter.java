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

public class FoodAdapter extends ArrayAdapter {
    private List<Food> foods;
    private DatabaseHelper dbHelper;

    public FoodAdapter(Context context, int resource, List<Food> foods, DatabaseHelper db) {
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
            targetView = li.inflate(R.layout.fooditems,null);
        }

        Food foodie = foods.get(position);
        if (foods != null) {
            TextView tvName = (TextView) targetView.findViewById(R.id.name);
            tvName.setText(foodie.getName());

        }
        return targetView;
    }
}
