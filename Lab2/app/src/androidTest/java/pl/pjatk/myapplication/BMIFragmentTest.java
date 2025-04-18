package pl.pjatk.myapplication;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class BMIFragmentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testBmiCalculation() {
        onView(withId(R.id.navigation_notifications)).perform(click());

        onView(withId(R.id.inputWeight)).perform(typeText("70"), closeSoftKeyboard());
        onView(withId(R.id.inputHeight)).perform(typeText("170"), closeSoftKeyboard());

        onView(withId(R.id.submit)).perform(click());

        onView(withId(R.id.textResult)).check(matches(withSubstring("24.22")));
    }
}
