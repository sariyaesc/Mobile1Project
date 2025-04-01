package com.example.mobile1project

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile1project.tempconv.viewModels.tempconvViewModel
import com.example.mobile1project.tempconv.views.tempoconvView
import com.example.mobile1project.ui.theme.Mobile1ProjectTheme
import imc.views.IMCScreen
import imc.viewmodels.IMCViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mobile1ProjectTheme {
                val viewModel: tempconvViewModel = viewModel()
                tempoconvView(viewModel = viewModel)

            }
        }
    }
}
