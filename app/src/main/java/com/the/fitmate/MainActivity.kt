package com.the.fitmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

import com.the.fitmate.ui.FitBodyApp
import com.the.fitmate.ui.LoginUser
import com.the.fitmate.ui.RegisterScreen
import com.the.fitmate.ui.ForgetPasswordScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val navController = rememberNavController()
            val user = FirebaseAuth.getInstance().currentUser
            val start = if (user == null) "main" else "main"

            MaterialTheme {

                NavHost(
                    navController = navController,
                    startDestination = start
                ) {

                    composable("login") {
                        LoginUser(
                            paddingValues = PaddingValues(),
                            navController = navController
                        )
                    }
                    composable("register") {
                        RegisterScreen(navController = navController)
                    }

                    composable("forget_password"){
                        ForgetPasswordScreen(navController = navController)
                    }

                    composable("main") {

                        FitBodyApp(navController)
                    }
                }
            }

        }
    }
}

