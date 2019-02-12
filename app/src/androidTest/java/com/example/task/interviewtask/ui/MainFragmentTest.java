package com.example.task.interviewtask.ui;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.example.task.interviewtask.MainActivity;
import com.example.task.interviewtask.R;
import com.example.task.interviewtask.data.ShopDatabase;
import com.example.task.interviewtask.ui.main.MainViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.mock;

public class MainFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private ShopDatabase shopDatabase;

    private MainViewModel mainViewModel;

    @Before
    public void setUp() {
        mainViewModel = mock(MainViewModel.class);

        Context context = InstrumentationRegistry.getTargetContext();
        shopDatabase = Room.inMemoryDatabaseBuilder(context, ShopDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void cleanUp() {
        shopDatabase.close();
    }

    @Test
    public void fragmentDisplayed() {
        onView(withId(R.id.main)).check(matches(isDisplayed()));
    }

    @Test
    public void productsDisplayed() {
        onView(withText("Coffee (240g)")).check(matches(isDisplayed()));
        onView(withText("Tea (box of 60)")).check(matches(isDisplayed()));
        onView(withText("Sugar (1kg)")).check(matches(isDisplayed()));
        onView(withText("Milk (bottle)")).check(matches(isDisplayed()));
        onView(withText("Cup")).check(matches(isDisplayed()));
    }

    @Test
    public void goToBasket() {
        onView(withId(R.id.action_go_to_basket)).perform(click());
        onView(withId(R.id.basketRecyclerView)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerView)).check(doesNotExist());
    }
}
