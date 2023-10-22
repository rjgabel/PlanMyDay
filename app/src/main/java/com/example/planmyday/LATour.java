package com.example.planmyday;

import androidx.annotation.NonNull;

import com.example.planmyday.models.Attraction;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class LATour extends TourType {
    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://planmyday-16506-default-rtdb.firebaseio.com/");
    @Override
    public ArrayList<Attraction> getAttractions() {
        //return attractions that are only in at LA
        dbRef.child("attractions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return new ArrayList<Attraction>();
    }

    public void attractionsHelper(Map<String, Object> attractions){
        ArrayList<String> names = new ArrayList<>();

        for (Map.Entry<String, Object> entry : attractions.entrySet()){
            //Map singleAttraction = (Map)
        }
    }
}