package com.example.planmyday.home;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.planmyday.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RyanInstrumentedTest1 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void ryanInstrumentedTest() {
        ViewInteraction appCompatButton = onView(allOf(withId(R.id.newitinerary), withText("+"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatImageView = onView(allOf(withId(R.id.uscButton), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatButton2 = onView(allOf(withId(R.id.favoritesButton), withText("ADD"), childAtPosition(withParent(withId(R.id.locationList)), 5), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(allOf(withId(R.id.favoritesButton), withText("ADD"), childAtPosition(withParent(withId(R.id.locationList)), 5), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(allOf(withId(R.id.nextButton), withText("NEXT"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 0), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction materialTextView = onView(allOf(withId(R.id.plusButton), withText("?"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 0), isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction appCompatButton5 = onView(allOf(withId(R.id.reviewItineraryButton), withText("CREATE TOUR"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(allOf(withId(R.id.redirect), withText("MAP"), childAtPosition(allOf(withId(R.id.slidingPanel), childAtPosition(withId(R.id.sliding_layout), 1)), 2), isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction materialButton = onView(allOf(withId(android.R.id.button2), withText("Cancel"), childAtPosition(childAtPosition(withId(com.google.android.material.R.id.buttonPanel), 0), 2)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction constraintLayout = onView(allOf(withId(R.id.slidingPanel), childAtPosition(allOf(withId(R.id.sliding_layout), childAtPosition(withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")), 0)), 1), isDisplayed()));
        constraintLayout.perform(click());
    }
}
