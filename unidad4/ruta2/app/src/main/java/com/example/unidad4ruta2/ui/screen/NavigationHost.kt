package com.example.unidad4ruta2.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.unidad4ruta2.LunchTrayScreen
import com.example.unidad4ruta2.datasource.DataSource
import com.example.unidad4ruta2.model.MenuItem
import com.example.unidad4ruta2.model.OrderUiState

@Composable
fun NavigationHostScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var orderUiState by remember { mutableStateOf(OrderUiState()) }

    NavHost(
        navController = navController,
        startDestination = LunchTrayScreen.Start.name,
        modifier = modifier
    ) {
        composable(route = LunchTrayScreen.Start.name) {
            StartScreen(
                onStartOrderButtonClicked = {
                    navController.navigate(LunchTrayScreen.Entree.name)
                },
                modifier = Modifier
            )
        }

        composable(route = LunchTrayScreen.Entree.name) {
            EntreeMenuScreen(
                options = DataSource.entreeMenuItems,
                onCancelButtonClicked = {
                    orderUiState = OrderUiState()
                    navController.popBackStack(
                        LunchTrayScreen.Start.name, inclusive = false
                    )
                },
                onNextButtonClicked = {
                    navController.navigate(LunchTrayScreen.SideDish.name)
                },
                onSelectionChanged = { item ->
                    val entree = item as MenuItem.EntreeItem
                    orderUiState = orderUiState.copy(
                        entree = entree,
                        itemTotalPrice = entree.price +
                                (orderUiState.sideDish?.price ?: 0.0) +
                                (orderUiState.accompaniment?.price ?: 0.0)
                    ).let {
                        it.copy(
                            orderTax = it.itemTotalPrice * 0.08,
                            orderTotalPrice = it.itemTotalPrice + it.itemTotalPrice * 0.08
                        )
                    }
                },
                modifier = Modifier
            )
        }

        composable(route = LunchTrayScreen.SideDish.name) {
            SidesScreen(
                options = DataSource.sideDishMenuItems,
                onCancelButtonClicked = {
                    orderUiState = OrderUiState()
                    navController.popBackStack(
                        LunchTrayScreen.Start.name, inclusive = false
                    )
                },
                onNextButtonClicked = {
                    navController.navigate(LunchTrayScreen.Accompaniment.name)
                },
                onSelectionChanged = { item ->
                    val sideDish = item as MenuItem.SideDishItem
                    orderUiState = orderUiState.copy(
                        sideDish = sideDish,
                        itemTotalPrice = (orderUiState.entree?.price ?: 0.0) +
                                sideDish.price +
                                (orderUiState.accompaniment?.price ?: 0.0)
                    ).let {
                        it.copy(
                            orderTax = it.itemTotalPrice * 0.08,
                            orderTotalPrice = it.itemTotalPrice + it.itemTotalPrice * 0.08
                        )
                    }
                },
                modifier = Modifier
            )
        }

        composable(route = LunchTrayScreen.Accompaniment.name) {
            DessertScreen(
                options = DataSource.accompanimentMenuItems,
                onCancelButtonClicked = {
                    orderUiState = OrderUiState()
                    navController.popBackStack(
                        LunchTrayScreen.Start.name, inclusive = false
                    )
                },
                onNextButtonClicked = {
                    navController.navigate(LunchTrayScreen.Checkout.name)
                },
                onSelectionChanged = { item ->
                    val accompaniment = item as MenuItem.AccompanimentItem
                    orderUiState = orderUiState.copy(
                        accompaniment = accompaniment,
                        itemTotalPrice = (orderUiState.entree?.price ?: 0.0) +
                                (orderUiState.sideDish?.price ?: 0.0) +
                                accompaniment.price
                    ).let {
                        it.copy(
                            orderTax = it.itemTotalPrice * 0.08,
                            orderTotalPrice = it.itemTotalPrice + it.itemTotalPrice * 0.08
                        )
                    }
                },
                modifier = Modifier
            )
        }

        composable(route = LunchTrayScreen.Checkout.name) {
            ConfirmationScreen(
                orderUiState = orderUiState,
                onNextButtonClicked = {
                    orderUiState = OrderUiState()
                    navController.popBackStack(
                        LunchTrayScreen.Start.name, inclusive = false
                    )
                },
                onCancelButtonClicked = {
                    orderUiState = OrderUiState()
                    navController.popBackStack(
                        LunchTrayScreen.Start.name, inclusive = false
                    )
                },
                modifier = Modifier
            )
        }
    }
}