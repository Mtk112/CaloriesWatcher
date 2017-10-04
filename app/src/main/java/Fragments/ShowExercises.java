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
import com.example.miikka.calorieswatcher.Exercise;
import com.example.miikka.calorieswatcher.ExerciseAdapter;
import com.example.miikka.calorieswatcher.R;

import java.util.ArrayList;

/**
 * Created by Miikka on 9/28/2017.
 */

//This Fragment will show a list of exercises that user have inputted. Clicking list item will show more details.
public class ShowExercises extends ListFragment {
    private DatabaseHelper dbHelper;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_showexercises,container,false);
        dbHelper = new DatabaseHelper(this.getContext());
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ArrayList<Exercise> exercises = (ArrayList<Exercise>) dbHelper.getExercies();


        ExerciseAdapter eAdapter = new ExerciseAdapter(this.getActivity(), R.layout.fragment_showexercises, exercises,dbHelper);
        // Attach the adapter to a ListView
        setListAdapter(eAdapter);
    }

    /*@Override
    public void onListItemClick(ListView parent, View v, int position, long id) {
        //sets new fragment and attaches data to it
        Log.d("click", ""+position);
        SecondaryFragment sfrag = new SecondaryFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("here", position );
        sfrag.setArguments(arguments);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ffrag, sfrag).addToBackStack(null).commit();

    }*/

}
