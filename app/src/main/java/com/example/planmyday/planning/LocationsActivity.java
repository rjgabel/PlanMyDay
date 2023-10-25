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
import android.widget.ListView;
import android.widget.TextView;
import com.example.planmyday.R;
import com.example.planmyday.models.Attraction;

import java.util.ArrayList;

public class LocationsActivity extends AppCompatActivity {
    TourType tourType;
    ArrayList<Attraction> attractions;
    AppCompatButton next;
    TextView tt;
    ImageView arrow;
    ListView locationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        Intent intent = getIntent();
        String type = intent.getStringExtra(Intent.EXTRA_TEXT);
        arrow = findViewById(R.id.arrow);
        tt = findViewById(R.id.tourType);
        next = findViewById(R.id.nextButton);
        locationList = findViewById(R.id.locationList);

        arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });

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

        Attraction[] aattractions = new Attraction[attractions.size()];
        aattractions = attractions.toArray(aattractions);

        MyAdapter myAdapter = new MyAdapter(LocationsActivity.this, aattractions);
        locationList.setAdapter(myAdapter);

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