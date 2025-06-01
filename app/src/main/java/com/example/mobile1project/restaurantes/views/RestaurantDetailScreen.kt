package com.example.mobile1project.restaurantes.views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mobile1project.restaurantes.model.Restaurant
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun RestaurantDetailScreen(restaurant: Restaurant) {
    val context = LocalContext.current

    val location = LatLng(restaurant.latitude.toDouble(), restaurant.longitude.toDouble())
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 15f)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        val imageName = restaurant.imgName.substringBeforeLast('.')
        val drawableId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "Imagen del restaurante",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mapa
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = location),
                title = restaurant.name
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${restaurant.phone}")
                context.startActivity(intent)
            }) {
                Text("Llamar")
            }

            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(restaurant.webSite))
                context.startActivity(intent)
            }) {
                Text("Sitio Web")
            }
        }
    }
}
