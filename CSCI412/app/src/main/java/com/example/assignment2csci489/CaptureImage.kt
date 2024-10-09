package com.example.assignment2csci489

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment2csci489.ui.theme.Assignment2CSCI489Theme

class CaptureImage : ComponentActivity() {
    private lateinit var imageResultLauncher: ActivityResultLauncher<Intent>
    private var capturedImage: Bitmap? by mutableStateOf(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        imageResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val bitmap = data?.extras?.get("data") as? Bitmap
                if (bitmap != null) {
                    capturedImage = bitmap
                }
            }
        }

        setContent {
            Assignment2CSCI489Theme {
                CaptureImageScreen(
                    imageBitmap = capturedImage,
                    onCaptureClick = {
                        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        imageResultLauncher.launch(cameraIntent) // Launch the camera intent
                    }
                )
            }
        }
    }
}

@Composable
fun CaptureImageScreen(imageBitmap: Bitmap?, onCaptureClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Capture Image", fontSize = 24.sp)

        Button(onClick = onCaptureClick) {
            Text(text = "Capture Image")
        }

        if (imageBitmap != null) {
            Image(bitmap = imageBitmap.asImageBitmap(), contentDescription = "Captured Image")
        } else {
            Text(text = "No image captured yet.", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CaptureImagePreview() {
    Assignment2CSCI489Theme {
        CaptureImageScreen(imageBitmap = null, onCaptureClick = {})
    }
}
