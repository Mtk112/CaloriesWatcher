package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.miikka.calorieswatcher.R;

import static android.R.id.list;

public class Histograph extends Fragment implements View.OnClickListener {
    private Button exercises,foods,graph;
    private FragmentTransaction transaction;

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
                ShowExercises se = new ShowExercises();
                transaction = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.histogramExtraFragment,se);
                transaction.commit();
                break;
            case(R.id.histogramFoodButton):
                ShowFood sf = new ShowFood();
                transaction = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.histogramExtraFragment,sf);
                transaction.commit();
                break;
            case(R.id.histogramGraphsButton):
                ShowGraph sg = new ShowGraph();
                transaction = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.histogramExtraFragment,sg);
                transaction.commit();
                break;
        }

    }
}
