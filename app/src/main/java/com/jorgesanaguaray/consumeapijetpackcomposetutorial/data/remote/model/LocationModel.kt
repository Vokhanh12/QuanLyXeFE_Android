package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model

import java.math.BigDecimal

data class LocationModel(
    val id: String,
    val name: String,
    val latitude: BigDecimal,
    val longitude: BigDecimal
)
