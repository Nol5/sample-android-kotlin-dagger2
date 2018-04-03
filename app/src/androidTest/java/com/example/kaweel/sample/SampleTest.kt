package com.example.kaweel.sample

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.google.common.io.Resources
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SampleTest {

    @Rule
    @JvmField
    var mIntentsTestRule = IntentsTestRule<MainActivity>(MainActivity::class.java, true, false)

    private lateinit var mWebServer: MockWebServer
    private lateinit var sampleApplication: SampleApplication

    @Before
    fun setUp() {
        mWebServer = MockWebServer()
        mWebServer.start()
        sampleApplication = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as SampleApplication
        sampleApplication.initialComponent(mWebServer.url("/").toString())
    }

    @Test
    fun first_ui_test(){
        mIntentsTestRule.launchActivity(null)
        mWebServer.enqueue(MockResponse().setResponseCode(200).setBody(Resources.toString(Resources.getResource("github.user.nol5.json"), Charsets.UTF_8)))
        Espresso.onView(ViewMatchers.withId(R.id.usernameTextView)).check(matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.userNameEditText)).perform(ViewActions.typeText("Nol5"))
        Espresso.onView(ViewMatchers.withId(R.id.loadButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.nameValTextView)).check(matches(ViewMatchers.withText("UI Test Kawee Lertrungmongkol")))
        Espresso.onView(ViewMatchers.withId(R.id.urlValTextView)).check(matches(ViewMatchers.withText("UI Test https://github.com/Nol5")))
        Espresso.onView(ViewMatchers.withId(R.id.blogValTextView)).check(matches(ViewMatchers.withText("UI Test https://medium.com/@Kaweel")))
    }

    @After
    fun tearDown() {
        mWebServer.shutdown()
    }

}