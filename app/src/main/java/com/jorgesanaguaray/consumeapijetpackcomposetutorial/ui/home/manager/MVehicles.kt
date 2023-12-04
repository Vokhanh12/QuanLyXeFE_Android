package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.manager

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VehicleItem
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.auth.LoginViewModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.HomeViewModel
import kotlinx.coroutines.launch


@Composable
fun MVehiclesScreen(){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    // Screen content
    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val vehicles by homeViewModel.vehicles.collectAsState()

    val mVehicleViewModel: MVehicleViewModel= hiltViewModel()

    Box(

    ){
        LazyColumn {

            items(vehicles) { vehicle: VehicleItem ->

                VehicleCard(vehicle = vehicle, mVehicleViewModel = mVehicleViewModel){

                }

            }

        }
        androidx.compose.material.Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(screenWidth.dp, 50.dp)
                .background(Color(android.graphics.Color.parseColor("#eeeee4"))),
            onClick = { /*TODO*/ }
        ) {

        }
    }



}



@Composable
fun VehicleCard(vehicle: VehicleItem, mVehicleViewModel: MVehicleViewModel, onDelete: () -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    val image = rememberImagePainter(data = vehicle.urlImage)
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val context = LocalContext.current


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
                    Text(text = vehicle.id, overflow = TextOverflow.Ellipsis)

                }

                Row(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "Loại: " + vehicle.type, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    Text(text = "Năm: "+vehicle.startYearOfUse, overflow = TextOverflow.Ellipsis)

                    Button(
                        modifier = Modifier
                            .size(100.dp,40.dp),
                        onClick = {

                        coroutineScope.launch {
                            // delete vehicle by ID
                            mVehicleViewModel.deleteVehicleById(vehicle.id.toString())

                            Toast.makeText(context, "Xóa thành công ${vehicle.id} ${vehicle.id} ${vehicle.name}", Toast.LENGTH_LONG).show()
                            // Gọi onDelete callback để cập nhật danh sách
                            onDelete()
                        }

                    },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)

                    ){
                        Text(text = "Xóa")
                    }


                }


            }

        }

    }


}