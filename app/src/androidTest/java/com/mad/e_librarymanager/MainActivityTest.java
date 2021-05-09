package com.mad.e_librarymanager;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class MainActivityTest {

    // Defining the Test Rule which will Test the MainActivity
    @Rule
    public ActivityTestRule<MainActivity> mActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    // Setup an Activity Monitor to track the Functionality
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null,false);

    private MainActivity mainActivity = null;

    //Before the Test Begin
    @Before
    public void setUp() throws Exception {
        mainActivity = mActivityActivityTestRule.getActivity();
    }

    // Testing
    @Test
    public void testLaunch(){

        // Initialize the button which we need to be tested
        View view = mainActivity.findViewById(R.id.btn_admin);

        // Test whether the button is not null
        assertNotNull(view);

        // Initialize the functional Test for Button
        onView(withId(R.id.btn_admin)).perform(click());

        //Set the Activity monitor for Tracking the function
        Activity testedFunction = getInstrumentation().waitForMonitorWithTimeout(monitor,25000);

        // check whether the returend activity is not null
        assertNotNull(testedFunction);

        testedFunction.finish();


    }

    //After Test Finish
    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}