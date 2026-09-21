package com.example.unidad4ruta3.data.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class Recommendation(
    @StringRes val title: Int,
    @StringRes val description: Int,
    val image: ImageVector,
    val category: Category
)

enum class Category {
    PARQUE, RESTAURANTE, MUSEO, PLAYA, CENTRO_COMERCIAL
}
