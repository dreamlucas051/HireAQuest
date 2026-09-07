package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.QuestViewModel
import com.example.viewmodel.QuestViewModelFactory

class MainActivity : ComponentActivity() {
    
    private val viewModel: QuestViewModel by viewModels {
        QuestViewModelFactory((application as HireaquestApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HireaquestNavigation(viewModel = viewModel)
                }
            }
        }
    }
}



