package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model

import java.math.BigDecimal

data class RouteModel(
    val id: String,
    val name: String?,
    val length: BigDecimal?,
    val note: String?
)