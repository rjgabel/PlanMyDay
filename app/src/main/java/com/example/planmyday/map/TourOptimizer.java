package com.example.planmyday.map;

import android.util.Log;

import com.example.planmyday.models.Attraction;
import com.example.planmyday.models.TourPlan;
import com.example.planmyday.models.TourStop;

import java.util.ArrayList;

public class TourOptimizer {
    static int[][] duration_matrix = {{0, 1, 3, 1, 2, 2, 2, 2, 19, 9, 13, 2, 21, 16, 9, 24, 14, 8, 23, 20}, {1, 0, 3, 1, 2, 2, 2, 2, 19, 10, 13, 2, 21, 16, 9, 24, 15, 9, 23, 20}, {2, 2, 0, 2, 1, 1, 3, 3, 20, 10, 14, 2, 22, 17, 9, 25, 16, 9, 24, 21}, {1, 2, 3, 0, 2, 2, 1, 2, 19, 9, 13, 2, 21, 15, 9, 24, 14, 8, 23, 20}, {2, 1, 2, 2, 0, 1, 3, 3, 20, 9, 14, 2, 22, 17, 8, 25, 15, 8, 23, 21}, {1, 1, 3, 1, 2, 0, 2, 3, 20, 9, 13, 1, 22, 16, 9, 24, 15, 8, 23, 21}, {2, 3, 4, 2, 3, 3, 0, 2, 19, 9, 13, 3, 21, 16, 9, 24, 15, 8, 24, 20}, {4, 4, 3, 4, 4, 4, 5, 0, 20, 9, 14, 4, 22, 16, 8, 25, 15, 8, 24, 21}, {18, 19, 20, 18, 20, 20, 19, 18, 0, 17, 20, 19, 34, 13, 16, 36, 19, 17, 36, 18}, {9, 9, 8, 9, 9, 8, 10, 10, 18, 0, 15, 9, 24, 14, 2, 27, 17, 3, 27, 19}, {12, 13, 14, 12, 13, 13, 13, 12, 21, 16, 0, 13, 18, 10, 15, 20, 3, 15, 20, 17}, {1, 1, 2, 1, 1, 1, 2, 2, 20, 9, 13, 0, 22, 16, 8, 24, 15, 8, 23, 21}, {20, 20, 21, 19, 21, 21, 21, 20, 33, 23, 17, 20, 0, 25, 23, 18, 19, 22, 9, 31}, {16, 17, 18, 16, 17, 17, 17, 16, 15, 14, 10, 17, 26, 0, 14, 26, 9, 15, 28, 10}, {8, 8, 7, 8, 8, 7, 9, 9, 17, 4, 14, 8, 23, 13, 0, 26, 15, 3, 26, 18}, {25, 25, 26, 24, 26, 26, 25, 25, 38, 28, 21, 25, 20, 27, 28, 0, 23, 27, 22, 27}, {14, 15, 16, 14, 15, 15, 15, 15, 20, 17, 3, 15, 20, 9, 16, 22, 0, 17, 22, 17}, {8, 8, 7, 9, 8, 8, 9, 9, 18, 3, 15, 8, 24, 14, 2, 26, 16, 0, 26, 19}, {23, 23, 24, 23, 23, 23, 24, 24, 37, 27, 20, 22, 9, 28, 27, 22, 22, 26, 0, 35}, {18, 19, 20, 18, 19, 19, 19, 18, 17, 16, 16, 19, 32, 8, 16, 26, 15, 17, 34, 0}};
    static String[] attraction_names = {"Allyson Felix Field", "Epstein Family Plaza", "Jill and Frank Fertitta Hall", "Leavey Library", "Ronald Tutor Campus Center", "USC Fisher Museum of Art", "USC School of Cinematic Arts", "USC Village", "Griffith Observatory", "Little Tokyo", "Los Angeles County Museum of Art", "Natural History Museum", "Santa Monica Beach", "TCL Chinese Theater", "The Broad Museum", "The Getty Center", "The Grove LA", "The Last Bookstore", "Venice Beach", "Universal Studios Hollywood"};

//    public static ArrayList<TourPlan> optimizeTour(ArrayList<Attraction> attractions) {
//        ArrayList<TourStop> stops = new ArrayList<>();
//        ArrayList<TourPlan> plans = new ArrayList<>();
//
//        int cur_day = 0;
//        int cur_time = 0;
//        int cur_attraction_id = -1;
//        while (!attractions.isEmpty()) {
//            int best_attraction = -1;
//            int best_arrival_time = -1;
//            int best_departure_time = -1;
//            for (int i = 0; i < attractions.size(); i++) {
//                Attraction attraction = attractions.get(i);
//                int attraction_id = getAttractionID(attraction);
//                int open_time = attraction.getHours().get(cur_day).get(0);
//                int close_time = attraction.getHours().get(cur_day).get(1);
//                int arrival_time = cur_time + getDuration(cur_attraction_id, attraction_id);
//                if (arrival_time < open_time) {
//                    arrival_time = open_time;
//                }
//                int departure_time = arrival_time + attraction.getTime();
//                if (departure_time > close_time) {
//                    // Can't make it to this attraction today
//                    continue;
//                }
//                if (best_arrival_time == -1 || arrival_time < best_arrival_time) {
//                    best_attraction = i;
//                    best_arrival_time = arrival_time;
//                    best_departure_time = departure_time;
//                }
//            }
//            if (best_attraction != -1) {
//                cur_attraction_id = getAttractionID(attractions.get(best_attraction));
//                stops.add(new TourStop(attractions.get(best_attraction), best_arrival_time, best_departure_time));
//                attractions.remove(best_attraction);
//                cur_time = best_departure_time;
//            } else {
//                // We can't visit any more attractions today, go to next
//                cur_day += 1;
//                if (cur_day >= 7) {
//                    // Can our thing even last more than one week? Fix this if needed!
//                    Log.d("TourOptimizer", "Going to crash now, check TourOptimizer for info");
//                    System.exit(-1);
//                }
//                cur_time = 0;
//                plans.add(new TourPlan(stops));
//                stops = new ArrayList<>();
//                cur_attraction_id = -1;
//            }
//        }
//        plans.add(new TourPlan(stops));
//        return plans;
//    }

