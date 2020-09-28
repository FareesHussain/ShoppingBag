package farees.hussain.shoppingbag.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import farees.hussain.shoppingbag.adapter.ImageAdapter
import farees.hussain.shoppingbag.adapter.ShoppingItemAdapter
import farees.hussain.shoppingbag.repositories.FakeShoppingRepositoryAndroidTest
import farees.hussain.shoppingbag.ui.ShoppingViewModel
import javax.inject.Inject

class TestShoppingFragmentFactory @Inject constructor(
    private val imageAdapter: ImageAdapter,
    private val glide : RequestManager,
    private val shoppingItemAdapter : ShoppingItemAdapter
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ImagePickFragment::class.java.name -> ImagePickFragment(imageAdapter)
            AddShoppingItemFragment::class.java.name -> AddShoppingItemFragment(glide)
            ShoppingFragment::class.java.name -> ShoppingFragment(
                shoppingItemAdapter,
                ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
            )
            else -> super.instantiate(classLoader, className)
        }
    }
}