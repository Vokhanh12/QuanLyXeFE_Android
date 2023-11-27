package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import android.util.Log
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VehicleModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class VerhicleService @Inject constructor(private val vehicleApi: VehicleApi) {

    suspend fun getVehicles(): List<VehicleModel>{
        return withContext(Dispatchers.IO){
            try {
                val vehicles = vehicleApi.getVehicles()
                val responseBody = vehicles.body()
                Log.d("API_RESPONSE", "Response Body: $responseBody")
                responseBody ?: emptyList()
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error fetching vehicles", e)
                emptyList()
            }
        }
    }

    suspend fun deleteVehicleById(vehicleId: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<Unit> = vehicleApi.deleteVehicleById(vehicleId)
                return@withContext response.isSuccessful
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error deleting vehicle", e)
                return@withContext false
            }
        }
    }

}