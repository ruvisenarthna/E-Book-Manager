package com.mad.e_librarymanager.admin;

import android.app.Instrumentation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import androidx.test.rule.ActivityTestRule;

import com.mad.e_librarymanager.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class Admin_Add_BookTest {

    // Defining the Test Rule which will Test the MainActivity
    @Rule
    public ActivityTestRule<Admin_Add_Book> mActivityActivityTestRule =
            new ActivityTestRule<Admin_Add_Book>(Admin_Add_Book.class);

    // Setup an Activity Monitor to track the Functionality
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Admin_Add_Book.class.getName(),null,false);

    private Admin_Add_Book admin_add_book = null;

    @Before
    public void setUp() throws Exception {
        admin_add_book = mActivityActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        admin_add_book = null;
    }

    @Test
    public void uploadData() {

        //Define the variables
        String title,author,price;

        //initialized values into variables
        title = "Matilda";
        author = "Roald Dahl";
        price = "750";

        int output = 0;
        int expected = 1;

        //Pass values in to method
        output = admin_add_book.uploadData(title,author,price);

        // Test whether the expected value is returned
        assertEquals(expected,output);

    }
}