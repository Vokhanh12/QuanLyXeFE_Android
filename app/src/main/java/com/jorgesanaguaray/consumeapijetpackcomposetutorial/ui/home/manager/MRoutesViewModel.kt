package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.manager

import androidx.lifecycle.ViewModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.DeleteVehiclesUseCase
import javax.inject.Inject

class MRoutesViewModel @Inject constructor(private val deleteVehiclesUseCase: DeleteVehiclesUseCase) : ViewModel() {
    // Example function to delete a vehicle by ID
    suspend fun deleteVehicleById(vehicleId: String): Boolean {
        return deleteVehiclesUseCase.deleteVehicleById(vehicleId)
    }

}