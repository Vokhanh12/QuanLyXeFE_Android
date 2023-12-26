package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import android.util.Log
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.LocationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationService @Inject constructor(private val locationApi: LocationApi) {
    suspend fun getLocations(): List<LocationModel> {
        return withContext(Dispatchers.IO) {
            try {
                val locations = locationApi.getLocations()
                val responseBody = locations.body()
                Log.d("API_RESPONSE", "Response Body: $responseBody")
                responseBody ?: emptyList()
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error fetching vehicles", e)
                emptyList()
            }
        }
    }
}