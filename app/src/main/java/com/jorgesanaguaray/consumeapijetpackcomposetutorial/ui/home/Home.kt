package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.GameItem
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VehicleItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Jorge Sanaguaray
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    /*

    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val games by homeViewModel.games.collectAsState()

    LazyColumn {

        items(games) { game: GameItem ->

            GameCard(game = game)

        }

    }

     */

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp
    
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Box(modifier = Modifier
                .fillMaxWidth()
            ){
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = Color.Black, // Icon color
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                            coroutineScope.launch {
                                drawerState.open()
                            }

                        } // Make the icon clickable
                        .padding(8.dp)
                    // Optional padding
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Trang chính"

                )
            }

        }
    
        Divider()
        ScreenForManager(drawerState,coroutineScope)
        
    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenForManager(drawerState: DrawerState,coroutineScope: CoroutineScope){


    val drawerItemList = prepareNavigationDrawerItems()
    val selectedItem = remember { mutableStateOf(drawerItemList[0]) }


    ModalNavigationDrawer(
        drawerState = drawerState,

        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Handle click action here
                            // For example, you can navigate to another screen or update some state
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Column (
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ){
                        Icon(
                            modifier = Modifier.size(size = 120.dp),
                            imageVector = Icons.Default.Person,
                            contentDescription = "Person Icon",
                            )

                        Text(text = "Nhân viên")
                    }

                }
                Divider()

                drawerItemList.forEach { item ->
                    NavigationDrawerItem(

                        icon = { Icon(imageVector = item.icon, contentDescription = null) },
                        label = { Text(text = "${item.label}") },
                        selected = false,
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedIconColor = Color.Red,
                            unselectedTextColor = Color.Red

                        ),


                    )
                }



                // ...other drawer items
            }
        }
    ) {
        // Screen content
        val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
        val vehicles by homeViewModel.vehicles.collectAsState()

        LazyColumn {

            items(vehicles) { vehicle: VehicleItem ->

                VehicleCard(vehicle = vehicle)

            }

        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenForEmployee(){

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Handle click action here
                            // For example, you can navigate to another screen or update some state
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Column (
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ){
                        Icon(
                            modifier = Modifier.size(size = 120.dp),
                            imageVector = Icons.Default.Person,
                            contentDescription = "Person Icon",
                        )

                        Text(text = "Quản lý", fontWeight = FontWeight.Bold)
                    }
                }
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Quản lý xe") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Quản lý tuyến") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Quản lý địa điểm") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Quản lý địa chi tiết tuyến ") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                // ...other drawer items
            }
        }
    ) {
        // Screen content
        val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
        val vehicles by homeViewModel.vehicles.collectAsState()

        LazyColumn {

            items(vehicles) { vehicle: VehicleItem ->

                VehicleCard(vehicle = vehicle)

            }

        }
    }


}

@Composable
fun GameCard(game: GameItem) {

    val image = rememberImagePainter(data = game.thumbnail)

    Card(

        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
            .fillMaxSize()

    ) {

        Column {

            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Column(modifier = Modifier.padding(10.dp)) {

                Text(text = game.title, fontWeight = FontWeight.Bold)
                Text(text = game.short_description, maxLines = 2, overflow = TextOverflow.Ellipsis)

            }

        }

    }

}


@Composable
fun VehicleCard(vehicle: VehicleItem) {

    val image = rememberImagePainter(data = vehicle.urlImage)

    Card(

        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
            .fillMaxSize()

    ) {

        Column {

            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Column(modifier = Modifier.padding(10.dp)) {

                Row(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = vehicle.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)

                    Text(text = vehicle.code, overflow = TextOverflow.Ellipsis)



                }

                Row(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = "Loại: " + vehicle.type, maxLines = 2, overflow = TextOverflow.Ellipsis)

                    Text(text = "Năm: "+vehicle.startYearOfUse, overflow = TextOverflow.Ellipsis)
                    
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier

                        ) {

                        Text(text = "Cho thuê")
                    }

                }


            }

        }

    }

}

private fun prepareNavigationDrawerItems(): List<NavigationDrawerData> {
    val drawerItemsList = arrayListOf<NavigationDrawerData>()

    // add items
    drawerItemsList.add(NavigationDrawerData(label = "Cho thuê", icon = Icons.Filled.ShoppingCart))
    drawerItemsList.add(NavigationDrawerData(label = "Lịch sử cho thuê", icon = Icons.Filled.Info))

    return drawerItemsList
}

data class NavigationDrawerData(val label: String, val icon: ImageVector)