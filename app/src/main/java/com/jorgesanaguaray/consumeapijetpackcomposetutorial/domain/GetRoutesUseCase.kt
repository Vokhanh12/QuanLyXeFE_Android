package com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo.RouteRepository
import javax.inject.Inject

class GetRoutesUseCase @Inject constructor(private val routeRepository: RouteRepository) {
    suspend fun getNextId(): String{
        return routeRepository.getNextId()
    }

}