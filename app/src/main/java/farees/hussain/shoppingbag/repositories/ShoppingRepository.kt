package farees.hussain.shoppingbag.repositories

import androidx.lifecycle.LiveData
import farees.hussain.shoppingbag.db.local.ShoppingItem
import farees.hussain.shoppingbag.db.network.response.ImageResponse
import farees.hussain.shoppingbag.other.Resource
import retrofit2.Response

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}