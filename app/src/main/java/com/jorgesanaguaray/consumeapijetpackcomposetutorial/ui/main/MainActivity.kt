package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.HomeScreen
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.theme.ConsumeApiJetpackComposeTutorialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            ConsumeApiJetpackComposeTutorialTheme {

                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {

                    HomeScreen()

                }

            }

        }

    }

}