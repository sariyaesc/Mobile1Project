package com.example.mobile1project.tempconv.views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.mobile1project.R
import com.example.mobile1project.tempconv.viewModels.tempconvViewModel
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource


@Composable
fun tempoconvView(viewModel: tempconvViewModel) {
    val context = LocalContext.current
    val temp by viewModel.temp.collectAsState()
    val tempResult by viewModel.tempResult.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(stringResource(R.string.temp_app_name))

        TextField(
            value = temp,
            onValueChange = { viewModel.temp.value = it },
            label = { Text(stringResource(R.string.enter_temperature)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.convertTemp(context) }) {
            Text(stringResource(R.string.calculate))
        }

        Spacer(modifier = Modifier.height(16.dp))

        tempResult?.let {
            Text(text = "${stringResource(R.string.temp_result_text)} $it")
        } ?: Text(text = stringResource(R.string.invalid_input))

        Spacer(modifier = Modifier.height(32.dp))


        Image(
            painter = painterResource(id = R.drawable.termometro),
            contentDescription = stringResource(R.string.temp_image_desc),
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )
    }
}
