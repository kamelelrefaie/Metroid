package com.example.metroid.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.metroid.R
import com.example.metroid.model.remote.MetroidApi
import com.example.metroid.repository.DataStoreManager
import com.example.metroid.repository.MetroidRepository
import com.example.metroid.repository.MetroidRepositoryImpl
import com.example.metroid.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Singleton
//    @Provides
//    fun provideShoppingItemDatabase(@ApplicationContext context: Context) =
//        Room.databaseBuilder(context, ShoppingItemsDataBase::class.java, DATABASE_NAME)
//            .build()
//
//    @Singleton
//    @Provides
//    fun provideShoppingDao(shoppingItemsDataBase: ShoppingItemsDataBase) =
//        shoppingItemsDataBase.shoppingDao()

    @Singleton
    @Provides
    fun provideGlideInstance(@ApplicationContext context: Context) =
        Glide.with(context)
            .setDefaultRequestOptions(RequestOptions().error(R.drawable.ic_delete_ticket))
    //

    @Singleton
    @Provides
    fun provideMetroidApi(): MetroidApi =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(MetroidApi::class.java)

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository( api: MetroidApi) =
        MetroidRepositoryImpl(api) as MetroidRepository

    @Singleton
    @Provides
    fun provideDataStoreRepo(@ApplicationContext context: Context) : DataStoreManager {
      return  DataStoreManager(context)
    }
}