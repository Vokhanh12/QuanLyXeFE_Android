package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.DeleteVehiclesUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class mRoutesViewModel @Inject constructor(private val deleteVehiclesUseCase: DeleteVehiclesUseCase) : ViewModel() {
    // Example function to delete a vehicle by ID
    suspend fun deleteVehicleById(vehicleId: String): Boolean {
        return deleteVehiclesUseCase.deleteVehicleById(vehicleId)
    }

}