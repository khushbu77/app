package com.example.retrivedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
    }

    public void OpenPieChart(View view) {

        Intent intent = new Intent(this, ChartActivity2.class);
        intent.putExtra("method","pie");
        startActivity(intent);
    }

    public void openBarChart(View view) {

        Intent intent = new Intent(this, ChartActivity2.class);
        intent.putExtra("method","bars");
        startActivity(intent);
    }
}
