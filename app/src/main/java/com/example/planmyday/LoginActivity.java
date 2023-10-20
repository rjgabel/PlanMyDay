package com.example.planmyday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText emailView, passwordView;
    Button login_btn, reroute;
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://planmyday-16506-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailView = findViewById(R.id.email);
        passwordView = findViewById(R.id.password);
        login_btn = findViewById(R.id.btn_login);
        reroute = findViewById(R.id.reroute_login);

        //TODO: forgot password option
        //TODO: email format verification
        //String str = String.valueOf(R.id.email);

        reroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRegistration();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(emailView.getText());
                password = String.valueOf(passwordView.getText());
                //check if they are empty
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this,"Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                //login
                dbRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(email)){
                            final String getPassword = snapshot.child(email).child("password").getValue(String.class);
                            if (getPassword.equals(password)){
                                Toast.makeText(LoginActivity.this,"Successfully Logged in", Toast.LENGTH_SHORT).show();
                                toHome();
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this,"Invalid Login", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Invalid Login", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

    private void toRegistration(){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    private void toHome(){
        Intent intent = new Intent(this, HomepageActivity.class);
        //TODO: add user information to intent
        startActivity(intent);
    }
}