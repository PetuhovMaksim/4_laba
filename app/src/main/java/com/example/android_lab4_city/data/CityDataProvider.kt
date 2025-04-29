package com.example.android_lab4_city.data

import com.example.android_lab4_city.data.model.Category
import com.example.android_lab4_city.data.model.Place
import com.example.android_lab4_city.R

/**
 * Объект-поставщик данных о городе.
 */
object CityDataProvider {
    // Список категорий мест
    val categories = listOf(
        Category(name = "Рестораны", R.drawable.rest_kare),
        Category(name = "Парки", R.drawable.park_kirova),
        Category(name = "Музеи", R.drawable.muzey_kalashnikova),
        Category(name = "Кофейни", R.drawable.kofi_chasha),
        Category(name = "Торовые центры", R.drawable.tc_flagman)
    )
    // Список всех мест в городе
    val places = listOf(
        Place(
            id = 1,
            category = "Рестораны",
            name = "Ресторан Каре",
            description = "Описание",
            imageResId = R.drawable.rest_kare
        ),
        Place(
            id = 2,
            category = "Рестораны",
            name = "Ресторан Панорама",
            description = "Описание",
            imageResId = R.drawable.rest_panorama
        ),
        Place(
            id = 3,
            category = "Парки",
            name = "Парк Кирова",
            description = "Описание",
            imageResId = R.drawable.park_kirova
        ),
        Place(
            id = 4,
            category = "Парки",
            name = "Парк Горького",
            description = "Описание",
            imageResId = R.drawable.park_gorkogo
        ),
        Place(
            id = 5,
            category = "Музеи",
            name = "Музей Калашникова",
            description = "Описание",
            imageResId = R.drawable.muzey_kalashnikova
        ),
        Place(
            id = 6,
            category = "Музеи",
            name = "Музей Лудорвай",
            description = "Описание",
            imageResId = R.drawable.muzey_ludorvai
        ),
        Place(
            id = 7,
            category = "Кофейни",
            name = "Кофейня Чаша",
            description = "Описание",
            imageResId = R.drawable.kofi_chasha
        ),
        Place(
            id = 8,
            category = "Кофейни",
            name = "Кофейня Блек",
            description = "Описание",
            imageResId = R.drawable.kofi_black
        ),
        Place(
            id = 9,
            category = "Торовые центры",
            name = "ТЦ Флагман",
            description = "Описание",
            imageResId = R.drawable.tc_flagman
        ),
        Place(
            id = 10,
            category = "Торовые центры",
            name = "ТЦ Европа",
            description = "Описание",
            imageResId = R.drawable.tc_evropa
        )
    )
}
