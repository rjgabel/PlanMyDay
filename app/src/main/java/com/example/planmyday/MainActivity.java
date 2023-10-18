package com.example.planmyday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*
HOME PAGE
Contains login and sign up
 */
public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://planmyday-16506-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create onClickListener for login/signup pages

        //when clicking login button, call toLogin

        //when clicking signup button, call toRegistration
    }

    private void toLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void toRegistration(){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }


}