    public static ArrayList<TourPlan> optimizeTour(ArrayList<Attraction> attractions, int num_days) {
        //ArrayList<TourStop> stops = new ArrayList<>();
        ArrayList<ArrayList<TourStop>> plans = new ArrayList<>();
        for (int i = 0; i < num_days; i++) {
            ArrayList<TourStop> stops = new ArrayList<>();
            plans.add(stops);
        }

        int cur_day = 0;
        int cur_time = 0;
        int cur_attraction_id = -1;
        while (!attractions.isEmpty()) {
            int best_attraction = -1;
            int best_arrival_time = -1;
            int best_departure_time = -1;
            for (int i = 0; i < attractions.size(); i++) {
                Attraction attraction = attractions.get(i);
                int attraction_id = getAttractionID(attraction);
                int open_time = attraction.getHours().get(cur_day).get(0);
                int close_time = attraction.getHours().get(cur_day).get(1);
                int arrival_time = cur_time + getDuration(cur_attraction_id, attraction_id);
                if (arrival_time < open_time) {
                    arrival_time = open_time;
                }
                int departure_time = arrival_time + attraction.getTime();
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
                cur_attraction_id = getAttractionID(attractions.get(best_attraction));
                plans.get(cur_day).add(new TourStop(attractions.get(best_attraction), best_arrival_time, best_departure_time));
                attractions.remove(best_attraction);
                cur_time = best_departure_time;
            }
            // Go to next day
            cur_day = (cur_day + 1) % num_days;
            int plan_size = plans.get(cur_day).size();
            if (plan_size == 0) {
                cur_time = 0;
                cur_attraction_id = -1;
            } else {
                cur_time = plans.get(cur_day).get(plan_size).getEndTime();
                cur_attraction_id = getAttractionID(plans.get(cur_day).get(plan_size).getAttraction());
            }
        }
        ArrayList<TourPlan> plan_list = new ArrayList<>();
        for (int i = 0; i < num_days; i++) {
            TourPlan plan = new TourPlan(plans.get(i));
            plan_list.add(plan);
        }
        return plan_list;
    }

    public static int calculateTotalTime(TourPlan plan) {
        ArrayList<TourStop> stops = plan.getStops();
        if (stops.isEmpty()) {
            return 0;
        }
        TourStop first_stop = stops.get(0);
        TourStop last_stop = stops.get(stops.size() - 1);
        return last_stop.getEndTime() - first_stop.getStartTime();
    }

    private static int getAttractionID(Attraction attraction) {
        for (int i = 0; i < attraction_names.length; i++) {
            if (attraction_names[i].equals(attraction.getName())) {
                return i;
            }
        }
        return -1;
    }

    private static int getDuration(int src, int dest) {
        return src < 0 || dest < 0 ? 0 : duration_matrix[src][dest];
    }

    public static void getRouteMatrix(ArrayList<Attraction> attractions) {

    }

}
