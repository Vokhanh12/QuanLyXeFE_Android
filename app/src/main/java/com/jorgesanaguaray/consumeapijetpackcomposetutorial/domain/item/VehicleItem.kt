package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VehicleModel

data class VehicleItem(
    val id: String,
    val vehicleId: String,
    val vehicleName: String,
    val vehicleType: String,
    val startYearOfUse: String,
    val urlImage: String
)

fun VehicleModel.toVehicleItem() = VehicleItem(id , vehicleId, vehicleName, vehicleType, startYearOfUse, urlImage)