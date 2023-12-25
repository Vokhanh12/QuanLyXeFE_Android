package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VehicleModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.util.Constants.Companion.VEHICLES_ENDPOINT
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface VehicleApi {

    // Lấy danh sách xe
    @GET(VEHICLES_ENDPOINT)
    suspend fun getVehicles(): Response<List<VehicleModel>>

    // Xóa xe bởi id
    @DELETE("$VEHICLES_ENDPOINT/{id}")
    suspend fun deleteVehicleById(@Path("id") vehicleId: String): Response<Unit>

    // Thêm xe
    @POST("$VEHICLES_ENDPOINT")
    suspend fun addVehicle(@Body vehicleModel: VehicleModel): Response<Unit>

    @Multipart
    @POST("$VEHICLES_ENDPOINT")
    suspend fun addImage(@Part image: MultipartBody.Part): Response<Unit>

}