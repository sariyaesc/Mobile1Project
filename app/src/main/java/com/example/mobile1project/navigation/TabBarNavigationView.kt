package com.example.mobile1project.navigation

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.mobile1project.Student.views.StudentListScreen
import com.example.mobile1project.ids.IdsView
import com.example.mobile1project.firstpartial.FirstPartialView
import com.example.mobile1project.restaurantes.network.RestaurantApiService
import com.example.mobile1project.restaurantes.repository.RestaurantRepository
import com.example.mobile1project.restaurantes.viewmodel.RestaurantViewModel
import com.example.mobile1project.restaurantes.viewmodel.RestaurantViewModelFactory
import com.example.mobile1project.restaurantes.views.RestaurantDetailScreen
import com.example.mobile1project.restaurantes.views.RestaurantScreen
import com.example.mobile1project.secondpartial.SecondPartialView
import com.example.mobile1project.sum.views.CalculatorScreen
import com.example.mobile1project.tempconv.views.tempconvView
import com.example.mobile1project.thirdpartial.ThirdPartialView
import imc.views.IMCScreen

@Composable
fun TabBarNavigationView(navController: NavHostController = rememberNavController()) {
    val items = listOf(
        ScreenNavigation.Ids,
        ScreenNavigation.FirstPartial,
        ScreenNavigation.SecondPartial,
        ScreenNavigation.ThirdPartial
    )

    // Instancia del ViewModel una sola vez
    val repository = RestaurantRepository(RestaurantApiService.create())
    val restaurantViewModel: RestaurantViewModel = viewModel(factory = RestaurantViewModelFactory(repository))

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route)
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNavigation.Ids.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ScreenNavigation.Ids.route) { IdsView(navController) }
            composable(ScreenNavigation.FirstPartial.route) { FirstPartialView() }
            composable(ScreenNavigation.SecondPartial.route) { SecondPartialView() }
            composable(ScreenNavigation.ThirdPartial.route) { ThirdPartialView(navController) }
            composable("CalculatorScreen") { CalculatorScreen() }
            composable("IMCScreen") { IMCScreen() }
            composable("tempconvView") { tempconvView() }
            composable("StudentListScreen") { StudentListScreen() }

            composable("RestaurantScreen") {
                RestaurantScreen(viewModel = restaurantViewModel, navController = navController)
            }

            composable(
                route = "RestaurantDetailScreen/{restaurantName}",
                arguments = listOf(navArgument("restaurantName") { type = NavType.StringType })
            ) { backStackEntry ->
                val encodedName = backStackEntry.arguments?.getString("restaurantName") ?: ""
                val restaurantName = Uri.decode(encodedName)

                val restaurant = restaurantViewModel.getRestaurantByName(restaurantName)
                if (restaurant != null) {
                    RestaurantDetailScreen(restaurant)
                } else {
                    Text("Restaurante no encontrado.")
                }
            }
        }
    }
}
