package com.example.planmyday.duration;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;

import com.example.planmyday.R;
import com.example.planmyday.home.MainActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddButtonTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);//   @Test
//    public void testAddButton() {
//
//        ListView locationList = onView(allOf(withId(R.id.locationList), withText("+"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
//        appCompatButton.perform(click());
//
//        Espresso.onView(ViewMatchers.withId(R.id.locationList))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
//
//        // Check if the favorites button color turns green
//        Espresso.onView(ViewMatchers.withId(R.id.favoritesButton)) // replace with the actual ID of your favorites button
//                .check(matches((Matcher<? super View>) isBackgroundColor(Color.parseColor("#11CA9D"))));
//    }
//
//    public static Matcher<View> isBackgroundColor(final int color) {
//        return new BoundedMatcher<View, View>(View.class) {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("with background color: ");
//                description.appendValue(color);
//            }
//
//            @Override
//            protected boolean matchesSafely(View view) {
//                int expectedColor = view.getResources().getColor(color);
//                int actualColor = ((ColorDrawable) view.getBackground()).getColor();
//                return actualColor == expectedColor;
//            }
//        };
//    }

}
