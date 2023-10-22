package com.example.planmyday.planning;

import com.example.planmyday.home.MainActivity;
import com.example.planmyday.models.Attraction;

import java.util.ArrayList;
import java.util.Map;

public class LATour extends TourType {
    //DatabaseReference dbRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://planmyday-16506-default-rtdb.firebaseio.com/");
    @Override
    public ArrayList<Attraction> filter(ArrayList<Attraction> rawAttractions) {
        //if name is not usc, remove
        ArrayList<Attraction> filteredAttractions = new ArrayList<>();
        for (Attraction attraction : rawAttractions){
            if (attraction.isUsc() == false){
                filteredAttractions.add(attraction);
            }
        }


        //return attractions that are only in at LA
//        dbRef.child("attractions").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        return filteredAttractions;
    }

//    public void attractionsHelper(Map<String, Object> attractions){
//        ArrayList<String> names = new ArrayList<>();
//
//        for (Map.Entry<String, Object> entry : attractions.entrySet()){
//            //Map singleAttraction = (Map)
//        }
//    }
}