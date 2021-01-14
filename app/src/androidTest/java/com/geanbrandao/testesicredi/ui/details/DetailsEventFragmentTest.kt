package com.geanbrandao.testesicredi.ui.details

import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geanbrandao.testesicredi.R
import com.geanbrandao.testesicredi.model.Event
import com.geanbrandao.testesicredi.module.adapterModule
import com.geanbrandao.testesicredi.module.networkModule
import com.geanbrandao.testesicredi.module.repositoryModule
import com.geanbrandao.testesicredi.module.viewModelModule
import com.geanbrandao.testesicredi.ui.main.MainActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules

@RunWith(AndroidJUnit4::class)
class DetailsEventFragmentTest {

    val EXPECTED_NAME = "Gean"
    val EXPECTED_EMAIL = "gean@gmail.com"

    /*
    @Test
    fun test_showDialog_getNameAndEmailInput() {

        // GIVEN - what it's given to test

        val fragmentArgs = Bundle()
        val event = Event(1, "test", "test description", "", 0.0, 0.0, 2f, 123L)
        fragmentArgs.putSerializable("key_event_arg", event)

//        loadKoinModules(listOf(viewModelModule, repositoryModule, adapterModule, networkModule))

        launchFragmentInContainer<DetailsEventFragment>(
            fragmentArgs = fragmentArgs, // Bundle
            themeResId = R.style.TesteSicredi,
        )

        // EXECUTE and VERIFY
        onView(withId(R.id.button_checkin)).perform(click()) // simulates the button click

        // now checks if the text from dialog is diplayed in screen
        // if this text is in view it means the dialog is in view
        onView(withText(R.string.dialog_checkin_text_title)).check(matches(isDisplayed()))

        // now need to check if the button ok from dialog dissmis the dialog without have any input
//        onView(withText(R.string.buttons_default_ok)).perform(click())
        // now check if the dialog still in view
//        onView(withText(R.string.dialog_checkin_text_title)).check(matches(isDisplayed()))

        // enter with some input
        onView(withId(R.id.input_name)).perform(typeText(EXPECTED_NAME))
        onView(withId(R.id.input_email)).perform(typeText(EXPECTED_EMAIL))

        // click again
        onView(withText(R.string.buttons_default_ok)).perform(click())

        // make sure dialog is dosent exist - can check if 'not(isDisplayed())' because is no longer exist in view hierarchy
        onView(withText(R.string.dialog_checkin_text_title)).check(doesNotExist())
    }*/

//    @Before
//    fun setUp() {
//        ActivityScenario.launch(MainActivity::class.java)
//    }

//    @Test
//    fun checkIfTextIsOnScreen() {
//        onView(withText(R.string.splashscreen_text_title_first)).check(matches(isDisplayed()))
//
//    }
}