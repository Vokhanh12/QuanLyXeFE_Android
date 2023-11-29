package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import com.google.gson.annotations.SerializedName
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.AccountItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
interface AccountApi {

    @POST("http://192.168.0.113:8080/api/login")
    suspend fun getTypeByUsernameAndPassword(
        @Body request: LoginRequest
    ): Response<AccountItem>

}
data class LoginRequest(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)

