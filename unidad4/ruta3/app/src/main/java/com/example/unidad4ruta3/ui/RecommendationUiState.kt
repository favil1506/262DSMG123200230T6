package com.example.unidad4ruta3.ui

import com.example.unidad4ruta3.data.model.Recommendation

data class RecommendationUiState(
    val parques: List<Recommendation> = emptyList(),
    val restaurantes: List<Recommendation> = emptyList(),
    val museos: List<Recommendation> = emptyList(),
    val playas: List<Recommendation> = emptyList(),
    val centrosComerciales: List<Recommendation> = emptyList(),
    val recommendation: Recommendation? = null,
    val currentScreen: AppScreen = AppScreen.Parque,
    val loading: Boolean = false
)
