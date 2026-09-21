package com.example.unidad4ruta3.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AirlineSeatFlat
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.BeachAccess
import androidx.compose.material.icons.filled.BrunchDining
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.DirectionsBoat
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Pool
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Sailing
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Surfing
import androidx.compose.material.icons.filled.Water
import com.example.unidad4ruta3.R
import com.example.unidad4ruta3.data.model.Category
import com.example.unidad4ruta3.data.model.Recommendation

object Datasource {

    val recommendations = listOf(
        // Parques
        Recommendation(
            R.string.parque_olivar_title,
            R.string.parque_olivar_description,
            Icons.Default.Forest,
            Category.PARQUE
        ),
        Recommendation(
            R.string.parque_reserva_title,
            R.string.parque_reserva_description,
            Icons.Default.Park,
            Category.PARQUE
        ),
        Recommendation(
            R.string.campo_marte_title,
            R.string.campo_marte_description,
            Icons.Default.Pool,
            Category.PARQUE
        ),
        // Restaurantes
        Recommendation(
            R.string.restaurante_central_title,
            R.string.restaurante_central_description,
            Icons.Default.Restaurant,
            Category.RESTAURANTE
        ),
        Recommendation(
            R.string.restaurante_maido_title,
            R.string.restaurante_maido_description,
            Icons.Default.Coffee,
            Category.RESTAURANTE
        ),
        Recommendation(
            R.string.restaurante_la_mar_title,
            R.string.restaurante_la_mar_description,
            Icons.Default.LunchDining,
            Category.RESTAURANTE
        ),
        // Museos
        Recommendation(
            R.string.museo_mali_title,
            R.string.museo_mali_description,
            Icons.Default.Museum,
            Category.MUSEO
        ),
        Recommendation(
            R.string.museo_larco_title,
            R.string.museo_larco_description,
            Icons.Default.AccountBalance,
            Category.MUSEO
        ),
        Recommendation(
            R.string.museo_artesanias_title,
            R.string.museo_artesanias_description,
            Icons.Default.Cake,
            Category.MUSEO
        ),
        // Playas
        Recommendation(
            R.string.playa_miraflores_title,
            R.string.playa_miraflores_description,
            Icons.Default.Surfing,
            Category.PLAYA
        ),
        Recommendation(
            R.string.playa_barranco_title,
            R.string.playa_barranco_description,
            Icons.Default.BeachAccess,
            Category.PLAYA
        ),
        Recommendation(
            R.string.playa_costa_verde_title,
            R.string.playa_costa_verde_description,
            Icons.Default.Water,
            Category.PLAYA
        ),
        // Centros Comerciales
        Recommendation(
            R.string.centro_jockey_title,
            R.string.centro_jockey_description,
            Icons.Default.ShoppingBag,
            Category.CENTRO_COMERCIAL
        ),
        Recommendation(
            R.string.centro_megaplaza_title,
            R.string.centro_megaplaza_description,
            Icons.Default.LocalGroceryStore,
            Category.CENTRO_COMERCIAL
        ),
        Recommendation(
            R.string.centro_real_plaza_title,
            R.string.centro_real_plaza_description,
            Icons.Default.BarChart,
            Category.CENTRO_COMERCIAL
        )
    )
}
