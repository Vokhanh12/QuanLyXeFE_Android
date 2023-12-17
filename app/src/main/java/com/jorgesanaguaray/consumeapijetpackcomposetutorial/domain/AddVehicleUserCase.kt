package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain

import android.graphics.Bitmap
import android.util.Log
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VehicleModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.VehicleRepository
import javax.inject.Inject
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.result.Result

class AddVehicleUserCase @Inject constructor(private val vehicleRepository: VehicleRepository){
    suspend fun addVehicle(vehicleModel: VehicleModel): Result<Unit>{
        return try {
            vehicleRepository.addVehicle(vehicleModel)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun addImageVehicle(imageBitMap: Bitmap, fileName: String): Result<Unit> {
        return try {
            vehicleRepository.addImageVehicle(imageBitMap, fileName)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}