package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote

import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VehicleModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.result.Result
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.result.Success
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.result.Error
import okhttp3.MediaType
import java.io.ByteArrayOutputStream
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

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

    suspend fun addVehicle(vehicleModel: VehicleModel): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                // Gọi phương thức POST với dữ liệu hình ảnh
                val response: Response<Unit> = vehicleApi.addVehicle(vehicleModel)

                // Kiểm tra xem response có thành công không
                if (response.isSuccessful) {
                    return@withContext Success(Unit)
                } else {
                    // Xử lý trường hợp response không thành công
                    return@withContext Error(Exception("Failed to add vehicle"))
                }
            } catch (e: Exception) {
                // Xử lý lỗi khi gọi API
                return@withContext Error(e)
            }
        }
    }

    suspend fun addImageVehicle(imageBitmap: Bitmap, fileName: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                // Chuyển đổi Bitmap thành mảng byte với định dạng JPEG
                val imageByteArray = convertBitmapToByteArray(imageBitmap, Bitmap.CompressFormat.JPEG)

                // Tạo MultipartBody.Part từ mảng byte hình ảnh
                val imageRequestBody =
                    imageByteArray.toRequestBody("image/jpeg".toMediaTypeOrNull(), 0)
                val imagePart = MultipartBody.Part.createFormData("image", fileName, imageRequestBody)

                // Gọi phương thức POST với dữ liệu hình ảnh
                val response: Response<Unit> = vehicleApi.addImage(imagePart)

                return@withContext Success(Unit)

            } catch (e: Exception) {
                // Xử lý lỗi khi gọi API
                Log.e("MVehicleViewModel", "Exception during API call: $e")
                return@withContext Error(e)
            }
        }
    }
    private fun convertBitmapToByteArray(bitmap: Bitmap, compressFormat: Bitmap.CompressFormat): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(compressFormat, 100, stream)
        return stream.toByteArray()
    }


}

