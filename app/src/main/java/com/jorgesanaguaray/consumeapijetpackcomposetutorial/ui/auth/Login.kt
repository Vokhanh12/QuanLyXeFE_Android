package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.auth

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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.main.Screen


@Composable
fun LoginScreen(navController: NavController){
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    var text by remember { mutableStateOf("Hello") }

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
            value = text,
            onValueChange = { text = it },
            label = { Text("Tên tài khoản") },
            modifier = Modifier
                .width(screenWidthDp.dp)
                .height(75.dp)
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Mật khẩu") },
            modifier = Modifier
                .width(screenWidthDp.dp)
                .height(75.dp)
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(26.dp))

        Row(

        ){
            Button(
                onClick = {

                    try {
                        navController.navigate(Screen.Companion.HomeScreen.route)
                    }catch (ex: Exception){}

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
