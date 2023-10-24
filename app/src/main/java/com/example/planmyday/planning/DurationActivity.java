package com.example.planmyday.planning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.planmyday.R;
import com.example.planmyday.map.ItineraryActivity;


public class DurationActivity extends AppCompatActivity {

    TextView tt;
    Button reviewItineraryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duration);
        Intent intent = getIntent();
        String type = intent.getStringExtra(Intent.EXTRA_TEXT);

        tt = findViewById(R.id.tourType);

        ImageView arrow = findViewById(R.id.arrow);
        reviewItineraryButton = findViewById(R.id.reviewItineraryButton);
        reviewItineraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toMap();
            }
        });

        if (type.equals("usc")){
            tt.setText("USC Tour");
        }
        else if (type.equals("la")){
            tt.setText("LA Tour");
        }

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