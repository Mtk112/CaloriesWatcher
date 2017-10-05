package Fragments;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miikka.calorieswatcher.DatabaseHelper;
import com.example.miikka.calorieswatcher.Eaten;
import com.example.miikka.calorieswatcher.EatenAdapter;
import com.example.miikka.calorieswatcher.R;

import java.util.ArrayList;

/**
 * Created by Miikka on 9/28/2017.
 */
//This fragment will list all the food in database. Selecting list item will show more details.
public class ShowFood extends ListFragment {
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


        ArrayList<Eaten> foods = (ArrayList<Eaten>) dbHelper.getEaten();

        EatenAdapter meAdapter = new EatenAdapter(this.getActivity(), R.layout.fragment_showexercises, foods,dbHelper);
        // Attach the adapter to a ListView
        setListAdapter(meAdapter);
    }

}
