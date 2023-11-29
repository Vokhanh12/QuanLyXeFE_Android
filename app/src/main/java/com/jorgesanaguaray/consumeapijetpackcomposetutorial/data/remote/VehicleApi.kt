package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VehicleModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.util.Constants.Companion.VEHICLES_ENDPOINT
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VehicleApi {

    @GET(VEHICLES_ENDPOINT)
    suspend fun getVehicles(): Response<List<VehicleModel>>

    @DELETE("$VEHICLES_ENDPOINT/{id}")
    suspend fun deleteVehicleById(@Path("id") vehicleId: String): Response<Unit>



}