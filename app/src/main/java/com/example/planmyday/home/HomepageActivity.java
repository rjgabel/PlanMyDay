package com.example.planmyday.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.planmyday.R;
import com.example.planmyday.models.UserAccount;
import com.example.planmyday.planning.LocationsActivity;
import com.example.planmyday.planning.MyAdapter;
import com.example.planmyday.planning.PlanActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomepageActivity extends AppCompatActivity {

    AppCompatButton itinerary;
    FirebaseAuth mAuth;
    UserAccount userAccount;
    String uid;
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://planmyday-16506-default-rtdb.firebaseio.com/");
    TextView welcome, logout;
    ListView oldPlansList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mAuth = FirebaseAuth.getInstance();
        //dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://planmyday-16506-default-rtdb.firebaseio.com/");

        welcome = findViewById(R.id.welcome);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                toHome();
            }
        });

        FirebaseUser currentUser = mAuth.getCurrentUser();
        uid = currentUser.getUid();

        if(currentUser == null){
            //send to mainActivity
        }

        dbRef.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    userAccount = snapshot.getValue(UserAccount.class);
                    Log.d("userAccount1", uid);
                    welcome.setText("Welcome, " + userAccount.getName() + "!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        itinerary = findViewById(R.id.newitinerary);

        itinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toPlan();
            }
        });

//        oldPlansList = findViewById(R.id.oldPlansList);
//        HomeAdapter myAdapter = new HomeAdapter(HomepageActivity.this, null);
//        oldPlansList.setAdapter(myAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        uid = currentUser.getUid();

        if(currentUser == null){
            //send to mainActivity
            startActivity(new Intent(this, MainActivity.class));
        }

    }

    private void toPlan(){
        Intent intent = new Intent(this, PlanActivity.class);
        startActivity(intent);
    }

    private void toHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}