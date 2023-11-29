package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.manager

import androidx.lifecycle.ViewModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.DeleteVehiclesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class mVehicleViewModel @Inject constructor(private val deleteVehiclesUseCase: DeleteVehiclesUseCase) : ViewModel() {
    // Example function to delete a vehicle by ID
    suspend fun deleteVehicleById(vehicleId: String): Boolean {
        return deleteVehiclesUseCase.deleteVehicleById(vehicleId)
    }
}