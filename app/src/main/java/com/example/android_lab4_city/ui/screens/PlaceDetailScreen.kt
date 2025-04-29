package com.example.android_lab4_city.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.android_lab4_city.data.model.Place
import androidx.compose.foundation.Image


/**
 * Экран с подробной информацией о месте.
 * @param place Данные места для отображения (может быть null)
 * @param onBack Обработчик возврата назад
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailScreen(
    place: Place?,
    onBack: () -> Unit
) {
    // Если место не найдено, показываем пустой экран
    if (place == null) return

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = place.name) },
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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = place.imageResId),
                contentDescription = "Фото ${place.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = place.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}