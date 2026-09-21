package com.example.unidad4ruta2

import androidx.lifecycle.ViewModel
import com.example.unidad4ruta2.model.MenuItem
import com.example.unidad4ruta2.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun selectEntree(entree: MenuItem.EntreeItem) {
        _uiState.update {
            val currentEntree = it.entree
            val shouldSelect = currentEntree == null || currentEntree.name != entree.name
            val newEntree = if (shouldSelect) entree else null
            it.copy(
                entree = newEntree,
                itemTotalPrice = when {
                    newEntree != null -> entree.price
                    currentEntree != null -> it.itemTotalPrice - currentEntree.price
                    else -> it.itemTotalPrice
                }
            )
        }
    }

    fun selectSideDish(sideDish: MenuItem.SideDishItem) {
        _uiState.update {
            val currentSideDish = it.sideDish
            val shouldSelect = currentSideDish == null || currentSideDish.name != sideDish.name
            val newSideDish = if (shouldSelect) sideDish else null
            it.copy(
                sideDish = newSideDish,
                itemTotalPrice = when {
                    newSideDish != null -> it.itemTotalPrice + sideDish.price
                    currentSideDish != null -> it.itemTotalPrice - currentSideDish.price
                    else -> it.itemTotalPrice
                }
            )
        }
    }

    fun selectAccompaniment(accompaniment: MenuItem.AccompanimentItem) {
        _uiState.update {
            val currentAccompaniment = it.accompaniment
            val shouldSelect = currentAccompaniment == null || currentAccompaniment.name != accompaniment.name
            val newAccompaniment = if (shouldSelect) accompaniment else null
            it.copy(
                accompaniment = newAccompaniment,
                itemTotalPrice = when {
                    newAccompaniment != null -> it.itemTotalPrice + accompaniment.price
                    currentAccompaniment != null -> it.itemTotalPrice - currentAccompaniment.price
                    else -> it.itemTotalPrice
                }
            )
        }
    }
}