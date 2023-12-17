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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.TextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VehicleItem

@Composable
fun RentScreen(){
    val homeViewModel = viewModel(modelClass = RentalViewModel::class.java)
    val vehicles by homeViewModel.vehicles.collectAsState()

    LazyColumn {

        items(vehicles) { vehicle: VehicleItem ->

           VehicleCard(vehicle = vehicle)

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


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )

                var status: String = "Unknown"
                when(vehicle.status){
                    "OFF" -> status = "Trống"
                    "ON" -> status = "Đã thuê"
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(5.dp)
                        .size(63.dp, 43.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (vehicle.status == "OFF") Color.Red
                            else
                                if (vehicle.status == "ON") Color.Green
                                else Color.Yellow
                        )
                ){
                    Text(
                        text = status,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 16.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .align(Alignment.Center)

                    )
                }

            }

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
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = "Loại: " + vehicle.type, maxLines = 2, overflow = TextOverflow.Ellipsis)

                    Text(text = "Năm: "+vehicle.startYearOfUse, overflow = TextOverflow.Ellipsis)

                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier

                    ) {

                        Text(text = "Cho thuê", color = Color.White)
                    }

                }


            }

        }

    }

}


@Preview(showSystemUi = true)
@Composable
fun RentalVehicleCardScreen(){

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    var tfRouteId by remember{ mutableStateOf("") }
    var tfRouteName by remember{ mutableStateOf("") }

    var drmVehicleType by remember { mutableStateOf("") }
    var drmVehicleName by remember { mutableStateOf("") }
    var drmLocationName by remember { mutableStateOf("") }


    Card(
        modifier = Modifier
            .size(width = screenWidth.dp - 13.dp, height = (screenHeight / 1.8).dp),
    ) {

        Column {

            TextField(
                value = tfRouteId,
                onValueChange = { tfRouteId = it },
                label = { Text("Mã tuyến") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                enabled = false,
                modifier = Modifier
                    .width(screenWidth.dp)
                    .height((screenHeight / 12).dp)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            TextField(
                value = tfRouteName,
                onValueChange = { tfRouteName = it },
                label = { Text("Tên tuyến") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                enabled = false,
                modifier = Modifier
                    .width(screenWidth.dp)
                    .height((screenHeight / 12).dp)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Row{
                VehicleTypesDropdownMenuBox({ itemType ->
                    drmVehicleType = itemType
                })

                VehicleNameDropdownMenuBox({ itemName ->
                    drmVehicleName = itemName
                })
            }

            LocationNameDropdownMenuBox({ itemName ->
                    drmLocationName = itemName
            })
            



        }


    }

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun VehicleTypesDropdownMenuBox(getType:(String) -> Unit) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    val types = arrayOf("Xe ô tô", "Xe tải", "Xe giải trí", "Xe mô tô", "Xe cứu thương","Xe bus",
        "Thuyền","Xe cứu hỏa","Trực thăng","Xe Tank")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(types.first()) }


    // Khi giá trị selectedText thay đổi, gọi lambda expression để thông báo giá trị mới
    DisposableEffect(selectedText) {
        onDispose {
            getType(selectedText)
        }
    }

    Box(
        modifier = Modifier
            .size((screenWidth / 1.8).dp, (screenHeight / 12).dp)
            .padding(8.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                types.forEach { item ->
                    DropdownMenuItem(
                        text = { androidx.compose.material.Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun VehicleNameDropdownMenuBox(getType:(String) -> Unit) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    val types = arrayOf("Xe ô tô", "Xe tải", "Xe giải trí", "Xe mô tô", "Xe cứu thương","Xe bus",
        "Thuyền","Xe cứu hỏa","Trực thăng","Xe Tank")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(types.first()) }


    // Khi giá trị selectedText thay đổi, gọi lambda expression để thông báo giá trị mới
    DisposableEffect(selectedText) {
        onDispose {
            getType(selectedText)
        }
    }

    Box(
        modifier = Modifier
            .size((screenWidth / 1.8).dp, (screenHeight / 12).dp)
            .padding(8.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                types.forEach { item ->
                    DropdownMenuItem(
                        text = { androidx.compose.material.Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun LocationNameDropdownMenuBox(getName:(String) -> Unit) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    val types = arrayOf("Xe ô tô", "Xe tải", "Xe giải trí", "Xe mô tô", "Xe cứu thương","Xe bus",
        "Thuyền","Xe cứu hỏa","Trực thăng","Xe Tank")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(types.first()) }


    // Khi giá trị selectedText thay đổi, gọi lambda expression để thông báo giá trị mới
    DisposableEffect(selectedText) {
        onDispose {
            getName(selectedText)
        }
    }

    Box(
        modifier = Modifier
            .size((screenWidth / 1.8).dp, (screenHeight / 12).dp)
            .padding(8.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                types.forEach { item ->
                    DropdownMenuItem(
                        text = { androidx.compose.material.Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}






