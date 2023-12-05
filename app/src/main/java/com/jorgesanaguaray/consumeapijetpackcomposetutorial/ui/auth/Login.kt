package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.main.Screen
import kotlinx.coroutines.launch
import androidx.hilt.navigation.compose.hiltViewModel



@Composable
fun LoginScreen(navController: NavController){
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .width(screenWidthDp.dp)
            .height(screenHeightDp.dp)
            .fillMaxSize()
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){

        Box(
            modifier = Modifier
                .height(40.dp)
        ) {
            Text("Đăng nhập", fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Tên tài khoản") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .width(screenWidthDp.dp)
                .height(75.dp)
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mật khẩu") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .width(screenWidthDp.dp)
                .height(75.dp)
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(26.dp))

        Row(

        ){

            val loginViewModel: LoginViewModel = hiltViewModel()

            Button(
                onClick = {
                    coroutineScope.launch {
                        val account = loginViewModel.getTypeByUsernameAndPassword(username = username, password = password)
                        navController.navigate(Screen.Companion.HomeScreen.route + "/${account?.type}")


                        /*

                        if(account?.type == "NV")
                            navController.navigate(Screen.Companion.HomeScreen.route)
                        else{
                            Log.d("Login","Login failed ${account?.type}. $username .$password")
                        }

                         */

                    }

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                modifier = Modifier
                    .width(screenWidthDp.dp / 2.4f)

            ){
                Text("Đăng nhập", color = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    try {
                        navController.navigate(Screen.Companion.RegisterScreen.route)
                    }catch (ex: Exception){}
                },
                modifier = Modifier
                    .width(screenWidthDp.dp / 2.4f)
            ){
                Text("Đăng ký")
            }

        }



    }



}

