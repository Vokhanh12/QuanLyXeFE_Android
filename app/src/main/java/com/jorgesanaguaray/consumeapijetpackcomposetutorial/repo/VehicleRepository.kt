package com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.VerhicleService
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VehicleItem
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.toVehicleItem
import javax.inject.Inject

class VehicleRepository @Inject constructor(private val vehicleService: VerhicleService) {
    suspend fun getVehicles(): List<VehicleItem>{
        return vehicleService.getVehicles().map{
            it.toVehicleItem()
        }
    }

    suspend fun deleteVehicleById(vehicleId: String): Boolean {
        return vehicleService.deleteVehicleById(vehicleId)
    }

}