package com.example.metroid.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.metroid.R
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
            .setDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_email).error(R.drawable.ic_email))
    //

//    @Singleton
//    @Provides
//    fun providePixabayApi(): PixabayAPI =
//        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
//            .build().create(PixabayAPI::class.java)

//    @Singleton
//    @Provides
//    fun provideDefaultShoppingRepository(dao: ShoppingDao, api: PixabayAPI) =
//        DefaultShoppingRepository(dao, api) as ShoppingRepository
}