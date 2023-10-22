package com.example.planmyday.planning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.view.View;

import com.example.planmyday.R;
import com.example.planmyday.map.ItineraryActivity;


public class DurationActivity extends AppCompatActivity {

    Button reviewItineraryButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duration);

        ImageView arrow = findViewById(R.id.arrow);
        reviewItineraryButton = findViewById(R.id.reviewItineraryButton);
        reviewItineraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toMap();
            }
        });

        arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void toMap(){
        Intent intent = new Intent(this, ItineraryActivity.class);
        startActivity(intent);
    }
}