package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miikka.calorieswatcher.DatabaseHelper;
import com.example.miikka.calorieswatcher.Eaten;
import com.example.miikka.calorieswatcher.Food;
import com.example.miikka.calorieswatcher.R;

import java.sql.Timestamp;
import java.util.Calendar;

public class FoodIntake extends Fragment implements View.OnClickListener{
    EditText giveAmount,giveCalories,giveFoodName;

    DatabaseHelper dbHelper;
    String foodName;
    int calories;
    int amount;



    public FoodIntake() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(R.string.eat);
        final View v = inflater.inflate(R.layout.fragment_food_intake, container, false);
        dbHelper = new DatabaseHelper(this.getContext());
        giveAmount=(EditText)v.findViewById(R.id.editTextFoodAmount);
        giveCalories = (EditText)v.findViewById(R.id.editTextCalories);
        giveFoodName=(EditText)v.findViewById(R.id.editTextFoodName);
        Button button = (Button)v.findViewById(R.id.buttonSaveFood);
        button.setOnClickListener(this);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v){
        try {
            foodName=giveFoodName.getText().toString();
            calories=Integer.parseInt(giveCalories.getText().toString());
            amount=Integer.parseInt(giveAmount.getText().toString());
            createNewFoodEvent();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this.getContext(), "Please fill in every field!", Toast.LENGTH_SHORT).show();
        }
    }
    //method for inserting Food and eaten information to the database
    private void createNewFoodEvent(){
        if(!foodName.isEmpty()) {
            Food food = new Food(foodName, calories);
            dbHelper.insertFood(food);
            int fid = dbHelper.getFidByName(foodName);
            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            Timestamp time = new java.sql.Timestamp(now.getTime());
            Eaten eaten = new Eaten(amount,time,fid);
            dbHelper.insertEaten(eaten);
            Toast.makeText(this.getContext(), "Eating saved!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this.getContext(), "Please fill in every field!", Toast.LENGTH_SHORT).show();
        }
    }
}
