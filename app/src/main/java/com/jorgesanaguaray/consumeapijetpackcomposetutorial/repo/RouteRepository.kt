package com.jorgesanaguaray.consumeapijetpackcomposetutorial.repo

import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.RouteService
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.VerhicleService
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.response.RouteNextIdResponse
import javax.inject.Inject

class RouteRepository @Inject constructor(private val routeService: RouteService) {
   suspend fun getNextId(): String{
       return routeService.getNextId()
   }

}