package farees.hussain.shoppingbag.ui.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentFactory
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import farees.hussain.shoppingbag.R
import farees.hussain.shoppingbag.db.local.ShoppingItem
import farees.hussain.shoppingbag.getOrAwaitValue
import farees.hussain.shoppingbag.launchFragmentsInHiltContainer
import farees.hussain.shoppingbag.repositories.FakeShoppingRepositoryAndroidTest
import farees.hussain.shoppingbag.ui.ShoppingViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject

@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class AddShoppingItemFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: ShoppingFragmentFactory

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun pressBackButton_toPopBackStack(){
        val navController = mock(NavController::class.java)
        launchFragmentsInHiltContainer<AddShoppingItemFragment>(
            fragmentFactory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(),navController)
        }
        pressBack()
        verify(navController).popBackStack()
    }

    @Test
    fun pressImage_navigateToImagePickFragment(){
        val navController = mock(NavController::class.java)
        launchFragmentsInHiltContainer<AddShoppingItemFragment>(
            fragmentFactory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(),navController)
        }
        onView(withId(R.id.ivShoppingImage)).perform(click())
        verify(navController).navigate(AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment())
    }

    @Test
    fun pressBackButton_checkResetCurrUrl(){
        val navController = mock(NavController::class.java)
        val testViewModel = ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
        launchFragmentsInHiltContainer<AddShoppingItemFragment>(
            fragmentFactory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(),navController)
            viewModel = testViewModel
        }
        pressBack()
        assertThat(testViewModel.curImgUrl.value?:"").isEqualTo("" as String?)
    }

    @Test
    fun clickInsertTodb_ShoppingItemInsertedIntoDB(){
        val testViewModel = ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
        launchFragmentsInHiltContainer<AddShoppingItemFragment>(
            fragmentFactory = fragmentFactory
        ) {
            viewModel = testViewModel
        }
        onView(withId(R.id.etShoppingItemName)).perform(replaceText("Shopping Item"))
        onView(withId(R.id.etShoppingItemAmount)).perform(replaceText("5"))
        onView(withId(R.id.etShoppingItemPrice)).perform(replaceText("3.4"))
        onView(withId(R.id.btnAddShoppingItem)).perform(click())
        assertThat(testViewModel.shoppingItems.getOrAwaitValue())
            .contains(ShoppingItem("Shopping Item",5,3.4f,""))
    }


}