package com.the.fitmate.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.room.util.TableInfo
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }


    val viewModel: AuthViewModel = hiltViewModel()
    val isSuccess by viewModel.registerSuccess.collectAsState()
    val registerError by viewModel.registerError.collectAsState()


    if (isSuccess) {
        LaunchedEffect(Unit) {
            navController.navigate("main") {
                popUpTo("register") { inclusive = true }
            }
        }
    }


    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        TextField(value = email, onValueChange = { email = it
            if (it.isNotBlank()) emailError = ""
        },
            label = {
                Text(
                    text = if (emailError.isNotEmpty()) emailError else "Email",
                    color = if (emailError.isNotEmpty()) Color.Red else LocalContentColor.current
                )
            },
            isError = emailError.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = password, onValueChange = { password = it
            if (it.isNotBlank()) passwordError = "" },
            label = {
                Text(
                    text = if (passwordError.isNotEmpty()) passwordError else "Password",
                    color = if (passwordError.isNotEmpty()) Color.Red else LocalContentColor.current
                )
            },
            isError = passwordError.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (registerError.isNotEmpty()) {

            Text(
                text = "registorError",
                color = Color.Red,
                modifier = Modifier.padding(10.dp)
            )
        }
        Button(onClick = {
            emailError = if (email.isBlank()) "Email is required" else ""
            passwordError = if (password.isBlank()) "Password is required" else ""

            if(emailError.isEmpty() && passwordError.isEmpty()){
                viewModel.register(email, password)
            }

        },modifier = Modifier.fillMaxWidth()) {
            Text("Sign Up")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Already have an account?",
            modifier = Modifier.clickable {
                navController.popBackStack()
            }
        )
    }

}