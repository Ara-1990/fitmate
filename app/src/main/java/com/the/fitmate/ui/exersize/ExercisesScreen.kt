package com.the.fitmate.ui.exersize

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ExercisesScreen(

    viewModel: ExersizeViewModel = hiltViewModel()) {


    val ui by viewModel.ui.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.refresh(week = 1, workout = 1)
    }


    Column (Modifier.fillMaxSize().padding(16.dp)){
         LazyColumn (verticalArrangement = Arrangement.spacedBy(12.dp)){
            items(ui.items){ex->
                ExersizeCart(
                    name = ex.name.orEmpty(),
                    muscleGroup = ex.muscleGroup.orEmpty(),
                    imagePath = ex.imagePath
                )
            }
         }
    }
}

@Composable
private fun ExersizeCart(
    name: String,
     muscleGroup: String,
    imagePath: String?
){

    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,

    ){
        Column(Modifier.padding(16.dp)) {


            imagePath?.let { path ->

                val resId = remember(imagePath) {
                    context.resources.getIdentifier(imagePath, "drawable", context.packageName)
                }

                if (resId != 0) {

                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),

                        contentScale = ContentScale.Fit
                    )
                }else{
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(Color.Gray),
                        contentAlignment = Alignment.Center
                    ){
                        Text("Image not found", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(muscleGroup, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}
