package com.example.planmyday.planning;

import com.example.planmyday.models.Attraction;
import com.example.planmyday.planning.TourType;

import java.util.ArrayList;

public class USCTour extends TourType {
    @Override
    public ArrayList<Attraction> getAttractions() {
        return new ArrayList<>();
    }
}
