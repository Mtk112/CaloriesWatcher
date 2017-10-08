package Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.miikka.calorieswatcher.R;

import static android.R.id.list;

public class Histograph extends Fragment implements View.OnClickListener {
    private Button exercises,foods,graph;
    private FragmentTransaction transaction;
    boolean exeB,foodB,graphB = false;

    public Histograph() {
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
        actionBar.setTitle(R.string.history);
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_histograph, container, false);
        exercises = (Button) v.findViewById(R.id.histogramExercisesButton);
        exercises.setOnClickListener(this);
        foods = (Button)v.findViewById(R.id.histogramFoodButton);
        foods.setOnClickListener(this);
        graph =(Button)v.findViewById(R.id.histogramGraphsButton);
        graph.setOnClickListener(this);


        return v;
    }

    //This onClick checks which button is pressed and executes the relevant code.
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case(R.id.histogramExercisesButton):
                exeB = true;
                if(foodB) {
                    foodB= false;
                    foods.setTextColor(Color.WHITE);
                }
                if(graphB){
                    graphB = false;
                    graph.setTextColor(Color.WHITE);
                }
                exercises.setTextColor(Color.RED);
                ShowExercises se = new ShowExercises();
                transaction = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.histogramExtraFragment,se);
                transaction.commit();
                break;
            case(R.id.histogramFoodButton):
                foodB = true;
                if(exeB){
                    exeB= false;
                    exercises.setTextColor(Color.WHITE);
                }
                if(graphB){
                    graphB=false;
                    graph.setTextColor(Color.WHITE);
                }
                foods.setTextColor(Color.RED);
                ShowFood sf = new ShowFood();
                transaction = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.histogramExtraFragment,sf);
                transaction.commit();
                break;
            case(R.id.histogramGraphsButton):
                graphB = true;
                if(exeB){
                    exeB=false;
                    exercises.setTextColor(Color.WHITE);
                }
                if(foodB){
                    foodB=false;
                    foods.setTextColor(Color.WHITE);
                }
                graph.setTextColor(Color.RED);
                ShowGraph sg = new ShowGraph();
                transaction = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.histogramExtraFragment,sg);
                transaction.commit();
                break;
        }

    }
}
