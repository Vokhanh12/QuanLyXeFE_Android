package com.jorgesanaguaray.consumeapijetpackcomposetutorial.di

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.VehicleApi
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Jorge Sanaguaray
 */

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


    @Singleton
    @Provides
    fun provideVehicleApi(retrofit: Retrofit): VehicleApi {
        return retrofit.create(VehicleApi::class.java)
    }

}