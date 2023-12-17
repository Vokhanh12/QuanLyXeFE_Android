package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.GetVehiclesUseCase
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VehicleItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RentalViewModel @Inject constructor(private val getVehiclesUseCase: GetVehiclesUseCase) : ViewModel() {

    private val _vehicles = MutableStateFlow(emptyList<VehicleItem>())
    val vehicles: StateFlow<List<VehicleItem>> get() = _vehicles

    init {
        println("ViewModel Initialized")
        getVehicles()
    }

    private fun getVehicles() {

        viewModelScope.launch {

            try {

                val vehicles = getVehiclesUseCase()
                _vehicles.value = vehicles

            } catch (_: Exception) {}

        }

    }

}