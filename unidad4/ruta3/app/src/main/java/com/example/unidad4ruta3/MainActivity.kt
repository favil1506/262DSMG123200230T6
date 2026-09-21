package com.example.unidad4ruta3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.unidad4ruta3.ui.MyCityApp
import com.example.unidad4ruta3.ui.theme.Unidad4Ruta3Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Unidad4Ruta3Theme {
                val windowSize = calculateWindowSizeClass(activity = this)
                MyCityApp(widthSizeClass = windowSize.widthSizeClass)
            }
        }
    }
}
