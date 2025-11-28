package com.risjsnx.v4aadvanced.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.v4acontrol.V4AInterface

@Composable
fun V4AControlScreen() {
    var installed by remember { mutableStateOf(false) }
    var version by remember { mutableStateOf<String?>(null) }
    var statusText by remember { mutableStateOf("Loading...") }

    // Load data on first launch
    LaunchedEffect(Unit) {
        installed = V4AInterface.isInstalled()
        version = V4AInterface.getVersion()
        statusText = if (installed) "ViperFX Installed" else "Not Installed"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "V4A Controller",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(text = "Status: $statusText")

        version?.let {
            Text(text = "Version: $it")
        }

        Button(
            onClick = {
                val result = V4AInterface.openApp()
                statusText = if (result) "Opened App" else "Failed to Open"
            }
        ) {
            Text("Open ViperFX-RE App")
        }

        Button(
            onClick = {
                installed = V4AInterface.isInstalled()
                statusText = if (installed) "ViperFX Installed" else "Not Installed"
            }
        ) {
            Text("Refresh Status")
        }
    }
}
