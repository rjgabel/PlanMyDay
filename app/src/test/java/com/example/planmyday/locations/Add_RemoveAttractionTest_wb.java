package com.example.planmyday.locations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

import android.os.Build;

import com.example.planmyday.models.Attraction;
import com.example.planmyday.planning.LocationsActivity;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class Add_RemoveAttractionTest_wb {

    private LocationsActivity locationsActivity;

    @Before
    public void setUp() {
        locationsActivity = new LocationsActivity();
    }

    @Test
    public void testAddRemoveAttraction() {


       //Test add to favorites
        Attraction attraction = new Attraction();
        attraction.setName("Sample Test");
        locationsActivity.addToFavorites(attraction);

        // Verify that the attraction is added to the selectedAttractions list. There should only be this exact attraction in the list.
        assertEquals(1, locationsActivity.selectedAttractions.size());
        assertEquals(attraction, locationsActivity.selectedAttractions.get(0));
        assertEquals(attraction.getName(), locationsActivity.selectedAttractions.get(0).getName());


        //Test remove from favorites
        locationsActivity.addToFavorites(attraction);

        // Verify that the attraction is removed from selectedAttractions. There should be no more attractions
        assertEquals(0, locationsActivity.selectedAttractions.size());
    }
}