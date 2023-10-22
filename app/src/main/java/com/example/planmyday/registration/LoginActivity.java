package com.example.planmyday.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.planmyday.R;
import com.example.planmyday.home.HomepageActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText emailView, passwordView;
    Button login_btn, reroute;
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://planmyday-16506-default-rtdb.firebaseio.com/");
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailView = findViewById(R.id.email);
        passwordView = findViewById(R.id.password);
        login_btn = findViewById(R.id.btn_login);
        reroute = findViewById(R.id.reroute_login);

        reroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRegistration();
            }
        });

        //TODO: make button for forget password and make new page

        //TODO: add forget password option

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
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(LoginActivity.this,"Successfully Logged in", Toast.LENGTH_SHORT).show();
                                    toHome();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.e("FirebaseAuth", task.getException().getMessage());
                                    String str = task.getException().getMessage();
                                    Toast.makeText(LoginActivity.this, str,
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
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

    private void toHome(){
        Intent intent = new Intent(this, HomepageActivity.class);
        //TODO: add user information to intent
        startActivity(intent);
    }
}