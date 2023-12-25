package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.response.RouteNextIdResponse
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.util.Constants.Companion.VEHICLES_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface RouteApi {
    @GET(VEHICLES_ENDPOINT)
    suspend fun getNextId(): Response<RouteNextIdResponse>

}
