package com.example.unidad4ruta2

import androidx.annotation.StringRes

enum class LunchTrayScreen(@param:StringRes val title: Int) {
    Start(title = R.string.screen_start),
    Entree(title = R.string.screen_entree_menu),
    SideDish(title = R.string.screen_sides),
    Accompaniment(title = R.string.screen_dessert),
    Checkout(title = R.string.screen_confirmation)
}