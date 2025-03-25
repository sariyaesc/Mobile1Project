package com.example.mobile1project.sum.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile1project.sum.viewModels.CalculatorViewModel

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Enter first number") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Enter second number") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val a = num1.toIntOrNull() ?: 0
            val b = num2.toIntOrNull() ?: 0
            viewModel.calculateSum(a, b)
        }) {
            Text("Calculate")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Result: ${viewModel.result.value}")
        }
    }
}
