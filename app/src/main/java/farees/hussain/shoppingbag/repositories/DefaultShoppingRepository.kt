package farees.hussain.shoppingbag.repositories

import androidx.lifecycle.LiveData
import farees.hussain.shoppingbag.db.local.ShoppingDao
import farees.hussain.shoppingbag.db.local.ShoppingItem
import farees.hussain.shoppingbag.db.network.PixabayAPI
import farees.hussain.shoppingbag.db.network.response.ImageResponse
import farees.hussain.shoppingbag.other.Resource
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository{
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?:Resource.error("An unknown error occured", null)
            } else{
                Resource.error("An unknown error occured", null)
            }
        }catch (e:Exception){
            Resource.error("Couldn't reach the server. Check your Internet Connection",null)
        }
    }
}