package com.example.unidad4ruta2.datasource

import com.example.unidad4ruta2.model.MenuItem

object DataSource {
    val entreeMenuItems = listOf(
        MenuItem.EntreeItem(
            name = "Sandwich de ternera",
            description = "Ternera de primera con pan artesanal",
            price = 7.99
        ),
        MenuItem.EntreeItem(
            name = "Pasta al pesto",
            description = "Pasta fresca con salsa pesto casera",
            price = 8.99
        ),
        MenuItem.EntreeItem(
            name = "Ensalada César",
            description = "Lechuga romana con aderezo César",
            price = 6.99
        ),
        MenuItem.EntreeItem(
            name = "Tacos de pollo",
            description = "Pollo a la plancha con guacamole",
            price = 9.49
        )
    )

    val sideDishMenuItems = listOf(
        MenuItem.SideDishItem(
            name = "Papas fritas",
            description = "Papas crujientes con sal marina",
            price = 2.99
        ),
        MenuItem.SideDishItem(
            name = "Arroz integral",
            description = "Arroz integral con hierbas",
            price = 2.49
        ),
        MenuItem.SideDishItem(
            name = "Ensalada verde",
            description = "Mix de lechugas frescas",
            price = 3.49
        ),
        MenuItem.SideDishItem(
            name = "Sopa del día",
            description = "Sopa caliente de temporada",
            price = 3.99
        )
    )

    val accompanimentMenuItems = listOf(
        MenuItem.AccompanimentItem(
            name = "Pan de centeno",
            description = "Rodajas de pan de centeno",
            price = 1.49
        ),
        MenuItem.AccompanimentItem(
            name = "Fruta fresca",
            description = "Trozos de fruta de temporada",
            price = 2.49
        ),
        MenuItem.AccompanimentItem(
            name = "Yogur natural",
            description = "Yogur griego con miel",
            price = 1.99
        ),
        MenuItem.AccompanimentItem(
            name = "Hummus con verduras",
            description = "Hummus casero con palitos de zanahoria",
            price = 2.99
        )
    )
}