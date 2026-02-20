package com.the.fitmate.ui

import androidx.compose.runtime.Composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.the.fitmate.R

import com.airbnb.lottie.compose.*





@Composable
fun LoginUser(paddingValues: PaddingValues, navController: NavController){

    val viewModel: AuthViewModel = hiltViewModel()
    val loginSuccess by viewModel.loginSuccess.collectAsState()
    val loginError by viewModel.loginError.collectAsState()


    var email by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}
    var visiblePassword by remember { mutableStateOf(false)}
    var passwordError by remember { mutableStateOf("")}
    var emailError by remember { mutableStateOf("")}



    if (loginSuccess) {
        LaunchedEffect(Unit){
        navController.navigate("main") {
            popUpTo("login") { inclusive = true }

        }
        }
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation))

    val progres by animateLottieCompositionAsState(
        isPlaying = true,
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = 0.7f
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LottieAnimation(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally),
            composition = composition,
            progress = {progres}
        )

        Text(text = "Login", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)

        Spacer(modifier = Modifier.height(20.dp))

        TextField(value = email, onValueChange = {email = it},
            label = { Text(emailError.ifEmpty{"Email"}, color = if (emailError.isNotEmpty()) Red else Unspecified)},
            leadingIcon = {
                Icon(Icons.Rounded.AccountCircle,
                    contentDescription = "")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 25.dp),
            shape = RoundedCornerShape(8.dp),
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password, onValueChange = { password = it },
            label = {
                Text(passwordError.ifEmpty { "Password" },
                    color = if (passwordError.isNotEmpty()) Red else Unspecified)
            },
            leadingIcon = {
                Icon(Icons.Rounded.Lock,
                    contentDescription = "")
            },
            visualTransformation = if (visiblePassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val lable = if (visiblePassword) {
                    stringResource(id = R.string.show_password)
                }else stringResource(id = R.string.hide_password)


                Icon(imageVector = if (visiblePassword) {
                    Icons.Default.Visibility
                } else {
                    Icons.Default.VisibilityOff
                }, contentDescription = null,
                    modifier = Modifier.clickable(
                        onClickLabel = lable
                    ) { visiblePassword = !visiblePassword }
                )

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 25.dp),
            shape = RoundedCornerShape(8.dp),

            )

        if (loginError.isNotEmpty()) {

            Text(
                text = "loginError",
                color = Color.Red,
                modifier = Modifier.padding(10.dp)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(onClick = {
            emailError = if (email.isBlank()) "Email is required" else ""
            passwordError = if (password.isBlank()) "Password is required" else ""
            if (emailError.isEmpty() && passwordError.isEmpty()){
                viewModel.login(email, password)
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 90.dp)
        ) {
            Text(text = "Login")

        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Forget Password", modifier = Modifier.clickable{
            navController.navigate("forget_password")
        })

        Spacer(modifier = Modifier.height(40.dp))

        Row {
            Text(text = "Sign in now",
                modifier = Modifier.clickable{
                    navController.navigate("register")
                },
                color = MaterialTheme.colorScheme.primary
            )

        }

    }

}