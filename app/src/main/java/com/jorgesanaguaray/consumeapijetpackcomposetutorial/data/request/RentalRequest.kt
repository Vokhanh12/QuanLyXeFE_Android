package com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.request

import com.google.gson.annotations.SerializedName

data class RentalRequest(
    @SerializedName("username") val vehicleName: String,
    @SerializedName("username") val vehicleType: String,
    @SerializedName("username") val routeId: String,
    @SerializedName("username") val routeName: String,
    @SerializedName("username") val locationName: String,
    @SerializedName("username") val startTime: String,
    @SerializedName("username") val endTime: String,
)