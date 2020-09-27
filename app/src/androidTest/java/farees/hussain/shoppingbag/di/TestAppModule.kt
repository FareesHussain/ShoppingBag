package farees.hussain.shoppingbag.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import farees.hussain.shoppingbag.db.local.ShoppingItemDatabase
import javax.inject.Named

@Module
@InstallIn(ApplicationComponent::class)
object TestAppModule {
    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context :Context) =
        Room.inMemoryDatabaseBuilder(context,ShoppingItemDatabase::class.java)
        .allowMainThreadQueries()
        .build()
}