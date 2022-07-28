package com.example.metroidadmin.di

import com.example.metroidadmin.remote.MetroApi
import com.example.metroidadmin.repository.MetroidRepository
import com.example.metroidadmin.repository.MetroidRepositoryImpl
import com.example.metroidadmin.utils.Constants.BASE_URL

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMetroApi(): MetroApi =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build().create(MetroApi::class.java)

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository( api: MetroApi) =
        MetroidRepositoryImpl(api) as MetroidRepository

}