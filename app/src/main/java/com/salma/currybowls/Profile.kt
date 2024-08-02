
package com.salma.currybowls

import android.Manifest
import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.salma.currybowls.components.HeadingTextComponent
import com.salma.currybowls.ui.theme.CurryBowlsTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.min

class UserProfileActivity : ComponentActivity() {

    enum class CameraPermissionStatus { NoPermission, PermissionGranted, PermissionDenied }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cameraPermissionStatusState = mutableStateOf(CameraPermissionStatus.NoPermission)
        val photoUriState: MutableState<Uri?> = mutableStateOf(null)
        val hasPhotoState = mutableStateOf(value = false)
        val resolver = applicationContext.contentResolver

        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                cameraPermissionStatusState.value = if (isGranted) {
                    CameraPermissionStatus.PermissionGranted
                } else {
                    CameraPermissionStatus.PermissionDenied
                }
            }

        val takePhotoLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { isSaved ->
                hasPhotoState.value = isSaved
            }

        val takePhoto: () -> Unit = {
            hasPhotoState.value = false

            val values = ContentValues().apply {
                val title = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
                put(MediaStore.Images.Media.TITLE, "Compose Camera Example Image - $title")
                put(MediaStore.Images.Media.DISPLAY_NAME, title)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
            }

            val uri = resolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
            )
            takePhotoLauncher.launch(uri)
            photoUriState.value = uri
        }

        val getThumbnail: (Uri?) -> ImageBitmap? = { uri ->
            val targetSize = 256f
            uri?.let {
                resolver.openInputStream(it)
            }?.let {
                BitmapFactory.decodeStream(it)
            }?.let {
                val height = it.height.toFloat()
                val width = it.width.toFloat()
                val scaleFactor = min(targetSize / height, targetSize / width)
                Bitmap.createScaledBitmap(it, (scaleFactor * width).toInt(), (scaleFactor * height).toInt(), true)
            }?.let {
                val rotation = getImageRotation(resolver, uri)
                Bitmap.createBitmap(it, 0, 0, it.width, it.height, Matrix().apply { postRotate(rotation.toFloat()) }, true)
            }?.asImageBitmap()
        }

        val getFullImage: (Uri?) -> ImageBitmap? = { uri ->
            uri?.let {
                resolver.openInputStream(it)
            }?.let {
                BitmapFactory.decodeStream(it)
            }?.let {
                val rotation = getImageRotation(resolver, uri)
                Bitmap.createBitmap(it, 0, 0, it.width, it.height, Matrix().apply { postRotate(rotation.toFloat()) }, true)
            }?.asImageBitmap()
        }

        setContent {
            val cameraPermissionStatus by remember { cameraPermissionStatusState }
            val hasPhoto by remember { hasPhotoState }
            var shouldShowFullImage by remember { mutableStateOf(false) }
            var isEditing by remember { mutableStateOf(false) }
            var name by remember { mutableStateOf("Salma") }
            var email by remember { mutableStateOf("S@gmail.com") }
            var location by remember { mutableStateOf("Uk") }

            CurryBowlsTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TakePhotoButton(
                            cameraPermissionStatus = cameraPermissionStatus,
                            requestPermissionLauncher = requestPermissionLauncher,
                            takePhoto = takePhoto
                        )

                        if (hasPhoto) {
                            val bitmap = getThumbnail(photoUriState.value)
                            if (bitmap != null) {
                                Image(
                                    bitmap = bitmap,
                                    contentDescription = "Thumbnail of Save Photo",
                                    modifier = Modifier
                                        .clickable {
                                            shouldShowFullImage = true
                                        }
                                        .padding(16.dp)
                                )
                            }
                        }

                        UserProfileContent(
                            isEditing = isEditing,
                            name = name,
                            email = email,
                            location = location,
                            onNameChange = { name = it },
                            onEmailChange = { email = it },
                            onLocationChange = { location = it }
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(onClick = {
                                if (isEditing) {
                                    // Save the changes
                                    isEditing = false
                                } else {
                                    isEditing = true
                                }
                            }) {
                                Text(text = if (isEditing) "Save" else "Edit")
                            }
                            OutlinedButton(onClick = { finish() }) {
                                Text(text = "Back")
                            }
                        }
                    }

                    if (shouldShowFullImage && hasPhoto) {
                        val bitmap = getFullImage(photoUriState.value)
                        if (bitmap != null) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    shouldShowFullImage = false
                                }) {
                                Image(
                                    bitmap = bitmap,
                                    contentDescription = "Full image of Save Photo",
                                    modifier = Modifier.align(Alignment.Center)
                                )
                                Surface(
                                    modifier = Modifier
                                        .background(MaterialTheme.colors.background)
                                        .align(Alignment.Center)
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = "Click to Close",
                                        style = MaterialTheme.typography.h4.copy(
                                            fontWeight = FontWeight.ExtraBold
                                        )
                                    )
                                }
                            }
                        } else {
                            shouldShowFullImage = false
                        }
                    }
                }
            }
        }
    }

    private fun getImageRotation(resolver: ContentResolver, uri: Uri): Int {
        val cursor = resolver.query(uri, arrayOf(MediaStore.Images.Media.ORIENTATION), null,
            null, null)
        var result = 0

        cursor?.apply {
            moveToFirst()
            val index = getColumnIndex(MediaStore.Images.Media.ORIENTATION)
            result = getInt(index)
            close()
        }
        return result
    }
}

@Composable
fun TakePhotoButton(
    cameraPermissionStatus: UserProfileActivity.CameraPermissionStatus,
    requestPermissionLauncher: ActivityResultLauncher<String>,
    takePhoto: () -> Unit
) {
    OutlinedButton(
        onClick = {
            when (cameraPermissionStatus) {
                UserProfileActivity.CameraPermissionStatus.NoPermission ->
                    requestPermissionLauncher.launch(Manifest.permission.CAMERA)

                UserProfileActivity.CameraPermissionStatus.PermissionGranted ->
                    takePhoto()

                UserProfileActivity.CameraPermissionStatus.PermissionDenied -> {}

            }
        }
    ) {
        when (cameraPermissionStatus) {
            UserProfileActivity.CameraPermissionStatus.NoPermission ->
                Text(text = "Request Camera Permissions")

            UserProfileActivity.CameraPermissionStatus.PermissionDenied ->
                Text(text = "Camera Permissions Have Been Denied")

            UserProfileActivity.CameraPermissionStatus.PermissionGranted ->
                Text(text = "Take Photo")
        }
    }
}

@Composable
fun UserProfileContent(
    isEditing: Boolean,
    name: String,
    email: String,
    location: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onLocationChange: (String) -> Unit
) {
    Column {
        if (isEditing) {


            TextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )













            Spacer(modifier = Modifier.height(15.dp))
        } else {
            HeadingTextComponent(value = "Name: $name")
            Spacer(modifier = Modifier.height(15.dp))

            HeadingTextComponent(value = "Email Id: $email")
            Spacer(modifier = Modifier.height(15.dp))

            HeadingTextComponent(value = "Location: $location")
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}
