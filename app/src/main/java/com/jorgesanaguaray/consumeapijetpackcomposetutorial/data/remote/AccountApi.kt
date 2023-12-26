package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.request.LoginRequest
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.AccountItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
interface AccountApi {
    // Đăng nhập
    // Đưa Username và password kiểm tra có trong csdl thì
    @POST("http://192.168.1.13:8080/api/login")
    suspend fun getTypeByUsernameAndPassword(
        @Body request: LoginRequest
    ): Response<AccountItem>

}



