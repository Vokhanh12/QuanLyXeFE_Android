package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.VehicleRepository
import javax.inject.Inject

class DeleteVehiclesUseCase @Inject constructor(private val vehicleRepository: VehicleRepository) {
    suspend fun deleteVehicleById(vehicleId: String): Boolean {
        // Delegate the deletion logic to the repository
        return vehicleRepository.deleteVehicleById(vehicleId)
    }

}