package com.example.task.interviewtask.ui;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.support.test.rule.ActivityTestRule;

import com.example.task.interviewtask.R;
import com.example.task.interviewtask.TestActivity;
import com.example.task.interviewtask.data.entities.CurrencyEntity;
import com.example.task.interviewtask.ui.checkout.CheckoutFragment;
import com.example.task.interviewtask.ui.checkout.CheckoutViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.InjectMocks;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckoutFragmentTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Rule
    public ActivityTestRule<TestActivity> activityRule = new ActivityTestRule<>(TestActivity.class);

    private CheckoutViewModel checkoutViewModel;
    private LiveData<List<CurrencyEntity>> currencyEntities;

    @InjectMocks
    CheckoutFragment checkoutFragment = CheckoutFragment.newInstance();

    @Before
    public void setUp() {

        //todo I didn't solve the issue with database UI thread here.

        activityRule.getActivity().replaceFragment(checkoutFragment);

        checkoutViewModel = mock(CheckoutViewModel.class);

        when(checkoutViewModel.getCurrencyList()).thenReturn(currencyEntities);
        when(checkoutViewModel.getTotalPrice(anyList())).thenReturn(25.50);
    }

    @Test
    public void initialized() {
        onView(withId(R.id.refreshBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void onCurrencyChanged() {
        checkoutViewModel.currencyChanged(any(CurrencyEntity.class));

        onView(withId(R.id.txtTotalPrice)).check(matches(withText("3.12")));
    }
}
