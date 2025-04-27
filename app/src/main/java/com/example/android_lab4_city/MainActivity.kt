// Разрешаем использование экспериментальных API Material 3 в этом файле
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.android_lab4_city

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.compose.foundation.Image

/**
 * Класс, представляющий место (достопримечательность) в городе.
 * @param id Уникальный идентификатор места
 * @param category Категория места (ресторан, парк и т.д.)
 * @param name Название места
 * @param description Описание места
 * @param imageResId картинка изображение места
 */
data class Place(
    val id: Int,
    val category: String,
    val name: String,
    val description: String,
    val imageResId: Int
)

/**
 * Класс, представляющий категорию мест.
 * @param name Название категории
 * @param imageUrl Ссылка на изображение категории
 */
data class Category(
    val name: String,
    val imageResId: Int
)

/**
 * Объект-поставщик данных о городе.
 * Содержит тестовые данные о категориях и местах.
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

/**
 * ViewModel для управления состоянием приложения.
 * Содержит бизнес-логику и данные для UI.
 */
class CityViewModel : ViewModel() {
    // Внутреннее состояние UI (приватное)
    private val _uiState = MutableStateFlow(CityUiState())
    // Публичное состояние UI (только для чтения)
    val uiState: StateFlow<CityUiState> = _uiState.asStateFlow()

    // Инициализация начального состояния данными из CityDataProvider
    init {
        _uiState.update {
            it.copy(
                categories = CityDataProvider.categories,
                places = CityDataProvider.places
            )
        }
    }

    /**
     * Получить места по категории.
     * @param category Название категории
     * @return Список мест в указанной категории
     */
    fun getPlacesByCategory(category: String): List<Place> {
        return _uiState.value.places.filter { it.category == category }
    }

    /**
     * Найти место по ID.
     * @param placeId ID места
     * @return Найденное место или null
     */
    fun getPlaceById(placeId: Int): Place? {
        return _uiState.value.places.find { it.id == placeId }
    }

    /**
     * Найти категорию по названию.
     * @param categoryName Название категории
     * @return Найденная категория или null
     */
    fun getCategoryByName(categoryName: String): Category? {
        return _uiState.value.categories.find { it.name == categoryName }
    }

}

/**
 * Класс состояния UI для CityViewModel.
 * @param categories Список всех категорий
 * @param places Список всех мест
 */
data class CityUiState(
    val categories: List<Category> = emptyList(),
    val places: List<Place> = emptyList()
)

/**
 * Перечисление экранов приложения.
 * Используется для навигации между экранами.
 */
enum class CityScreen {
    Categories,
    Places,
    PlaceDetail
}

/**
 * Главная Activity приложения.
 * Устанавливает Compose-контент при создании.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            City()
        }
    }
}


/**
 * Главный Composable приложения.
 * Содержит навигационный граф и управляет переходами между экранами.
 */
