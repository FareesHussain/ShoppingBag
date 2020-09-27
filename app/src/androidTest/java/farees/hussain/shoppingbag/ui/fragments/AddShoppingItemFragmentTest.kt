package farees.hussain.shoppingbag.ui.fragments

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import farees.hussain.shoppingbag.R
import farees.hussain.shoppingbag.launchFragmentsInHiltContainer
import farees.hussain.shoppingbag.repositories.DefaultShoppingRepository
import farees.hussain.shoppingbag.repositories.ShoppingRepository
import farees.hussain.shoppingbag.ui.ShoppingViewModel
import farees.hussain.shoppingbag.ui.fragments.AddShoppingItemFragmentTest_ComponentDataHolder.get
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class AddShoppingItemFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun pressBackButton_toPopBackStack(){
        val navController = mock(NavController::class.java)
        launchFragmentsInHiltContainer<AddShoppingItemFragment> {
            Navigation.setViewNavController(requireView(),navController)
        }
        pressBack()
        verify(navController).popBackStack()
    }

    @Test
    fun pressImage_navigateToImagePickFragment(){
        val navController = mock(NavController::class.java)
        launchFragmentsInHiltContainer<AddShoppingItemFragment> {
            Navigation.setViewNavController(requireView(),navController)
        }
        onView(withId(R.id.ivShoppingImage)).perform(click())
        verify(navController).navigate(AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment())
    }

    //todo -> to implement a test cases which verifies press back button to reset the current url

//    @Test
//    fun pressBackButton_resetCurrUrl(){
//        val navController = mock(NavController::class.java)
//        launchFragmentsInHiltContainer<AddShoppingItemFragment> (
//            fragmentFactory = fragmentFactory
//        ){
//            Navigation.setViewNavController(requireView(),navController)
//        }
//        pressBack()
//    }


}