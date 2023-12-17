package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.manager

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VehicleModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.AddVehicleUserCase
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.DeleteVehiclesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.result.Result

@HiltViewModel
class MVehicleViewModel @Inject constructor(
    private val deleteVehiclesUseCase: DeleteVehiclesUseCase,
    private val addVehicleUserCase: AddVehicleUserCase) : ViewModel() {

    // Example function to delete a vehicle by ID
    suspend fun deleteVehicleById(vehicleId: String): Boolean {
        return deleteVehiclesUseCase.deleteVehicleById(vehicleId)
    }
    suspend fun addVehicle(vehicleModel: VehicleModel): Result<Unit>{
        return addVehicleUserCase.addVehicle(vehicleModel)
    }
    suspend fun addImageVehicle(imageBitMap: Bitmap, fileName: String): Result<Unit>{
        return addVehicleUserCase.addImageVehicle(imageBitMap, fileName)
    }



}