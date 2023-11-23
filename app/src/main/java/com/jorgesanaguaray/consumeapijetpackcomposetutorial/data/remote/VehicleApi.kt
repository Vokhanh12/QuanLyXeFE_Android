package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VehicleModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.util.Constants.Companion.VEHICLES_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface VehicleApi {

    @GET(VEHICLES_ENDPOINT)
    suspend fun getVehicles(): Response<List<VehicleModel>>

}