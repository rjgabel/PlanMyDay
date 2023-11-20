package com.example.planmyday;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;

import com.example.planmyday.models.Attraction;
import com.example.planmyday.planning.LocationsActivity;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class LocationTest {

    private LocationsActivity locationsActivity;

    @Before
    public void setUp() {
        locationsActivity = new LocationsActivity();
    }

    @Test
    public void testAddAttraction() {
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

    @Test
    public void testRemoveAttraction() {
        Attraction attraction = new Attraction();
        attraction.setName("Sample Test");
        locationsActivity.addToFavorites(attraction);

        //Test remove from favorites
        locationsActivity.addToFavorites(attraction);

        // Verify that the attraction is removed from selectedAttractions. There should be no more attractions
        assertEquals(0, locationsActivity.selectedAttractions.size());
    }

    @Test
    public void testEmptyLocationInput() {

        // Call the toDuration function while selectedAttractions is empty
        locationsActivity.toDuration("usc");

        assertEquals(0, locationsActivity.selectedAttractions.size());

        //Toast is shown
        assertEquals(true, ShadowToast.showedToast("No selected attractions"));
        //Does not go to the next page
        assertEquals(false, locationsActivity.nextPage);
    }
}