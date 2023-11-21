package com.example.planmyday.LocationTest;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.planmyday.R;
import com.example.planmyday.home.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddLocationTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void addLocationTest() {
        ViewInteraction appCompatButtonlogin = onView(allOf(withId(R.id.loginButton), withText("LOG IN"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()));
        appCompatButtonlogin.perform(click());

        ViewInteraction textInputEditText = onView(allOf(withId(R.id.email), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()));
        textInputEditText.perform(replaceText("sysung@usc.edu"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(allOf(withId(R.id.password), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
        textInputEditText2.perform(replaceText("orange2"), closeSoftKeyboard());

        ViewInteraction appCompatButtonbtn = onView(allOf(withId(R.id.btn_login), withText("LOG IN"), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 4), isDisplayed()));
        appCompatButtonbtn.perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.newitinerary), withText("+"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.laButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageView.perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //check initial text and color of button
        ViewInteraction button = onView(
                allOf(withId(R.id.favoritesButton), withText("ADD"),
                        childAtPosition(
                                withParent(withId(R.id.locationList)),
                                5),
                        isDisplayed()));
        button.check(matches(isDisplayed()));
        button.check(matches(withTextColor(Color.parseColor("#000001"))));

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.favoritesButton), withText("ADD"),
                        childAtPosition(
                                withParent(withId(R.id.locationList)),
                                5),
                        isDisplayed()));
        appCompatButton4.perform(click());

        //when a location is added, the text is changed and button changes color
        ViewInteraction button2 = onView(
                allOf(withId(R.id.favoritesButton), withText("ADDED"),
                        childAtPosition(
                                withParent(withId(R.id.locationList)),
                                5),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
        button2.check(matches(withTextColor(Color.parseColor("#000000"))));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static Matcher<View> withTextColor(final int expectedId) {
        return new BoundedMatcher<View, TextView>(TextView.class) {

            @Override
            protected boolean matchesSafely(TextView textView) {
                return expectedId == textView.getCurrentTextColor();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text color: ");
                description.appendValue(expectedId);
            }
        };
    }
}