package com.example.assignment2csci489

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment2csci489.ui.theme.Assignment2CSCI489Theme

class SecondAct : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment2CSCI489Theme {
                ScaffoldSecondAct(onBackToMainClick = {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                })
            }
        }
    }
}

@Composable
fun ScaffoldSecondAct(onBackToMainClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Mobile Software Engineering Challenges:")

        val challenges = listOf(
            "1. Battery Efficiency Optimization",
            "2. Security and Privacy Concerns",
            "3. Fragmentation of Mobile Platforms",
            "4. Handling Network Connectivity Issues",
            "5. Limited Computational Resources"
        )


        challenges.forEach { challenge ->
            Text(text = challenge)
        }

        Spacer(modifier = Modifier.height(32.dp))


        Button(onClick = onBackToMainClick) {
            Text(text = "Main Activity")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondActPreview() {
    Assignment2CSCI489Theme {
        ScaffoldSecondAct(onBackToMainClick = {})
    }
}
