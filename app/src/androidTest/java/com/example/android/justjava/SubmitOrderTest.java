package com.example.android.justjava;


import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class SubmitOrderTest {

	private static final String USERNAME = "Gerrard";
	private static final String QUANTITY = "1";
	private static final String ORDER_TOTAL = "$8.00";
	private static final String ADDONS = "Yes";
	private static final String VALID_SUBJECT = "JustJava order for " + USERNAME;

	@Rule
	public IntentsTestRule<MainActivity> mActivityIntentsTestRule = new IntentsTestRule<>(MainActivity.class);

	@Before
	public void stubAllExternalIntents() {
		intending(not(isInternal()))
				.respondWith(new ActivityResult(Activity.RESULT_OK, null));
	}

	@Test
	public void clickSubmitOrder_SendsEmail() {
		// Mock order
		String orderSum =
				"Name: " + USERNAME + "\n" +
						"Number of Coffee: " + QUANTITY + "\n" +
						"Add Whipped Cream? " + ADDONS + "\n" +
						"Add Chocolate? " + ADDONS + "\n" +
						"Total: " + ORDER_TOTAL + "\n" +
						"Thank You!";

		onView(withId(R.id.get_name)).perform(typeText(USERNAME)); // Enter user's name
		onView(withId(R.id.cream_checkbox))
				.check(matches(isNotChecked()))
				.perform(scrollTo(), click()); // Toggle checkbox for cream to "ON"
		onView(withId(R.id.chocolate_checkbox))
				.check(matches(isNotChecked()))
				.perform(scrollTo(), click()); // Toggle checkbox for chocolate to "ON"

		onView(withId(R.id.orderSubmit)).perform(scrollTo(), click()); // Submit order

		intended(allOf(
				hasAction(Intent.ACTION_SENDTO),
				hasExtra(Intent.EXTRA_SUBJECT, VALID_SUBJECT),
				hasExtra(Intent.EXTRA_TEXT, orderSum)
		));
	}
}
