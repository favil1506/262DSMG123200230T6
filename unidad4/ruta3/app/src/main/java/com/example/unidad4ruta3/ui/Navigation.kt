package com.example.unidad4ruta3.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.LocalMall
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.outlined.Flight
import androidx.compose.material.icons.outlined.LocalMall
import androidx.compose.material.icons.outlined.Museum
import androidx.compose.material.icons.outlined.Park
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.unidad4ruta3.R

enum class AppScreen(@StringRes val title: Int) {
    Parque(R.string.category_parque),
    Restaurante(R.string.category_restaurante),
    Museo(R.string.category_museo),
    Playa(R.string.category_playa),
    CentroComercial(R.string.category_centro_comercial),
    Details(R.string.recommendation_details)
}

object NavMenuItems {

    val menuItems = listOf(
        MenuItem(
            AppScreen.Parque.name,
            Icons.Outlined.Park,
            Icons.Filled.Park
        ),
        MenuItem(
            AppScreen.Restaurante.name,
            Icons.Outlined.Restaurant,
            Icons.Filled.Restaurant
        ),
        MenuItem(
            AppScreen.Museo.name,
            Icons.Outlined.Museum,
            Icons.Filled.Museum
        ),
        MenuItem(
            AppScreen.Playa.name,
            Icons.Outlined.Flight,
            Icons.Filled.Flight
        ),
        MenuItem(
            AppScreen.CentroComercial.name,
            Icons.Outlined.LocalMall,
            Icons.Filled.LocalMall
        )
    )
}

data class MenuItem(
    val label: String,
    val icon: ImageVector,
    val iconSelected: ImageVector
)

enum class NavigationType {
    BOTTOM, RAIL, DRAWER
}

enum class ContentType {
    LIST, LIST_DETAIL
}
