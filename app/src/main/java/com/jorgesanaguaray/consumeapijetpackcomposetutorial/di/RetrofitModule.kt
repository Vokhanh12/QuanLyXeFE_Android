package com.jorgesanaguaray.consumeapijetpackcomposetutorial.di

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.AccountApi
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.VehicleApi
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.GetVehiclesUseCase
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.LoginAccountsUseCase
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.AccountRepository
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.HomeViewModelFactory
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

    @Singleton
    @Provides
    fun provideAccountApi(retrofit: Retrofit): AccountApi {
        return retrofit.create(AccountApi::class.java)
    }


    @Singleton
    @Provides
    fun provideHomeViewModelFactory(getVehiclesUseCase: GetVehiclesUseCase): HomeViewModelFactory {
        return HomeViewModelFactory(getVehiclesUseCase)
    }


    @Singleton
    @Provides
    fun provideLoginAccountsUseCase(accountRepository: AccountRepository): LoginAccountsUseCase {
        return LoginAccountsUseCase(accountRepository)
    }


}