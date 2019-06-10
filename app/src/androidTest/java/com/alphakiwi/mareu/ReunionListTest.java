package com.alphakiwi.mareu;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import com.alphakiwi.mareu.reunion_list.DetailReunionActivity;
import com.alphakiwi.mareu.reunion_list.MaReuActivity;
import com.alphakiwi.mareu.utils.DeleteViewAction;
import com.alphakiwi.mareu.utils.LaunchActivityAction;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.alphakiwi.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for list of reunions
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class ReunionListTest {

    // This is fixed
    private static int ITEMS_COUNT = 4;

    private MaReuActivity mActivity;

    @Rule
    public ActivityTestRule<MaReuActivity> mActivityRule =
            new ActivityTestRule(MaReuActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void A_myReunionsList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_reunions))
                .check(matches(hasMinimumChildCount(1)));
    }


    @Test
    public void B_myReunionsList_LauchDetailActivity() {

        Intents.init();

        onView(withId(R.id.list_reunions))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new LaunchActivityAction()));

        intended(hasComponent(DetailReunionActivity.class.getName()));

    }

    @Test
    public void C_myReunionList_DetailActivity_withGoodName() {

        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());


        onView(withId(R.id.list_reunions))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new LaunchActivityAction()));

        ViewInteraction textConfirmPeach = onView(withId(R.id.text));
        textConfirmPeach.check(matches(withText("Peach")));

        mDevice.pressBack();


        onView(withId(R.id.list_reunions))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new LaunchActivityAction()));

        ViewInteraction textConfirmMario = onView(withId(R.id.text));
        textConfirmMario.check(matches(withText("Mario")));



    }




    @Test
    public void D_myReunionsList_triSalleItem() {

        onView(withContentDescription("Trier"))
                .perform(click());
        onView(withText("Trier")).perform(click());

        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());


        onView(withId(R.id.list_reunions))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new LaunchActivityAction()));

        ViewInteraction textConfirmPeach = onView(withId(R.id.text));
        textConfirmPeach.check(matches(withText("Peach")));

        mDevice.pressBack();


        onView(withId(R.id.list_reunions))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new LaunchActivityAction()));

        ViewInteraction textConfirmMario = onView(withId(R.id.text));
        textConfirmMario.check(matches(withText("Mario")));

        mDevice.pressBack();

        onView(withId(R.id.list_reunions))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, new LaunchActivityAction()));

        ViewInteraction textConfirmLuigi = onView(withId(R.id.text));
        textConfirmPeach.check(matches(withText("Luigi")));

        mDevice.pressBack();


        onView(withId(R.id.list_reunions))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, new LaunchActivityAction()));

        ViewInteraction textConfirmDaisy = onView(withId(R.id.text));
        textConfirmDaisy.check(matches(withText("Daisy")));


    }

    @Test
    public void E_myReunionsList_triDateItem() {

        onView(withContentDescription("Trier"))
                .perform(click());

        onView(withId(R.id.triLieu))
                .perform(click());


        onView(withText("Trier")).perform(click());

        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());


        onView(withId(R.id.list_reunions))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new LaunchActivityAction()));

        ViewInteraction textConfirmPeach = onView(withId(R.id.text));
        textConfirmPeach.check(matches(withText("Peach")));

        mDevice.pressBack();


        onView(withId(R.id.list_reunions))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new LaunchActivityAction()));


        ViewInteraction textConfirmDaisy = onView(withId(R.id.text));
        textConfirmDaisy.check(matches(withText("Daisy")));


        mDevice.pressBack();

        onView(withId(R.id.list_reunions))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, new LaunchActivityAction()));

        ViewInteraction textConfirmMario = onView(withId(R.id.text));
        textConfirmMario.check(matches(withText("Mario")));

        mDevice.pressBack();


        onView(withId(R.id.list_reunions))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, new LaunchActivityAction()));

        ViewInteraction textConfirmLuigi = onView(withId(R.id.text));
        textConfirmLuigi.check(matches(withText("Luigi")));



    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void F_myReunionsList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_reunions)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_reunions))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 3
        ITEMS_COUNT = ITEMS_COUNT - 1;
        onView(withId(R.id.list_reunions)).check(withItemCount(ITEMS_COUNT));
    }


    @Test
    public void G_myReunionsList_addItem() {

        onView(withId(R.id.list_reunions)).check(withItemCount(ITEMS_COUNT));

        onView(withId(R.id.fab)).perform(click());

        onView(withText("Valider")).perform(click());

        ITEMS_COUNT = ITEMS_COUNT + 1;

        onView(withId(R.id.list_reunions)).check(withItemCount(ITEMS_COUNT));
    }






}