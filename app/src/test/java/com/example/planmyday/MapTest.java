package com.example.planmyday;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.widget.Button;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.planmyday.map.ItineraryActivity;
import com.example.planmyday.map.TourOptimizer;
import com.example.planmyday.models.Attraction;
import com.example.planmyday.models.TourPlan;
import com.example.planmyday.models.TourStop;
import com.example.planmyday.planning.CreateAttractions;
import com.example.planmyday.planning.LocationsActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.TravelMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class MapTest {
    private ItineraryActivity mockItineraryActivity;
    private ItineraryActivity itineraryActivity;
    @Mock
    private GeoApiContext mockedGeoApiContext;
    @Mock
    private DirectionsApiRequest mockedDirectionsApiRequest;
    @Mock
    private GoogleMap mockMap;

    private Context context;

    @Before
    public void setUp() {
        itineraryActivity = new ItineraryActivity();
        mockItineraryActivity = mock(ItineraryActivity.class);

        //mockedGeoApiContext = new GeoApiContext.Builder().apiKey(String.valueOf(R.string.maps_key)).build();
        //mockMap = mock(GoogleMap.class);
//        mockedDirectionsApiRequest = Mockito.mock(DirectionsApiRequest.class);
        //mockItineraryActivity.map = mockMap;
    }


    @Test
    public void tours_on_multiple_days(){
        HashMap<String, ArrayList<Integer>> hoursMap = new HashMap<>();
        ArrayList<Integer> hours = new ArrayList<>();
        hours.add(900);
        hours.add(1800);
        for (int i = 0; i < 7; i++){
            hoursMap.put(Integer.toString(i), hours);
        }


        ArrayList<Attraction> attractions = new ArrayList<>();
        Attraction attraction1 =
                new Attraction("Allyson Felix Field", "address", true,
                        "description", 10, 0, hoursMap, "image",
                        34.022415, -118.285530);
        Attraction attraction2 =
                new Attraction("Epstein Family Plaza", "address", true,
                        "description", 10, 0, hoursMap, "image",
                        34.022415, -118.285530);


        attractions.add(attraction1);
        attractions.add(attraction2);

        ArrayList<Attraction> attractionsCopy = new ArrayList<>(attractions);

        //input only 1 day should give only a 1 day combined tour
        ArrayList<TourPlan> optimizedTour1 = TourOptimizer.optimizeTour(attractions, 1);
        assertEquals(1, optimizedTour1.size());

        //input 2 days should give 2 day tour, splitting up necessary parts
        ArrayList<TourPlan> optimizedTour2 = TourOptimizer.optimizeTour(attractionsCopy, 2);
        assertEquals(2, optimizedTour2.size());
    }


    @Test
    public void change_day(){
        //itineraryActivity.currDay = 1;
        assertEquals(0, itineraryActivity.currDay);
        itineraryActivity.updateDay(1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, itineraryActivity.currDay);

        itineraryActivity.updateDay(-1);
        assertEquals(0, itineraryActivity.currDay);

        for (int i = 0; i < 4; i++){
            itineraryActivity.updateDay(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            assertEquals(i+1, itineraryActivity.currDay);
        }

    }


    //Add markers to the map
    @Test
    public void markers_on_map(){
        //itineraryActivity.map = mockMap;
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        TourStop stop1 = new TourStop(new Attraction("1", "address", false, "description", 1, 1, null, "image", 34.022415, -118.285530), 1100, 1300);
        TourStop stop2 = new TourStop(new Attraction("1", "address", false, "description", 1, 1, null, "image", 34.022423, -118.285512), 1100, 1300);
        ArrayList<TourStop> mockStops = new ArrayList<>();
        mockStops.add(stop1);
        mockStops.add(stop2);

        ArrayList<TourPlan> tempTour = new ArrayList<>();
        tempTour.add(new TourPlan(mockStops));
        mockItineraryActivity.tourPlans = tempTour;
        mockItineraryActivity.currDay = 1;

        //itineraryActivity.calculateDirections(34.022415, -118.285530, 34.022423, -118.285512, true, TravelMode.DRIVING);

        // Verify that addMarker is called for each TourStop
//        for (TourStop stop : mockStops) {
//            Attraction currAttraction = stop.getAttraction();
//            LatLng curr = new LatLng(currAttraction.getLatitude(), currAttraction.getLongitude());
//            Marker marker = itineraryActivity.map.addMarker(new MarkerOptions().position(curr).title(currAttraction.getName()));
//            verify(mockMap).addMarker(any(MarkerOptions.class));
//        }
    }

}
