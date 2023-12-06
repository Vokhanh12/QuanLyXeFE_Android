package com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.manager

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.util.Size
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.domain.item.VehicleItem
import com.jorgesanaguaray.consumeapijetpackcomposetutorial.ui.home.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MVehiclesScreen(){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    // Screen content
    var isShowNotifty by remember { mutableStateOf(false) }

    val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val vehicles by homeViewModel.vehicles.collectAsState()

    val mVehicleViewModel: MVehicleViewModel= hiltViewModel()

    Box(
            contentAlignment = Alignment.Center
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
            onClick = { isShowNotifty = true}
        ) {
            Icon(Icons.Filled.Add, "Add")
        }

        //  nếu isShowNotify true thì hiển thị, false thì không hiển thị
        if(isShowNotifty){
            CreateVehicleNotify{
                    isShowNotifty = false
            }
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

@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateVehicleNotify(onCloseClicked: () -> Unit){

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    var tfId by remember { mutableStateOf("") }
    var tfName by remember { mutableStateOf("") }
    var tfUrlImage by remember { mutableStateOf("") }

    val context = LocalContext.current
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    var description by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    var bitmap: Bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);


    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            val fileName = getFileNameFromUri(context.contentResolver, uri)
            // Xử lý tên tệp tin ở đây (ví dụ: hiển thị tên tệp tin)

            // Đưa file name vào textField Hình ảnh
            tfUrlImage = fileName
        }
    }







    androidx.compose.material3.Card(
        modifier = Modifier
            .size(width = screenWidth.dp - 13.dp, height = (screenHeight / 1.8).dp),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),

            ) {
                Text(
                    text = "Thêm phương tiện",
                    fontSize = 21.sp,
                    modifier = Modifier
                        .padding(8.dp,0.dp,0.dp,0.dp)
                        .padding(12.dp),

                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.Black, // Icon color
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {

                            // Xử  Đóng Create Notifier
                            onCloseClicked()

                        } // Make the icon clickable
                        .padding(8.dp)
                    // Optional padding
                )
            }

            TextField(
                value = tfId,
                onValueChange = { tfId = it },
                label = { Text("Mã xe") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .width(screenWidth.dp)
                    .height((screenHeight / 12).dp)
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            TextField(
                value = tfName,
                onValueChange = { tfName = it },
                label = { Text("Tên xe") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .width(screenWidth.dp)
                    .height((screenHeight / 12).dp)
                    .fillMaxWidth()
                    .padding(8.dp)
            )


            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){

                TextField(
                    value = tfUrlImage,
                    onValueChange = { tfId = it },
                    label = { Text("Hình ảnh") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    enabled = false,
                    modifier = Modifier
                        .width(screenWidth.dp)
                        .height((screenHeight / 12).dp)
                        .fillMaxWidth()
                        .padding(8.dp)

                )

                // Hiển thị ảnh đã chọn
                if (selectedImageUri != null) {
                    bitmap = context.contentResolver.loadThumbnail(selectedImageUri!!, Size(50, 50), null)
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(8.dp,15.dp,20.dp,8.dp)
                ){

                    Image(
                        painter = OverlayImagePainter(bitmap.asImageBitmap(),bitmap.asImageBitmap()),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .padding(0.dp,0.dp,0.dp,10.dp)
                            .clickable {
                                // Xử lý khi nhấn vào ảnh
                            }
                    )

                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = null,
                        tint = Color.Black, // Icon color
                        modifier = Modifier
                            .size(37.dp)
                            .clickable {
                                // Gọi getContent khi icon được nhấn
                                getContent.launch("image/*")

                            }
                            .padding(0.dp,5.dp,0.dp,0.dp)
                    )

                }




            }




            Row {
                TypesDropdownMenuBox()
                YearsDropdownMenuBox()
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(screenWidth.dp, (screenHeight / 14).dp)
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
            ) {
                Text("Thêm",color = Color.White)
            }




        }


    }


}



