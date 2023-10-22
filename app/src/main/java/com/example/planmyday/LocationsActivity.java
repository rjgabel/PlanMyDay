package com.example.planmyday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.planmyday.activities.DurationActivity;
import com.example.planmyday.models.Attraction;

import java.util.ArrayList;

public class LocationsActivity extends AppCompatActivity {
    TourType tourType;
    ArrayList<Attraction> attractions;
    AppCompatButton next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        Intent intent = getIntent();
        String type = intent.getStringExtra(Intent.EXTRA_TEXT);

        next = findViewById(R.id.nextButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDuration();
            }
        });

        ImageView arrow = findViewById(R.id.arrow);

        arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //make the correct type of Tour
        if (type.equals("usc")){
            tourType = new USCTour();
        }
        else if (type.equals("LA")){
            tourType = new LATour();
        }
        //get the right type of attractions from database
        //attractions = tourType.getAttractions();

        //create all buttons
        LayoutInflater li = LayoutInflater.from(this);
        //make a new grid layout to be included in the xml
//        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
//        for (int i = 0; i < attractions.size(); i++){
//            //TODO: create a custom attractions layout
//            TextView tv = (TextView) li.inflate(R.layout.attractions_layout, grid, false);
//        }

    }

    //intent to set up how long
    public void toDuration(){
        Intent intent = new Intent(this, DurationActivity.class);
        startActivity(intent);
    }
}