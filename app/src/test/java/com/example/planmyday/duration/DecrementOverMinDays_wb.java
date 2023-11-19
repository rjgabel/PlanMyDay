package com.example.planmyday.duration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;

import com.example.planmyday.models.Attraction;
import com.example.planmyday.planning.DurationActivity;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class DecrementOverMinDays_wb {

    private DurationActivity durationActivity;

    @Before
    public void setUp() {
        durationActivity = new DurationActivity();
    }

    @Test
    public void testDecrementOverMinDays() {

        durationActivity.setCurrentDay(5);
        assertEquals(5, durationActivity.getCurrentDay());


        for(int i = 4; i <= 1; i--) {
            durationActivity.decrementDay();
            assertEquals(i, durationActivity.getCurrentDay());
        }

        //once we hit the min at 1, current day should stay at 1
        durationActivity.decrementDay();
        assertEquals(1, durationActivity.getCurrentDay());

        //Toast is shown
        assertEquals(true, ShadowToast.showedToast("Minimum 1 day allowed"));

        ArrayList<Attraction> attractions = new ArrayList<Attraction>();
        Attraction attraction = new Attraction();
        attraction.setName("Sample Test");
        attractions.add(attraction);

        //should still be able to go to the next page
        durationActivity.toMap(attractions);
        assertEquals(true, durationActivity.nextPage);


    }
}
