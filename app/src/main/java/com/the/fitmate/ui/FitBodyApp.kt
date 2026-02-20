package com.the.fitmate.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.the.fitmate.R
import com.the.fitmate.ui.article.ArticleDetailScreen
import com.the.fitmate.ui.article.ArticlesScreen
import com.the.fitmate.ui.exersize.ExercisesScreen
import com.the.fitmate.ui.exersize.ExersizeViewModel
import com.the.fitmate.ui.saved.SavedExercisesScreen
import com.the.fitmate.ui.workout.WorkOutsScreen
import com.the.fitmate.ui.workout.WorkoutDetailsScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitBodyApp(rootNavController: NavController) {

    val nav = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TrainUp") },
                actions = {

                    TextButton(
                            onClick = {
                                rootNavController.navigate("login"){
                                    popUpTo("main") { inclusive = true }
                                }
                            }
                        ) {
                            Text("Login")
                        }

                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        nav.navigate("home") {
                            popUpTo(nav.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true; restoreState = true
                        }
                    },

                    icon = { Icon(Icons.Outlined.Home, null) },
                    label = { Text("Exercises") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        nav.navigate("workout") {
                            popUpTo(nav.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true; restoreState = true
                        }
                    },

                    icon = { Image(
                        painter = painterResource(id = R.drawable.workout_icon),
                        contentDescription = "Workout",
                        modifier = Modifier.size(24.dp),
                        contentScale = ContentScale.Crop
                    )

                           },
                    label = { Text("workout") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        nav.navigate("saved") {
                            popUpTo(nav.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true; restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Outlined.Info, null) },
                    label = { Text("Saved") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        nav.navigate("articles") {
                            popUpTo(nav.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true; restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Outlined.DateRange, contentDescription = "Articles") },
                    label = { Text("Articles") }
                )
            }
        }
    ) {
        padding ->
        NavHost(
            navController = nav,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") {
                ExercisesScreen()

            }
            composable("workout") {

                WorkOutsScreen { week, workout ->
                    nav.navigate("workout/$week/$workout")
                }
            }

            composable("workout/{week}/{workout}") { backStackEntry ->
                val week = backStackEntry.arguments?.getString("week")?.toInt() ?: 1
                val workout = backStackEntry.arguments?.getString("workout")?.toInt() ?: 1

                val viewModel: ExersizeViewModel = hiltViewModel(backStackEntry)
                WorkoutDetailsScreen(viewModel = viewModel,week = week, workout =  workout)
            }

            composable("articles") {
                ArticlesScreen(navController = nav)
            }


            composable("article/{id}") { backStackEntry ->
                ArticleDetailScreen(backStackEntry = backStackEntry, onBack = {
                    nav.popBackStack()
                })
            }

            composable("saved") {
                SavedExercisesScreen()
            }


        }

    }

}