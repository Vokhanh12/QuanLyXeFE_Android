package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VehicleModel

data class VehicleItem(
    val id: String,
    val code: String,
    val name: String,
    val type: String,
    val startYearOfUse: String,
    val urlImage: String
)

fun VehicleModel.toVehicleItem() = VehicleItem(id , code, name, type, startYearOfUse, urlImage)