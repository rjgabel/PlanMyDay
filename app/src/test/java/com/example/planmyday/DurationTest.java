package com.example.planmyday;

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
public class DurationTest {

    private DurationActivity durationActivity;

    @Before
    public void setUp() {
        durationActivity = new DurationActivity();
    }

    @Test
    public void testIncrementOverMaxDays() {

        assertEquals(1, durationActivity.getCurrentDay());


        for(int i = 2; i <= 5; i++) {
            durationActivity.incrementDay();
            assertEquals(i, durationActivity.getCurrentDay());
        }

        //once we max out at 5, current day should stay at 5
        durationActivity.incrementDay();
        assertEquals(5, durationActivity.getCurrentDay());

        //Toast is shown
        assertEquals(true, ShadowToast.showedToast("Maximum 5 days allowed"));


    }
    @Test
    public void testDecrementOverMinDays() {

        assertEquals(1, durationActivity.getCurrentDay());

        //once we hit the min at 1, current day should stay at 1
        durationActivity.decrementDay();
        assertEquals(1, durationActivity.getCurrentDay());

        //Toast is shown
        assertEquals(true, ShadowToast.showedToast("Minimum 1 day allowed"));

    }

}
