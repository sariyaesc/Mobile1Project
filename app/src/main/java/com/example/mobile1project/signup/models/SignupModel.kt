package com.example.mobile1project.signup.models

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignupModel(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello Sara!",
        modifier = modifier
    )
}
