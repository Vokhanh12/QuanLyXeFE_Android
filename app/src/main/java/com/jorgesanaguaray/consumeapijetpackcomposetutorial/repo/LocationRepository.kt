package com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.LocationService
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.LocationModel
import retrofit2.Response
import javax.inject.Inject

class LocationRepository @Inject constructor(private val locationService: LocationService) {

    suspend fun getLocations(): List<LocationModel> {
        return locationService.getLocations()
    }



}