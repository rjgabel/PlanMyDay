package com.example.planmyday.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.planmyday.R;
import com.example.planmyday.home.HomepageActivity;
import com.example.planmyday.models.Attraction;
import com.example.planmyday.models.SavedPlan;
import com.example.planmyday.models.TourPlan;
import com.example.planmyday.models.UserAccount;
import com.example.planmyday.planning.CreateAttractions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Attr;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {
    TextInputEditText emailView, passwordView, confirmPasswordView, nameView;
    Button login_btn, reroute;
    //FirebaseAuth mAuth;
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://planmyday-16506-default-rtdb.firebaseio.com/");
    FirebaseAuth mAuth;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //TODO: add name for registration
        mAuth = FirebaseAuth.getInstance();
        nameView = findViewById(R.id.name);
        emailView = findViewById(R.id.email);
        passwordView = findViewById(R.id.password);
        confirmPasswordView = findViewById(R.id.password_check);
        login_btn = findViewById(R.id.btn_login);
        reroute = findViewById(R.id.reroute_login);

        FirebaseApp.initializeApp(this);

        reroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });
        Context context = this;
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
                //TODO: check for email formatting
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(RegistrationActivity.this,"Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6){
                    Toast.makeText(RegistrationActivity.this,"Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(confirmPassword)){
                    Toast.makeText(RegistrationActivity.this,"Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<SavedPlan> temp = new ArrayList<>();
//                CreateAttractions ca = new CreateAttractions(context);
//                ca.generate();
//                ArrayList<Attraction> arr = ca.getAttractions();
//                SavedPlan sp = new SavedPlan(arr, 5, "Jan 31, 2003");
//                temp.add(sp);
                UserAccount userAccount = new UserAccount(name, email, password, temp);

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String uid = user.getUid();
                                    dbRef.child("users").child(uid).setValue(userAccount);
                                    Toast.makeText(RegistrationActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                    toHome();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    if (task.getException() != null) {
                                        Log.e("FirebaseAuth", task.getException().getMessage());
                                        String str = task.getException().getMessage();
                                        Toast.makeText(RegistrationActivity.this, str,
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });



    }


    private void toLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
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