@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun TypesDropdownMenuBox() {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    val types = arrayOf("Xe ô tô", "Xe tải", "Xe giải trí", "Xe mô tô", "Xe cứu thương","Xe bus",
        "Thuyền","Xe cứu hỏa","Trực thăng","Xe Tank")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(types[0]) }

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

// DM load cái củ shit này lên 40 mb
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun YearsDropdownMenuBox(viewModel: YourViewModel = viewModel()) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    // Sử dụng viewModel để lấy dữ liệu từ YourViewModel
    val years by viewModel.years.collectAsState()

    var expanded by remember { mutableStateOf(false) }
    var selectedYear by remember { mutableStateOf(years.firstOrNull() ?: 2023) }

    LaunchedEffect(viewModel) {
        viewModel.viewModelScope.launch {
            viewModel.loadData()
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
                value = "$selectedYear",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                years.forEach { year ->
                    DropdownMenuItem(
                        text = { Text(text = "$year") },
                        onClick = {
                            selectedYear = year
                            expanded = false
                            Toast.makeText(context, "$year", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}
class YourViewModel : ViewModel() {
    var _years = MutableStateFlow<List<Int>>(emptyList())
    val years: StateFlow<List<Int>> = _years.asStateFlow()

    fun loadData() {
        if (_years.value.isEmpty()) {
            viewModelScope.launch {
                _years.value = (1900..2023).toList()
            }
        }
    }
}


class OverlayImagePainter constructor(
    private val image: ImageBitmap,
    private val imageOverlay: ImageBitmap,
    private val srcOffset: IntOffset = IntOffset.Zero,
    private val srcSize: IntSize = IntSize(image.width, image.height),
    private val overlaySize: IntSize = IntSize(imageOverlay.width, imageOverlay.height)
) : Painter() {

    private val size: IntSize = validateSize(srcOffset, srcSize)
    override fun DrawScope.onDraw() {
        // draw the first image without any blend mode
        drawImage(
            image,
            srcOffset,
            srcSize,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            )
        )
        // draw the second image with an Overlay blend mode to blend the two together
        drawImage(
            imageOverlay,
            srcOffset,
            overlaySize,
            dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            ),
            blendMode = BlendMode.Overlay
        )
    }

    /**
     * Return the dimension of the underlying [ImageBitmap] as it's intrinsic width and height
     */
    override val intrinsicSize: androidx.compose.ui.geometry.Size get() = size.toSize()

    private fun validateSize(srcOffset: IntOffset, srcSize: IntSize): IntSize {
        require(
            srcOffset.x >= 0 &&
                    srcOffset.y >= 0 &&
                    srcSize.width >= 0 &&
                    srcSize.height >= 0 &&
                    srcSize.width <= image.width &&
                    srcSize.height <= image.height
        )
        return srcSize
    }
}

// Hàm lấy tên tệp tin từ Uri
private fun getFileNameFromUri(contentResolver: ContentResolver, uri: Uri): String {
    var fileName = ""
    contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cursor.moveToFirst()
        fileName = cursor.getString(nameIndex)
    }
    return fileName
}

@Composable
fun ImagePickerIcon(context: Context, onImageSelected: (Uri, String) -> Unit) {
    val getContent = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            val fileName = getFileNameFromUri(context.contentResolver, uri)
            onImageSelected(uri, fileName)
            // Xử lý tên tệp tin ở đây (ví dụ: hiển thị tên tệp tin)
            println("Selected file name: $fileName")
        }
    }

    Icon(
        imageVector = Icons.Default.AddCircle,
        contentDescription = null,
        tint = Color.Black, // Icon color
        modifier = Modifier
            .size(45.dp)
            .clickable {
                // Gọi getContent khi icon được nhấn
                getContent.launch("image/*")
            }
            .padding(0.dp, 0.dp, 20.dp, 0.dp)
    )
}
