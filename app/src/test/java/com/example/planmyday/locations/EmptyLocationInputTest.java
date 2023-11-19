package com.example.planmyday.locations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;


import static org.junit.Assert.assertEquals;

import com.example.planmyday.planning.LocationsActivity;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class EmptyLocationInputTest {

    private LocationsActivity locationsActivity;

    @Before
    public void setUp() {
        locationsActivity = new LocationsActivity();
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
