package com.example

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.Quest
import com.example.viewmodel.QuestViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(
    personName: String,
    tier: String,
    viewModel: QuestViewModel,
    onTaskPosted: () -> Unit,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var bountyStr by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task for $personName ($tier)", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "What do you need $personName to do?",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Task Title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Details") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            
            OutlinedTextField(
                value = bountyStr,
                onValueChange = { bountyStr = it },
                label = { Text("Bounty ($)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = {
                    val bounty = bountyStr.toDoubleOrNull() ?: 0.0
                    if (title.isNotBlank() && location.isNotBlank()) {
                        viewModel.addQuest(
                            Quest(
                                title = title,
                                description = description,
                                location = location,
                                bounty = bounty,
                                status = "ACCEPTED" // Instantly accepted since we picked someone
                            )
                        )
                        onTaskPosted()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Confirm & Hire $personName", fontWeight = FontWeight.Bold)
            }
        }
    }
}
