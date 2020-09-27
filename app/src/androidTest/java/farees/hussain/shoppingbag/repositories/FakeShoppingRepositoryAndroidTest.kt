package farees.hussain.shoppingbag.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import farees.hussain.shoppingbag.db.local.ShoppingItem
import farees.hussain.shoppingbag.db.network.response.ImageResponse
import farees.hussain.shoppingbag.other.Resource

class FakeShoppingRepositoryAndroidTest:ShoppingRepository {

    private val shoppingItems = mutableListOf<ShoppingItem>()

    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val obserableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean){
        shouldReturnNetworkError = value
    }

    fun refreshLiveData(){
        observableShoppingItems.postValue(shoppingItems)
        obserableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float {
        return shoppingItems.sumByDouble { it.price.toDouble() }.toFloat()
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>
        = observableShoppingItems

    override fun observeTotalPrice(): LiveData<Float>
        = obserableTotalPrice

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if(shouldReturnNetworkError){
            Resource.error("Error", null)
        }else{
            Resource.success(ImageResponse(listOf(),0,0))
        }
    }
}