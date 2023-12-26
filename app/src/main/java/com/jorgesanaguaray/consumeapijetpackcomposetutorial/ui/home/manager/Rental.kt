package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.manager

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.LocationModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.data.remote.model.VehicleModel
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VehicleItem
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun RentScreen(){

    var isRentalCardVisible by remember { mutableStateOf(false) }

    val homeViewModel = viewModel(modelClass = RentalViewModel::class.java)
    val vehicles by homeViewModel.vehicles.collectAsState()

    val rentalViewModel: RentalViewModel= hiltViewModel()


    var vehicleNameProvider by remember { mutableStateOf("") }
    var vehicleTypeProvider by remember { mutableStateOf("") }

    var nextId by remember { mutableStateOf<String?>(null) }
    var arrLocation by remember { mutableStateOf(emptyList<LocationModel>()) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        LazyColumn {

            items(vehicles) { vehicle: VehicleItem ->

                VehicleCard({
                    isRentalCardVisible = true
                },{getName ->
                    println("get:$getName")
                    vehicleNameProvider = getName
                },{getType ->
                    println("get:$getType")
                    vehicleTypeProvider = getType
                },vehicle = vehicle)


            }

        }

        if(isRentalCardVisible)
            if(vehicleNameProvider != "" && vehicleTypeProvider != ""){
                println("Provider:$vehicleNameProvider")
                println("Provider:$vehicleTypeProvider")

                if (isRentalCardVisible && vehicleNameProvider != "" && vehicleTypeProvider != "") {
                    println("Provider:$vehicleNameProvider")
                    println("Provider:$vehicleTypeProvider")

                    LaunchedEffect(rentalViewModel) {
                        nextId = rentalViewModel.getRouteNextId()
                        arrLocation = rentalViewModel.getLocations()
                    }
                }

                nextId?.let { id ->
                    RentalVehicleCardScreen({
                        isRentalCardVisible = false
                    }, vehicleNameProvider, vehicleTypeProvider, id,arrLocation)
                }


            }



    }


}

@Composable
fun VehicleCard(onCLickOpened:() -> Unit, getName:(String) -> Unit, getType: (String) -> Unit, vehicle: VehicleItem) {

    val image = rememberImagePainter(data = vehicle.urlImage)


    var nameState by remember { mutableStateOf("") }
    var typeState by remember { mutableStateOf("") }

    // Khi giá trị selectedText thay đổi, gọi lambda expression để thông báo giá trị mới
    LaunchedEffect(nameState, typeState) {
        getName(nameState)
        getType(typeState)
    }



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

                        Button(onClick = {

                            println("button:${vehicle.name}")
                            println("button:${vehicle.type}")

                            nameState = vehicle.name
                            typeState = vehicle.type

                            println("state:${nameState}")
                            println("state:${typeState}")
                            // click change is Rental card false or true
                            onCLickOpened()

                        },
                            modifier = Modifier

                        ) {

                            Text(text = "Cho thuê", color = Color.White)
                        }

                    }


                }

            }

        }


}

@Composable
fun RentalVehicleCardScreen(onCLickClosed: () -> Unit,vehicleName: String,
                            vehicleType: String, nextRouteId: String,
                            arrLocation: List<LocationModel>){

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    var isDateTimePickerVisible by remember {mutableStateOf(false)}


    var tfVehicleName by remember{ mutableStateOf(vehicleName) }
    var tfVehicleType by remember{ mutableStateOf(vehicleType) }

    var tfRouteId by remember{ mutableStateOf(nextRouteId) }
    var tfRouteName by remember{ mutableStateOf("") }

    var drmVehicleType by remember { mutableStateOf("") }
    var drmVehicleName by remember { mutableStateOf("") }
    var drmLocationName by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

            .background(Color(0xFF000000).copy(alpha = 0.5f)),

                contentAlignment = Alignment.Center



    ){

            Box{

                Card(
                    modifier = Modifier
                        .size(width = screenWidth.dp - 13.dp, height = (screenHeight / 1.6).dp)

                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(0.dp,5.dp,5.dp,0.dp),
                        Arrangement.SpaceAround
                    ) {

                        Column(
                            modifier = Modifier

                        ) {
                            Row{
                                TextField(
                                    value = tfVehicleName,
                                    onValueChange = { tfVehicleName = it },
                                    label = { Text("Tên xe") },
                                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                                    enabled = false,
                                    modifier = Modifier
                                        .width(screenWidth.dp/2)
                                        .height((screenHeight / 12).dp)
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                )
                                TextField(
                                    value = tfVehicleType,
                                    onValueChange = { tfVehicleType = it },
                                    label = { Text("Loại xe") },
                                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                                    enabled = false,
                                    modifier = Modifier
                                        .width(screenWidth.dp/2)
                                        .height((screenHeight / 12).dp)
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                )
                            }

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
                                enabled = true,
                                modifier = Modifier
                                    .width(screenWidth.dp)
                                    .height((screenHeight / 12).dp)
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )

                            LocationNameDropdownMenuBox({ itemName ->
                                drmLocationName = itemName
                            },arrLocation)

                            Row{
                                DateTimePicker("Thời gian bắt đầu")

                                DateTimePicker("Thời gian kết thúc")

                            }
                        }
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height((screenHeight / 13).dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(8.dp),

                            shape = RoundedCornerShape(0),
                            onClick = {
                                // TODO
                            }
                        ) {
                            Text(text = "Cho thuê")
                        }

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height((screenHeight / 13).dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(8.dp),

                            shape = RoundedCornerShape(0),
                            onClick = {
                                onCLickClosed()
                            }
                        ) {
                            Text(text = "Quay lại")
                        }


                    }


                }

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
fun LocationNameDropdownMenuBox(getName:(String) -> Unit, arrLocation: List<LocationModel>) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val rentalViewModel: RentalViewModel= hiltViewModel()

    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    val types = arrLocation.map { it.name }.toTypedArray()

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Địa điểm") }


    // Khi giá trị selectedText thay đổi, gọi lambda expression để thông báo giá trị mới
    DisposableEffect(selectedText) {
        onDispose {
            getName(selectedText)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePicker(text: String){

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    var currentText by remember{ mutableStateOf(text) }

    val calendar = Calendar.getInstance()
    calendar.set(1990, 0, 22) // add year, month (Jan), date

    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)


    // set the initial date
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var selectedDate by remember {
        mutableLongStateOf(calendar.timeInMillis) // or use mutableStateOf(calendar.timeInMillis)
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    selectedDate = datePickerState.selectedDateMillis!!
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }

    Button(
        modifier = Modifier
            .width(screenWidth.dp / 2)
            .height((screenHeight / 13).dp)
            .padding(8.dp),

        shape = RoundedCornerShape(0),
        onClick = {
            showDatePicker = true
            currentText =  formatter.format(Date(selectedDate))

        }
    ) {

        Text(text = "$currentText")
    }

    /*


    Text(
        text = "Selected date: ${formatter.format(Date(selectedDate))}"
    )

     */

}


