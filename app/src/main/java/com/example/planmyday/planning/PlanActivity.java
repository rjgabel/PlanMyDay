package com.example.planmyday.planning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.planmyday.planning.LocationsActivity;
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