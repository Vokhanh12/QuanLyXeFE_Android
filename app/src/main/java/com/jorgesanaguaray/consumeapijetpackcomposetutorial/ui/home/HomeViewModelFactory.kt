package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.GetVehiclesUseCase
import javax.inject.Inject


class HomeViewModelFactory @Inject constructor(private val getVehiclesUseCase: GetVehiclesUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(getVehiclesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
