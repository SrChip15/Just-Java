package com.example.android.justjava;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ChangeQuantityTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.android.justjava", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickDecrementButton_changesQuantity() {
        onView(withId(R.id.quantity_text_view)).check(matches(withText("1"))); // Minimum order quantity
        onView(withId(R.id.decrementCoffee)).perform(click()); // Click decrement button
        onView(withId(R.id.quantity_text_view)).check(matches(withText("1"))); // Below minimum qty?
    }

    @Test
    public void clickIncrementButton_changesQuantity() {
        onView(withId(R.id.incrementCoffee)).perform(click()); // Click increment button
        onView(withId(R.id.quantity_text_view)).check(matches(withText("2")));
    }
}
