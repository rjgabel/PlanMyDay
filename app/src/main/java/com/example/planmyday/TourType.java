package com.example.planmyday;

import com.example.planmyday.models.Attraction;

public abstract class TourType {
    private Attraction[] attractions;

    public abstract Attraction[] getAttractions();
}
