package com.example.android_lab4_city

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_lab4_city.domain.CityViewModel
import com.example.android_lab4_city.navigation.CityNavGraph
import com.example.android_lab4_city.ui.theme.AndroidLab4CityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidLab4CityTheme {
                Surface {
                    val viewModel: CityViewModel = viewModel()
                    CityNavGraph(viewModel = viewModel)
                }
            }
        }
    }
}
