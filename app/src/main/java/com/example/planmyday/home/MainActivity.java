package com.example.planmyday.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.planmyday.map.TourOptimizer;
import com.example.planmyday.R;
import com.example.planmyday.planning.CreateAttractions;
import com.example.planmyday.registration.LoginActivity;
import com.example.planmyday.registration.RegistrationActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
HOME PAGE
Contains login and sign up
 */
public class MainActivity extends AppCompatActivity {
    AppCompatButton login, signUp;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.loginButton);
        signUp = findViewById(R.id.signUpButton);
        //TODO: comment out next two lines once attractions are in database
        //CreateAttractions ca = new CreateAttractions(this);
        //ca.generate();
        //TourOptimizer.optimizeTour(ca.attractions);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

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

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            toHome();
        }
    }
    private void createAttractions(){

    }
    private void toHome(){
        Intent intent = new Intent(this, HomepageActivity.class);
        //TODO: add user information to intent
        startActivity(intent);
    }


}
