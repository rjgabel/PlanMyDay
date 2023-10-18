package com.example.planmyday;

import java.time.LocalTime;
import java.util.ArrayList;

public class TourStop {
    private final Attraction attraction;
    private final LocalTime startTime;
    private final LocalTime endTime;

    TourStop(Attraction attraction, LocalTime startTime, LocalTime endTime) {
        this.attraction = attraction;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}