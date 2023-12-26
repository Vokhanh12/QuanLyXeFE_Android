package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.LocationModel
import retrofit2.Response
import retrofit2.http.GET

interface LocationApi {
    @GET("locations")
    suspend fun getLocations(): Response<List<LocationModel>>


}