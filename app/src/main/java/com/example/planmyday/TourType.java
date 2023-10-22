package com.example.planmyday;

import com.example.planmyday.models.Attraction;

import java.util.ArrayList;

public abstract class TourType {
    private ArrayList<Attraction> attractions;

    public abstract ArrayList<Attraction> getAttractions();
}
