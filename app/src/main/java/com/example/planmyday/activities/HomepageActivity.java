package com.example.planmyday.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.planmyday.R;

public class HomepageActivity extends AppCompatActivity {

    AppCompatButton itinerary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        itinerary = findViewById(R.id.newitinerary);

        itinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toPlan();
            }
        });
    }

    private void toPlan(){
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);
    }
}