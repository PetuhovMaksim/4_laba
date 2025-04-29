package com.example.android_lab4_city.data.model

/**
 * Класс состояния UI для CityViewModel.
 * @param categories Список всех категорий
 * @param places Список всех мест
 */
data class CityUiState(
    val categories: List<Category> = emptyList(),
    val places: List<Place> = emptyList()
)
