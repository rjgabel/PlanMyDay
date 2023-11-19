package com.example.planmyday;

import static org.junit.Assert.assertNull;

import com.example.planmyday.map.TourOptimizer;
import com.example.planmyday.models.Attraction;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class RyanUnitTest {
    @Test
    public void tourOptimizer_attractionsNull() {
        assertNull(TourOptimizer.optimizeTour(null, 1));
    }

    @Test
    public void tourOptimizer_attractionsEmpty() {
        assertNull(TourOptimizer.optimizeTour(new ArrayList<>(), 1));
    }

    @Test
    public void tourOptimizer_containsNull() {
        ArrayList<Attraction> attractions = new ArrayList<>();
        attractions.add(null);
        assertNull(TourOptimizer.optimizeTour(attractions, 1));
    }

    @Test
    public void tourOptimizer_attractionHoursNull() {
        Attraction a = new Attraction(null, null, false, null, 0, 0, null, null, 0, 0);
        ArrayList<Attraction> attractions = new ArrayList<>();
        attractions.add(a);
        assertNull(TourOptimizer.optimizeTour(attractions, 1));
    }

    @Test
    public void tourOptimizer_attractionHoursContainsNull() {
        Attraction a = new Attraction(null, null, false, null, 0, 0, new HashMap<>(), null, 0, 0);
        ArrayList<Attraction> attractions = new ArrayList<>();
        attractions.add(a);
        assertNull(TourOptimizer.optimizeTour(attractions, 1));
    }
}
