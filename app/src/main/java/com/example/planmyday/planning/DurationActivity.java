package com.example.planmyday.planning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planmyday.R;
import com.example.planmyday.map.ItineraryActivity;


public class DurationActivity extends AppCompatActivity {

    TextView tt;
    Button reviewItineraryButton;
    TextView dayTextView;

    int currentDay = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duration);
        Intent intent = getIntent();
        String type = intent.getStringExtra(Intent.EXTRA_TEXT);

        tt = findViewById(R.id.tourType);

        dayTextView = findViewById(R.id.day);
        updateDayTextView();

        ImageView arrow = findViewById(R.id.arrow);
        reviewItineraryButton = findViewById(R.id.reviewItineraryButton);
        reviewItineraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toMap();
            }
        });

        if (type == null) {
            type = new String();
        }

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

        findViewById(R.id.plusButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementDay();
            }
        });

        findViewById(R.id.subtractButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementDay();
            }
        });
    }

    private void toMap(){
        Intent intent = new Intent(this, ItineraryActivity.class);
        startActivity(intent);
    }

    private void updateDayTextView() {
        String dayText = currentDay == 1 ? "1 day" : currentDay + " days";
        dayTextView.setText(dayText);
    }

    private void incrementDay() {
        if (currentDay < 7) {
            currentDay++;
            updateDayTextView();
        } else {
            // Day count can't go beyond 7, show a toast
            Toast.makeText(this, "Maximum 7 days allowed", Toast.LENGTH_SHORT).show();
        }
    }

    private void decrementDay() {
        if (currentDay > 1) {
            currentDay--;
            updateDayTextView();
        } else {
            // Day count can't go below 1, show a toast
            Toast.makeText(this, "Minimum 1 day allowed", Toast.LENGTH_SHORT).show();
        }
    }
}