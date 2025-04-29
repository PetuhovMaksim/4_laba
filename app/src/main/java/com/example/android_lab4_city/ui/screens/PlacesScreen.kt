package com.example.android_lab4_city.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_lab4_city.data.model.Place
import com.example.android_lab4_city.ui.components.PlaceCard

/**
 * Экран со списком мест в выбранной категории.
 * @param categoryName Название текущей категории
 * @param places Список мест для отображения
 * @param onPlaceClick Обработчик выбора места
 * @param onBack Обработчик возврата назад
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesScreen(
    categoryName: String,
    places: List<Place>,
    onPlaceClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = categoryName) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(places) { place ->
                PlaceCard(
                    place = place,
                    onClick = onPlaceClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}