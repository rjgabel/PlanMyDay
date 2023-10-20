package com.example.planmyday;

import android.util.Log;

import com.example.planmyday.models.Attraction;
import com.example.planmyday.models.TourPlan;

import java.util.ArrayList;

public class TourOptimizer {
    public static ArrayList<TourPlan> optimizeTour(ArrayList<Attraction> attractions) {
        ArrayList<TourStop> stops = new ArrayList<>();
        for (Attraction attraction : attractions) {
            stops.add(new TourStop(attraction, attraction.getHours().get(0).get(0), attraction.getHours().get(0).get(1)));
        }
        ArrayList<TourPlan> plans = new ArrayList<>();
        plans.add(new TourPlan(stops));
        return plans;
    }
}
