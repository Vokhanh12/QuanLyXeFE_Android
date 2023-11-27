package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.GetVehiclesUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class mRoutesViewModel @Inject constructor(private val getVehiclesUseCase: GetVehiclesUseCase) : ViewModel() {

    val vehiclesLiveData = liveData(Dispatchers.IO) {
        // Invoke the use case to get the vehicles
        val vehicles = getVehiclesUseCase.invoke()

        // Emit the result to the LiveData
        emit(vehicles)
    }

    // Example function to delete a vehicle by ID
    suspend fun deleteVehicleById(vehicleId: String): Boolean {
        return getVehiclesUseCase.deleteVehicleById(vehicleId)
    }

}