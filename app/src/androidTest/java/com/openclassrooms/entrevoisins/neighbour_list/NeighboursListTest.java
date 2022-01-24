
package com.openclassrooms.entrevoisins.neighbour_list;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static com.openclassrooms.entrevoisins.utils.Waiter.waitFor;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule = new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }


    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));

    }


    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void launch_NeighbourDetailsActivity() {
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(allOf(withId(R.id.neighbour_detail_tv_name), isDisplayed())).check(matches(withText("Jack")));

    }


    @Test
    public void neighboursDetailActivity_textView_isCorrect() {
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.neighbour_detail_tv_name)).check(matches(not(withText(""))));

    }

    @Test
    public void only_favorite_in_FavoritesFragment() {

        //Swipe to FavoritesFragment
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform((swipeLeft()));

        onView(isRoot()).perform(waitFor(1000));
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(0));

        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform((swipeRight()));

        onView(isRoot()).perform(waitFor(1000));

        //Click on first Item
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //Click on FloatingActionButton
        onView(allOf(withId(R.id.neighbour_detail_fab_add_favorite)))
                .perform((click()));
        //Click on 'goBack' button
        Espresso.pressBack();

        //Swipe to FavoritesFragment
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform((swipeLeft()));

        onView(isRoot()).perform(waitFor(1000));

        //Check if an item is added in the Itemlist
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(1));
    }
}