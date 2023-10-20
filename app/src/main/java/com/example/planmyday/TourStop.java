package com.example.planmyday;

import com.example.planmyday.models.Attraction;

import java.time.LocalTime;

public class TourStop {
    private final Attraction attraction;
    private final int startTime;
    private final int endTime;

    TourStop(Attraction attraction, int startTime, int endTime) {
        this.attraction = attraction;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }
}