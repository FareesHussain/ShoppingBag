package farees.hussain.shoppingbag.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import farees.hussain.shoppingbag.MainCoroutineRule
import farees.hussain.shoppingbag.getOrAwaitValueTest
import farees.hussain.shoppingbag.other.Constants
import farees.hussain.shoppingbag.other.Status
import farees.hussain.shoppingbag.repositories.FakeShoppingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ShoppingViewModelTest{
    private lateinit var viewModel: ShoppingViewModel

    // to run everything in the same thread one after other
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup(){
        viewModel = ShoppingViewModel(FakeShoppingRepository())
    }

    @Test
    fun `insert shopping item with empty field returns error`(){
        viewModel.insertShoppingItem("name","","3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert shopping item with too long names`(){
        val string = buildString {
            for(i in 1..Constants.MAX_NAME_LENGTH + 1)
                append(1)
        }
        viewModel.insertShoppingItem(string,"4","3.0")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert shopping item with too high price returns error`(){
        val string = buildString {
            for(i in 1..Constants.MAX_PRICE_LENGTH + 1)
                append(1)
        }
        viewModel.insertShoppingItem("string","4",string)
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert shopping item with too large amoung returns error`(){
        viewModel.insertShoppingItem("string","9999999999999999999999","3.9")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item with valid input return success`(){
        viewModel.insertShoppingItem("product name","5","3.9")
        val value = viewModel.insertShoppingItemStatus.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }



}