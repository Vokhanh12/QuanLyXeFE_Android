package com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.VerhicleService
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VehicleModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VehicleItem
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.toVehicleItem
import javax.inject.Inject
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.result.Result


class VehicleRepository @Inject constructor(private val vehicleService: VerhicleService) {
    suspend fun getVehicles(): List<VehicleItem>{
        return vehicleService.getVehicles().map{
            it.toVehicleItem()
        }
    }

    suspend fun deleteVehicleById(vehicleId: String): Boolean {
        return vehicleService.deleteVehicleById(vehicleId)
    }

    suspend fun addVehicle(vehicleModel: VehicleModel): Result<Unit>{
        return vehicleService.addVehicle(vehicleModel)
    }

    suspend fun addImageVehicle(imageBitmap: Bitmap, fileName: String): Result<Unit> {
        return vehicleService.addImageVehicle(imageBitmap, fileName)
    }

}