package com.example.unidad4ruta2.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.unidad4ruta2.model.MenuItem

@Composable
fun SidesScreen(
    options: List<MenuItem.SideDishItem>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (MenuItem) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseMenuScreen(
        options = options,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        onSelectionChanged = onSelectionChanged,
        modifier = modifier
    )
}