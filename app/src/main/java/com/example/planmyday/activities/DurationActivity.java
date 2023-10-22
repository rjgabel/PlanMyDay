package com.example.planmyday.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.os.Bundle;
import android.view.View;

import com.example.planmyday.R;


public class DurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duration);

        ImageView arrow = findViewById(R.id.arrow);

        arrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}