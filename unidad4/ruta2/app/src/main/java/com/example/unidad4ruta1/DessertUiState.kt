package com.example.unidad4ruta1

import androidx.annotation.DrawableRes
import com.example.unidad4ruta1.data.Datasource

data class DessertUiState(
    val currentDessertIndex: Int = 0,
    val dessertsSold: Int = 0,
    val revenue: Int = 0,
    val currentDessertPrice: Int = Datasource.dessertList[currentDessertIndex].price,
    @DrawableRes val currentDessertImageId: Int = Datasource.dessertList[currentDessertIndex].imageId
)
