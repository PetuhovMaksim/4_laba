package com.example.android_lab4_city.data.model

/**
 * Класс, представляющий место (достопримечательность) в городе.
 * @param id Уникальный идентификатор места
 * @param category Категория места
 * @param name Название места
 * @param description Описание места
 * @param imageResId ID ресурса изображения места
 */
data class Place(
    val id: Int,
    val category: String,
    val name: String,
    val description: String,
    val imageResId: Int
)
