package farees.hussain.shoppingbag.ui.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import farees.hussain.shoppingbag.R
import farees.hussain.shoppingbag.adapter.ShoppingItemAdapter
import farees.hussain.shoppingbag.db.local.ShoppingItem
import farees.hussain.shoppingbag.getOrAwaitValue
import farees.hussain.shoppingbag.launchFragmentsInHiltContainer
import farees.hussain.shoppingbag.ui.ShoppingViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ShoppingFragmentTest{
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Inject
    lateinit var testFragmentFactory: TestShoppingFragmentFactory

    @Test
    fun clickAddShoppingItemButton_navigateToAddShoppingItemFragment(){
        val navController = mock(NavController::class.java)
        launchFragmentsInHiltContainer<ShoppingFragment>(
            fragmentFactory = testFragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.fabAddShoppingItem)).perform(click())
        verify(navController).navigate(ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment())
    }

    @Test
    fun swipeShoppingItem_deleteTheItemInDB(){
        val shoppingItem = ShoppingItem("TEST",1,1f,"test",1)
        var testViewModel : ShoppingViewModel ?= null
        launchFragmentsInHiltContainer<ShoppingFragment>(
            fragmentFactory = testFragmentFactory
        ) {
            testViewModel = viewModel
            viewModel?.insertShoppingItem(shoppingItem)
        }

        onView(withId(R.id.rvShoppingItems)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ShoppingItemAdapter.ShoppingItemViewHolder>(
                0,
                swipeLeft()
            )
        )
        assertThat(testViewModel?.shoppingItems?.getOrAwaitValue()).isEmpty()
    }
}