package com.example.planmyday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
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
        CreateAttractions ca = new CreateAttractions(this);
        ca.generate();

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
