package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RouteService @Inject constructor(private val routeApi: RouteApi) {
    suspend fun getNextId(): String{
        return withContext(Dispatchers.IO) {
            try {
                // Make a network request to the API endpoint for nextId
                val response = routeApi.getNextId()

                // Check if the request was successful (HTTP 200-299)
                if (response.isSuccessful) {
                    // Assuming your API returns the next ID as a string
                    return@withContext response.body()?.nextId  ?: "Error: Next ID not found"
                } else {
                    return@withContext "Error: ${response.code()} ${response.message()}"
                }
            } catch (e: Exception) {
                return@withContext "Error: ${e.message}"
            }
        }
    }

}