@Composable
fun City() {
    // Создаем контроллер навигации
    val navController = rememberNavController()
    // Получаем ViewModel
    val viewModel: CityViewModel = viewModel()

    // Навигационный граф приложения
    NavHost(
        navController = navController,
        startDestination = CityScreen.Categories.name, // Стартовый экран
        // Анимации переходов между экранами:
        enterTransition = { slideInHorizontally(initialOffsetX = { 10000 }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -10000 }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -10000 }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 10000 }) }
    ) {
        // Экран категорий
        composable(route = CityScreen.Categories.name) {
            CategoriesScreen(
                categories = viewModel.uiState.collectAsState().value.categories,
                onCategoryClick = { categoryName ->
                    // Переход на экран мест с передачей названия категории
                    navController.navigate("${CityScreen.Places.name}/$categoryName")
                }
            )
        }
        // Экран мест в категории (с параметром categoryName)
        composable(
            route = "${CityScreen.Places.name}/{categoryName}",
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            // Извлекаем переданный параметр категории
            val categoryName = backStackEntry.arguments?.getString("categoryName")
            // Получаем места для этой категории
            val places = categoryName?.let { viewModel.getPlacesByCategory(it) } ?: emptyList()
            PlacesScreen(
                categoryName = categoryName ?: "Unknown",
                places = places,
                onPlaceClick = { placeId ->
                    // Переход на экран деталей места с передачей ID
                    navController.navigate("${CityScreen.PlaceDetail.name}/$placeId")
                },
                // Обработка кнопки "Назад"
                onBack = { navController.popBackStack() }
            )
        }
        // Экран деталей места (с параметром placeId)
        composable(
            route = "${CityScreen.PlaceDetail.name}/{placeId}",
            arguments = listOf(navArgument("placeId") { type = NavType.IntType })
        ) { backStackEntry ->
            // Извлекаем переданный ID места
            val placeId = backStackEntry.arguments?.getInt("placeId")
            // Находим место по ID
            val place = placeId?.let { viewModel.getPlaceById(it) }

            // Если место найдено, показываем экран деталей
            if (place != null) {
                PlaceDetailScreen(
                    place = place,
                    // Обработка кнопки "Назад"
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

// [Остальные Composable-функции (CategoriesScreen, PlacesScreen и т.д.)
// прокомментированы аналогичным образом с описанием их назначения и параметров]


/**
 * Composable-функция для экрана списка категорий.
 * @param categories Список категорий для отображения
 * @param onCategoryClick Обработчик клика по категории
 */
@Composable
fun CategoriesScreen(categories: List<Category>, onCategoryClick: (String) -> Unit) {
    // Используем Scaffold как основу для экрана с TopAppBar
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Места в Ижевске") } // Заголовок экрана
        )
    }
    ) { padding ->
        // LazyColumn для эффективного отображения списка
        LazyColumn(
            modifier = Modifier.padding(padding), // Учитываем отступы от Scaffold
            contentPadding = PaddingValues(16.dp), // Внутренние отступы
            verticalArrangement = Arrangement.spacedBy(8.dp) // Расстояние между элементами
        ) {
            // Отображаем каждый элемент списка категорий
            items(categories) { category ->
                CategoryCard(category = category, onClick = onCategoryClick)
            }
        }
    }
}

/**
 * Composable-функция для карточки категории.
 * @param category Данные категории
 * @param onClick Обработчик клика по карточке
 */
@Composable
fun CategoryCard(category: Category, onClick: (String) -> Unit) {
    // Карточка Material Design
    Card(
        modifier = Modifier
            .fillMaxWidth() // Занимает всю доступную ширину
            .clickable { onClick(category.name) }, // Обработка клика
        shape = MaterialTheme.shapes.medium // Скругленные углы
    ) {
        // Горизонтальное расположение элементов внутри карточки
        Row(
            modifier = Modifier.padding(16.dp), // Внутренние отступы
            verticalAlignment = Alignment.CenterVertically // Выравнивание по центру
        ) {
            //изображения
            Image(
                painter = painterResource(id = category.imageResId),
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp)) // Отступ между изображением и текстом
            // Название категории
            Text(text = category.name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

/**
 * Composable-функция для экрана списка мест в категории.
 * @param categoryName Название текущей категории
 * @param places Список мест для отображения
 * @param onPlaceClick Обработчик клика по месту
 * @param onBack Обработчик нажатия кнопки "Назад"
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
                title = { Text(text = categoryName) }, // Заголовок с названием категории
                navigationIcon = {
                    // Кнопка "Назад" с иконкой
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding), // Учитываем отступы от Scaffold
            contentPadding = PaddingValues(16.dp), // Внутренние отступы
            verticalArrangement = Arrangement.spacedBy(8.dp) // Расстояние между элементами
        ) {
            // Отображаем каждый элемент списка мест
            items(places) { place ->
                PlaceCard(place = place, onClick = onPlaceClick)
            }
        }
    }
}

/**
 * Composable-функция для карточки места.
 * @param place Данные места
 * @param onClick Обработчик клика по карточке
 */
@Composable
fun PlaceCard(place: Place, onClick: (Int) -> Unit) {
    // Карточка Material Design
    Card(
        modifier = Modifier
            .fillMaxWidth() // Занимает всю доступную ширину
            .clickable { onClick(place.id) }, // Обработка клика
        shape = MaterialTheme.shapes.medium // Скругленные углы
    ) {
        // Горизонтальное расположение элементов внутри карточки
        Row(
            modifier = Modifier.padding(16.dp), // Внутренние отступы
            verticalAlignment = Alignment.CenterVertically // Выравнивание по центру
        ) {
            // Асинхронная загрузка изображения
            Image(
                painter = painterResource(id = place.imageResId),
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp)) // Отступ между изображением и текстом
            // Название места
            Text(text = place.name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

/**
 * Composable-функция для экрана деталей места.
 * @param place Данные места для отображения
 * @param onBack Обработчик нажатия кнопки "Назад"
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailScreen(place: Place, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = place.name) }, // Заголовок с названием места
                navigationIcon = {
                    // Кнопка "Назад" с иконкой
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { padding ->
        // Вертикальное расположение элементов
        Column(
            modifier = Modifier
                .padding(padding) // Учитываем отступы от Scaffold
                .padding(16.dp) // Дополнительные отступы
        ) {
            // Большое изображение места
            Image(
                painter = painterResource(id = place.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp)) // Отступ между изображением и текстом
            // Описание места
            Text(text = place.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}