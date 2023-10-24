package com.example.planmyday.planning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.planmyday.R;
import com.example.planmyday.models.Attraction;

import java.util.ArrayList;

public class LocationsActivity extends AppCompatActivity {
    TourType tourType;
    ArrayList<Attraction> attractions;
    AppCompatButton next;

    TextView tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        Intent intent = getIntent();
        String type = intent.getStringExtra(Intent.EXTRA_TEXT);

        ImageView arrow = findViewById(R.id.arrow);

        tt = findViewById(R.id.tourType);

        arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });

        next = findViewById(R.id.nextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDuration(type);
            }
        });

        CreateAttractions ca = new CreateAttractions(this);
        ca.generate();
        ArrayList<Attraction> rawAttractions = ca.attractions;


        //make the correct type of Tour
        if (type.equals("usc")){
            tourType = new USCTour();
            tt.setText("USC Tour");
        }
        else if (type.equals("la")){
            tourType = new LATour();
            tt.setText("LA Tour");
        }
        //get the right type of attractions from database
        attractions = tourType.filter(rawAttractions);
        for (Attraction attraction : attractions){
            Log.d("Attractions", attraction.getName());
        }

        //create all buttons
        LayoutInflater li = LayoutInflater.from(this);
        //make a new grid layout to be included in the xml
//        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < attractions.size(); i++){
            //TODO: create a custom attractions layout
//            TextView tv = (TextView) li.inflate(R.layout.attractions_layout, grid, false);
        }

    }

    //intent to set up how long
    public void toDuration(String type){
        Intent intent = new Intent(this, DurationActivity.class);

        if (type.equals("usc")){
            intent.putExtra(Intent.EXTRA_TEXT, "usc");
        }
        else if (type.equals("la")){
            intent.putExtra(Intent.EXTRA_TEXT, "la");
        }

        startActivity(intent);
    }
}