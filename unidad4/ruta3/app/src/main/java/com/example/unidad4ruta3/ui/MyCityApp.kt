package com.example.unidad4ruta3.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.unidad4ruta3.data.model.Recommendation
import com.example.unidad4ruta3.ui.theme.Unidad4Ruta3Theme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyCityApp(
    modifier: Modifier = Modifier,
    viewModel: RecommendationViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    widthSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact
) {
    val navigationType: NavigationType
    val contentType: ContentType
    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            navigationType = NavigationType.BOTTOM
            contentType = ContentType.LIST
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = NavigationType.RAIL
            contentType = ContentType.LIST
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = NavigationType.DRAWER
            contentType = ContentType.LIST_DETAIL
        }
        else -> {
            navigationType = NavigationType.BOTTOM
            contentType = ContentType.LIST
        }
    }

    val defaultUi = navigationType == NavigationType.BOTTOM && contentType == ContentType.LIST

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.Parque.name
    )

    val uiState by viewModel.uiState.collectAsState()

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    LaunchedEffect(key1 = contentType.name) {
        if (contentType == ContentType.LIST && uiState.recommendation != null && viewModel.shouldNavigateToDetails) {
            if (uiState.currentScreen.name != AppScreen.Parque.name) {
                navController.navigate(uiState.currentScreen.name) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
            navController.navigate(AppScreen.Details.name)
        }
        if (contentType == ContentType.LIST_DETAIL) {
            viewModel.setupShouldNavigateToDetails()
            viewModel.setRecommendationInfoForCurrentScreen(uiState.currentScreen)
        }
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppBar(
                canNavigateBack = navController.previousBackStackEntry != null && contentType == ContentType.LIST,
                navigateUp = { navController.navigateUp() },
                title = if (contentType == ContentType.LIST) stringResource(id = currentScreen.title) else stringResource(
                    id = uiState.currentScreen.title
                ),
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            if (defaultUi) {
                AppNavigation(
                    navigationType = navigationType,
                    navItems = NavMenuItems.menuItems,
                    currentScreen = uiState.currentScreen,
                    currentDestination = backStackEntry?.destination,
                    navController = navController,
                    onMenuClicked = {
                        onNavMenuClicked(contentType, it, navController, viewModel)
                    },
                    onSelectedSaved = {
                        viewModel.setCurrentScreen(it)
                    }
                )
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (uiState.loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            Row {
                if (navigationType != NavigationType.BOTTOM) {
                    AppNavigation(
                        navigationType = navigationType,
                        navItems = NavMenuItems.menuItems,
                        currentScreen = uiState.currentScreen,
                        currentDestination = backStackEntry?.destination,
                        navController = navController,
                        onMenuClicked = {
                            onNavMenuClicked(contentType, it, navController, viewModel)
                        },
                        onSelectedSaved = {
                            viewModel.setCurrentScreen(it)
                        }
                    )
                }
                if (contentType == ContentType.LIST) {
                    NavHost(
                        navController = navController,
                        startDestination = AppScreen.Parque.name
                    ) {
                        composable(route = AppScreen.Parque.name) {
                            CategoryScreen(
                                recommendations = uiState.parques,
                                onItemClicked = {
                                    onRecommendationClicked(
                                        it,
                                        viewModel,
                                        navController,
                                        contentType
                                    )
                                }
                            )
                        }
                        composable(route = AppScreen.Restaurante.name) {
                            CategoryScreen(
                                recommendations = uiState.restaurantes,
                                onItemClicked = {
                                    onRecommendationClicked(
                                        it,
                                        viewModel,
                                        navController,
                                        contentType
                                    )
                                }
                            )
                        }
                        composable(route = AppScreen.Museo.name) {
                            CategoryScreen(
                                recommendations = uiState.museos,
                                onItemClicked = {
                                    onRecommendationClicked(
                                        it,
                                        viewModel,
                                        navController,
                                        contentType
                                    )
                                }
                            )
                        }
                        composable(route = AppScreen.Playa.name) {
                            CategoryScreen(
                                recommendations = uiState.playas,
                                onItemClicked = {
                                    onRecommendationClicked(
                                        it,
                                        viewModel,
                                        navController,
                                        contentType
                                    )
                                }
                            )
                        }
                        composable(route = AppScreen.CentroComercial.name) {
                            CategoryScreen(
                                recommendations = uiState.centrosComerciales,
                                onItemClicked = {
                                    onRecommendationClicked(
                                        it,
                                        viewModel,
                                        navController,
                                        contentType
                                    )
                                }
                            )
                        }
                        composable(route = AppScreen.Details.name) {
                            RecommendationInfoScreen(
                                contentType = contentType,
                                recommendation = uiState.recommendation,
                                onNavigateUp = {
                                    if (contentType == ContentType.LIST) {
                                        navController.navigateUp()
                                    }
                                }
                            )
                        }
                    }
                }

                if (contentType == ContentType.LIST_DETAIL) {
                    when (uiState.currentScreen) {
                        AppScreen.Parque -> {
                            CategoryScreen(
                                modifier = Modifier.weight(1f),
                                recommendations = uiState.parques,
                                onItemClicked = {
                                    onRecommendationClicked(
                                        it,
                                        viewModel,
                                        navController,
                                        contentType
                                    )
                                }
                            )
                        }
                        AppScreen.Restaurante -> {
                            CategoryScreen(
                                modifier = Modifier.weight(1f),
                                recommendations = uiState.restaurantes,
                                onItemClicked = {
                                    onRecommendationClicked(
                                        it,
                                        viewModel,
                                        navController,
                                        contentType
                                    )
                                }
                            )
                        }
                        AppScreen.Museo -> {
                            CategoryScreen(
                                modifier = Modifier.weight(1f),
                                recommendations = uiState.museos,
                                onItemClicked = {
                                    onRecommendationClicked(
                                        it,
                                        viewModel,
                                        navController,
                                        contentType
                                    )
                                }
                            )
                        }
                        AppScreen.Playa -> {
                            CategoryScreen(
                                modifier = Modifier.weight(1f),
                                recommendations = uiState.playas,
                                onItemClicked = {
                                    onRecommendationClicked(
                                        it,
                                        viewModel,
                                        navController,
                                        contentType
                                    )
                                }
                            )
                        }
                        AppScreen.CentroComercial -> {
                            CategoryScreen(
                                modifier = Modifier.weight(1f),
                                recommendations = uiState.centrosComerciales,
                                onItemClicked = {
                                    onRecommendationClicked(
                                        it,
                                        viewModel,
                                        navController,
                                        contentType
                                    )
                                }
                            )
                        }
                        else -> Unit
                    }
                    RecommendationInfoScreen(
                        modifier = Modifier.weight(1f),
                        contentType = contentType,
                        recommendation = uiState.recommendation
                    )
                }
            }
        }
    }
}

private fun onRecommendationClicked(
    recommendation: Recommendation,
    viewModel: RecommendationViewModel,
    navController: NavHostController,
    contentType: ContentType
) {
    viewModel.setRecommendationInfo(recommendation)
    if (contentType == ContentType.LIST) {
        navController.navigate(AppScreen.Details.name)
    }
}

private fun onNavMenuClicked(
    contentType: ContentType,
    menuItem: MenuItem,
    navController: NavHostController,
    viewModel: RecommendationViewModel
) {
    if (contentType == ContentType.LIST) {
        navController.navigate(menuItem.label) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    } else {
        viewModel.setCurrentScreen(menuItem.label, updateFirstRecommendation = true)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
) {
    LargeTopAppBar(
        modifier = modifier,
        title = { Text(text = title) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    navigationType: NavigationType,
    navItems: List<MenuItem>,
    currentScreen: AppScreen,
    currentDestination: androidx.navigation.NavDestination?,
    navController: NavHostController,
    onMenuClicked: (MenuItem) -> Unit,
    onSelectedSaved: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (navigationType) {
        NavigationType.BOTTOM -> {
            NavigationBar(modifier = modifier) {
                navItems.forEach { item ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == item.label } == true
                    if (isSelected) onSelectedSaved(item.label)
                    NavigationBarItem(
                        icon = {
                            Icon(
                                if (isSelected) item.iconSelected else item.icon,
                                contentDescription = null
                            )
                        },
                        label = { Text(item.label) },
                        selected = isSelected,
                        onClick = { onMenuClicked(item) }
                    )
                }
            }
        }
        NavigationType.RAIL -> {
            NavigationRail(modifier = modifier) {
                navItems.forEach { item ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == item.label } == true
                    if (isSelected) onSelectedSaved(item.label)
                    NavigationRailItem(
                        icon = {
                            Icon(
                                if (isSelected) item.iconSelected else item.icon,
                                contentDescription = null
                            )
                        },
                        label = { Text(item.label) },
                        selected = isSelected,
                        onClick = { onMenuClicked(item) }
                    )
                }
            }
        }
        NavigationType.DRAWER -> {
            PermanentDrawerSheet(
                modifier = Modifier.width(240.dp),
                drawerContainerColor = MaterialTheme.colorScheme.surface
            ) {
                Spacer(Modifier.height(12.dp))
                navItems.forEach { item ->
                    val isSelected = currentScreen.name == item.label
                    val defaultColor =
                        if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                if (isSelected) item.iconSelected else item.icon,
                                contentDescription = null,
                                tint = defaultColor
                            )
                        },
                        label = {
                            Text(
                                text = item.label,
                                color = defaultColor
                            )
                        },
                        selected = isSelected,
                        onClick = { onMenuClicked(item) },
                        modifier = Modifier.padding(horizontal = 12.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            unselectedContainerColor = MaterialTheme.colorScheme.surface
                        )
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 400)
@Preview(widthDp = 400, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MyCityCompactPreview() {
    Unidad4Ruta3Theme {
        MyCityApp(
            widthSizeClass = WindowWidthSizeClass.Compact
        )
    }
}

@Preview(widthDp = 700)
@Preview(widthDp = 700, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MyCityMediumPreview() {
    Unidad4Ruta3Theme {
        MyCityApp(
            widthSizeClass = WindowWidthSizeClass.Medium
        )
    }
}

@Preview(widthDp = 1000)
@Preview(widthDp = 1000, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MyCityExpandedPreview() {
    Unidad4Ruta3Theme {
        MyCityApp(
            widthSizeClass = WindowWidthSizeClass.Expanded
        )
    }
}
