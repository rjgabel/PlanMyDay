package com.example.planmyday;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.planmyday.map.TourOptimizer;
import com.example.planmyday.models.Attraction;
import com.example.planmyday.models.TourPlan;
import com.example.planmyday.planning.CreateAttractions;
import com.example.planmyday.planning.LATour;
import com.example.planmyday.planning.USCTour;
import com.google.firebase.FirebaseApp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.HashMap;

@RunWith(RobolectricTestRunner.class)
public class RyanUnitTest {
    @Before
    public void setUp(){
    }

    //WRITTEN BY TYLER TRAN
    @Test
    public void tours_on_multiple_days(){
        ArrayList<Attraction> attractions = new ArrayList<>();
        for (int i = 0; i < 12; i++){
            Attraction attraction =
                    new Attraction("1", "address", false, "description", 1, 1, null, "image", 34.022415, -118.285530);
        }

        //ArrayList<TourPlan> optimizedTour =
    }

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
