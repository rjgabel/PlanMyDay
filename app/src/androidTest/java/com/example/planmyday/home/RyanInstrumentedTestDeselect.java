package com.example.planmyday.home;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

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
public class RyanInstrumentedTestDeselect {

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
    public void ryanInstrumentedTestDeselect() {
        ViewInteraction appCompatButton = onView(allOf(withId(R.id.loginButton), withText("LOG IN"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textInputEditText = onView(allOf(withId(R.id.email), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()));
        textInputEditText.perform(replaceText("rjgabel@usc.edu"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(allOf(withId(R.id.password), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        textInputEditText2.perform(replaceText("rjgabel"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(allOf(withId(R.id.btn_login), withText("LOG IN"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 4), isDisplayed()));
        appCompatButton2.perform(click());

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ViewInteraction appCompatButton3 = onView(allOf(withId(R.id.newitinerary), withText("+"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatImageView = onView(allOf(withId(R.id.uscButton), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatButton4 = onView(allOf(withId(R.id.favoritesButton), withText("ADD"), childAtPosition(withParent(withId(R.id.locationList)), 5), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(allOf(withId(R.id.favoritesButton), withText("ADDED"), childAtPosition(withParent(withId(R.id.locationList)), 5), isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(allOf(withId(R.id.nextButton), withText("NEXT"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 0), isDisplayed()));
        appCompatButton6.perform(click());
    }
}
