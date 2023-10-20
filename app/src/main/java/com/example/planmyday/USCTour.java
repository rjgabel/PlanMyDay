package com.example.planmyday;

import com.example.planmyday.models.Attraction;

public class USCTour extends TourType{
    @Override
    public Attraction[] getAttractions() {
        return new Attraction[0];
    }
}
