package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VehicleItem
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.VehicleRepository
import javax.inject.Inject

class GetVehiclesUseCase @Inject constructor(private val vehicleRepository: VehicleRepository) {

    suspend operator fun invoke(): List<VehicleItem>{
        return vehicleRepository.getVehicles().shuffled()
    }

    suspend fun deleteVehicleById(vehicleId: String): Boolean {
        // Delegate the deletion logic to the repository
        return vehicleRepository.deleteVehicleById(vehicleId)
    }

}