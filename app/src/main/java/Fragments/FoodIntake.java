package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.miikka.calorieswatcher.Eaten;
import com.example.miikka.calorieswatcher.Food;
import com.example.miikka.calorieswatcher.R;

public class FoodIntake extends Fragment implements View.OnClickListener{
    EditText giveAmount,giveCalories,giveFoodName;

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

        final View v = inflater.inflate(R.layout.fragment_food_intake, container, false);
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
        }
    }
    
    private void createNewFoodEvent(){
        //// TODO: 9/22/2017 save the food and create eat event for calories counter
        Food food = new Food(foodName,calories);
        Eaten eaten = new Eaten(food,amount);
    }
}
