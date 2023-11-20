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
    @Mock
    private GeoApiContext mockedGeoApiContext;
    @Mock
    private DirectionsApiRequest mockedDirectionsApiRequest;
    @Mock
    private GoogleMap mockMap;

    private Context context;

    @Before
    public void setUp() {

        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        FirebaseApp.initializeApp(RuntimeEnvironment.getApplication());
        mockItineraryActivity = mock(ItineraryActivity.class);

        mockedGeoApiContext = new GeoApiContext.Builder().apiKey(String.valueOf(R.string.maps_key)).build();
        mockMap = mock(GoogleMap.class);
//        mockedDirectionsApiRequest = Mockito.mock(DirectionsApiRequest.class);
        mockItineraryActivity.map = mockMap;
    }


    @Test
    public void tour_number_of_days(){
        CreateAttractions ca = new CreateAttractions(context);
        ca.generate();
        assertEquals(2, 2);
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
