package com.example.mobile1project.Student.views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile1project.Student.viewmodel.StudentViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile1project.API.views.getDrawableId
import com.example.mobile1project.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(viewModel: StudentViewModel = viewModel()) {
    val students = viewModel.students.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()
    val context = LocalContext.current // Solo accedes al contexto una vez

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Student List") }
            )
        }
    ) { paddingValues ->
        if (errorMessage.value != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = errorMessage.value!!)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(students.value) { student ->
                    Card(
                        elevation = CardDefaults.cardElevation(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            val imageResId = remember(student.imageName) {
                                getDrawableId(context, student.imageName)
                            }

                            if (imageResId != 0) {
                                Image(
                                    painter = painterResource(id = imageResId),
                                    contentDescription = null,
                                    modifier = Modifier.size(64.dp),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_image_not_found),
                                        contentDescription = null,
                                        modifier = Modifier.size(64.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        text = "Imagen no encontrada",
                                        modifier = Modifier.padding(top = 4.dp),
                                        fontSize = 12.sp
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(text = "Name: ${student.name}")
                                Text(text = "Student Id: ${student.studentId}")
                                Text(text = student.quote)
                            }
                        }
                    }
                }
            }
        }
    }
}

// Función de limpieza del nombre de la imagen
fun cleanImageName(rawName: String): String {
    return rawName.substringBeforeLast(".").lowercase()
}

// Función normal (NO @Composable) para buscar el ID del recurso
fun getDrawableId(context: Context, imageName: String): Int {
    val cleanName = cleanImageName(imageName)
    val id = context.resources.getIdentifier(cleanName, "drawable", context.packageName)
    println("Buscando imagen '$cleanName' -> ID: $id")
    return id
}
