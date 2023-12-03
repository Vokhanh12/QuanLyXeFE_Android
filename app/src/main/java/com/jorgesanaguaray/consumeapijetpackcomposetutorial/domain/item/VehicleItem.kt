package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VehicleModel
data class VehicleItem(
    val id: String,
    val name: String,
    val type: String,
    val startYearOfUse: String,
    val status: String,
    val urlImage: String
)
fun VehicleModel.toVehicleItem() = VehicleItem(id, name, type, startYearOfUse, status, urlImage)