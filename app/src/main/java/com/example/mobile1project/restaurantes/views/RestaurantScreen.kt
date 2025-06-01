package com.example.mobile1project.restaurantes.views

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mobile1project.restaurantes.model.Restaurant
import com.example.mobile1project.restaurantes.viewmodel.RestaurantViewModel

@Composable
fun RestaurantScreen(viewModel: RestaurantViewModel,navController: NavHostController) {
    val restaurants by viewModel.restaurants.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadRestaurants()
    }

    if (error != null) {
        Text("Error: $error", color = MaterialTheme.colorScheme.error)
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(restaurants) { restaurant ->
            RestaurantItem(restaurant) {
                val encodedName = Uri.encode(restaurant.name)
                navController.navigate("RestaurantDetailScreen/${encodedName}")
            }
        }
    }
}

@Composable
fun RestaurantItem(restaurant: Restaurant, onClick: () -> Unit) {
    val context = LocalContext.current

    val imageName = restaurant.imgName.substringBeforeLast('.')
    val drawableId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            if (drawableId != 0) {
                AsyncImage(
                    model = drawableId,
                    contentDescription = "Imagen del restaurante",
                    modifier = Modifier
                        .size(80.dp)
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(restaurant.name, style = MaterialTheme.typography.titleMedium)
                Text("⭐ ${restaurant.rating}")
                Text("Costo de envío: ${restaurant.fee}")
                Text("Tiempo de entrega: ${restaurant.delivery}")
            }
        }
    }
}
