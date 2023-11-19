package com.example.planmyday;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

import android.content.Intent;
import android.widget.Button;

import com.example.planmyday.models.Attraction;

import java.util.Arrays;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MapTest {

    @Test
    public void markers_on_map(){

    }

//    @Test
//    public void attractions_to_map(){
//        // Arrange
//        Attraction mockAttraction1 = Mockito.mock(Attraction.class);
//        Attraction mockAttraction2 = Mockito.mock(Attraction.class);
//        List<Attraction> mockAttractionsList = Arrays.asList(mockAttraction1, mockAttraction2);
//
//        // Access the activity's confirm button
//        //Button confirmButton = activityRule.getActivity().findViewById(R.id.confirmButton);
//
//        // Act: Click the confirm button
//        //Espresso.onView(ViewMatchers.withId(R.id.confirmButton)).perform(ViewActions.click());
//
//        // Assert: Check that the intent contains the expected list of attractions
//        //Intent intent = Iterables.getOnlyElement(Intents.getIntents());
//        List<Attraction> passedAttractions = intent.getParcelableArrayListExtra(AttractionSelectionActivity.ATTRACTION_LIST_KEY);
//
//        assertEquals(mockAttractionsList.size(), passedAttractions.size());
//        assertTrue(passedAttractions.contains(mockAttraction1));
//        assertTrue(passedAttractions.contains(mockAttraction2));
//    }
//    }
}
