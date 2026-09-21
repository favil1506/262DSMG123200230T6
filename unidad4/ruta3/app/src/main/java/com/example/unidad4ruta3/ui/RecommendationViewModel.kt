package com.example.unidad4ruta3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unidad4ruta3.data.local.Datasource
import com.example.unidad4ruta3.data.model.Category
import com.example.unidad4ruta3.data.model.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecommendationViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RecommendationUiState())
    val uiState: StateFlow<RecommendationUiState> = _uiState.asStateFlow()

    var shouldNavigateToDetails = false
        private set

    fun setupShouldNavigateToDetails() {
        shouldNavigateToDetails = true
    }

    init {
        getRecommendations()
    }

    private fun getRecommendations() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            _uiState.update { uiState ->
                val recommendations = Datasource.recommendations
                val parques = recommendations.filter { it.category == Category.PARQUE }
                val restaurantes = recommendations.filter { it.category == Category.RESTAURANTE }
                val museos = recommendations.filter { it.category == Category.MUSEO }
                val playas = recommendations.filter { it.category == Category.PLAYA }
                val centrosComerciales = recommendations.filter { it.category == Category.CENTRO_COMERCIAL }
                val firstRecommendation = when (uiState.currentScreen) {
                    AppScreen.Parque -> parques.firstOrNull()
                    AppScreen.Restaurante -> restaurantes.firstOrNull()
                    AppScreen.Museo -> museos.firstOrNull()
                    AppScreen.Playa -> playas.firstOrNull()
                    AppScreen.CentroComercial -> centrosComerciales.firstOrNull()
                    AppScreen.Details -> uiState.recommendation
                }
                uiState.copy(
                    parques = parques,
                    restaurantes = restaurantes,
                    museos = museos,
                    playas = playas,
                    centrosComerciales = centrosComerciales,
                    recommendation = firstRecommendation,
                    loading = false
                )
            }
        }
    }

    fun setRecommendationInfo(recommendation: Recommendation) {
        _uiState.update { it.copy(recommendation = recommendation) }
    }

    fun setRecommendationInfoForCurrentScreen(currentScreen: AppScreen) {
        _uiState.update {
            val name = currentScreen.name.lowercase()
            val found = it.recommendation?.category?.name?.lowercase() == name
            val firstRecommendation = when (currentScreen) {
                AppScreen.Parque -> if (found) it.recommendation else it.parques.firstOrNull()
                AppScreen.Restaurante -> if (found) it.recommendation else it.restaurantes.firstOrNull()
                AppScreen.Museo -> if (found) it.recommendation else it.museos.firstOrNull()
                AppScreen.Playa -> if (found) it.recommendation else it.playas.firstOrNull()
                AppScreen.CentroComercial -> if (found) it.recommendation else it.centrosComerciales.firstOrNull()
                AppScreen.Details -> it.recommendation
            }
            it.copy(recommendation = firstRecommendation)
        }
    }

    fun setCurrentScreen(title: String, updateFirstRecommendation: Boolean = false) {
        if (updateFirstRecommendation) {
            _uiState.update {
                val currentScreen = AppScreen.valueOf(title)
                val firstRecommendation = when (currentScreen) {
                    AppScreen.Parque -> it.parques.firstOrNull()
                    AppScreen.Restaurante -> it.restaurantes.firstOrNull()
                    AppScreen.Museo -> it.museos.firstOrNull()
                    AppScreen.Playa -> it.playas.firstOrNull()
                    AppScreen.CentroComercial -> it.centrosComerciales.firstOrNull()
                    AppScreen.Details -> it.recommendation
                }
                it.copy(
                    currentScreen = currentScreen,
                    recommendation = firstRecommendation
                )
            }
        } else {
            _uiState.update { it.copy(currentScreen = AppScreen.valueOf(title)) }
        }
    }
}
