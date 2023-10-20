package com.example.planmyday.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.planmyday.R;
import com.example.planmyday.models.TourPlan;
import com.example.planmyday.models.UserAccount;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {
    TextInputEditText emailView, passwordView, confirmPasswordView, nameView;
    Button login_btn, reroute;
    //FirebaseAuth mAuth;
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://planmyday-16506-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //TODO: add name for registration

        nameView = findViewById(R.id.name);
        emailView = findViewById(R.id.email);
        passwordView = findViewById(R.id.password);
        confirmPasswordView = findViewById(R.id.password_check);
        login_btn = findViewById(R.id.btn_login);
        reroute = findViewById(R.id.reroute_login);

        reroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });

        //set off registration when button is clicked
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password, confirmPassword, name;
                email = String.valueOf(emailView.getText());
                password = String.valueOf(passwordView.getText());
                confirmPassword = String.valueOf(confirmPasswordView.getText());
                name = String.valueOf(nameView.getText());

                //check if they are empty
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(RegistrationActivity.this,"Enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(RegistrationActivity.this,"Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(RegistrationActivity.this,"Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(confirmPassword)){
                    Toast.makeText(RegistrationActivity.this,"Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<TourPlan> temp = new ArrayList<>();
                //temp.add();
                UserAccount user = new UserAccount(name, email, password, temp);

                dbRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //check if email has been registered before
                        if (snapshot.hasChild(email)){
                            Toast.makeText(RegistrationActivity.this, "Email is already registered", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //TODO: fix issue with unique email containing @ and .
                            //TODO: add user to firebase auth
                            dbRef.child("users").child(email).setValue(user);
                            Toast.makeText(RegistrationActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            toHome();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



//                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            FirebaseUser user = mAuth.getCurrentUser();
//                        }
//                        else {
//                            // If sign in fails, display a message to the user
//                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


            }
        });



    }
    private void toLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void toHome(){
        Intent intent = new Intent(this, HomepageActivity.class);
        //TODO: add user information to intent
        startActivity(intent);
    }
}