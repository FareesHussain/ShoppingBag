package farees.hussain.shoppingbag.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import farees.hussain.shoppingbag.R
import farees.hussain.shoppingbag.ui.fragments.ShoppingFragmentFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * Using libraries like - Jetpack navigation,Room, Coroutines, Retrofit, Glide, Dagger-Hilt, Timber
     *
     * Tests - junit, mockito
     */

    @Inject
    lateinit var fragmentFactory: ShoppingFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)
    }
}