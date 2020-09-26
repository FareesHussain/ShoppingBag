package farees.hussain.shoppingbag.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import farees.hussain.shoppingbag.db.local.ShoppingDao
import farees.hussain.shoppingbag.db.local.ShoppingItemDatabase
import farees.hussain.shoppingbag.db.network.PixabayAPI
import farees.hussain.shoppingbag.other.Constants.BASE_URL_PIXABAY
import farees.hussain.shoppingbag.other.Constants.DATABASE_NAME
import farees.hussain.shoppingbag.repositories.DefaultShoppingRepository
import farees.hussain.shoppingbag.repositories.ShoppingRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
        api: PixabayAPI
    ) = DefaultShoppingRepository(dao,api) as ShoppingRepository


    @Singleton
    @Provides
    fun provideShoppingItemDatabasse(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,ShoppingItemDatabase::class.java,DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayAPI(): PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL_PIXABAY)
            .build()
            .create(PixabayAPI::class.java)
    }

}