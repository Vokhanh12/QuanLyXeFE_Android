package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RouteService @Inject constructor(private val routeApi: RouteApi) {
        suspend fun getNextId(): String {
            return withContext(Dispatchers.IO) {
                try {
                    val response = routeApi.getNextId()

                    if (response.isSuccessful) {
                        return@withContext response.body() ?: "Error: Next ID not found"
                    } else {
                        return@withContext "Error: ${response.code()} ${response.message()}"
                    }
                } catch (e: Exception) {
                    return@withContext "Error: ${e.message}"
                }
            }
        }
    }

