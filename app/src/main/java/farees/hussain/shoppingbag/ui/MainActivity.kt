package farees.hussain.shoppingbag.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import farees.hussain.shoppingbag.R
import farees.hussain.shoppingbag.ui.fragments.ShoppingFragmentFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    /**
     * Using libraries like - Jetpack navigation,Room, Coroutines, Retrofit, Glide, Dagger-Hilt, Timber
     *
     * Tests - junit, mockito
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}