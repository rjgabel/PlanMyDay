package com.example.planmyday.map;

import android.util.Log;

import com.example.planmyday.models.Attraction;
import com.example.planmyday.models.TourPlan;
import com.example.planmyday.models.TourStop;

import java.util.ArrayList;

public class TourOptimizer {
    public static ArrayList<TourPlan> optimizeTour(ArrayList<Attraction> attractions) {
        ArrayList<TourStop> stops = new ArrayList<>();
        ArrayList<TourPlan> plans = new ArrayList<>();

        int cur_day = 0;
        int cur_time = 0;
        while (!attractions.isEmpty()) {
            int best_attraction = -1;
            int best_arrival_time = -1;
            int best_departure_time = -1;
            for (int i = 0; i < attractions.size(); i++) {
                Attraction attraction = attractions.get(i);
                int open_time = attraction.getHours().get(cur_day).get(0);
                int close_time = attraction.getHours().get(cur_day).get(1);
                int arrival_time = cur_time;
                if (arrival_time < open_time) {
                    arrival_time = open_time;
                }
                int departure_time = open_time + attraction.getTime();
                if (departure_time > close_time) {
                    // Can't make it to this attraction today
                    continue;
                }
                if (best_arrival_time == -1 || arrival_time < best_arrival_time) {
                    best_attraction = i;
                    best_arrival_time = arrival_time;
                    best_departure_time = departure_time;
                }
            }
            if (best_attraction != -1) {
                stops.add(new TourStop(attractions.get(best_attraction), best_arrival_time, best_departure_time));
                attractions.remove(best_attraction);
                cur_time = best_departure_time;
            } else {
                // We can't visit any more attractions today, go to next
                cur_day += 1;
                if (cur_day >= 7) {
                    // Can our thing even last more than one week? Fix this if needed!
                    Log.d("TourOptimizer", "Going to crash now, check TourOptimizer for info");
                    System.exit(-1);
                }
                cur_time = 0;
                plans.add(new TourPlan(stops));
                stops = new ArrayList<>();
            }
        }
        plans.add(new TourPlan(stops));
        return plans;
    }

    public static void getRouteMatrix(ArrayList<Attraction> attractions){

    }

}
