package Fragments;

import android.graphics.Color;
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

//Fragment for drawing graph from databases information
public class ShowGraph extends Fragment {
    //Variables
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

    //Draws BarGraph from Database, only works with overall data :(
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Gets the overall calories gained and burned and calculates the difference.
        caloriesGained = dbHelper.CaloriesGained();
        caloriesBurnt = dbHelper.CaloriesBurned();
        difference = caloriesGained-caloriesBurnt;

        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry((float)caloriesGained,0));
        barEntries.add(new BarEntry((float)caloriesBurnt,1));
        barEntries.add(new BarEntry((float)difference,2));
        //Sets the bar data.
        BarDataSet barDataSet = new BarDataSet(barEntries,"Calories Gained, Calories Burnt, Difference");

        //Sets the Bar Colors
        if(difference<=0){
            barDataSet.setColors(new int[] {Color.RED, Color.GREEN, Color.GREEN});
        }
        else{
            barDataSet.setColors(new int[] {Color.RED, Color.GREEN, Color.RED});
        }

        //List of X-axis labels
        List<String> info = new ArrayList<>();
        info.add("Gained");
        info.add("Burnt");
        info.add("Difference");


        BarData theData = new BarData(info,barDataSet);
        //Customizing the Bar Chart
        theData.setValueTextColor(Color.WHITE);
        theData.setValueTextSize(5);
        bars.getLegend().setTextColor(Color.WHITE);
        bars.getXAxis().setTextColor(Color.WHITE);
        bars.getAxisLeft().setTextColor(Color.WHITE);
        bars.getAxisRight().setTextColor(Color.WHITE);
        bars.setDescription("Calories gained and burned: since the beginning of time.");
        bars.setDescriptionColor(Color.WHITE);
        bars.setBorderColor(Color.WHITE);
        bars.setData(theData);
        bars.invalidate();
        bars.setTouchEnabled(true);
        bars.setDragEnabled(true);
        bars.setScaleEnabled(true);



    }

}
