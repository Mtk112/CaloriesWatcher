package Fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miikka.calorieswatcher.DatabaseHelper;
import com.example.miikka.calorieswatcher.MyExercise;
import com.example.miikka.calorieswatcher.MyExerciseAdapter;
import com.example.miikka.calorieswatcher.R;

import java.util.ArrayList;

/**
 * Created by Miikka on 9/28/2017.
 */

//This Fragment will show a list of exercises that user have inputted. Clicking list item will show more details.
public class ShowExercises extends ListFragment {
    private DatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_showexercises,container,false);
        dbHelper = new DatabaseHelper(this.getContext());
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ArrayList<MyExercise> exercises = (ArrayList<MyExercise>) dbHelper.getMyExercies();


        MyExerciseAdapter meAdapter = new MyExerciseAdapter(this.getActivity(), R.layout.fragment_showexercises, exercises,dbHelper);
        // Attach the adapter to a ListView
        setListAdapter(meAdapter);
    }


}
