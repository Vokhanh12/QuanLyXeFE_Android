package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.LocationModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.LocationRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(private val locationRepository: LocationRepository) {
    suspend fun getLocations(): List<LocationModel>{
        return locationRepository.getLocations()
    }

}