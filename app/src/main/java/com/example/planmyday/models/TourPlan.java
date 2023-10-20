package com.example.planmyday.models;

import com.example.planmyday.TourStop;

import java.time.LocalTime;
import java.util.ArrayList;

public class TourPlan {
    private ArrayList<TourStop> stops;
    private LocalTime startTime;
    private LocalTime endTime;
    private int durationChoice;
    private ArrayList<LocalTime> acceptableTimes;

    public ArrayList<TourStop> getStops() {
        return stops;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
