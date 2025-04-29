package com.example.android_lab4_city.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.android_lab4_city.data.CityDataProvider
import com.example.android_lab4_city.data.model.CityUiState

class CityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                categories = CityDataProvider.categories,
                places = CityDataProvider.places
            )
        }
    }

    fun getPlacesByCategory(category: String) =
        _uiState.value.places.filter { it.category == category }

    fun getPlaceById(placeId: Int) =
        _uiState.value.places.find { it.id == placeId }

    fun getCategoryByName(categoryName: String) =
        _uiState.value.categories.find { it.name == categoryName }
}