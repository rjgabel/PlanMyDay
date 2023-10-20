package com.example.planmyday.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.planmyday.TourOptimizer;
import com.example.planmyday.activities.CreateAttractions;
import com.example.planmyday.R;
import com.google.firebase.database.FirebaseDatabase;

/*
HOME PAGE
Contains login and sign up
 */
public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://planmyday-16506-default-rtdb.firebaseio.com/");
    AppCompatButton login, signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.loginButton);
        signUp = findViewById(R.id.signUpButton);
        //TODO: comment out next two lines once attractions are in database
        CreateAttractions ca = new CreateAttractions(this);
        ca.generate();
        TourOptimizer.optimizeTour(ca.attractions);

        //create onClickListener for login/signup pages
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRegistration();
            }
        });
    }

    private void toLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void toRegistration(){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    private void createAttractions(){

    }


}
