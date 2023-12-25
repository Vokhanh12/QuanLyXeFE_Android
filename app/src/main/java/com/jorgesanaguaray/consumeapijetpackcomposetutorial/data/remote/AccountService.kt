package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import android.util.Log
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.request.LoginRequest
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.AccountItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountService @Inject constructor(private val accountApi: AccountApi) {

    suspend fun getTypeByUsernameAndPassword(username: String, password: String): AccountItem? {

        return withContext(Dispatchers.IO){
            try {
                Log.d("API_REQUEST", "Sending request for username: $username, password: $password")
                val response = accountApi.getTypeByUsernameAndPassword(
                    LoginRequest(username, password)
                )

                if (response != null) {
                    Log.d("API_RESPONSE", "Raw Response Body: ${response.raw().toString()}")

                    if (response.isSuccessful) {
                        // Handle successful response
                        val userType = response.body()
                        Log.d("API_RESPONSE", "Response Body: $userType")
                        // Convert userType to AccountModel if needed
                        // For now, assuming AccountModel has a property 'type'
                        return@withContext AccountItem(userType?.type)
                    } else {
                        // Handle unsuccessful response
                        Log.e("API_RESPONSE", "Unsuccessful response. Code: ${response.code()}")
                        return@withContext null
                    }
                } else {
                    Log.e("API_RESPONSE", "Response is null")
                    return@withContext null
                }
            } catch (e: Exception) {
                // Handle exception
                Log.e("AccountService", "Exception: $e")
                return@withContext null
            }

    }

}
}

