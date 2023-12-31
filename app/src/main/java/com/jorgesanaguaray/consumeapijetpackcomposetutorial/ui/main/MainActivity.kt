package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.R
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.auth.LoginScreen
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.auth.RegisterScreen
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.HomeScreen
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.HomeViewModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.HomeViewModelFactory
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.ManagerLocationsScreen
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.ManagerRoutesScreen
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.ManagerVehiclesScreen
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.manager.MVehiclesScreen
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.manager.MapBoxMap
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.manager.MapScreen
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.theme.ConsumeApiJetpackComposeTutorialTheme
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.plugin.Plugin
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {

            ConsumeApiJetpackComposeTutorialTheme {

                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {

                   //Navigation(homeViewModelFactory)
                   //HomeScreen(typeForScreen = "QL")
                   //mVehiclesScreen()
                    MapScreen()


                }

            }

        }

    }

}

@Composable
fun Navigation(homeViewModelFactory: HomeViewModelFactory){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Companion.LoginScreen.route){
        composable(Screen.Companion.LoginScreen.route) {
            LoginScreen(navController)
        }

        composable("${Screen.Companion.HomeScreen.route}/{type}"){ backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: ""
            val viewModel: HomeViewModel = viewModel(factory = homeViewModelFactory)
            HomeScreen(type)
        }



    }

}



sealed class Screen(val route: String) {
    companion object {
        object HomeScreen : Screen("home_screen") {

        }
        object LoginScreen : Screen("login_screen")
        object RegisterScreen : Screen("register_screen")
    }
}