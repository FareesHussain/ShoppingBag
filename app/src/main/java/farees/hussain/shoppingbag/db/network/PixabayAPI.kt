package farees.hussain.shoppingbag.db.network

import androidx.room.PrimaryKey
import farees.hussain.shoppingbag.BuildConfig
import farees.hussain.shoppingbag.db.network.response.ImageResponse
import farees.hussain.shoppingbag.db.network.response.ImageResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery : String,
        @Query("key") apiKey: String = BuildConfig.PIXABAY_API_KEY
    ): Response<ImageResponse>
}