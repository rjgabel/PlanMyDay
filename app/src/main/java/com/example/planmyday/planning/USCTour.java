package com.example.planmyday.planning;

import com.example.planmyday.models.Attraction;
import com.example.planmyday.planning.TourType;

import java.util.ArrayList;

public class USCTour extends TourType {
    @Override
    public ArrayList<Attraction> filter(ArrayList<Attraction> rawAttractions) {
        ArrayList<Attraction> filteredAttractions = new ArrayList<>();
        for (Attraction attraction : rawAttractions){
            if (attraction.isUsc() == true){
                filteredAttractions.add(attraction);
            }
        }
        return filteredAttractions;
    }
}
