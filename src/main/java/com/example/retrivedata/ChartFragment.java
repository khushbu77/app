package com.example.retrivedata;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment {

    private PieChart mPieChart;
    private BarChart mBarChart;


    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        mPieChart = view.findViewById(R.id.pieChart);
        mBarChart = view.findViewById(R.id.barChart);

        getGrowthChart(getArguments().getString("method"));

        return view;
    }

    private void getGrowthChart(final String method){

        Call<List<Growth>> call = ApiClient.getApiClient().create(ApiInterface.class).getGrowthInfo();

        call.enqueue(new Callback<List<Growth>>() {
            @Override
            public void onResponse(Call<List<Growth>> call, Response<List<Growth>> response) {

                if(response.body()!=null){

                    if(method.equals("bars")) {

                        List<BarEntry> barEntries = new ArrayList<>();

                        for (Growth growth : response.body()) {
                            barEntries.add(new BarEntry(growth.getCandidate(), growth.getVotes()));

                        }

                        BarDataSet barDataSet = new BarDataSet(barEntries, "Growth");
                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        BarData barData = new BarData(barDataSet);
                        barData.setBarWidth(0.9f);

                        mBarChart.setVisibility(View.VISIBLE);
                        mBarChart.animateY(5000);
                        mBarChart.setData(barData);
                        mBarChart.setFitBars(true);

                        Description description = new Description();
                        description.setText("Votes per Candidate");
                        mBarChart.setDescription(description);
                        mBarChart.invalidate();
                    }

                    else if(method.equals("pie")){

                        List<PieEntry> pieEntries = new ArrayList<>();

                        for (Growth growth : response.body()) {

                            pieEntries.add(new PieEntry((growth.getCandidate()), Integer.toString(growth.getVotes())));
                        }

                        mPieChart.setVisibility(View.VISIBLE);
                        mPieChart.animateXY(5000,5000);

                        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Votes per candidate");
                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        PieData pieData = new PieData(pieDataSet);

                        mPieChart.setData(pieData);

                        Description description = new Description();
                        description.setText("Votes per Candidate");

                        mPieChart.setDescription(description);

                        mPieChart.invalidate();

                    }
                }




            }

            @Override
            public void onFailure(Call<List<Growth>> call, Throwable t) {

            }
        });

    }

}
