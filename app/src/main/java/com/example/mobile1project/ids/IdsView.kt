package com.example.mobile1project.ids

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun IdsView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Button(
            onClick = { navController.navigate("CalculatorScreen") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Suma")
        }
        Button(
            onClick = { navController.navigate("IMCScreen") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a IMC")
        }
        Button(
            onClick = { navController.navigate("tempconvView") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Conversor de Temperatura")
        }
    }
}
