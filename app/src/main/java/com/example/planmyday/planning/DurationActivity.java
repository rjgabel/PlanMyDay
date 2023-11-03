package com.example.planmyday.planning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.planmyday.R;
import com.example.planmyday.map.ItineraryActivity;
import com.example.planmyday.map.TourOptimizer;
import com.example.planmyday.models.Attraction;
import com.example.planmyday.models.TourPlan;
import com.example.planmyday.models.TourStop;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class DurationActivity extends AppCompatActivity {

    TextView tt;
    Button reviewItineraryButton;
    ArrayList<Attraction> attractions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duration);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        attractions = (ArrayList<Attraction>) args.getSerializable("ARRAYLIST");
        Log.d("Sizer1", String.valueOf(attractions.size()));
        for (int i = 0; i < attractions.size(); i++){
            Log.d("SA1", attractions.get(i).getName());
        }
        ArrayList<Attraction> attractionsCopy = new ArrayList<>(attractions);
        ArrayList<TourPlan> tourPlan = TourOptimizer.optimizeTour(attractionsCopy);
        ArrayList<TourStop> stops = tourPlan.get(0).getStops();
        for (int i = 0; i < stops.size(); i++){
            Log.d("Stops", stops.get(i).getAttraction().getName());
        }

        tt = findViewById(R.id.tourType);

        ImageView arrow = findViewById(R.id.arrow);
        reviewItineraryButton = findViewById(R.id.reviewItineraryButton);
        Log.d("Sizer2", String.valueOf(attractions.size()));
        reviewItineraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toMap(attractions);
            }
        });

//        if (type == null) {
//            type = new String();
//        }
//
//        if (type.equals("usc")){
//            tt.setText("USC Tour");
//        }
//        else if (type.equals("la")){
//            tt.setText("LA Tour");
//        }

        arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void toMap(ArrayList<Attraction> attractions){
        Intent intent = new Intent(this, ItineraryActivity.class);
        Bundle args2 = new Bundle();
        Log.d("SIZER", String.valueOf(attractions.size()));
        args2.putSerializable("ARRAYLIST2", (Serializable) attractions);
        intent.putExtra("BUNDLE2", args2);

        startActivity(intent);
    }
}