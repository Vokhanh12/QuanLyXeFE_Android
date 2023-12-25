package com.jorgesanaguaray.consumeapijetpackcomposetutorial.di

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.AccountApi
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.RouteApi
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.RouteService
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.VehicleApi
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.GetVehiclesUseCase
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.GetAccountsUseCase
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.GetRoutesUseCase
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.AccountRepository
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.RouteRepository
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.VehicleRepository
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

    @Provides
    @Singleton
    fun provideRouteApi(retrofit: Retrofit): RouteApi {
        return retrofit.create(RouteApi::class.java)
    }


    @Singleton
    @Provides
    fun provideHomeViewModelFactory(getVehiclesUseCase: GetVehiclesUseCase): HomeViewModelFactory {
        return HomeViewModelFactory(getVehiclesUseCase)
    }


    @Singleton
    @Provides
    fun provideLoginAccountsUseCase(accountRepository: AccountRepository): GetAccountsUseCase {
        return GetAccountsUseCase(accountRepository)
    }

    @Singleton
    @Provides
    fun provideGetVehiclesUseCase(vehicleRepository: VehicleRepository): GetVehiclesUseCase {
        return GetVehiclesUseCase(vehicleRepository)
    }

    @Provides
    @Singleton
    fun provideGetRoutesUseCase(routeRepository: RouteRepository): GetRoutesUseCase {
        return GetRoutesUseCase(routeRepository)
    }



}