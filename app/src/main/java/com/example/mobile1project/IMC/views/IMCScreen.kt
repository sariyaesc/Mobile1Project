package imc.views

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile1project.R
import imc.viewmodels.IMCViewModel

@Composable
fun IMCScreen(viewModel: IMCViewModel = viewModel()) {
    val context = LocalContext.current
    val weight by viewModel.weight.collectAsState()
    val height by viewModel.height.collectAsState()
    val imcResult by viewModel.imcResult.collectAsState()
    val imcVal by viewModel.imcVal.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {



        OutlinedTextField(
            value = weight,
            onValueChange = { viewModel.weight.value = it },
            label = { Text(stringResource(R.string.enter_weight)) }
        )
        OutlinedTextField(
            value = height,
            onValueChange = { viewModel.height.value = it },
            label = { Text(stringResource(R.string.enter_height)) }
        )
        Button(onClick = { viewModel.calculateIMC(context) }) {
            Text(stringResource(R.string.calculate))
        }

        Text(text = imcResult)

        imcVal?.let {
            Text(text = "BMI: %.2f".format(it)) // Display the BMI with two decimal places
        }

    }
}
