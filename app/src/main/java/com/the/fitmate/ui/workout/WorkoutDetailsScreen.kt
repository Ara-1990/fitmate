package com.the.fitmate.ui.workout

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.the.fitmate.ui.exersize.ExerEvent
import com.the.fitmate.ui.exersize.ExersizeViewModel

@Composable
fun WorkoutDetailsScreen(
    viewModel: ExersizeViewModel = hiltViewModel(),
    week:Int, workout:Int) {

    val ui by viewModel.ui.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                ExerEvent.LoginRequired -> {
                    Toast.makeText(
                        context,
                        "Please login to save exercises",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                ExerEvent.Saved -> {
                    Toast.makeText(
                        context,
                        "Exercise saved ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    LaunchedEffect(week, workout) {
        viewModel.refresh(week, workout)
    }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {

        LazyColumn (verticalArrangement = Arrangement.spacedBy(12.dp)){
            items(ui.items){ex->
                com.the.fitmate.ui.workout.ExersizeCart(
                    name = ex.name.orEmpty(),
                    muscleGroup = ex.muscleGroup.orEmpty(),
                    imagePath = ex.imagePath,
                    description = ex.description.orEmpty(),
                    weight = ex.weight,
                    number = ex.numbers,
                    sets = ex.sets,
                    viewModel = viewModel
                )
            }
        }
    }


}

@Composable
private fun ExersizeCart(
    name: String,
    muscleGroup: String,
    imagePath: String?,
    description:String,
    weight: String?,
    number: String?,
    sets:String?,
    viewModel: ExersizeViewModel

){

    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }

    val done by viewModel.doneExercises.collectAsState()
    val isDone = done.contains(name)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
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

            Spacer(Modifier.height(8.dp))

            Text(name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(muscleGroup, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)


            Spacer(modifier = Modifier.height(8.dp))
            Text("Weight: ${weight.orEmpty()}", style = MaterialTheme.typography.bodyMedium)
            Text("Number: ${number.orEmpty()}", style = MaterialTheme.typography.bodyMedium)
            Text("Sets: ${sets.orEmpty()}", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(12.dp))
            LinearProgressIndicator(

                progress = if (isDone) 1f else 0f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(50)),
                color = Color(0xFF4CAF50),
                trackColor = Color.LightGray
            )

            Spacer(Modifier.height(12.dp))

            Spacer(modifier = Modifier.height(8.dp))


            if (!isDone) {
                Column {
                    Text("Weight: ${weight.orEmpty()}", style = MaterialTheme.typography.bodyMedium)
                    Text("Number: ${number.orEmpty()}", style = MaterialTheme.typography.bodyMedium)
                    Text("Sets: ${sets.orEmpty()}", style = MaterialTheme.typography.bodyMedium)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            Text(description, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold,
                maxLines = if(expanded) Int.MAX_VALUE else 2, overflow = TextOverflow.Ellipsis)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded  }
                    .padding(top = 4.dp),
                     horizontalArrangement = Arrangement.End

            ) {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Show less" else "Show more"
                )

                Spacer(Modifier.height(12.dp))

                Button(
                    onClick = { viewModel.markDone(name) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =  if (isDone) Color(0xFF4CAF50) else MaterialTheme.colorScheme.primary
                    )

                ) {
                    Text(
                        if (isDone) "Completed ✔" else "Mark as Done",
                        color = Color.White
                    )
                }
            }
        }
    }
}