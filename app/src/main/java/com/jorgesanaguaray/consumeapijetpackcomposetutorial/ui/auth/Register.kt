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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.main.Screen


@Composable
fun RegisterScreen(navController: NavController){
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    var text by remember { mutableStateOf("Hello") }
    Column (

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Box(
            modifier = Modifier
                .height(40.dp)
        ) {
            Text("Đăng ký", fontSize = 25.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Tên tài khoản") },
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Mật khẩu") },
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(26.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Nhập lại mật khẩu") },
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(26.dp))

        Row(

        ){

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue) ,
                modifier = Modifier
                    .width(screenWidthDp.dp / 2.4f)
            ){
                Text("Đăng ký",color = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    //Navigation to LoginScreen
                          try {
                              navController.navigate(Screen.Companion.LoginScreen.route)
                          }catch (ex: Exception){}
                },
                modifier = Modifier
                    .width(screenWidthDp.dp / 2.4f)
            ){
                Text("Đăng nhập")
            }

        }
    }
}