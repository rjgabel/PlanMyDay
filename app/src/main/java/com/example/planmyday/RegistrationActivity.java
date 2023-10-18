package com.example.planmyday;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {
    TextInputEditText emailView, passwordView;
    Button login_btn;
    FirebaseAuth mAuth;
    FirebaseDatabase db = FirebaseDatabase.getInstance("https://planmyday-16506-default-rtdb.firebaseio.com/");
    DatabaseReference dbRef = db.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //TODO: add name for registration

        emailView = findViewById(R.id.email);
        passwordView = findViewById(R.id.password);
        login_btn = findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();

        //set of registration when
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(emailView.getText());
                password = String.valueOf(passwordView.getText());
                //check if they are empty
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(RegistrationActivity.this,"Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(RegistrationActivity.this,"Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserAccount user = new UserAccount("",email, password);

                dbRef.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //check if email has been registered before
                        if (snapshot.hasChild(email)){
                            Toast.makeText(RegistrationActivity.this, "Email is already registered", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                dbRef.child("users").child(email).setValue(user);

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
}