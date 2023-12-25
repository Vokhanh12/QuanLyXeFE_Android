package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.RouteModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VehicleModel
import java.math.BigDecimal

data class RouteItem (
    val id: String,
    val name: String?,
    val length: BigDecimal?,
    val note: String?
)

data class RouteNextIdItem(
    val next_id: String,
    val name: String?,
    val length: BigDecimal?,
    val note: String?
)

fun RouteModel.toRouteItem() = RouteItem(id, name, length, note)
fun RouteModel.toRouteNextIdItem() = RouteNextIdItem(id,null,null,null);