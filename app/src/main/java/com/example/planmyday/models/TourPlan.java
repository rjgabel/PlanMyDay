package com.example.planmyday.models;

import com.example.planmyday.TourStop;

import java.time.LocalTime;
import java.util.ArrayList;

public class TourPlan {
    private final ArrayList<TourStop> stops;
    private int startTime;
    private int endTime;
    private int durationChoice;
    private ArrayList<LocalTime> acceptableTimes;

    public TourPlan(ArrayList<TourStop> stops) {
        this.stops = stops;
    }

    public ArrayList<TourStop> getStops() {
        return stops;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }
}
