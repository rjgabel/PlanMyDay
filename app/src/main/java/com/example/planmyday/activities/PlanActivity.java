package com.example.planmyday.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.planmyday.LocationsActivity;
import com.example.planmyday.R;

public class PlanActivity extends AppCompatActivity {

    ImageView uscButton, laButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        uscButton = findViewById(R.id.uscButton);
        laButton = findViewById(R.id.laButton);

        uscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toUsc();
            }
        });

        laButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLA();
            }
        });

        ImageView arrow = findViewById(R.id.arrow);

        arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void toUsc(){
        Intent intent = new Intent(this, LocationsActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, "usc");
        startActivity(intent);
    }

    public void toLA(){
        Intent intent = new Intent(this, LocationsActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, "la");
        startActivity(intent);
    }
}