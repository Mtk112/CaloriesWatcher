package Fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miikka.calorieswatcher.DatabaseHelper;
import com.example.miikka.calorieswatcher.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miikka on 10/8/2017.
 */

public class ShowGraph extends Fragment {
    private DatabaseHelper dbHelper;
    BarChart bars;
    int caloriesGained,caloriesBurnt,difference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.graph, container, false);
        bars = (BarChart)v.findViewById(R.id.graph);
        dbHelper = new DatabaseHelper(this.getContext());

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        caloriesGained = dbHelper.CaloriesGsined();
        caloriesBurnt = dbHelper.CaloriesBurned();
        difference = caloriesBurnt-caloriesGained;

        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0f,(float)caloriesGained));
        barEntries.add(new BarEntry(1f,(float)caloriesBurnt));
        barEntries.add(new BarEntry(2f,(float)difference));
        BarDataSet barDataSet = new BarDataSet(barEntries,"Calories Gained, Calories Burnt, Difference");

        List<String> info = new ArrayList<>();
        info.add("Calories Gained");
        info.add("Calories Burnt");
        info.add("Difference");

        BarData theData = new BarData(barDataSet);
        theData.setBarWidth(0.9f); // set custom bar width
        bars.setData(theData);
        bars.setFitBars(true); // make the x-axis fit exactly all bars
        bars.invalidate(); // refresh



    }
}
