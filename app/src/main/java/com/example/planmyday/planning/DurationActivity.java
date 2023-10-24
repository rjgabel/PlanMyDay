package com.example.planmyday.planning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.ImageView;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.planmyday.R;


public class DurationActivity extends AppCompatActivity {

    TextView tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duration);
        Intent intent = getIntent();
        String type = intent.getStringExtra(Intent.EXTRA_TEXT);

        tt = findViewById(R.id.tourType);

        ImageView arrow = findViewById(R.id.arrow);

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
}