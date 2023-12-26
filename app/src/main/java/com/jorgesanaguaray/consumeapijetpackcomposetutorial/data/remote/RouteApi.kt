package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.response.RouteNextIdResponse
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.util.Constants.Companion.VEHICLES_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface RouteApi {
    @POST("RoutesVehicle/nextId")
    suspend fun getNextId(): Response<String>

}
