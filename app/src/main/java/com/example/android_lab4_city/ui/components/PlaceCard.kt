package com.example.android_lab4_city.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.android_lab4_city.data.model.Place

/**
 * Composable-функция для карточки места.
 * @param place Данные места для отображения
 * @param onClick Обработчик клика по карточке
 * @param modifier Модификатор для настройки внешнего вида
 */
@Composable
fun PlaceCard(
    place: Place,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(place.id) },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = place.imageResId),
                contentDescription = "Изображение ${place.name}",
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = place.name,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}