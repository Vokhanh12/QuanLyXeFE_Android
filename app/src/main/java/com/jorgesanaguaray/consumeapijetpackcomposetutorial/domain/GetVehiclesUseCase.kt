package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VehicleItem
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.VehicleRepository
import javax.inject.Inject

class GetVehiclesUseCase @Inject constructor(private val vehicleRepository: VehicleRepository) {

    suspend operator fun invoke(): List<VehicleItem>{
        return vehicleRepository.getVehicles().shuffled()
    }

}