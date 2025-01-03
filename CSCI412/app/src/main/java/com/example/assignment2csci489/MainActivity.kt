package com.example.assignment2csci489

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment2csci489.ui.theme.Assignment2CSCI489Theme

class MainActivity : ComponentActivity() {

    private val MSE412_PERMISSION = "com.example.assignment2csci489.MSE412"
    private val REQUEST_PERMISSION_CODE = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2CSCI489Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Steven Champagne",
                        modifier = Modifier.padding(innerPadding),
                        onExplicitClick = { startExplicitActivity() },
                        onImplicitClick = { startImplicitActivity() },
                        onImageClick = { startImageActivity() }
                    )
                }
            }
        }
    }


    private fun checkAndRequestPermission() {
        if (ContextCompat.checkSelfPermission(this, MSE412_PERMISSION) != PackageManager.PERMISSION_GRANTED) { ActivityCompat.requestPermissions(this, arrayOf(MSE412_PERMISSION), REQUEST_PERMISSION_CODE)
        } else {
            startExplicitActivity()
        }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == REQUEST_PERMISSION_CODE && grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                startExplicitActivity()
            } else {
                Toast.makeText(this, "Permission denied. Cannot open SecondAct.", Toast.LENGTH_SHORT).show()
            }
        }

    // Explicit Intent
    private fun startExplicitActivity() {
        val intent = Intent(this, SecondAct::class.java)
        startActivity(intent)
    }

    private fun startImageActivity()
    {
        val intent = Intent(this, CaptureImage::class.java)
        startActivity(intent)
    }

    // Implicit Intent
    private fun startImplicitActivity() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = android.net.Uri.parse("https://www.google.com")
        startActivity(intent)
    }
}

@Composable
fun Greeting(
    name: String, modifier: Modifier = Modifier, onExplicitClick: () -> Unit,
    onImplicitClick: () -> Unit, onImageClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "$name StudentID: 1309959")

        // exp button
        Button(onClick = onExplicitClick) {
            Text(text = "Start Activity Explicitly")
        }

        // imp button
        Button(onClick = onImplicitClick) {
            Text(text = "Start Activity Implicitly")
        }

        Button(onClick = onImageClick) {
            Text(text = "View Image Activity")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment2CSCI489Theme {
        Greeting(
            name = "Steven",
            onExplicitClick = {},
            onImplicitClick = {},
            onImageClick = {}
        )
    }
}
